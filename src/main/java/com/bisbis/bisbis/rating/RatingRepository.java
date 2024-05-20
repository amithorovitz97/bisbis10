package com.bisbis.bisbis.rating;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bisbis.bisbis.restaurant.Restaurant;

import java.util.List;


@Repository
public interface RatingRepository extends JpaRepository<Rating, Integer>{

    Rating save(Rating rating);

    List<Rating> findByRestaurant(Restaurant restaurant);

    void delete(Rating rating);

    //@Query("SELECT AVARAGE(rating) AS res From Restaurants_rating WHERE restaurant_id = #{res_id} GROUP BY restaurant_id")
    //Optional <float> getRestaurantRating(@param("res_id") int restaurant_id);
}
