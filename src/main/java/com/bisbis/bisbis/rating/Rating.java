package com.bisbis.bisbis.rating;

import com.bisbis.bisbis.restaurant.Restaurant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "ratings")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rate_id")
    int rate_id;

    @Column(name = "rating")
    private float rating;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", insertable=false, updatable=false)
    private Restaurant restaurant;

    //private int restaurant_id;

    public Rating(Restaurant restaurant, float rating){
        this.rating = rating;
        this.restaurant = restaurant;
        //this.restaurant_id = restaurant.get_id();
    }

    public Rating(){
        this.rating = 0f;
    }

    public int getRestaurantId() {
        return this.restaurant.get_id();
    }

    public Float getRate(){
        return this.rating;
    }

    public Restaurant getRestaurant() {
        return this.restaurant;
    }

}
