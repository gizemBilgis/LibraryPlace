package com.example.libraryplaces.controller;

import com.example.libraryplaces.model.Places;
import com.example.libraryplaces.repository.PlaceRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Place")
public class PlacesController {

    private final  PlaceRepository placeRepository;
    public PlacesController(PlaceRepository placeRepository){
        this.placeRepository = placeRepository;
    }
    @GetMapping("/all")
    public ResponseEntity<List<Places>> getAllPlace(){
        List<Places>  places= placeRepository.findAll();
        System.out.printf("Places: %s\n",places);
        return new ResponseEntity<>(places, HttpStatus.OK);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<Places> getCarById(@PathVariable("id") Long id){
        Optional<Places> place = placeRepository.findById(id);
        if(place.isPresent()) {
            return new ResponseEntity<>(place.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping("/add")
    public ResponseEntity<Places> addPlace(@RequestBody Places place){
        return new ResponseEntity<>(placeRepository.save(place), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Places> deleteCar(@PathVariable("id") Long id){
        Optional<Places> place = placeRepository.findById(id);
        if(place.isPresent()) {
            placeRepository.delete(place.get());
            return new ResponseEntity<>(place.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
