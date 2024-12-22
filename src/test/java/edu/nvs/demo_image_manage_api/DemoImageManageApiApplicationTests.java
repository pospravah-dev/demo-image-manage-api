package edu.nvs.demo_image_manage_api;

import edu.nvs.demo_image_manage_api.controller.TestBaseConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootTest
@AutoConfigureDataJpa
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class DemoImageManageApiApplicationTests extends TestBaseConfig {

//	@Test
//	void contextLoads() {
//	}

}
