package cn.codeyang.common.config;

/**
 * @author yangzhongyang
 */
public interface YangDefaults {
	interface Async {
		int corePoolSize = 2;
		int maxPoolSize = 50;
		int queueCapacity = 10000;
	}

	interface Cache {
		interface Hazelcast {
			/**
			 * 1小时
			 */
			int timeToLiveSeconds = 3600;
			/**
			 * 备份数量
			 */
			int backupCount = 1;

			/**
			 * 管理中心
			 */
			interface ManagementCenter {
				/**
				 * 是否开启
				 */
				boolean enabled = false;

				int updateInterval = 3;

				String url = "";
			}
		}
	}

	interface Mail {
		boolean enabled = false;
		String from = "";
		String baseUrl = "";
	}
}
