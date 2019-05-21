package cn.codeyang.cms.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = {"cn.codeyang"})
@EnableDiscoveryClient
@EnableFeignClients
public class YangCmsWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(YangCmsWebApplication.class, args);
	}

}
