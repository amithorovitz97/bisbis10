package com.bisbis.bisbis.dish;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bisbis.bisbis.restaurant.Restaurant;

@Service
public class DishService {

    @Autowired
    private DishRepository dishRepository;

    public void save(Dish dish) {
       dishRepository.save(dish);
    }

    public  List<Dish> GetRestaurantDishes(Restaurant restaurant) {
        return dishRepository.findByRestaurant(restaurant);
    }

    public void UpdateDish(int restaurant_id, int dish_id, Dish changes) {
        Dish dish = dishRepository.findById(dish_id);
        if (changes.getDishDescription() != null){
            dish.setDishDescription(changes.getDishDescription());
        }
        if (Integer.valueOf(changes.getDishPrice()).equals(null)){
            dish.setDishPrice(changes.getDishPrice());
            dishRepository.save(dish);
        }
    }

    public void DeleteDish(Dish dish) {
        dishRepository.delete(dish);;
    }

    public void DeleteDishById(int dishId) {
        dishRepository.deleteById(dishId);
    }

    public void saveAll(List<Dish> dishes) {
        dishRepository.saveAll(dishes);
    }

    public Dish getByDish_idandRestaurant(Restaurant restaurant, int id) {
        return dishRepository.getByDishidAndRestaurant(id, restaurant);
    }

    
}
