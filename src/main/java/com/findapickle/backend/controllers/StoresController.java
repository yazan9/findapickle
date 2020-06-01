package com.findapickle.backend.controllers;

import java.util.List;

import com.findapickle.backend.models.dto.StoreDTO;
import com.findapickle.backend.services.StoresService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/stores")
public class StoresController {
    
    @Autowired private StoresService storesService;

    @GetMapping(value="/{id}")
    public StoreDTO getStoreById(@PathVariable Long id)
    {
        return storesService.findById(id);
    }
    
    @GetMapping(value="/")
    public List<StoreDTO> getAllStores()
    {
        return storesService.getAllStores();
    }    
}