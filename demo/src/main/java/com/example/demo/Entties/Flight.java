package com.example.demo.Entties;

import java.time.LocalTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Flight {

    @Id
    private String flightNo;

    @ManyToOne
    @JoinColumn(name = "fromCity")
    private City fromCity;

    @ManyToOne
    @JoinColumn(name="toCity")
    private City toCity;

    private int durationInMinutes;

    private LocalTime departureTime;

    private LocalTime arrivalTime;

    private String Aircraft;

    @JsonIgnore
    @OneToMany(mappedBy = "flight")
    private List<ScheduledFlight> scheduledFlight;
    
    @JsonIgnore
    @OneToMany(mappedBy = "flight")
    private List<FlightHistory> flightHistory;

    public Flight() {
    }

    public Flight(String flightNo, City fromCity, City toCity, int durationInMinutes, LocalTime departureTime,
            LocalTime arrivalTime, String aircraft) {
        this.flightNo = flightNo;
        this.fromCity = fromCity;
        this.toCity = toCity;
        this.durationInMinutes = durationInMinutes;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        Aircraft = aircraft;
    }

    public String getFlightNo() {
        return flightNo;
    }

    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo;
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

    @Override
    public String toString() {
        return "Flight [flightNo=" + flightNo + ", fromCity=" + fromCity + ", toCity=" + toCity + ", durationInMinutes="
                + durationInMinutes + ", departureTime=" + departureTime + ", arrivalTime=" + arrivalTime
                + ", Aircraft=" + Aircraft + "]";
    }

    
}
