package cn.codeyang.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author yangzhongyang
 */
@Component
@ConfigurationProperties(prefix = "yang", ignoreUnknownFields = false)
@Data
public class YangProperties {

	private final Cache cache = new Cache();

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
}
