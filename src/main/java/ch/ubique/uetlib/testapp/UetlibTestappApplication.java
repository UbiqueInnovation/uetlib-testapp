package ch.ubique.uetlib.testapp;

import ch.ubique.uetlib.job.JobApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
        scanBasePackages = {
            "ch.ubique.uetlib.testapp",
            "ch.ubique.uetlib.job",
            "ch.ubique.uetlib.monitoring"
        })
public class UetlibTestappApplication {

    static void main(final String[] args) {
        JobApplication.run(UetlibTestappApplication.class, args);
    }
}
