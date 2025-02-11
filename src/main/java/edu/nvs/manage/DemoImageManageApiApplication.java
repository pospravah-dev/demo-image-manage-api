package edu.nvs.manage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableR2dbcRepositories
@EnableWebFlux
public class DemoImageManageApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoImageManageApiApplication.class, args);
	}

}
