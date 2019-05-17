package cn.codeyang.common.utils;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

/**
 * @author yangzhongyang
 */
public class SecurityUtils {
	public static String getTokenValue() {
		OAuth2AuthenticationDetails authenticationDetails = (OAuth2AuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
		if (authenticationDetails == null) {
			return "";
		}
		return authenticationDetails.getTokenValue();
	}

	public static Object getCurrentUser(){
		SecurityContext context = SecurityContextHolder.getContext();
		return context.getAuthentication().getPrincipal();
	}

}