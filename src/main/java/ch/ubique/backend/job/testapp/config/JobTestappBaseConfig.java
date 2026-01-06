package ch.ubique.backend.job.testapp.config;

import ch.ubique.backend.core.jackson.ObjectMapperUtils;
import ch.ubique.backend.job.annotation.EnableJobs;
import ch.ubique.backend.storage.spring.annotations.EnableStorages;

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import tools.jackson.databind.ObjectMapper;

@Configuration
@EntityScan(basePackages = "ch.ubique.backend.job.model.entity")
@EnableJpaRepositories(basePackages = "ch.ubique.backend.job.data.repository")
@EnableJobs(basePackages = "ch.ubique.backend.job")
@EnableStorages(basePackages = "ch.ubique.backend.job.storage")
public class JobTestappBaseConfig {

    @Bean
    ObjectMapper objectMapper() {
        // If you need JTS support for geometries, use ObjectMapperJtsUtils from geo module
        return ObjectMapperUtils.objectMapper();
    }

    @Bean
    CloseableHttpClient httpClient() {
        return HttpClients.createSystem();
    }
}
