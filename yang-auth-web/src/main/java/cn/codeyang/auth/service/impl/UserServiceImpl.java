package cn.codeyang.auth.service.impl;

import cn.codeyang.auth.api.entity.Authority;
import cn.codeyang.auth.api.entity.Role;
import cn.codeyang.auth.api.entity.User;
import cn.codeyang.auth.api.entity.query.UserQuery;
import cn.codeyang.auth.api.service.UserService;
import cn.codeyang.auth.repositories.AuthorityRepository;
import cn.codeyang.auth.repositories.RoleRepository;
import cn.codeyang.auth.repositories.UserRepository;
import cn.codeyang.common.constant.AuthoritiesConstants;
import cn.codeyang.common.utils.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.internal.constraintvalidators.hv.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

/**
 * @author yangzhongyang
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AuthorityRepository authorityRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Cacheable(cacheNames = "userCache:username", key = "#username")
	@Override
	public User loadUserByUsername(String username) throws UsernameNotFoundException {
		if (log.isDebugEnabled()) {
			log.debug("Authenticating {}", username);
		}

		if (new EmailValidator().isValid(username, null)) {
			//如果用户名是邮箱格式
			return userRepository.findOneWithAUthoritiesAndRolesByEmail(username)
					.orElseThrow(() -> new UsernameNotFoundException("User with email " + username + " was not found in the database"));
		}


		String usernameLower = username.toLowerCase(Locale.ENGLISH);

		return userRepository.findOneWithAuthoritiesAndRolesByUsername(usernameLower)
				.orElseThrow(() -> new UsernameNotFoundException("User with username " + username + " was not found in the database"));
	}


	@Override
	public User registerUser(User user) throws Exception {
		userRepository.findOneByUsername(user.getUsername().toLowerCase()).ifPresent(existingUser -> {
			boolean removed = removeNonActivatedUser(existingUser);
			if (!removed) {
				log.error("用户已存在");
			}
		});


		userRepository.findOneByEmailIgnoreCase(user.getEmail()).ifPresent(existingUser -> {
			boolean removed = removeNonActivatedUser(existingUser);
			if (!removed) {
				log.error("邮箱已存在");
			}
		});

		User newUser = new User();
		String encryptedPassword = passwordEncoder.encode(user.getPassword());
		newUser.setUsername(user.getUsername().toLowerCase());
		newUser.setPassword(encryptedPassword);
		newUser.setEmail(user.getEmail().toLowerCase());
		newUser.setAvatar(user.getAvatar());
		if (StringUtils.isNotBlank(user.getLangKey())) {
			newUser.setLangKey(user.getLangKey());
		} else {
			newUser.setLangKey("zh_cn");
		}
		newUser.setActivated(false);
		newUser.setActivationKey(RandomUtil.generateActivationKey());

		Set<Authority> authorities = new HashSet<>();
		authorityRepository.findByAuthorityValue(AuthoritiesConstants.USER).ifPresent(authorities::add);
		newUser.setAuthorities(authorities);
		Set<Role> roles = new HashSet<>();
		roleRepository.findByName(AuthoritiesConstants.USER).ifPresent(roles::add);
		newUser.setRoles(roles);

		userRepository.save(newUser);
		return newUser;
	}

	@Override
	public Optional<User> activateRegistration(String key) {
		log.debug("开始激活用户 key: {}", key);

		return userRepository.findOneByActivationKey(key)
				.map(user -> {
					user.setActivated(true);
					user.setActivationKey(null);
					log.debug("激活成功");
					return user;
				});
	}

	@Override
	public Page<User> findByCondition(int page, int size, UserQuery userQuery) {
		Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "id");

		User user = new User();

		ExampleMatcher matcher = ExampleMatcher.matching().withIgnorePaths("password").withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
		if (StringUtils.isNotEmpty(userQuery.getUsername())) {
			user.setUsername(userQuery.getUsername());
			matcher.withMatcher("username", ExampleMatcher.GenericPropertyMatcher::startsWith);
		}
		if (StringUtils.isNotEmpty(userQuery.getNickname())) {
			user.setNickname(userQuery.getNickname());
			matcher.withMatcher("nickname", ExampleMatcher.GenericPropertyMatcher::startsWith);
		}

		Example<User> example = Example.of(user, matcher);
		return userRepository.findAll(example, pageable);
	}

	@Override
	public Page<User> findByCondition2(int page, int size, UserQuery userQuery) {
		Pageable pageable = PageRequest.of(page, size);
		return userRepository.findAll(new Specification<User>() {
			@Override
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
				List<Predicate> list = new ArrayList<Predicate>();

				if (StringUtils.isNotEmpty(userQuery.getUsername())) {
					list.add(criteriaBuilder.like(root.get("username"), "%" + userQuery.getUsername() + "%"));
				}
				if (StringUtils.isNotEmpty(userQuery.getNickname())) {
					list.add(criteriaBuilder.like(root.get("nickname"), "%" + userQuery.getNickname() + "%"));
				}

				Predicate[] p = new Predicate[list.size()];
				return criteriaBuilder.and(list.toArray(p));

			}
		}, pageable);
	}

	private boolean removeNonActivatedUser(User existingUser) {
		if (existingUser.getActivated()) {
			return false;
		}

		userRepository.delete(existingUser);
		userRepository.flush();
		return true;
	}

}
