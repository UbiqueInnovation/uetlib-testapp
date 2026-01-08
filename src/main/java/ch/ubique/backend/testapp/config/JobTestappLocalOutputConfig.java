package ch.ubique.backend.testapp.config;

import ch.ubique.backend.storage.StorageClient;
import ch.ubique.backend.storage.clients.FileSystemStorageClient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Path;

@Configuration
public class JobTestappLocalOutputConfig {

    private final String localOutputDir;

    public JobTestappLocalOutputConfig(
            @Value("/tmp/${spring.application.name}") final String localOutputDir) {
        this.localOutputDir = localOutputDir;
    }

    @Bean
    public StorageClient jobRunStorageClient() {
        return new FileSystemStorageClient(Path.of(localOutputDir, "job-runs").toString());
    }
}
