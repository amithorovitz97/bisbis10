package com.bisbis.bisbis.order_item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bisbis.bisbis.dish.*;

@Repository
public interface OrderItemRepository extends JpaRepository<Dish, Integer>{

    void save(Order_item item);


}
