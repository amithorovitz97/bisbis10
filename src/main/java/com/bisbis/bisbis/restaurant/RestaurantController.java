package com.bisbis.bisbis.restaurant;

import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import com.bisbis.bisbis.cuisine.*;
import com.bisbis.bisbis.order.*;
import com.bisbis.bisbis.dish.*;
import com.bisbis.bisbis.rating.*;

import org.json.simple.*;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@Validated
@RequestMapping("")
public class RestaurantController {

    @Autowired
    CuisineService cuisineService;

    @Autowired
    RestaurantService restaurantService;

    @Autowired
    OrderService orderService;

    @Autowired
    DishService dishService;

    @Autowired
    RatingService ratingService;

    @PostMapping("POST/restaurants")
    public ResponseEntity<String> AddRestaturant(@RequestBody JSONObject restaurant) {
        String name = (String) restaurant.get("name");
        boolean kosher = (boolean) restaurant.get("isKosher");
        List<String> cuisinelist = (List<String>) restaurant.get("cuisines");
        if(restaurantService.getByName(name) != null){
            return new ResponseEntity<>("", HttpStatus.NO_CONTENT);
        }
        List<Cuisine> Cuisines = new ArrayList<>();
        if (!cuisinelist.isEmpty()){
            Iterator<String> iter = cuisinelist.iterator();
            while(iter.hasNext()){
                String cuisineName = iter.next();
                Cuisine cuisine = cuisineService.findByName(cuisineName);
                if (cuisine == null){
                    Cuisine cuisine2 = new Cuisine(cuisineName);
                    cuisineService.save(cuisine2); 
                    Cuisines.add(cuisine2);
                    }
                else{
                    Cuisines.add(cuisine);
                    } 
                }
            }
        Restaurant res = new Restaurant(name, kosher, Cuisines);
        restaurantService.save(res);
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }

    @GetMapping("GET/restaurants/{id}")
    public ResponseEntity<Restaurant> GetRestaurant(@PathVariable int id) {
        Restaurant restaurant = restaurantService.getById(id);
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    @GetMapping("GET/restaurants")
    public ResponseEntity<List<Restaurant>> GetRestaurants() {
        List<Restaurant> restaurants = restaurantService.findAll();
        return new ResponseEntity<>( restaurants,HttpStatus.OK);
    }

    @GetMapping("GET/restaurants?cuisine={cuisine}")
    public ResponseEntity<List<Restaurant>> GetRestaurantsByCuisine(@RequestParam("cuisine") String cuisine_name) {
        Cuisine cuisine = cuisineService.findByName(cuisine_name);
        if (cuisine != null){
            List<Restaurant> restaurants = restaurantService.GetByCuisine(cuisine);
            if (restaurants != null){
                return new ResponseEntity<>(restaurants, HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
            }
        }
        else{
            return new ResponseEntity<>(new ArrayList<>(),HttpStatus.OK);
        }
    }


    @PutMapping("PUT/restaurants/{id}")
    public ResponseEntity<String> UpdateRestaurant(@PathVariable int id, @RequestBody JSONObject updates) {
        List<String> cuisines = (List<String>) updates.get("cuisines");
        String name = (String) updates.get("name");
        Restaurant restaurant = restaurantService.getById(id);
        if (restaurant != null){
            if (updates.containsKey("isKosher")){
                boolean kosher = (boolean) updates.get("isKosher");
                restaurant.setKosher(kosher);
            }
            if(cuisines.size() > 0) {
                Iterator<String> iter = cuisines.iterator();
                while(iter.hasNext()){
                    String cuisineName = iter.next();
                    Cuisine cuisine = cuisineService.findByName(cuisineName);
                    if (cuisine == null){
                        Cuisine cuisine2 = new Cuisine(cuisineName);
                        cuisineService.save(cuisine2); 
                        restaurantService.assignCuisine(restaurant, cuisine2);
                    }
                    else{
                        restaurantService.assignCuisine(restaurant, cuisine);
                    } 
                }
            }
            if (name != null){
                restaurant.changeName(name);
            }
            restaurantService.save(restaurant);     
        }
        return new ResponseEntity<>("", HttpStatus.OK);
    }

    @DeleteMapping("DELETE/restaurants/{id}")
    public ResponseEntity<String> DeleteRestaurant(@PathVariable int id){
        Restaurant restaurant = restaurantService.getById(id);
        List<Order> orders = orderService.getOrdersByRestaurant(restaurant);
        List<Dish> dishes = dishService.GetRestaurantDishes(restaurant);
        List<Rating> ratings = ratingService.getByRestaurant(restaurant);
        if (orders != null){
            Iterator<Order> iter = orders.iterator();
            while(iter.hasNext()){
                orderService.delete(iter.next());
            }
        }
        if (dishes != null){
            Iterator<Dish> iterdish = dishes.iterator();
            while(iterdish.hasNext()){
                dishService.DeleteDish(iterdish.next());
            }
        }
        if (ratings != null){
            Iterator<Rating> iterrate = ratings.iterator();
            while(iterrate.hasNext()){
                ratingService.delete(iterrate.next());
            }
        }
        return new ResponseEntity<>("", HttpStatus.NO_CONTENT);
    }
    
}
