package cn.codeyang.auth.config;

import cn.codeyang.auth.constant.AuthoritiesConstants;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author yangzhongyang
 */
@Configuration
@EnableAuthorizationServer
public class AuthConfiguration extends AuthorizationServerConfigurerAdapter implements ApplicationContextAware {
	/**
	 * token过期时间（秒）
	 */
	public static final int MIN_ACCESS_TOKEN_VALIDITY_SECS = 60;

	private ApplicationContext applicationContext;
	@Autowired
	private AuthProperties authProperties;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private TokenStore tokenStore;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	@EnableResourceServer
	public static class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

		@Autowired
		private TokenStore tokenStore;
		@Autowired
		private CorsFilter corsFilter;

		@Override
		public void configure(HttpSecurity http) throws Exception {
			http
					.exceptionHandling()
					.authenticationEntryPoint((request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED))
					.and()
					.csrf()
					.disable()
					.addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)
					.headers()
					.frameOptions()
					.disable()
					.and()
					.sessionManagement()
					.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
					.and()
					.authorizeRequests()
					.antMatchers("/api/register").permitAll()
					.antMatchers("/api/activate").permitAll()
					.antMatchers("/api/authenticate").permitAll()
					.antMatchers("/api/account/reset-password/init").permitAll()
					.antMatchers("/api/account/reset-password/finish").permitAll()
					.antMatchers("/api/**").authenticated()
					.antMatchers("/management/health").permitAll()
					.antMatchers("/management/**").hasAuthority(AuthoritiesConstants.ADMIN)
					.antMatchers("/v2/api-docs/**").permitAll()
					.antMatchers("/swagger-resources/configuration/ui").permitAll()
					.antMatchers("/swagger/ui/index.html").hasAuthority(AuthoritiesConstants.ADMIN)
					.and()
					.cors();
		}

		@Override
		public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
			resources.resourceId("yang-auth").tokenStore(tokenStore);
		}
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		int accessTokenValidity = authProperties.getWebClientConfiguration().getAccessTokenValidityInSeconds();
		accessTokenValidity = Math.max(accessTokenValidity, MIN_ACCESS_TOKEN_VALIDITY_SECS);

		int refreshTokenValidity = authProperties.getWebClientConfiguration().getRefreshTokenValidityInSecondsForRememberMe();
		refreshTokenValidity = Math.max(refreshTokenValidity, accessTokenValidity);

		clients.inMemory()
				.withClient(authProperties.getWebClientConfiguration().getClientId())
				.secret(passwordEncoder.encode(authProperties.getWebClientConfiguration().getSecret()))
				.scopes("openid")
				.autoApprove(true)
				.authorizedGrantTypes("implicit", "refresh_token", "password", "authorization_code")
				.accessTokenValiditySeconds(accessTokenValidity)
				.refreshTokenValiditySeconds(refreshTokenValidity)
				.and()
				.withClient("yang")
				.secret(passwordEncoder.encode("hello"))
				.scopes("web-app")
				.authorities("ROLE_ADMIN")
				.autoApprove(true)
				.authorizedGrantTypes("client_credentials")
				.accessTokenValiditySeconds(30 * 60)
				.refreshTokenValiditySeconds(7 * 24 * 60 * 60);
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		Collection<TokenEnhancer> tokenEnhancers = applicationContext.getBeansOfType(TokenEnhancer.class).values();

		TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
		tokenEnhancerChain.setTokenEnhancers(new ArrayList<>(tokenEnhancers));

		endpoints
				.authenticationManager(authenticationManager)
				.tokenStore(tokenStore)
				.tokenEnhancer(tokenEnhancerChain)
				.reuseRefreshTokens(false);
	}

	@Autowired
	@Qualifier("authenticationManagerBean")
	private AuthenticationManager authenticationManager;


	/**
	 * 项目中配置了auth.store-type=jwt或不配置时生效
	 *
	 * @param security
	 * @throws Exception
	 */
	@Override
	@ConditionalOnProperty(prefix = "auth", name = "store-type", havingValue = "jwt", matchIfMissing = true)
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		/**
		 * 配置jwt令牌端点的安全约束
		 */
		security
				.tokenKeyAccess("permitAll()")
				.checkTokenAccess("isAuthenticated()");
	}
}
