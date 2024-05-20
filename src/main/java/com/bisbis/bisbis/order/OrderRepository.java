package com.bisbis.bisbis.order;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bisbis.bisbis.restaurant.Restaurant;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>{

    Order save(Order order);
    void delete(Order order);

    List<Order> findByRestaurant(Restaurant restaurant);

}
