package cn.codeyang.common.util;

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

}
