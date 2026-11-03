package ch.ubique.uetlib.testapp.controller;

import ch.ubique.uetlib.spring.boot.validation.ValidateResponse;
import ch.ubique.uetlib.testapp.problem.TestappErrorType;
import ch.ubique.uetlib.testapp.problem.TestappException;
import ch.ubique.uetlib.testapp.problem.TestappSpringDocProblem;

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
@RequestMapping("/v1/problem")
public class ProblemController {

    /** always throws {@link NoSuchElementException} */
    @GetMapping("/no-such-value-test")
    public String problemNoSuchValueTest() {
        //noinspection DataFlowIssue
        return Optional.ofNullable(null).orElseThrow().toString();
    }

    /** always throws {@link TestappSpringDocProblem} */
    @GetMapping("/testapp-problem-test")
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
        throw new TestappException(TestappErrorType.TESTAPP_ERROR1);
    }
}
