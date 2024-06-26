package com.pst.lookup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class LookupApplication {

	public static void main(String[] args) {
		SpringApplication.run(LookupApplication.class, args);
	}

}
