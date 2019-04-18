package cn.codeyang.cms.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages = {"cn.codeyang"})
@EnableDiscoveryClient
public class YangCmsWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(YangCmsWebApplication.class, args);
	}

}
