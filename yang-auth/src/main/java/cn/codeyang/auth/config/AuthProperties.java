package cn.codeyang.auth.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;

/**
 * ignoreUnknownFields 表示遇到不能匹配的域时抛出异常
 *
 * @author yangzhongyang
 */
@Component
@ConfigurationProperties(prefix = "auth", ignoreUnknownFields = false)
@Data
public class AuthProperties {
	private CorsConfiguration cors = new CorsConfiguration();

	private WebClientConfiguration webClientConfiguration = new WebClientConfiguration();

	@Data
	public static class WebClientConfiguration {
		/**
		 * access-token过期时间
		 * 5 分钟
		 */
		private int accessTokenValidityInSeconds = 5 * 60;

		/**
		 * refresh-token过期时间（在remember me中定义）
		 * 7天
		 */
		private int refreshTokenValidityInSecondsForRememberMe = 7 * 24 * 60 * 60;

		private String clientId = "web_app";
		private String secret = "hello";
	}
}
