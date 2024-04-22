package com.example.demo.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Dto.FlightHistoryDto;
import com.example.demo.Entties.FlightHistory;
import com.example.demo.Repos.FlightHistoryRepo;

@RestController
@RequestMapping("/flighthistory")
public class FlghtHistoryController {
    @Autowired
    FlightHistoryRepo flightHistoryRepo;
    @GetMapping("/all")
    public List<FlightHistoryDto> allHistory(){
        List<FlightHistory> flightHistory=flightHistoryRepo.findAll();
        List<FlightHistoryDto> fDto=new ArrayList<>();
        for(FlightHistory fh:flightHistory){
            FlightHistoryDto dto=new FlightHistoryDto();
            dto.setAircraft(fh.getAircraft());
            dto.setArrivalDate(fh.getArrivalDate());
            dto.setArrivalTime(fh.getArrivalTime());
            dto.setFlightNo(fh.getFlight().getFlightNo());
            dto.setFromCity(fh.getFromCity().getCityCode());
            dto.setToCity(fh.getToCity().getCityCode());
            dto.setDepartureDate(fh.getDepartureDate());
            dto.setDepartureTime(fh.getDepartureTime());
            dto.setDurationInMinutes(fh.getDurationInMinutes());
            dto.setRemarks(fh.getRemarks());
            dto.setFlightHistoryId(fh.getFlightHistoryId());
            fDto.add(dto);
        }
        return fDto;
    }

    //4
    @GetMapping("/{flightno}")
    public ResponseEntity<?> byFlightNo(@PathVariable String flightno){
        try{
   List<FlightHistory> flightHistories=flightHistoryRepo.findByFlight_FlightNo(flightno);
   if (flightHistories.isEmpty()) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid data: No flight history found for the given flight number.");
}

      List<FlightHistoryDto> fDto=new ArrayList<>();
        for(FlightHistory fh:flightHistories){
            FlightHistoryDto dto=new FlightHistoryDto();
            dto.setAircraft(fh.getAircraft());
            dto.setArrivalDate(fh.getArrivalDate());
            dto.setArrivalTime(fh.getArrivalTime());
            dto.setFlightNo(fh.getFlight().getFlightNo());
            dto.setFromCity(fh.getFromCity().getCityCode());
            dto.setToCity(fh.getToCity().getCityCode());
            dto.setDepartureDate(fh.getDepartureDate());
            dto.setDepartureTime(fh.getDepartureTime());
            dto.setDurationInMinutes(fh.getDurationInMinutes());
            dto.setRemarks(fh.getRemarks());
            dto.setFlightHistoryId(fh.getFlightHistoryId());
            fDto.add(dto);
        }
        return ResponseEntity.ok(fDto);
    }catch(Exception e){
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while processing the request.");
       
    }
}
}

    
