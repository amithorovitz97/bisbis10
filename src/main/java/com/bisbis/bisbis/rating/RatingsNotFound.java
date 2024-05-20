package com.bisbis.bisbis.rating;

public class RatingsNotFound extends RuntimeException{

    public RatingsNotFound(int restaurant_id){
        super("Ratings were not found for restaurant id " + String.valueOf(restaurant_id));
    }
    
}
