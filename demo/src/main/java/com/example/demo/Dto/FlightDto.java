package com.example.demo.Dto;

import java.time.LocalTime;

import jakarta.persistence.Id;

public class FlightDto {
    @Id
    private String flightNo;

    private String fromCity;

    private String toCity;

    private int durationInMinutes;

    private LocalTime departureTime;

    private LocalTime arrivalTime;

    private String Aircraft;

    public FlightDto() {
    }

    public String getFlightNo() {
        return flightNo;
    }

    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo;
    }

    public String getFromCity() {
        return fromCity;
    }

    public void setFromCity(String fromCity) {
        this.fromCity = fromCity;
    }

    public String getToCity() {
        return toCity;
    }

    public void setToCity(String toCity) {
        this.toCity = toCity;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(int durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getAircraft() {
        return Aircraft;
    }

    public void setAircraft(String aircraft) {
        Aircraft = aircraft;
    }

    
    
}
