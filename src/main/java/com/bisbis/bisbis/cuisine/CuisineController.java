package com.bisbis.bisbis.cuisine;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("")
public class CuisineController {

    @Autowired
    private CuisineService cuisineService;

    @PostMapping("path")
    public void AddCuisine(@RequestBody List<Cuisine> cuisine) {
        Iterator<Cuisine> iter = cuisine.iterator();
        while (iter.hasNext()){
            Cuisine currCuisine = iter.next();
            Cuisine cuisineNew = new Cuisine(currCuisine);
            cuisineService.save(cuisineNew); 
        }
    }
    
    
}
