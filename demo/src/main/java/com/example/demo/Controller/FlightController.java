package com.example.demo.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Dto.FlightDto;
import com.example.demo.Entties.Flight;
import com.example.demo.Repos.FlightRepo;

@RestController
@RequestMapping("/flight")
public class FlightController {
    @Autowired
    FlightRepo flightRepo;

 @GetMapping("/all")
    public List<Flight> allflights(){
                 List<Flight> flight=flightRepo.findAll();
                 return flight;
    }

    @GetMapping("/allflights")
    public List<FlightDto> all(){
        List<Flight> flight=flightRepo.findAll();
        List<FlightDto> flightDto=new ArrayList<>();
        for(Flight fl:flight){
            FlightDto dto=new FlightDto();
            dto.setFlightNo(fl.getFlightNo());
            dto.setAircraft(fl.getAircraft());
            dto.setArrivalTime(fl.getArrivalTime());
            dto.setDepartureTime(fl.getDepartureTime());
            dto.setDurationInMinutes(fl.getDurationInMinutes());
            dto.setFromCity(fl.getFromCity().getCityCode());
            dto.setToCity(fl.getToCity().getCityCode());

            flightDto.add(dto);
        }
        return flightDto;
    }

    //2
    @GetMapping("/pagination/{num}/{size}")
    public ResponseEntity<?> pagination(@RequestParam int num,@RequestParam int size){
        try{
        List<Flight> flights=flightRepo.findAll(PageRequest.of(num, size)).getContent();
        List<FlightDto> fd=new ArrayList<>();
        for(Flight f: flights){
            FlightDto dto=new FlightDto();
            dto.setAircraft(f.getAircraft());
            dto.setDurationInMinutes(f.getDurationInMinutes());
            dto.setFlightNo(f.getFlightNo());
            dto.setFromCity(f.getFromCity().getCityCode());
            dto.setToCity(f.getToCity().getCityCode());
            dto.setArrivalTime(f.getArrivalTime());
            dto.setDepartureTime(f.getDepartureTime());
            fd.add(dto);
        }
        return ResponseEntity.ok(fd);
    }
    catch (Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Invalid page num or size");
    }
}

//3
@GetMapping("/flights/{fromcity}/{tocity}")
public ResponseEntity<?> fromTo(@PathVariable String fromcity,@PathVariable String tocity){
    try{
   List<Flight> flights=flightRepo.findByFromCityCityCodeAndToCityCityCode(fromcity,tocity);
   if(flights.isEmpty()){
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No flights are present for given data.");

   }
   List<FlightDto> fDtos=new ArrayList<>();
   for(Flight f:flights){
    FlightDto dto=new FlightDto();
    dto.setAircraft(f.getAircraft());
    dto.setDurationInMinutes(f.getDurationInMinutes());
    dto.setFlightNo(f.getFlightNo());
    dto.setFromCity(f.getFromCity().getCityCode());
    dto.setToCity(f.getToCity().getCityCode());
    dto.setArrivalTime(f.getArrivalTime());
    dto.setDepartureTime(f.getDepartureTime());

    fDtos.add(dto);
   }
    return ResponseEntity.ok(fDtos);
    }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while processing the request.");
        }
    }
}

    
