package io.github.elcattivo13.ecpoker.exception;

import org.springframework.http.HttpStatus;

public class InvalidCardException extends EcPokerException {

    public InvalidCardException() {
        super(HttpStatus.BAD_REQUEST, "Invalid card");
    }
}