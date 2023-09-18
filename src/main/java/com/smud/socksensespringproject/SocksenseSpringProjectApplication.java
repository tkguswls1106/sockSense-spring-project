package com.smud.socksensespringproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class SocksenseSpringProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocksenseSpringProjectApplication.class, args);
	}

}
