package com.china.ciic.studyweb.speechsynthesis.exception;

public class BookTtsException extends TtsException {

    public BookTtsException(String message) {
        super(message);
    }

    public BookTtsException(Exception e) {
        super(e);
    }

    public BookTtsException(String message, Exception e) {
        super(message, e);
    }
}
