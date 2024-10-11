package io.github.elcattivo13.ecpoker.exception;

import org.springframework.http.HttpStatus;

import java.io.Serializable;

public class EcPokerException extends Exception {

    private final HttpStatus httpStatus;
    private final ReasonObj reason;

    public EcPokerException(HttpStatus httpStatus, String reason) {
        this.httpStatus = httpStatus;
        this.reason = new ReasonObj();
        this.reason.reason = reason;
    }

    public ReasonObj getReason() {
        return reason;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public static class ReasonObj implements Serializable {

        private String reason;

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }
    }
}