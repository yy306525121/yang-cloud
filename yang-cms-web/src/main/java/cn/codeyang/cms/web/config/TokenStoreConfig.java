package cn.codeyang.cms.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * @author yangzhongyang
 */
@Configuration
public class TokenStoreConfig {
	private final RedisConnectionFactory redisConnectionFactory;

	@Autowired
	public TokenStoreConfig(RedisConnectionFactory redisConnectionFactory) {
		this.redisConnectionFactory = redisConnectionFactory;
	}


	/**
	 * havingValue 当name的值与此值相同时加载配置
	 * @return
	 */
	@Bean
	public TokenStore redisTokenStore() {
		RedisTokenStore redisTokenStore = new RedisTokenStore(redisConnectionFactory);
		redisTokenStore.setPrefix("user-token:");
		return redisTokenStore;
	}

}
