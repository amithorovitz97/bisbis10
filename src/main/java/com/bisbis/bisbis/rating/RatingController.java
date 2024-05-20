package com.bisbis.bisbis.rating;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import com.bisbis.bisbis.restaurant.Restaurant;
import com.bisbis.bisbis.restaurant.RestaurantService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@Validated
@RequestMapping("")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @Autowired
    private RestaurantService restaurantService;

    @PostMapping("POST/ratings")
    public ResponseEntity<String> addRating(@RequestBody JSONObject rating) {
        int restaurant_id = (Integer) rating.get("restaurantId");
        Float rate = (Float) rating.get("rating");
        Restaurant restaurant = restaurantService.getById(restaurant_id);
        Rating ratingNew= new Rating(restaurant, rate);
        restaurant.addRating(ratingNew);
        ratingService.save(ratingNew); 
        return new ResponseEntity<String>("Rating created!", HttpStatus.OK);
    }
 
}
