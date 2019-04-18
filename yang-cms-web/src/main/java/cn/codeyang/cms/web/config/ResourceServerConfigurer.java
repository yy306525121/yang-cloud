package cn.codeyang.cms.web.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @author yangzhongyang
 */
@Configuration
@EnableResourceServer
@Slf4j
public class ResourceServerConfigurer extends ResourceServerConfigurerAdapter {

	private TokenStore tokenStore;

	public ResourceServerConfigurer(TokenStore redisTokenStore) {
		this.tokenStore = redisTokenStore;
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		//http
		//		.exceptionHandling()
		//		.authenticationEntryPoint((request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED))
		//		.and()
		//		.csrf()
		//		.disable()
		//		.addFilterBefore(corsFilter(), UsernamePasswordAuthenticationFilter.class)
		//		.headers()
		//		.frameOptions()
		//		.disable()
		//		.and()
		//		.sessionManagement()
		//		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		//		.and()
		//		.authorizeRequests()
		//		.antMatchers("/api/register").permitAll()
		//		.antMatchers("/api/activate").permitAll()
		//		.antMatchers("/api/authenticate").permitAll()
		//		.antMatchers("/api/account/reset-password/init").permitAll()
		//		.antMatchers("/api/account/reset-password/finish").permitAll()
		//		.antMatchers("/api/**").authenticated()
		//		.antMatchers("/management/health").permitAll()
		//		.antMatchers("/management/**").hasAuthority(AuthoritiesConstants.ADMIN)
		//		.antMatchers("/v2/api-docs/**").permitAll()
		//		.antMatchers("/swagger-resources/configuration/ui").permitAll()
		//		.antMatchers("/swagger/ui/index.html").hasAuthority(AuthoritiesConstants.ADMIN)
		//		.and()
		//		.cors();

		http
				.csrf().disable()
				.headers()
				.and()
				.authorizeRequests()
				.antMatchers("/api/register").permitAll()
				.anyRequest().authenticated()
				.and()
				.cors();
	}

	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();

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
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.resourceId("yang-cms").tokenStore(tokenStore);
	}

	@Bean
	@Order(0)
	public RequestContextListener requestContextListener() {
		return new RequestContextListener();
	}
}
