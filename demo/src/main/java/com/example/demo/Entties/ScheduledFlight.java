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
public class ScheduledFlight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scheduledFlightId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "fromCity")
    private City fromCity;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="toCity")
    private City toCity;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="flightNo")
    private Flight flight;

    private LocalDate departureDate;

    private LocalTime departureTime;

    private LocalDate arrivalDate;

    private LocalTime arrivalTime;

    private int durationInMinutes;

    public ScheduledFlight() {
    }

    public ScheduledFlight(City fromCity, City toCity, Flight flight, LocalDate departureDate, LocalTime departureTime,
            LocalDate arrivalDate, LocalTime arrivalTime, int durationInMinutes) {
        this.fromCity = fromCity;
        this.toCity = toCity;
        this.flight = flight;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.arrivalDate = arrivalDate;
        this.arrivalTime = arrivalTime;
        this.durationInMinutes = durationInMinutes;
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
    
    public Long getScheduledFlightId() {
        return scheduledFlightId;
    }

    @Override
    public String toString() {
        return "ScheduledFlight [scheduledFlightId=" + scheduledFlightId + ", fromCity=" + fromCity + ", toCity="
                + toCity + ", flight=" + flight + ", departureDate=" + departureDate + ", departureTime="
                + departureTime + ", arrivalDate=" + arrivalDate + ", arrivalTime=" + arrivalTime
                + ", durationInMinutes=" + durationInMinutes + "]";
    }

   

    


}
