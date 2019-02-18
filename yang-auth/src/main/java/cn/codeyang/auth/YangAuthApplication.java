package cn.codeyang.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Hello world!
 */
@SpringBootApplication(scanBasePackages = {"cn.codeyang"})
@EnableDiscoveryClient
public class YangAuthApplication {
	public static void main(String[] args) {
		new SpringApplication(YangAuthApplication.class).run(args);

	}
}
