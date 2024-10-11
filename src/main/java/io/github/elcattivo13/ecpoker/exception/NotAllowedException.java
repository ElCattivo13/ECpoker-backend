package io.github.elcattivo13.ecpoker.exception;

import org.springframework.http.HttpStatus;

public class NotAllowedException extends EcPokerException {

    public NotAllowedException() {
        super(HttpStatus.FORBIDDEN, "Not allowed");
    }
}