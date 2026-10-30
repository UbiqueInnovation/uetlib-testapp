package ch.ubique.uetlib.testapp.config;

import ch.ubique.uetlib.storage.StorageClient;
import ch.ubique.uetlib.storage.clients.FileSystemStorageClient;
import java.nio.file.Path;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
