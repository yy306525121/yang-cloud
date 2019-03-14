package cn.codeyang.auth.service.impl;

import cn.codeyang.auth.entity.Authority;
import cn.codeyang.auth.entity.Role;
import cn.codeyang.auth.entity.User;
import cn.codeyang.auth.entity.enums.ActivatedStatus;
import cn.codeyang.auth.mapper.AuthorityMapper;
import cn.codeyang.auth.mapper.RoleMapper;
import cn.codeyang.auth.mapper.UserMapper;
import cn.codeyang.auth.service.UserService;
import cn.codeyang.auth.service.dto.UserDTO;
import cn.codeyang.common.constant.AuthoritiesConstants;
import cn.codeyang.common.util.RandomUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.internal.constraintvalidators.hv.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

/**
 * @author yangzhongyang
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private AuthorityMapper authorityMapper;
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public User loadUserByUsername(String username) throws UsernameNotFoundException {
		if (log.isDebugEnabled()) {
			log.debug("Authenticating {}", username);
		}

		if (new EmailValidator().isValid(username, null)) {
			//如果用户名是邮箱格式
			User userByEmail = userMapper.findByEmail(username);
			if (userByEmail == null) {
				throw new UsernameNotFoundException("User with email " + username + " was not found in the database");
			}
			return fillUser(userByEmail);
		}


		String usernameLower = username.toLowerCase(Locale.ENGLISH);
		User userByUsername = userMapper.findByUsername(usernameLower);
		if (userByUsername == null) {
			throw new UsernameNotFoundException("User with username " + username + " was not found in the database");
		}
		return fillUser(userByUsername);
	}

	private User fillUser(User userByUsername) {
		List<Authority> authorities = authorityMapper.findAuthoritiesByUserId(userByUsername.getId());
		userByUsername.setAuthorities(new HashSet<>(authorities));
		List<Role> roles = roleMapper.findByUserId(userByUsername.getId());
		userByUsername.setRoles(new HashSet<>(roles));
		return userByUsername;
	}

	@Override
	public User registerUser(UserDTO userDTO, String password) throws Exception {
		User userDB = userMapper.findOneByUsername(userDTO.getUsername().toLowerCase());
		if (userDB != null) {
			boolean removed = removeNonActivatedUser(userDB);
			if (!removed) {
				throw new Exception("用户已存在");
			}
		}

		userDB = userMapper.findOneByEmailIgnoreCase(userDTO.getEmail());
		if (userDB != null) {
			boolean removed = removeNonActivatedUser(userDB);
			if (!removed) {
				throw new Exception("邮箱已存在");
			}
		}

		User newUser = new User();
		String encryptedPassword = passwordEncoder.encode(password);
		newUser.setUsername(userDTO.getUsername().toLowerCase());
		newUser.setPassword(encryptedPassword);
		newUser.setEmail(userDTO.getEmail().toLowerCase());
		newUser.setAvatar(userDTO.getAvatar());
		if (StringUtils.isNotBlank(userDTO.getLangKey())){
			newUser.setLangKey(userDTO.getLangKey());
		} else {
			newUser.setLangKey("zh_cn");
		}
		newUser.setActivated(ActivatedStatus.INACTIVATE);
		newUser.setActivationKey(RandomUtil.generateActivationKey());
		userMapper.insert(newUser);
		Set<Role> roles = new HashSet<>();
		Role role = roleMapper.selectByName(AuthoritiesConstants.USER);
		if (role != null) {
			roles.add(role);
			userMapper.addRole(newUser.getId(), roles);
		}


		return newUser;
	}

	@Override
	public User activateRegistration(String key) {
		log.debug("开始激活用户 key: {}", key);
		User userDB = userMapper.findOneByActivationKey(key);
		if (userDB != null) {
			userDB.setActivated(ActivatedStatus.ACTIVATE);
			userDB.setActivationKey(null);
			userMapper.updateActivate(userDB);
			log.debug("激活成功 user: {}", userDB);
			return userDB;
		}

		return null;
	}

	private boolean removeNonActivatedUser(User existingUser) {
		if (existingUser.getActivated().getStatus()) {
			return false;
		}

		userMapper.deleteById(existingUser.getId());
		return true;
	}

}
