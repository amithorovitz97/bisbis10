package com.bisbis.bisbis.dish;

import java.util.Iterator;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bisbis.bisbis.restaurant.*;

@RestController
@Validated
@RequestMapping("")
public class DishController {
    
    @Autowired
    private DishService dishService;


    @Autowired
    private RestaurantService restaurantService;
    

    @PostMapping("POST/restaurants/{id}/dishes")
    public ResponseEntity<String> AddDish(@RequestBody JSONObject dish, @PathVariable int restaurant_id) {
        String name = (String) dish.get("name");
        String description = (String) dish.get("description");
        int price = (Integer) dish.get("price");
        Restaurant restaurant = restaurantService.getById(restaurant_id);
        Dish dishNew = new Dish(name, description, price, restaurant);
        dishService.save(dishNew);
        restaurant.addDish(dishNew);
        return new ResponseEntity<String>("Dish created!", HttpStatus.CREATED);
        
    }

    @GetMapping("GET/restaurants/{id}/dishes")
    public ResponseEntity<String> GetDishesOfRestaurant(@PathVariable int id) {
        List<Dish> dishes = null;
        Restaurant restaurant = restaurantService.getById(id);
        dishes = dishService.GetRestaurantDishes(restaurant);
        String res = "[ ";
        Iterator<Dish> iter = dishes.iterator();
        while(iter.hasNext()){
            res = res + iter.next().toString();
        }
        return new ResponseEntity<String>(res + "]", HttpStatus.OK);
    }

    @PutMapping("PUT/restaurants/{id}/dishes/{dishId}")
    public ResponseEntity<Dish> UpdateDish(@PathVariable int id, @RequestBody JSONObject changes, @PathVariable int dishId) {
        String name = (String) changes.get("name");
        String descpition = (String) changes.get("description");
        String price = (String) changes.get("price");
        Restaurant restaurant = restaurantService.getById(id);
        Dish dish = dishService.getByDish_idandRestaurant(restaurant, id);
        if (name != null){
            dish.setName(name);
        }
        if(descpition != null){
            dish.setDishDescription(descpition);
        }
        if (price != null){
            dish.setDishPrice(Integer.valueOf(price));
        }
        dishService.save(dish);
        return new ResponseEntity<Dish>(dish, HttpStatus.OK);
    }
    
    @DeleteMapping("DELETE/restaurants/{id}/dishes/{dishId}")
    public ResponseEntity<String> DeleteDish(@PathVariable int id, @PathVariable int dishId){
        Restaurant restaurant = restaurantService.getById(id);
        Dish dish = dishService.getByDish_idandRestaurant(restaurant, dishId);
        if (restaurant != null && dish != null){
            restaurant.DeletDish(dish);
            dishService.DeleteDish(dish);
        }
        return new ResponseEntity<String>("dish deleted!", HttpStatus.NO_CONTENT);
        
    }
    
}
