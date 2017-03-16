package com.airline.ws;

public class ToughJetResponse {
    public String carrier; //; //; // Name of the Airline
    public String basePrice; // Price without tax(doesn’t include discount)
    public String tax; // Tax which needs to be charged along with the price
    public String discount; // Discount which needs to be applied on the price(in percentage)
    public String departureAirportName; // 3 letter IATA code(eg. LHR, AMS)
    public String arrivalAirportName; // 3 letter IATA code(eg. LHR, AMS)
    public String departureDay; // Day of the Month
    public String departureMonth; // Month as an Integer(1-12)
    public String departureYear; // 4 digit Year
    public String returnDay; // Day of the Month
    public String returnMonth; // Month as an Integer(1-12)
    public String returnYear;

    public ToughJetResponse() {

    }

    public ToughJetResponse(String carrier, String basePrice, String tax, String discount, String departureAirportName,
                            String arrivalAirportName, String departureDay, String departureMonth, String departureYear,
                            String returnDay, String returnMonth, String returnYear) {
        this.carrier = carrier;
        this.basePrice = basePrice;
        this.tax = tax;
        this.discount = discount;
        this.departureAirportName = departureAirportName;
        this.arrivalAirportName = arrivalAirportName;
        this.departureDay = departureDay;
        this.departureMonth = departureMonth;
        this.departureYear = departureYear;
        this.returnDay = returnDay;
        this.returnMonth = returnMonth;
        this.returnYear = returnYear;
    }
}
