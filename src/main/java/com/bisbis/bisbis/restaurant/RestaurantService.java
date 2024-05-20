package com.bisbis.bisbis.restaurant;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.bisbis.bisbis.cuisine.*;
import com.bisbis.bisbis.dish.Dish;

@Service
@Validated
public class RestaurantService {
    @Autowired
    private RestaurantRepository restaurantRepository;

    public Restaurant assignCuisine(Restaurant restaurant, Cuisine cuisine) {
        List<Cuisine> cuisine_set = null;
        cuisine_set = restaurant.getCuisines();
        cuisine_set.add(cuisine);
        restaurant.setCuisines(cuisine_set);
        return restaurantRepository.save(restaurant);
    }

    public Restaurant save(Restaurant restaurant) {
        if (restaurantRepository.getByName(restaurant.get_name()) == null){
            return restaurantRepository.save(restaurant);
        }
        else{
        return restaurant;
        }
    }

    public List<Restaurant> findAll() {
        return restaurantRepository.findAll();
    }

    public List<Restaurant> GetByCuisine(Cuisine cuisine) {
        return restaurantRepository.getByCuisines(cuisine);
    }

    public Restaurant getByName(String name) {
        return restaurantRepository.findByName(name);
    }

    public Restaurant getById(int id) {
        return restaurantRepository.findById(id);
    }

    public void DeleteRestaurant(int id) {
        restaurantRepository.deleteById(id);
    }

    public void assignCuisine(Restaurant restaurant, List<Cuisine> cuisines) {
        List<Cuisine> currCuisines = restaurant.getCuisines();
        currCuisines.addAll(cuisines);
        restaurant.setCuisines(currCuisines);
    }

    public void saveDish(Restaurant restaurant, Dish dish) {
        restaurant.addDish(dish);
    }


}
