package io.github.elcattivo13.ecpoker.rest;

import io.github.elcattivo13.ecpoker.exception.EcPokerException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EcPokerException.class)
    protected ResponseEntity<Object> handleInvalidPokerConfigException(EcPokerException ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getReason(), new HttpHeaders(), ex.getHttpStatus(), request);
    }
}