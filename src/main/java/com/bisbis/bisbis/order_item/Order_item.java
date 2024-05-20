package com.bisbis.bisbis.order_item;

import com.bisbis.bisbis.dish.Dish;
import com.bisbis.bisbis.order.Order;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Order_items")
public class Order_item {
    //@Autowired
    //OrderItemRepository repository;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "key") 
    private int key;

    @ManyToOne
    @JoinColumn(name = "dish_id", insertable=false, updatable=false)
    private Dish dish;

    private int dish_id;

    @Column(name = "amount")
    private int amount;

    @ManyToOne
    @JoinColumn(name = "order_id", insertable=false, updatable=false)
    private Order order;



    public Order_item(Dish dish, int amount, Order order){
        //this.dish_id = dish_id;
        this.dish = dish;
        this.amount = amount;
        this.order = order;
    }


    public void set_Amount(int amount){
        this.amount = amount;
    }

    public void set_Dish(Dish dish){
        this.dish = dish;
    }

    public int get_Amount(){
        return this.amount;
    }

    public int get_dish_id(){
        return this.dish.getDishId();
    }

    public int get_order_id(){
        return this.order.get_order_id();
    }

    @Override
    public String toString(){
        return "{dishId: " + String.valueOf(this.get_dish_id()) + " , amount: " + String.valueOf(this.amount)
                + "}"; 
    }

    }

    

