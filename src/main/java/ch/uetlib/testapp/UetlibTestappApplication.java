package ch.uetlib.testapp;

import ch.uetlib.job.JobApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
        scanBasePackages = {"ch.uetlib.testapp", "ch.uetlib.job", "ch.uetlib.monitoring"})
public class UetlibTestappApplication {

    static void main(final String[] args) {
        JobApplication.run(UetlibTestappApplication.class, args);
    }
}
