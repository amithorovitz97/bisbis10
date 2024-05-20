package com.bisbis.bisbis.restaurant;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bisbis.bisbis.cuisine.Cuisine;



@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
    List<Restaurant> getByCuisines(Cuisine cuisine);
    Restaurant findById(int id);
    Restaurant findByName(String name);
    void deleteById(int id);
    Restaurant save(Restaurant restaurant);
    Restaurant getByName(String name);
    List<Restaurant> findAll();

}
