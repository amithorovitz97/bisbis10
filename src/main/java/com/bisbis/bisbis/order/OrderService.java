package com.bisbis.bisbis.order;

import  java.util.List;
import  java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.bisbis.bisbis.order_item.*;
import com.bisbis.bisbis.restaurant.Restaurant;


@Service
@Validated
public class OrderService {

    @Autowired
    OrderRepository orderRepository;


    @Autowired
    private OrderItemRepository itemRepository;

    public Order save(Order order) {
        return orderRepository.save(order);
    }

    public void saveItem(Order_item item) {
        itemRepository.save(item);
    }

    public void saveItems(List<Order_item> items){
        Iterator<Order_item> iter = items.iterator();
        while(iter.hasNext()){
            itemRepository.save(iter.next());
        }
    }


    public void delete(Order order) {
        //List<Order_item> items = order.getItems();
        //if(items != null){
        //    Iterator<Order_item> iter = items.iterator();
        //    while(iter.hasNext()){
        //        itemRepository.delete(iter.next());
        //    }
       // }
        orderRepository.delete(order);;
    }

    public List<Order> getOrdersByRestaurant(Restaurant restaurant) {
        return orderRepository.findByRestaurant(restaurant);
    }
    
}
