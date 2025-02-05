package com.ireveal.EstateRunner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableAspectJAutoProxy
@EnableJpaAuditing

public class EstateRunnerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EstateRunnerApplication.class, args);
	}

}
