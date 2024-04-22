package com.example.demo.Repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entties.FlightHistory;

@Repository
public interface FlightHistoryRepo extends JpaRepository<FlightHistory,Long>{

    List<FlightHistory> findByFlight_FlightNo(String flightno);
    
}
