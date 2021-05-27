package br.com.bandtec.avaliacaoContinuada3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class AvaliacaoContinuada3Application {

	public static void main(String[] args) {
		SpringApplication.run(AvaliacaoContinuada3Application.class, args);
	}

}
