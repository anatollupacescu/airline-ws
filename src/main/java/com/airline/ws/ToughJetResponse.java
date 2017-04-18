package com.airline.ws;

public class ToughJetResponse {

    private String carrier; //; //; // Name of the Airline
    private String basePrice; // Price without tax(doesn’t include discount)
    private String tax; // Tax which needs to be charged along with the price
    private String discount; // Discount which needs to be applied on the price(in percentage)
    private String departureAirportName; // 3 letter IATA code(eg. LHR, AMS)
    private String arrivalAirportName; // 3 letter IATA code(eg. LHR, AMS)
    private String departureDay; // Day of the Month
    private String departureMonth; // Month as an Integer(1-12)
    private String departureYear; // 4 digit Year
    private String returnDay; // Day of the Month
    private String returnMonth; // Month as an Integer(1-12)
    private String returnYear;

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

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public String getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(String basePrice) {
        this.basePrice = basePrice;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getDepartureAirportName() {
        return departureAirportName;
    }

    public void setDepartureAirportName(String departureAirportName) {
        this.departureAirportName = departureAirportName;
    }

    public String getArrivalAirportName() {
        return arrivalAirportName;
    }

    public void setArrivalAirportName(String arrivalAirportName) {
        this.arrivalAirportName = arrivalAirportName;
    }

    public String getDepartureDay() {
        return departureDay;
    }

    public void setDepartureDay(String departureDay) {
        this.departureDay = departureDay;
    }

    public String getDepartureMonth() {
        return departureMonth;
    }

    public void setDepartureMonth(String departureMonth) {
        this.departureMonth = departureMonth;
    }

    public String getDepartureYear() {
        return departureYear;
    }

    public void setDepartureYear(String departureYear) {
        this.departureYear = departureYear;
    }

    public String getReturnDay() {
        return returnDay;
    }

    public void setReturnDay(String returnDay) {
        this.returnDay = returnDay;
    }

    public String getReturnMonth() {
        return returnMonth;
    }

    public void setReturnMonth(String returnMonth) {
        this.returnMonth = returnMonth;
    }

    public String getReturnYear() {
        return returnYear;
    }

    public void setReturnYear(String returnYear) {
        this.returnYear = returnYear;
    }
}
