package io.github.elcattivo13.ecpoker.exception;

import org.springframework.http.HttpStatus;

public class CardAlreadyPlayedException extends EcPokerException {

    public CardAlreadyPlayedException() {
        super(HttpStatus.BAD_REQUEST, "Card already played");
    }
}