package com.example.demo.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.webjars.NotFoundException;

import com.example.demo.Entties.City;
import com.example.demo.Repos.CityRepo;

@RestController
@RequestMapping("/city")
public class CityController {
    @Autowired
    CityRepo cityRepo;

//1
    @GetMapping("/all")
    public ResponseEntity<?> allCities(){
        try{
        List<City> city=cityRepo.findAll();
        if(city.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No data found");
           
        }
        return ResponseEntity.ok(city);
       }
       catch(ResponseStatusException e){
        throw e;
       }
       catch(Exception e){
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred", e);
       }
    }

    @DeleteMapping("/delete/{citycode}")
public ResponseEntity<?> delCity(@PathVariable String citycode) {
   try {
       City city = cityRepo.findByCityCode(citycode);
       if(city==null){
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No data found with given city code");
       }
       cityRepo.delete(city);
       return ResponseEntity.ok(city);
   } catch (Exception e) {
       return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while deleting the city: " + e.getMessage());
   }
}

@PutMapping("/update/{citycode}")
   public ResponseEntity<?> setUpdate(@PathVariable String citycode){
       try{
        Optional<City>  city=cityRepo.findByCityCode(citycode);
       if(city==null){
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No data found with given city code");
      
       }
       city.setCountry("Eng");
       cityRepo.save(city);
       return ResponseEntity.ok(city);
       }catch (Exception e){
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while updating the city: " + e.getMessage());
       }
   }
    
}
