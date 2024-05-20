package com.bisbis.bisbis.restaurant;

import com.bisbis.bisbis.cuisine.Cuisine;

public class RestaurantNotFound extends RuntimeException{

    public RestaurantNotFound(int id)
	{
		super("Restaurant id not found : " + id);
	}

    public RestaurantNotFound(String name)
	{
		super("Restaurant " + name + " not found");
	}

	public RestaurantNotFound() {
        super("No restaurants were found");
    }

    public RestaurantNotFound(Cuisine cuisine) {
        super("No restaurants in cuisine " + cuisine.get_cuisine_name());
    }

    @Override
	public String toString() {
		return "RestaurantNotFound";
	}

}
