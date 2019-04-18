//package cn.codeyang.auth.config.security;
//
//import cn.codeyang.common.constant.AuthoritiesConstants;
//import org.springframework.beans.BeansException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.ApplicationContextAware;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
//import org.springframework.security.oauth2.provider.approval.ApprovalStore;
//import org.springframework.security.oauth2.provider.token.TokenEnhancer;
//import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
//import org.springframework.security.oauth2.provider.token.TokenStore;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.web.filter.CorsFilter;
//
//import javax.servlet.http.HttpServletResponse;
//import javax.sql.DataSource;
//import java.util.ArrayList;
//import java.util.Collection;
//
///**
// * @author yangzhongyang
// */
//@Configuration
//@EnableAuthorizationServer
//public class AuthConfiguration extends AuthorizationServerConfigurerAdapter implements ApplicationContextAware {
//
//	private ApplicationContext applicationContext;
//	@Autowired
//	private TokenStore redisTokenStore;
//	@Autowired
//	private DataSource dataSource;
//	@Autowired
//	private ApprovalStore approvalStore;
//
//	@Override
//	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//		this.applicationContext = applicationContext;
//	}
//
//	@EnableResourceServer
//	public static class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
//
//		@Autowired
//		private TokenStore tokenStore;
//		@Autowired
//		private CorsFilter corsFilter;
//
//		@Override
//		public void configure(HttpSecurity http) throws Exception {
//			http
//					.exceptionHandling()
//					.authenticationEntryPoint((request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED))
//					.and()
//					.csrf()
//					.disable()
//					.addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)
//					.headers()
//					.frameOptions()
//					.disable()
//					.and()
//					.sessionManagement()
//					.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//					.and()
//					.authorizeRequests()
//					.antMatchers("/api/register").permitAll()
//					.antMatchers("/api/activate").permitAll()
//					.antMatchers("/api/authenticate").permitAll()
//					.antMatchers("/api/account/reset-password/init").permitAll()
//					.antMatchers("/api/account/reset-password/finish").permitAll()
//					.antMatchers("/api/**").authenticated()
//					.antMatchers("/management/health").permitAll()
//					.antMatchers("/management/**").hasAuthority(AuthoritiesConstants.ADMIN)
//					.antMatchers("/v2/api-docs/**").permitAll()
//					.antMatchers("/swagger-resources/configuration/ui").permitAll()
//					.antMatchers("/swagger/ui/index.html").hasAuthority(AuthoritiesConstants.ADMIN)
//					.and()
//					.cors();
//		}
//
//		@Override
//		public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
//			resources.resourceId("yang-auth").tokenStore(tokenStore);
//		}
//	}
//
//	@Override
//	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//		clients.jdbc(dataSource);
//	}
//
//	@Override
//	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//		Collection<TokenEnhancer> tokenEnhancers = applicationContext.getBeansOfType(TokenEnhancer.class).values();
//
//		TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
//		tokenEnhancerChain.setTokenEnhancers(new ArrayList<>(tokenEnhancers));
//
//		endpoints
//				.authenticationManager(authenticationManager)
//				.tokenStore(redisTokenStore)
//				.approvalStore(approvalStore)
//				.tokenEnhancer(tokenEnhancerChain)
//				.reuseRefreshTokens(false);
//	}
//
//	@Autowired
//	@Qualifier("authenticationManagerBean")
//	private AuthenticationManager authenticationManager;
//
//
//	/**
//	 * 项目中配置了auth.store-type=jwt或不配置时生效
//	 *
//	 * @param security
//	 * @throws Exception
//	 */
//	@Override
//	@ConditionalOnProperty(prefix = "auth", name = "store-type", havingValue = "jwt", matchIfMissing = true)
//	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
//		/**
//		 * 配置jwt令牌端点的安全约束
//		 */
//		security
//				.tokenKeyAccess("permitAll()")
//				.checkTokenAccess("isAuthenticated()");
//	}
//}
//
