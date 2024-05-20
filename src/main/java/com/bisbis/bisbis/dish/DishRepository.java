package com.bisbis.bisbis.dish;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bisbis.bisbis.restaurant.Restaurant;


@Repository
public interface DishRepository extends JpaRepository<Dish, Integer>{

    List<Dish> findByRestaurant(Restaurant restaurant);
    Dish findById(int dish_id);
    Dish save(Dish dish);
    void deleteById(int id);
    void delete(Dish dish);
    Dish getByDishidAndRestaurant( int id, Restaurant restaurant);
    
}
