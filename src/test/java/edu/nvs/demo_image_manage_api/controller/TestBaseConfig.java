package edu.nvs.demo_image_manage_api.controller;

import com.github.dockerjava.api.model.ExposedPort;
import com.github.dockerjava.api.model.PortBinding;
import com.github.dockerjava.api.model.Ports;

import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@AutoConfigureWebTestClient
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:test/application-test.yml")
@Testcontainers
public abstract class TestBaseConfig {
    @Container
    private static final PostgreSQLContainer<?> postgreSQLContainer =
            new PostgreSQLContainer<>("postgres:16.3")
                    .withDatabaseName("testdb")
                    .withExposedPorts(5432)
                    .withCreateContainerCmdModifier(cmd -> cmd.withPortBindings(new PortBinding(Ports.Binding.bindPort(59664), new ExposedPort(5432))));

    @DynamicPropertySource
    static void dynamicProperties(DynamicPropertyRegistry registry) {
        int mappedPort = postgreSQLContainer.getMappedPort(5432); // Use default exposed port
        String r2dbcUrl = String.format("r2dbc:postgresql://localhost:%d/testdb", mappedPort);
        String jdbcUrl = String.format("jdbc:postgresql://localhost:%d/testdb", mappedPort);

        registry.add("spring.r2dbc.url", () -> r2dbcUrl);
        registry.add("spring.r2dbc.username", postgreSQLContainer::getUsername);
        registry.add("spring.r2dbc.password", postgreSQLContainer::getPassword);
        registry.add("spring.datasource.url", () -> jdbcUrl);
        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
        registry.add("spring.flyway.url", () -> String.format("jdbc:postgresql://localhost:%d/testdb", mappedPort));
        registry.add("spring.flyway.user", postgreSQLContainer::getUsername);
        registry.add("spring.flyway.password", postgreSQLContainer::getPassword);
    }

    public static PostgreSQLContainer<?> getPostgreSQLContainer() {
        return postgreSQLContainer;
    }
}