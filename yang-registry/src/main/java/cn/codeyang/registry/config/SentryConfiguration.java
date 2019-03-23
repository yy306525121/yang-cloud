package cn.codeyang.registry.config;

import cn.codeyang.common.config.YangProperties;
import io.sentry.Sentry;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Configuration;

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
}
