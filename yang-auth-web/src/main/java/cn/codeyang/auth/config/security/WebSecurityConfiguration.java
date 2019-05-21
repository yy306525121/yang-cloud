package cn.codeyang.auth.config.security;

import cn.codeyang.common.properties.YangProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @author yangzhongyang
 */
@Configuration
@EnableWebSecurity
@Slf4j
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
	private YangProperties yangProperties;

	public WebSecurityConfiguration(YangProperties yangProperties) {
		this.yangProperties = yangProperties;
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = yangProperties.getCors();

		config.addAllowedOrigin("*");
		config.addAllowedHeader("*");
		config.addAllowedMethod("*");
		config.setAllowCredentials(true);

		if (config.getAllowedOrigins() != null && !config.getAllowedOrigins().isEmpty()) {
			log.debug("Registering CORS filter");
			source.registerCorsConfiguration("/oauth/**", config);
			source.registerCorsConfiguration("/api/**", config);
			source.registerCorsConfiguration("/management/**", config);
			source.registerCorsConfiguration("/v2/api-docs", config);
		}

		return new CorsFilter(source);
	}

	@Override
	public void configure(WebSecurity web) {
		web.ignoring()
				.antMatchers(HttpMethod.OPTIONS, "/**") //结局跨域问题
				.antMatchers("/app/**/*.{js,html}")
				.antMatchers("/i18n/**")
				.antMatchers("/content/**")
				.antMatchers("/swagger-ui/index.html")
				.antMatchers("/test/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		/**
		 * 添加httpBasic登陆作为code授权时输入用户名和密码
		 */
		http.httpBasic();
	}
}
