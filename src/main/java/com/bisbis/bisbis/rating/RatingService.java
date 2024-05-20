package com.bisbis.bisbis.rating;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.bisbis.bisbis.restaurant.Restaurant;


@Service
@Validated
public class RatingService {

    @Autowired
    private RatingRepository ratingRepository;


    public Rating save(Rating rating){
        return ratingRepository.save(rating);
    }


    public List<Rating> getByRestaurant(Restaurant restaurant) {
        return ratingRepository.findByRestaurant(restaurant);
    }


    public void delete(Rating rating) {
        ratingRepository.delete(rating);
    }

}
