package cn.codeyang.auth.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientRegistrationService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;

import javax.sql.DataSource;

/**
 * @author yangzhongyang
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {
	private AuthenticationManager authenticationManager;
	private UserDetailsService userDetailsService;
	private DataSource dataSource;
	private TokenStore tokenStore;

	public AuthorizationServerConfiguration(AuthenticationManager authenticationManager, UserDetailsService userDetailsService, DataSource dataSource, TokenStore redisTokenStore) {
		this.authenticationManager = authenticationManager;
		this.userDetailsService = userDetailsService;
		this.dataSource = dataSource;
		this.tokenStore = redisTokenStore;
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.jdbc(dataSource);
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.authenticationManager(authenticationManager)
				.userDetailsService(userDetailsService)
				.tokenStore(tokenStore);
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.checkTokenAccess("permitAll()");
	}

	@Bean
	public ClientRegistrationService clientRegistrationService(){
		return new JdbcClientDetailsService(dataSource);
	}


}
