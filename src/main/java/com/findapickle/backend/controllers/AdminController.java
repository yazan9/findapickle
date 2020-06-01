package com.findapickle.backend.controllers;

import java.util.List;

import com.findapickle.backend.models.dto.ShoppingListDTO;
import com.findapickle.backend.models.dto.UserDTO;
import com.findapickle.backend.services.AdminService;
import com.findapickle.backend.services.LocationsService;
import com.findapickle.backend.services.ShoppingListsService;
import com.findapickle.backend.services.StoresService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/admin")
public class AdminController {

    @Autowired private AdminService adminService;
    @Autowired private StoresService storesService;
    @Autowired private LocationsService locationsService;
    @Autowired private ShoppingListsService shoppingListsService;

    //USERS
    @GetMapping(value="/users/all")
    public List<UserDTO> getAllUsers()
    {
        return adminService.getAllUsers();
    }

    @DeleteMapping(value="/users/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable long id){
        return adminService.deleteById(id);
    }

    //STORES
    @PostMapping(value="/stores/add")
    public ResponseEntity<?> addStore(@RequestBody String store){
        return storesService.save(store);
    }   

    @PutMapping(value="/stores/edit")
    public ResponseEntity<?> updateStore(@RequestBody String store){
        return storesService.update(store);
    }

    @DeleteMapping(value="/stores/delete/{id}")
    public ResponseEntity<?> deleteStore(@PathVariable long id){
        return storesService.delete(id);
    }

    //LOCATIONS
    @PostMapping(value="/locations/add")
    public ResponseEntity<?> addLocation(@RequestBody String location){
        return locationsService.save(location);
    }   

    @PutMapping(value="/locations/edit")
    public ResponseEntity<?> updateLocation(@RequestBody String location){
        return locationsService.update(location);
    }

    @DeleteMapping(value="/locations/delete/{id}")
    public ResponseEntity<?> deleteLocation(@PathVariable long id){
        return locationsService.delete(id);
    }

    //SHOPPING LISTS
    @GetMapping(value="/shoppingLists")
    public List<ShoppingListDTO> getAllShoppingLists()
    {
        return shoppingListsService.getAllShoppingLists();
    }    
}