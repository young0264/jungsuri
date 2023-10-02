package com.app.jungsuri;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.test.context.ActiveProfiles;

@SpringBootApplication
@ActiveProfiles("${profile}")
//@ConditionalOnProperty(prefix = "spring.flyway", name = "enabled", matchIfMissing = true)
public class JungsuriApplication {

	public static void main(String[] args) {
		SpringApplication.run(JungsuriApplication.class, args);
	}

}
