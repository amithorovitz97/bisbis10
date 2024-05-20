package com.bisbis.bisbis.dish;

import java.util.List;

import com.bisbis.bisbis.order_item.Order_item;
import com.bisbis.bisbis.restaurant.*;
import jakarta.persistence.CascadeType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Dishes")
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dish_id")
    private int dishid;

    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private int price;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", insertable=false, updatable=false)
    private Restaurant restaurant;

    @OneToMany(mappedBy = "dish", cascade = CascadeType.ALL)
    private List<Order_item> order_items;

    public Dish(String name, String descrpition, int price, Restaurant restaurant){
        this.description = descrpition;
        this.name = name;
        this.restaurant = restaurant;
        this.price = price;
    }

    public Dish(){
        this.description = null;
        this.name = null;
        this.price = 0;
    }

    public String getDishName(){
        return this.name;
    }

    public int getDishId(){
        return this.dishid;
    }

    public String getDishDescription(){
        return this.description;
    }

    public int getDishPrice(){
        return this.price;
    }

    public void setDishDescription(String dishDescription) {
        this.description = dishDescription;
    }

    public void setDishPrice(int dishPrice) {
        this.price = dishPrice;
    }

    @Override
    public String toString(){
        return  "{ id: " + String.valueOf(this.dishid) + ", name: " + this.name + " description: " + this.description
                + " price: " + String.valueOf(this.price) + "}";
    }

    public void setName(String name2) {
        this.name = name2;
    }

}
