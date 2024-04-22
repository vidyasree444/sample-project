package com.example.demo.Entties;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class FlightHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long flightHistoryId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "fromCity")
    private City fromCity;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "toCity")
    private City toCity;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "flightNo")
    private Flight flight;


    private LocalDate departureDate;

    private LocalTime departureTime;

    private LocalDate arrivalDate;

    private LocalTime arrivalTime;

    private int durationInMinutes;

    private String aircraft;

    private String remarks;

    public FlightHistory() {
    }

    public City getFromCity() {
        return fromCity;
    }

    public void setFromCity(City fromCity) {
        this.fromCity = fromCity;
    }

    public City getToCity() {
        return toCity;
    }

    public void setToCity(City toCity) {
        this.toCity = toCity;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(int durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public String getAircraft() {
        return aircraft;
    }

    public void setAircraft(String aircraft) {
        this.aircraft = aircraft;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    public Long getFlightHistoryId() {
        return flightHistoryId;
    }

    

    @Override
    public String toString() {
        return "FlightHistory [flightHistoryId=" + flightHistoryId + ", fromCity=" + fromCity + ", toCity=" + toCity
                + ", flight=" + flight + ", departureDate=" + departureDate + ", departureTime=" + departureTime
                + ", arrivalDate=" + arrivalDate + ", arrivalTime=" + arrivalTime + ", durationInMinutes="
                + durationInMinutes + ", aircraft=" + aircraft + ", remarks=" + remarks + "]";
    }

  
    


}
