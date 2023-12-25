package com.app.jungsuri;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.test.context.ActiveProfiles;

@SpringBootApplication
@ActiveProfiles("${profile}")
//@ActiveProfiles("${spring.profiles.active}")
public class JungsuriApplication {

	public static void main(String[] args) {
		SpringApplication.run(JungsuriApplication.class, args);
	}

}
