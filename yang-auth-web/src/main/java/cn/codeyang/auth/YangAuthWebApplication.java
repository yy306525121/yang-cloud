package cn.codeyang.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages = {"cn.codeyang"})
@EnableDiscoveryClient
public class YangAuthWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(YangAuthWebApplication.class, args);
	}

}
