package cn.codeyang.auth.utils;

import cn.codeyang.auth.entity.User;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author yangzhongyang
 */
public class SecurityUtils {
	public static User getCurrentUser(){
		SecurityContext context = SecurityContextHolder.getContext();
		Object principal = context.getAuthentication().getPrincipal();
		if (principal instanceof User) {
			return (User) principal;
		} else {
			return null;
		}
	}
}
