package ch.ubique.backend.job.testapp.config;

import ch.ubique.backend.job.annotation.EnableJobs;
import ch.ubique.backend.storage.annotations.EnableStorages;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = "ch.ubique.backend.job.model.entity")
@EnableJpaRepositories(basePackages = "ch.ubique.backend.job.data.repository")
@EnableJobs(basePackages = "ch.ubique.backend.job")
@EnableStorages(basePackages = "ch.ubique.backend.job.storage")
public class JobBaseConfig {}
