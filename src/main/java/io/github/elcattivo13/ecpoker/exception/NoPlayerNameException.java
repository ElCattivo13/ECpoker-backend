package io.github.elcattivo13.ecpoker.exception;

import org.springframework.http.HttpStatus;

public class NoPlayerNameException extends EcPokerException {

    public NoPlayerNameException() {
        super(HttpStatus.BAD_REQUEST, "No player name provided");
    }
}