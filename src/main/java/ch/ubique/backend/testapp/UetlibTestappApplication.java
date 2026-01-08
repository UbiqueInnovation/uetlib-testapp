package ch.ubique.backend.testapp;

import ch.ubique.backend.job.JobApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
        scanBasePackages = {
            "ch.ubique.backend.testapp",
            "ch.ubique.backend.job",
            "ch.ubique.backend.monitoring"
        })
public class UetlibTestappApplication {

    static void main(final String[] args) {
        JobApplication.run(UetlibTestappApplication.class, args);
    }
}
