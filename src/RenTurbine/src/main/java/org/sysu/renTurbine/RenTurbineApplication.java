package org.sysu.renTurbine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

@SpringBootApplication
@EnableTurbine
public class RenTurbineApplication {

	public static void main(String[] args) {
		SpringApplication.run(RenTurbineApplication.class, args);
	}
}
