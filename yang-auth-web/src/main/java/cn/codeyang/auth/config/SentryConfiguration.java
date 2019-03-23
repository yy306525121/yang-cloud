package cn.codeyang.auth.config;

import cn.codeyang.common.config.YangProperties;
import io.sentry.Sentry;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.annotation.PostConstruct;

/**
 * @author yangzhongyang
 */
@Configuration
@ConditionalOnExpression(value = "${yang.sentry.enabled:true}")
public class SentryConfiguration {

	private YangProperties yangProperties;

	public SentryConfiguration(YangProperties yangProperties) {
		this.yangProperties = yangProperties;
	}

	@PostConstruct
	public void init(){
		Sentry.init(yangProperties.getSentry().getDsn());
	}

	@Bean
	public HandlerExceptionResolver sentryExceptionResolver() {
		return new io.sentry.spring.SentryExceptionResolver();
	}

	@Bean
	public ServletContextInitializer sentryServletContextInitializer() {
		return new io.sentry.spring.SentryServletContextInitializer();
	}



}
