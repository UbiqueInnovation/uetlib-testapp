package ch.ubique.backend.job.testapp.advice;

import ch.ubique.backend.spring.problem.handler.UetlibExceptionHandler;

import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler extends UetlibExceptionHandler {}
