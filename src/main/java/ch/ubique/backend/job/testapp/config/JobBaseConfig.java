package ch.ubique.backend.job.testapp.config;

import ch.ubique.backend.core.jackson.ObjectMapperUtils;
import ch.ubique.backend.core.springdoc.SpringDocConfig;
import ch.ubique.backend.job.annotation.EnableJobs;
import ch.ubique.backend.problem.springdoc.SpringDocProblem;
import ch.ubique.backend.storage.spring.annotations.EnableStorages;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.zalando.problem.Problem;
import org.zalando.problem.jackson.ProblemModule;
import org.zalando.problem.violations.ConstraintViolationProblemModule;

@Configuration
@EntityScan(basePackages = "ch.ubique.backend.job.model.entity")
@EnableJpaRepositories(basePackages = "ch.ubique.backend.job.data.repository")
@EnableJobs(basePackages = "ch.ubique.backend.job")
@EnableStorages(basePackages = "ch.ubique.backend.job.storage")
public class JobBaseConfig {

    static {
        SpringDocConfig.setDefaultConfigWith(
                customizer -> customizer.replaceWithClass(Problem.class, SpringDocProblem.class));
    }

    @Bean
    ObjectMapper objectMapper() {
        // If you need JTS support for geometries, use ObjectMapperJtsUtils from geo module
        return ObjectMapperUtils.objectMapper(
                customizer ->
                        customizer
                                .addModule(new ProblemModule())
                                .addModule(new ConstraintViolationProblemModule()));
    }
}
