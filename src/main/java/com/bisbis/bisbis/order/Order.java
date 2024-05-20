package com.bisbis.bisbis.order;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.bisbis.bisbis.order_item.Order_item;
import com.bisbis.bisbis.restaurant.Restaurant;

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
@Table(name  = "Orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", insertable = false, updatable = false)
    private int order_id;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", insertable=false, updatable=false)
    private Restaurant restaurant;


    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order_item> items;


    public Order(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Order(){
        this.restaurant = null;
        items = new ArrayList<Order_item>();
    }

    public int get_order_id(){
        return this.order_id;
    }

    public int get_restaurant_id(){
        return this.restaurant.get_id();
    }

    public Restaurant getRestaurant(){
        return this.restaurant;
    }

    public List<Order_item> getItems(){
        return this.items;
    }

    @Override
    public String toString(){
        Iterator<Order_item> iter = items.iterator();
        String res = "{ restaurantId: " + String.valueOf(this.get_restaurant_id()) + ", orderItems: [";
        while (iter.hasNext()){
            res = res + iter.next().toString();
        }
        return res + " ]}";
    }
}
