package com.example.demo.Controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.DeleteExchange;

import com.example.demo.Dto.ScheduledFlightDto;
import com.example.demo.Entties.Flight;
import com.example.demo.Entties.ScheduledFlight;
import com.example.demo.Repos.FlightRepo;
import com.example.demo.Repos.ScheduledFlightRepo;

@RestController
@RequestMapping("/scheduledflight")
public class ScheduledFlightController {
    @Autowired
    ScheduledFlightRepo scheduledFlightRepo;
    @Autowired
    FlightRepo flightRepo;
    
    @GetMapping("/all")
    public List<ScheduledFlightDto> allScheduledFlights(){
        List<ScheduledFlight> scheduledFlight=scheduledFlightRepo.findAll();
        List<ScheduledFlightDto> sf=new ArrayList<>();
        for(ScheduledFlight s: scheduledFlight){
            ScheduledFlightDto dto=new ScheduledFlightDto();
            dto.setArrivalDate(s.getArrivalDate());
            dto.setArrivalTime(s.getArrivalTime());
            dto.setDepartureDate(s.getDepartureDate());
            dto.setDepartureTime(s.getDepartureTime());
            dto.setDurationInMinutes(s.getDurationInMinutes());
            dto.setFromCity(s.getFromCity().getCityCode());
            dto.setToCity(s.getToCity().getCityCode());
            dto.setScheduledFlightId(s.getScheduledFlightId());
            dto.setFlight(s.getFlight().getFlightNo());

            sf.add(dto);
        }
        return sf;

    }

    //5
    @PostMapping("/adding/{flightNo}/{departurDate}/{arrivalDate}")
public ResponseEntity<?> addingflight(
        @PathVariable String flightNo,
        @PathVariable LocalDate departurDate,
        @PathVariable LocalDate arrivalDate) {
    try {
        List<Flight> flights = flightRepo.findByFlightNo(flightNo);
        if (flights.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Flight not found with flight number: " + flightNo);
        }

        ScheduledFlight scheduledFlight = new ScheduledFlight();
        scheduledFlight.setArrivalDate(arrivalDate);
        scheduledFlight.setDepartureDate(departurDate);

        for (Flight f : flights) {
            scheduledFlight.setArrivalTime(f.getArrivalTime());
            scheduledFlight.setFlight(f);
            scheduledFlight.setFromCity(f.getFromCity());
            scheduledFlight.setToCity(f.getToCity());
            scheduledFlight.setDurationInMinutes(f.getDurationInMinutes());
            scheduledFlight.setDepartureTime(f.getDepartureTime());
        }

        scheduledFlightRepo.save(scheduledFlight);
        return ResponseEntity.ok(scheduledFlight);
    } catch (Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Some error occurred while processing the request");
    }
}

//7
@GetMapping("/{fromdate}/{citycode}")
public ResponseEntity<?> dateAndCity(@PathVariable LocalDate fromdate,@PathVariable String citycode){
    try{
    List<ScheduledFlight> scheduledFlights=scheduledFlightRepo.findAllByDepartureDateAndFromCityCityCode(fromdate,citycode);
    if(scheduledFlights.isEmpty()){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("departure date or citycode not found");

    }
    List<ScheduledFlightDto> sf=new ArrayList<>();
    for(ScheduledFlight s: scheduledFlights){
        ScheduledFlightDto dto=new ScheduledFlightDto();
        dto.setArrivalDate(s.getArrivalDate());
        dto.setArrivalTime(s.getArrivalTime());
        dto.setDepartureDate(s.getDepartureDate());
        dto.setDepartureTime(s.getDepartureTime());
        dto.setDurationInMinutes(s.getDurationInMinutes());
        dto.setFromCity(s.getFromCity().getCityCode());
        dto.setToCity(s.getToCity().getCityCode());
        dto.setScheduledFlightId(s.getScheduledFlightId());
        dto.setFlight(s.getFlight().getFlightNo());

        sf.add(dto);
    }
    return ResponseEntity.ok(sf);
}catch(Exception e){
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("some error occured while processing request");
}

}

//10
@DeleteMapping("/cancelallflights/{start}/{end}")
public ResponseEntity<String> cancelAllFlights(@RequestParam("start") LocalDate start, @RequestParam("end") LocalDate end) {
    try {
        List<ScheduledFlight> scheduledFlights = scheduledFlightRepo.findAllByDepartureDateBetween(start, end);
        if (scheduledFlights.isEmpty()) {
            return ResponseEntity.ok("No flights found between " + start + " and " + end + ".");
        }
        for (ScheduledFlight sf : scheduledFlights) {
            scheduledFlightRepo.deleteById(sf.getScheduledFlightId());
        }
        return ResponseEntity.ok("all flights between \" + start + \" and \" + end + \" have been canceled.");
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while canceling flights: " + e.getMessage());
    }
}
}
