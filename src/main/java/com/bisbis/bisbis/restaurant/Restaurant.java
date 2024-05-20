package com.bisbis.bisbis.restaurant;

import java.util.ArrayList;
import java.util.List;


import java.util.Iterator;

import com.bisbis.bisbis.cuisine.Cuisine;
import com.bisbis.bisbis.dish.Dish;
import com.bisbis.bisbis.order.Order;
import com.bisbis.bisbis.rating.Rating;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Id;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Restaurants")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "restaurant_id")
    private int restaurant_id;

    @Column(name = "name")
    private String name;

    @Column(name = "isKosher")
    private boolean kosher;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "Restaurants_to_cuisine", 
        joinColumns = @JoinColumn(name = "restaurant_id"), 
        inverseJoinColumns = @JoinColumn(name = "cuisine_id"))
    private List<Cuisine> cuisines;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Dish> dishes;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Order> orders;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rating> ratings;

    public Restaurant(String name, boolean kosher, List<Cuisine> cuisines) {
        this.name = name;
        this.kosher = kosher;
        this.cuisines = cuisines;
        this.ratings = new ArrayList<>();
        this.orders = new ArrayList<>();
        this.dishes = new ArrayList<>();

    }

    public Restaurant(){
        this.name = "noName";
        this.kosher = true;
        this.ratings = new ArrayList<>();
        this.orders = new ArrayList<>();
        this.dishes = new ArrayList<>();
        this.cuisines = new ArrayList<>();
        
    }

    public Restaurant(String name, boolean kosher) {
        this.name = name;
        this.kosher = kosher;
        this.cuisines = new ArrayList<>();
        this.ratings = new ArrayList<>();
        this.orders = new ArrayList<>();
        this.dishes = new ArrayList<>();
    }

    public Restaurant(Restaurant restaurant) {
        this.name = restaurant.get_name();
        this.kosher = restaurant.isKosher();
        this.cuisines = restaurant.getCuisines();
        this.dishes = restaurant.getDishes();
        this.orders = restaurant.getOrders();
        this.ratings = restaurant.getRatings();
    }

    private List<Rating> getRatings() {
        return this.ratings;
    }

    private List<Order> getOrders() {
        return this.orders;
    }

    private void setOrders(List<Order> newOrders) {
        this.orders = newOrders;
    }

    public boolean isKosher() {
        return this.kosher;
    }

    public List<Cuisine> getCuisines() {
        return this.cuisines;
    }

    public void setCuisines(List<Cuisine> cuisine_set) {
        this.cuisines = cuisine_set;
    }

    public Restaurant get() {
        return this;
    }

    public int get_id() {
        return this.restaurant_id;
    }

    public String get_name() {
        return this.name;
    }

    public List<Dish> getDishes() {
        return this.dishes;
    }

    public void addDish(Dish dish) {
        this.dishes.add(dish);
    }

    public void DeletDish(Dish dish) {
        this.dishes.remove(dish);
    }

    public void DeletDish(int dish_id) {
        Iterator<Dish> iter = dishes.iterator();
        while (iter.hasNext()) {
            Dish dish = iter.next();
            if (dish.getDishId() == dish_id) {
                dishes.remove(dish);
                break;
            }
        }
    }

    public void addRating(Rating rating) {
        this.ratings.add(rating);
    }

    public Float getAvgRate() {
        List<Rating> ratings = this.ratings;
        if (ratings.size() == 0){
            return 0f;
        }
        Iterator<Rating> iter = ratings.iterator();
        int size = ratings.size();
        Float avg = 0f;
        while (iter.hasNext()) {
            avg += iter.next().getRate();
        }
        return avg / (float) size;
    }

    public void addCuisine(Cuisine cuisine) {
        this.cuisines.add(cuisine);
    }

    @Override
    public String toString(){
        Float rating = this.getAvgRate();
        String res =  "{id: " + String.valueOf(this.get_id()) + ", name: " + this.get_name() 
                +" avarageRating: " + String.valueOf(rating) +  " ,isKosher: " + this.kosher + " cuisines: [";
        if (this.cuisines.size() >0){
            Iterator<Cuisine> iter = this.cuisines.iterator();
                while (iter.hasNext()){
            res = res + iter.next().toString() + ", ";
        }
    }
        if (this.dishes.size() > 0){
        res = res + "], dishes: [";
        Iterator<Dish> iter1 = this.dishes.iterator();
        while (iter1.hasNext()){
            res = res + iter1.next().toString() + ", ";
        }
    }
        return res + " ]}";
   }

    public void setKosher(boolean kosherString) {
        this.kosher = kosherString;
    }

    public void changeName(String name2) {
        this.name = name2;
    }

    public void addOrder(Order orderNew) {
        List<Order> orders = this.getOrders();
        orders.add(orderNew);
        this.setOrders(orders);
    }

}