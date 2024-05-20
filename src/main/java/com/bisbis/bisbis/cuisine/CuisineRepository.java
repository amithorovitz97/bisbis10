package com.bisbis.bisbis.cuisine;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuisineRepository extends JpaRepository<Cuisine, Integer> {
    Cuisine findByName(String name);
    Cuisine save(Cuisine cuisne);
    List<Cuisine> findAll();


}
