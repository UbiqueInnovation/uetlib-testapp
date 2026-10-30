package ch.ubique.uetlib.testapp.problem;

import ch.ubique.uetlib.spring.boot.problem.errortype.ProblemErrorType;
import ch.ubique.uetlib.spring.boot.problem.errortype.ProblemType;
import java.net.URI;
import org.jspecify.annotations.NonNull;

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
