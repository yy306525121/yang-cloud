package cn.codeyang.registry;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableAdminServer
@EnableDiscoveryClient
public class YangRegistryApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(YangRegistryApplication.class)
				.web(WebApplicationType.REACTIVE)
				.run(args);
	}

}

