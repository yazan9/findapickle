package com.findapickle.backend.controllers;

import java.util.List;

import com.findapickle.backend.models.dto.Location;
import com.findapickle.backend.services.LocationsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/locations")
public class LocationsController {

    @Autowired
    private LocationsService locationsService;

    @GetMapping(value="/{id}")
    public Location getLocationById(@PathVariable Long id)
    {
        return locationsService.findById(id);
    }
    
    @GetMapping(value="/store/{id}")
    public List<Location> getStoreLocations(@PathVariable Long id)
    {
        return locationsService.getStoreLocations(id);
    }    

}