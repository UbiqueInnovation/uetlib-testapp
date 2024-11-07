package ch.ubique.backend.job.testapp.advice;

import ch.ubique.backend.problem.UbiqueProblemHandling;

import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandling implements UbiqueProblemHandling {}
