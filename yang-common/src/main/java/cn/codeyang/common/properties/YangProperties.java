package cn.codeyang.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;

/**
 * @author yangzhongyang
 */
@Component
@ConfigurationProperties(prefix = "yang", ignoreUnknownFields = false)
@Data
public class YangProperties {

	private final Cache cache = new Cache();
	private final Mail mail = new Mail();
	private CorsConfiguration cors = new CorsConfiguration();
	private Sentry sentry = new Sentry();

	@Data
	public static class Cache {
		private final Hazelcast hazelcast = new Hazelcast();

		@Data
		public static class Hazelcast {
			private int timeToLiveSeconds = YangDefaults.Cache.Hazelcast.timeToLiveSeconds;
			private int backupCount = YangDefaults.Cache.Hazelcast.backupCount;
			private ManagementCenter managementCenter = new ManagementCenter();

			@Data
			public static class ManagementCenter {
				private boolean enabled = YangDefaults.Cache.Hazelcast.ManagementCenter.enabled;
				private int updateInterval = YangDefaults.Cache.Hazelcast.ManagementCenter.updateInterval;
				private String url = YangDefaults.Cache.Hazelcast.ManagementCenter.url;
			}
		}
	}

	@Data
	public static class Mail {

		private boolean enabled = YangDefaults.Mail.enabled;

		private String from = YangDefaults.Mail.from;

		private String baseUrl = YangDefaults.Mail.baseUrl;

	}


	@Data
	public static class Sentry {
		private boolean enabled = YangDefaults.Sentry.enabled;
		private String dsn = YangDefaults.Sentry.dsn;
	}
}
