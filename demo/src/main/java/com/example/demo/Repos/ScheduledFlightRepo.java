package com.example.demo.Repos;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entties.ScheduledFlight;

@Repository
public interface ScheduledFlightRepo extends JpaRepository<ScheduledFlight,Long> {
   List<ScheduledFlight> findAllByDepartureDateAndFromCityCityCode(LocalDate fromdate,String citycode);
   List<ScheduledFlight> findAllByDepartureDateBetween(LocalDate start,LocalDate end);
   void deleteByScheduledFlightId(Long scheduledFlightId);
}
