package com.china.ciic.studyweb.speechsynthesis.exception;

public class ArticleTtsException extends TtsException {

    public ArticleTtsException(String message) {
        super(message);
    }

    public ArticleTtsException(Exception e) {
        super(e);
    }

    public ArticleTtsException(String message, Exception e) {
        super(message, e);
    }
}
