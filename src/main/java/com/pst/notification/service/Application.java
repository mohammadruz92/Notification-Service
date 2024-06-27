package com.pst.notification.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableKafka
@EnableWebMvc
@EnableAsync
@EnableAspectJAutoProxy
@EnableTransactionManagement
@EntityScan(basePackages = {"com.pst.notification.service"})
@EnableJpaRepositories({"com.pst.notification.service"})
@SpringBootApplication(scanBasePackages = {"com.pst.notification.service"})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
