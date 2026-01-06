package ch.ubique.backend.job.testapp.problem;

import ch.ubique.backend.spring.boot.problem.errortype.ProblemErrorType;

public enum TestappErrorType implements ProblemErrorType {
    TESTAPP(1);

    private final int code;

    TestappErrorType(final int code) {
        this.code = code;
    }

    @Override
    public int code() {
        return code;
    }
}
