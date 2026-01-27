package ch.ubique.backend.testapp.problem;

import ch.ubique.backend.spring.boot.problem.AbstractThrowableProblem;
import ch.ubique.backend.spring.boot.problem.ThrowableProblem;

import org.jspecify.annotations.NonNull;
import org.springframework.http.HttpStatus;

public class TestappException extends AbstractThrowableProblem
        implements ThrowableProblem<TestappErrorType> {

    public TestappException(final @NonNull TestappErrorType errorType) {
        super(errorType, "Testapp Problem", HttpStatus.BAD_REQUEST);
    }
}
