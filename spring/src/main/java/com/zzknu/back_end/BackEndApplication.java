package com.zzknu.back_end;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class BackEndApplication {
	public static void main(String[] args) {
		new SpringApplicationBuilder(BackEndApplication.class)
				.properties("spring.config.location=classpath:/application.yml,classpath:/application-secret.yml")
				.run(args);
	}

}