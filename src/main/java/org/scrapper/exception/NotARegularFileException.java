package org.scrapper.exception;

public class NotARegularFileException extends RuntimeException {

    public NotARegularFileException(String message) {
        super(message);
    }

}
