package ch.ubique.backend.job.testapp.problem;

import ch.ubique.backend.spring.boot.problem.AbstractUetlibThrowableProblem;
import ch.ubique.backend.spring.boot.problem.UetlibThrowableProblem;

import org.jspecify.annotations.NonNull;
import org.springframework.http.HttpStatus;

import java.net.URI;

public class TestappThrowableProblem extends AbstractUetlibThrowableProblem
        implements UetlibThrowableProblem<TestappErrorType> {

    public static final URI TYPE = URI.create("urn:ch:uetlib:job-testapp:problem:testapp");

    private final TestappErrorType errorType;

    public TestappThrowableProblem(final @NonNull TestappErrorType errorType) {
        super(TYPE, "Testapp Problem", HttpStatus.BAD_REQUEST);
        this.errorType = errorType;
    }

    @Override
    public @NonNull TestappErrorType errorType() {
        return errorType;
    }
}
