package ch.ubique.backend.job.testapp.controller;

import ch.ubique.backend.job.testapp.problem.TestappErrorType;
import ch.ubique.backend.job.testapp.problem.TestappSpringDocProblem;
import ch.ubique.backend.job.testapp.problem.TestappThrowableProblem;
import ch.ubique.backend.spring.validation.ValidateResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import org.springframework.http.ProblemDetail;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;
import java.util.Optional;

@Validated
@ValidateResponse
@RestController
@RequestMapping("/v1/testapp")
public class TestappController {

    /** always throws {@link NoSuchElementException} */
    @GetMapping("/problem-no-such-value-test")
    public String problemNoSuchValueTest() {
        //noinspection DataFlowIssue
        return Optional.ofNullable(null).orElseThrow().toString();
    }

    /** always throws {@link TestappSpringDocProblem} */
    @GetMapping("/problem-testapp-problem-test")
    @Operation(
            responses = {
                @ApiResponse(responseCode = "200", description = "never happens"),
                @ApiResponse(
                        responseCode = "400",
                        description = "Bad Request: Testapp Problem",
                        content =
                                @Content(
                                        schema =
                                                @Schema(
                                                        implementation =
                                                                TestappSpringDocProblem.class))),
                @ApiResponse(
                        responseCode = "500",
                        description = "Internal Server Error: Generic Problem",
                        content = @Content(schema = @Schema(implementation = ProblemDetail.class)))
            })
    public String problemTestappProblemTest() {
        throw new TestappThrowableProblem(TestappErrorType.TESTAPP);
    }
}
