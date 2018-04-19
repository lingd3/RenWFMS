package org.sysu.renConfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class RenConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(RenConfigApplication.class, args);
	}
}
