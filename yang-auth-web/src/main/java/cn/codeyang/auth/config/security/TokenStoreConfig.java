package cn.codeyang.auth.config.security;

import cn.codeyang.auth.config.YangRedisTokenStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * @author yangzhongyang
 */
@Configuration
public class TokenStoreConfig {
	private final RedisConnectionFactory redisConnectionFactory;
	private final ClientDetailsService clientDetailsService;

	@Autowired
	public TokenStoreConfig(RedisConnectionFactory redisConnectionFactory, ClientDetailsService clientDetailsService) {
		this.redisConnectionFactory = redisConnectionFactory;
		this.clientDetailsService = clientDetailsService;
	}

	/**
	 * havingValue 当name的值与此值相同时加载配置
	 * @return
	 */
	@Bean
	public TokenStore redisTokenStore() {
		YangRedisTokenStore tokenStoreService = new YangRedisTokenStore(redisConnectionFactory, clientDetailsService);
		tokenStoreService.setPrefix("user-token:");
		return tokenStoreService;
	}

	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

}

