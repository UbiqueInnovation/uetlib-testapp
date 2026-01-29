package ch.ubique.backend.testapp.problem;

import ch.ubique.backend.spring.boot.problem.errortype.ProblemErrorType;
import ch.ubique.backend.spring.boot.problem.errortype.ProblemType;

import org.jspecify.annotations.NonNull;

import java.net.URI;

public enum TestappErrorType implements ProblemErrorType {
    TESTAPP_ERROR1("TE1");

    public static final ProblemType PROBLEM_TYPE =
            new ProblemType(URI.create("urn:ch:uetlib:testapp:problem:testapp"), "TA");

    @NonNull private final String code;

    TestappErrorType(final @NonNull String code) {
        this.code = code;
    }

    @Override
    public @NonNull ProblemType problemType() {
        return PROBLEM_TYPE;
    }

    @Override
    public @NonNull String code() {
        return code;
    }
}
