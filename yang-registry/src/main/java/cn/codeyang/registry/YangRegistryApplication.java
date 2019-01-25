package cn.codeyang.registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class YangRegistryApplication {

	public static void main(String[] args) {
		//new SpringApplicationBuilder(YangRegistryApplication.class)
		//		.web(WebApplicationType.REACTIVE)
		//		.run(args);
		new SpringApplication(YangRegistryApplication.class).run(args);
	}

}

