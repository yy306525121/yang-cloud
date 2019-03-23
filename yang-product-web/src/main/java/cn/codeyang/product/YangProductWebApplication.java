package cn.codeyang.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"cn.codeyang"})
public class YangProductWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(YangProductWebApplication.class, args);
	}

}
