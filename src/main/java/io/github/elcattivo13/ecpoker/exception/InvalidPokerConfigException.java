package io.github.elcattivo13.ecpoker.exception;

import org.springframework.http.HttpStatus;

public class InvalidPokerConfigException extends EcPokerException {

    public InvalidPokerConfigException() {
        super(HttpStatus.BAD_REQUEST, "Invalid Poker Config");
    }
}