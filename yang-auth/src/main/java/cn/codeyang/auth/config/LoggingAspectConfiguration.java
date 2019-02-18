package cn.codeyang.auth.config;

import cn.codeyang.auth.aop.logging.LoggingAspect;
import cn.codeyang.common.config.YangConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;

/**
 * @author yangzhongyang
 */
@Configuration
@EnableAspectJAutoProxy
public class LoggingAspectConfiguration {

	@Bean
	@Profile(YangConstants.SPRING_PROFILE_DEVELOPMENT)
	public LoggingAspect loggingAspect(Environment env) {
		return new LoggingAspect(env);
	}
}
