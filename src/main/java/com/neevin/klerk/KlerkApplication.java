package com.neevin.klerk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableJms
@EnableScheduling
public class KlerkApplication {

	public static void main(String[] args) {
		SpringApplication.run(KlerkApplication.class, args);
	}
}
