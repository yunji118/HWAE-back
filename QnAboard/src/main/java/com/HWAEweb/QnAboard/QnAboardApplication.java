package com.HWAEweb.QnAboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class QnAboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(QnAboardApplication.class, args);
	}

}
