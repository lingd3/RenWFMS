package org.sysu.renZipkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import zipkin.server.EnableZipkinServer;

@SpringBootApplication
@EnableZipkinServer
public class RenZipkinApplication {

	public static void main(String[] args) {
		SpringApplication.run(RenZipkinApplication.class, args);
	}
}
