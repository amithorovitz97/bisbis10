package com.bisbis.bisbis.cuisine;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Iterator;

@Service
@Validated
public class CuisineService {

    @Autowired
    private CuisineRepository cuisineRepository;

    public void save(Cuisine currCuisine) {
        //if (cuisineRepository.findByName(currCuisine.get_cuisine_name()) == null){
            cuisineRepository.save(currCuisine);
        //}
    }

    public List<Cuisine> getAllCuisines(){
        return (List<Cuisine>) cuisineRepository.findAll();
    }

    public void saveAll(List<Cuisine> items) {
        Iterator<Cuisine> iter = items.iterator();
        while (iter.hasNext()){
            cuisineRepository.save(iter.next());
        }
    }

    public Cuisine findByName(String cuisine_name) {
        return cuisineRepository.findByName(cuisine_name);
    }
    
}
