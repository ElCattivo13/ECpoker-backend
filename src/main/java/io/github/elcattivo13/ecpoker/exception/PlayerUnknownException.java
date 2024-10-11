package io.github.elcattivo13.ecpoker.exception;

import org.springframework.http.HttpStatus;

public class PlayerUnknownException extends EcPokerException {

    public PlayerUnknownException() {
        super(HttpStatus.NOT_FOUND, "Player not found");
        //
    }
}