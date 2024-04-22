package com.example.demo.Entties;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class City {

    @Id
    private String cityCode;

    private String cityName;

    private String minutesFromUtc;

    private String Country;

    @JsonIgnore
    @OneToMany(mappedBy = "fromCity")
    private List<Flight> fromCity;

    @JsonIgnore
    @OneToMany(mappedBy = "toCity")
    private List<Flight> toCity;

    @JsonIgnore
    @OneToMany(mappedBy = "fromCity")
    private List<ScheduledFlight> fromCityScheduledFlights;

    @JsonIgnore
    @OneToMany(mappedBy = "toCity")
    private List<ScheduledFlight> toCityScheduledFlights;

    @JsonIgnore
    @OneToMany(mappedBy = "fromCity")
    private List<FlightHistory> fromCityFlightHistory;

    @JsonIgnore
    @OneToMany(mappedBy = "toCity")
    private List<FlightHistory> toCityFlightHistory;

    public City() {
    }

    public City(String cityCode, String cityName, String minutesFromUtc, String country) {
        this.cityCode = cityCode;
        this.cityName = cityName;
        this.minutesFromUtc = minutesFromUtc;
        Country = country;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getMinutesFromUtc() {
        return minutesFromUtc;
    }

    public void setMinutesFromUtc(String minutesFromUtc) {
        this.minutesFromUtc = minutesFromUtc;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    @Override
    public String toString() {
        return "City [cityCode=" + cityCode + ", cityName=" + cityName + ", minutesFromUtc=" + minutesFromUtc
                + ", Country=" + Country + "]";
    }

    



    
}
