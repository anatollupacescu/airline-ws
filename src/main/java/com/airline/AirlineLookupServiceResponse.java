package com.airline;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AirlineLookupServiceResponse {

    private String airline; //Name of Airline
    private String supplier; // Eg: CrazyAir or ToughJet
    private BigDecimal fare; // Total price rounded to 2 decimals
    private String departureAirportCode; // 3 letter IATA code(eg. LHR, AMS)
    private String destinationAirportCode; // 3 letter IATA code(eg. LHR, AMS)
    private String departureDate; // Date Time in ISO8601
    private String arrivalDate;

    private static final DateTimeFormatter ISO8601_DateFormat = DateTimeFormatter.ofPattern("L-d-u");

    public AirlineLookupServiceResponse() {
        //it's empty because jackson needs it, FFS
    }

    public AirlineLookupServiceResponse(String airline, String supplier, String fare, String departureAirportCode,
                                        String destinationAirportCode, LocalDate departureDate, LocalDate arrivalDate) {
        this.airline = airline;
        this.supplier = supplier;
        this.fare = BigDecimal.valueOf(Double.valueOf(fare)).setScale(2, BigDecimal.ROUND_FLOOR);
        this.departureAirportCode = departureAirportCode;
        this.destinationAirportCode = destinationAirportCode;
        this.departureDate = ISO8601_DateFormat.format(departureDate);
        this.arrivalDate = ISO8601_DateFormat.format(arrivalDate);
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public BigDecimal getFare() {
        return fare;
    }

    public void setFare(BigDecimal fare) {
        this.fare = fare;
    }

    public String getDepartureAirportCode() {
        return departureAirportCode;
    }

    public void setDepartureAirportCode(String departureAirportCode) {
        this.departureAirportCode = departureAirportCode;
    }

    public String getDestinationAirportCode() {
        return destinationAirportCode;
    }

    public void setDestinationAirportCode(String destinationAirportCode) {
        this.destinationAirportCode = destinationAirportCode;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }
}
