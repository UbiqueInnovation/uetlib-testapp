package ch.ubique.backend.testapp.advice;

import ch.ubique.backend.spring.boot.problem.handler.UetlibExceptionHandler;

import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler extends UetlibExceptionHandler {}
