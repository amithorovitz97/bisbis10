package com.bisbis.bisbis.order;

public class OrderInfoIsIncorrect extends RuntimeException{

    public OrderInfoIsIncorrect(){
        super("Order information is Incorrect");
    }

    @Override
    public String toString(){
        return "Order Information is Incorrect";
    }
    
}
