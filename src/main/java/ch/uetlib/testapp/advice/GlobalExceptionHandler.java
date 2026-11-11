package ch.uetlib.testapp.advice;

import ch.uetlib.spring.boot.problem.handler.UetlibExceptionHandler;

import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler extends UetlibExceptionHandler {}
