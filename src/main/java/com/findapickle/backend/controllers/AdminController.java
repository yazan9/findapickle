package com.findapickle.backend.controllers;

import java.util.List;

import com.findapickle.backend.models.dto.Location;
import com.findapickle.backend.models.dto.ShoppingList;
import com.findapickle.backend.models.dto.Store;
import com.findapickle.backend.models.dto.User;
import com.findapickle.backend.services.AdminService;
import com.findapickle.backend.services.LocationsService;
import com.findapickle.backend.services.ShoppingListsService;
import com.findapickle.backend.services.StoresService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/admin")
public class AdminController {

    @Autowired private AdminService adminService;
    @Autowired private StoresService storesService;
    @Autowired private LocationsService locationsService;
    @Autowired private ShoppingListsService shoppingListsService;

    //USERS
    @GetMapping(value="/users/all")
    public List<User> getAllUsers(@RequestHeader("Authorization") String token)
    {
        return adminService.getAllUsers(token);
    }

    @DeleteMapping(value="/users/delete/{id}")
    public ResponseEntity<?> deleteUser(@RequestHeader("Authorization") String token, @PathVariable long id){
        adminService.deleteUserById(token, id);
        return ResponseEntity.ok().build();
    }

    //STORES
    @PostMapping(value="/stores/add")
    public ResponseEntity<?> addStore(@RequestHeader("Authorization") String token, @RequestBody Store store){
        return storesService.save(token, store);
    }   

    @PutMapping(value="/stores/edit")
    public ResponseEntity<?> updateStore(@RequestHeader("Authorization") String token, @RequestBody Store store){
        return storesService.update(token, store);
    }

    @DeleteMapping(value="/stores/delete/{id}")
    public ResponseEntity<?> deleteStore(@RequestHeader("Authorization") String token, @PathVariable long id){
        return storesService.delete(token, id);
    }

    //LOCATIONS
    @PostMapping(value="/locations/add")
    public Location addLocation(@RequestHeader("Authorization") String token, @RequestBody Location location){
        return locationsService.save(token, location);
    }   

    @PutMapping(value="/locations/edit")
    public Location updateLocation(@RequestHeader("Authorization") String token, @RequestBody Location location){
        return locationsService.update(token, location);
    }

    @DeleteMapping(value="/locations/delete/{id}")
    public ResponseEntity<?> deleteLocation(@RequestHeader("Authorization") String token, @PathVariable long id){
        locationsService.delete(token, id);
        return ResponseEntity.ok().build();
    }

    //SHOPPING LISTS
    @GetMapping(value="/shoppingLists")
    public List<ShoppingList> getAllShoppingLists(@RequestHeader("Authorization") String token)
    {
        return shoppingListsService.getAllShoppingLists(token);
    }    
}