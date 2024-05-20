package com.bisbis.bisbis.dish;

import com.bisbis.bisbis.restaurant.Restaurant;

public class DishNotFound extends RuntimeException {
    
    public DishNotFound(int id)
	{
		super("Dish id not found : " + id);
	}

    public DishNotFound(String name)
	{
		super("Dish " + name + " not found");
	}

	public DishNotFound(int restaurant_id, int dish_id) {
        super("Dish id " + String.valueOf(dish_id) + " was not dound in restaurant id " + String.valueOf(restaurant_id) );
	}

    public DishNotFound(Restaurant restaurant) {
        super("No dishes were found for restaurant " + restaurant.get_name());
    }

    @Override
	public String toString() {
		return "DishNotFound []";
	}
}
