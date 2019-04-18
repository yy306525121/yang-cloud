package cn.codeyang.auth.config.security;

import cn.codeyang.common.constant.AuthoritiesConstants;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;

/**
 * @author yangzhongyang
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
	private CorsFilter corsFilter;
	private TokenStore tokenStore;

	public ResourceServerConfiguration(CorsFilter corsFilter, TokenStore redisTokenStore) {
		this.corsFilter = corsFilter;
		this.tokenStore = redisTokenStore;
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
				.authorizeRequests()
				.antMatchers("/api/register").permitAll()
				.antMatchers("/api/activate").permitAll()
				.antMatchers("/api/activate").permitAll()
				.antMatchers("/api/**").authenticated()
				.antMatchers("/management/health").permitAll()
				.antMatchers("/management/**").hasAuthority(AuthoritiesConstants.ADMIN)
				.antMatchers("/v2/api-docs/**").permitAll()
				.antMatchers("/swagger-resources/configuration/ui").permitAll()
				.antMatchers("/swagger/ui/index.html").hasAuthority(AuthoritiesConstants.ADMIN)
				.and()
				.csrf().disable()
				.addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)
				.cors();
	}

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.resourceId("yang-auth").tokenStore(tokenStore);
	}
}
