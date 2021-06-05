package com.mariworld.mreview;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MreviewJpaUploadApplication {

	public static void main(String[] args) {
		SpringApplication.run(MreviewJpaUploadApplication.class, args);
	}

}
