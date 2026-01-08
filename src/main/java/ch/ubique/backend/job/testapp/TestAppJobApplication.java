package ch.ubique.backend.job.testapp;

import ch.ubique.backend.job.JobApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"ch.ubique.backend.job", "ch.ubique.backend.monitoring"})
public class TestAppJobApplication {

    static void main(final String[] args) {
        JobApplication.run(TestAppJobApplication.class, args);
    }
}
