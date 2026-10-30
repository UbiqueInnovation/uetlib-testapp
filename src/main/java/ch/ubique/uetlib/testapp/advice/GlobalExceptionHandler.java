package ch.ubique.uetlib.testapp.advice;

import ch.ubique.uetlib.spring.boot.problem.handler.UetlibExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler extends UetlibExceptionHandler {}
