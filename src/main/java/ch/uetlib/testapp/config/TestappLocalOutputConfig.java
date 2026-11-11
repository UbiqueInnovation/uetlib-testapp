package ch.uetlib.testapp.config;

import ch.uetlib.storage.StorageClient;
import ch.uetlib.storage.clients.FileSystemStorageClient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Path;

@Configuration
public class TestappLocalOutputConfig {

    private final String localOutputDir;

    public TestappLocalOutputConfig(
            @Value("/tmp/${spring.application.name}") final String localOutputDir) {
        this.localOutputDir = localOutputDir;
    }

    @Bean
    public StorageClient jobRunStorageClient() {
        return new FileSystemStorageClient(Path.of(localOutputDir, "job-runs").toString());
    }
}
