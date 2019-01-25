package cn.codeyang.auth.service.impl;

import cn.codeyang.auth.entity.Authority;
import cn.codeyang.auth.entity.User;
import cn.codeyang.auth.mapper.AuthorityMapper;
import cn.codeyang.auth.mapper.UserMapper;
import cn.codeyang.auth.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.constraintvalidators.hv.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Locale;

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
			List<Authority> authorities = authorityMapper.findAuthoritiesByUserId(userByEmail.getId());
			userByEmail.setAuthorities(new HashSet<>(authorities));
			return userByEmail;
		}


		String usernameLower = username.toLowerCase(Locale.ENGLISH);
		User userByUsername = userMapper.findByUsername(usernameLower);
		if (userByUsername == null){
			throw new UsernameNotFoundException("User with username " + username + " was not found in the database");
		}
		List<Authority> authorities = authorityMapper.findAuthoritiesByUserId(userByUsername.getId());
		userByUsername.setAuthorities(new HashSet<>(authorities));
		return userByUsername;
	}
}
