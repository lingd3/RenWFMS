package org.sysu.renZuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class RenZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(RenZuulApplication.class, args);
	}
}
