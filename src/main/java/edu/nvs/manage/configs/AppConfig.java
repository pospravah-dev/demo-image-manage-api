package edu.nvs.manage.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.flywaydb.core.Flyway;
import org.springframework.boot.autoconfigure.flyway.FlywayProperties;
import org.springframework.boot.autoconfigure.r2dbc.R2dbcProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({ R2dbcProperties.class, FlywayProperties.class })
public class AppConfig {

    @Bean(initMethod = "migrate")
    public Flyway flyway(FlywayProperties flywayProperties, R2dbcProperties r2dbcProperties) {
      return Flyway.configure()
        .dataSource(
            flywayProperties.getUrl(),
            r2dbcProperties.getUsername(),
            r2dbcProperties.getPassword()
        )
        .locations(flywayProperties.getLocations()
          .stream()
          .toArray(String[]::new))
        .baselineOnMigrate(true)
        .load();
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        return mapper;
    }
}