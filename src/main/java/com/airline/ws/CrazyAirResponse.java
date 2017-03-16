package com.airline.ws;

public class CrazyAirResponse {
    public String airline; // Name of the airline
    public String price; // Total price
    public String cabinclass; // E for Economy and B for Business
    public String departureAirportCode; // Eg: LHR
    public String destinationAirportCode; // Eg: LHR
    public String departureDate; // mm-dd-yyyy HH:MM:ss
    public String arrivalDate;

    public CrazyAirResponse() {
    }

    public CrazyAirResponse(String airline, String price, String cabinclass, String departureAirportCode,
                            String destinationAirportCode, String departureDate, String arrivalDate) {
        this.airline = airline;
        this.price = price;
        this.cabinclass = cabinclass;
        this.departureAirportCode = departureAirportCode;
        this.destinationAirportCode = destinationAirportCode;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
    }
}
