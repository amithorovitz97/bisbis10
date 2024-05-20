package com.bisbis.bisbis.cuisine;
import java.util.ArrayList;
import java.util.List;

import com.bisbis.bisbis.restaurant.*;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Cuisines")
public class Cuisine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cuisine_id;

    private String name;


    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
    @JoinTable(name = "restaurants_to_cuisine", joinColumns = @JoinColumn(name = "cuisine_id"), 
        inverseJoinColumns = @JoinColumn(name = "restaurant_id"))
    private List<Restaurant> restaurants = new ArrayList<>();

   
    public Cuisine(String cname){
        this.name = cname;
    }

    public Cuisine(){
        this.name = null;
    }

    public Cuisine(Cuisine currCuisine) {
        this.name = currCuisine.get_cuisine_name();
    }

    public String get_cuisine_name(){
        return this.name;
    }

    public Cuisine get() {
        return this;
    }

    public int get_id() {
        return this.cuisine_id;
    }

    public List<Restaurant> getRestaurantSet(){
        return this.restaurants;
    }

    public void addRestaurantToSet(Restaurant restaurant){
        this.restaurants.add(restaurant);
    }

    @Override
    public String toString(){
        return " cuisine name : " + this.get_cuisine_name(); 
    }

}


