package com.example.demo.Repos;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entties.City;

@Repository
public interface CityRepo extends JpaRepository<City,String>{

    Optional<City>  findByCityCode(String citycode);
}
