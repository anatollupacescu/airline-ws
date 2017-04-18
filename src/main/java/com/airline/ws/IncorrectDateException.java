package com.airline.ws;

import java.time.format.DateTimeParseException;

public class IncorrectDateException extends RuntimeException {
    public IncorrectDateException(DateTimeParseException e) {
        super(e);
    }

    public IncorrectDateException(String s, DateTimeParseException e) {
        super(s, e);
    }
}
