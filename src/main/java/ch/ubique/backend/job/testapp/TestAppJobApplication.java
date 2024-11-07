package ch.ubique.backend.job.testapp;

import ch.ubique.backend.job.JobApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.zalando.problem.spring.web.autoconfigure.security.ProblemSecurityAutoConfiguration;

@SpringBootApplication(
        scanBasePackages = {"ch.ubique.backend.job", "ch.ubique.backend.monitoring"},
        // exclude ErrorMvcAutoConfiguration and ProblemSecurityAutoConfiguration when using
        // zalando/problem
        exclude = {ErrorMvcAutoConfiguration.class, ProblemSecurityAutoConfiguration.class})
public class TestAppJobApplication {

    public static void main(final String[] args) {
        JobApplication.run(TestAppJobApplication.class, args);
    }
}
