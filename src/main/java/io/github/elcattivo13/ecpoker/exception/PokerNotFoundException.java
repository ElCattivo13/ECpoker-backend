package io.github.elcattivo13.ecpoker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class PokerNotFoundException extends ResponseStatusException {

    public PokerNotFoundException() {
        super(HttpStatus.NOT_FOUND, "Poker not found");
    }
}