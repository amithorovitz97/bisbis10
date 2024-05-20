package com.bisbis.bisbis.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bisbis.bisbis.order_item.OrderItemRepository;
import com.bisbis.bisbis.order_item.Order_item;
import com.bisbis.bisbis.restaurant.Restaurant;
import com.bisbis.bisbis.restaurant.RestaurantController;
import com.bisbis.bisbis.restaurant.RestaurantService;

@RestController
@RequestMapping("")
@Validated
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    RestaurantController restaurantController;

    @Autowired
    OrderItemRepository itemRepository;

    @Autowired
    RestaurantService restaurantService;

    @PostMapping("POST/order")
    public ResponseEntity<String> Order(@RequestParam int restaurant_id,@RequestParam List<Order_item> items) {
        Restaurant restaurant = restaurantService.getById(restaurant_id);
        Order orderNew = new Order(restaurant);
        restaurant.addOrder(orderNew);
        orderService.saveItems(items);
        orderService.save(orderNew);
        return new ResponseEntity<String>(String.valueOf(orderNew.get_order_id()), HttpStatus.OK);
    }  
}
