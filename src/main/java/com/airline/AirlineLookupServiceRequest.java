package com.airline;

import com.airline.ws.IncorrectDateException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AirlineLookupServiceRequest {

    public final String origin;
    public final String destination;
    public final LocalDate departureDate;
    public final LocalDate returnDate;
    public final Integer numberOfPassengers;

    private static final DateTimeFormatter ISO8601_DateTimeFormat = DateTimeFormatter.ofPattern("L-d-u H:m:s");

    public AirlineLookupServiceRequest(String origin, String destination, String departureDate, String returnDate, String numberOfPassengers) {
        this.origin = origin;
        this.destination = destination;
        this.departureDate = parseDate(departureDate);
        this.returnDate = parseDate(returnDate);
        this.numberOfPassengers = Integer.valueOf(numberOfPassengers);
    }

    private LocalDate parseDate(String date) {
        try {
            return LocalDate.parse(date, ISO8601_DateTimeFormat);
        } catch (DateTimeParseException e) {
            throw new IncorrectDateException("Date could not be parsed: " + date, e);
        }
    }
}
