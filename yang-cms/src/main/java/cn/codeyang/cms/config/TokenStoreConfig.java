package cn.codeyang.cms.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * @author yangzhongyang
 */
@Configuration
public class TokenStoreConfig {
	@Autowired(required = false)
	private RedisConnectionFactory redisConnectionFactory;

	/**
	 * havingValue 当name的值与此值相同时加载配置
	 * @return
	 */
	@Bean
	@ConditionalOnProperty(prefix = "auth", name = "store-type", havingValue = "redis")
	public TokenStore redisTokenStore() {
		RedisTokenStore redisTokenStore = new RedisTokenStore(redisConnectionFactory);
		redisTokenStore.setPrefix("user-token:");
		return redisTokenStore;
	}

	@Configuration
	@ConditionalOnProperty(prefix = "auth", name = "store-type", havingValue = "jwt", matchIfMissing = true)
	public static class JwtTokenConfig {
		@Bean
		public JwtTokenStore tokenStore() {
			return new JwtTokenStore(jwtAccessTokenConverter());
		}

		/**
		 * 做令牌的办法和校验用
		 * @return
		 */
		@Bean
		public JwtAccessTokenConverter jwtAccessTokenConverter() {
			JwtAccessTokenConverter converter = new JwtAccessTokenConverter();

			//生成签名的key
			converter.setSigningKey("merryyou");
			return converter;
		}
	}

}