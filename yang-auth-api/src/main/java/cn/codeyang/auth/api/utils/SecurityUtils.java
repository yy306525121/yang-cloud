package cn.codeyang.auth.api.utils;

import cn.codeyang.auth.api.entity.User;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

/**
 * @author akafra
 */
public class SecurityUtils {
	public static Optional<User> getCurrentUserLogin(){

		SecurityContext securityContext = SecurityContextHolder.getContext();
		return Optional.ofNullable(securityContext.getAuthentication())
				.map(authentication -> {
					if (authentication.getPrincipal() instanceof User) {
						return (User) authentication.getPrincipal();
					} else if (authentication.getPrincipal() instanceof String) {
						User user = new User();
						user.setId(0L);
						return user;
					}
					return null;
				});
	}
}
