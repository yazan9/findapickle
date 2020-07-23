package com.findapickle.backend.controllers;

import com.findapickle.backend.models.dto.ShoppingList;
import com.findapickle.backend.services.ShoppingListsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/shoppingLists")
public class ShoppingListsController {

    @Autowired
    private ShoppingListsService shoppingListsService;

    @GetMapping(value="/{id}")
    public ShoppingList getById(@RequestHeader("Authorization") String token, @PathVariable Long id)
    {
        return shoppingListsService.findById(token, id);
    }

    @PostMapping(value="/create")
    public ShoppingList save(@RequestHeader("Authorization") String token, @RequestBody ShoppingList shoppingList){
        return shoppingListsService.save(token, shoppingList);
    }   

    @PutMapping(value="/edit")
    public ShoppingList update(@RequestHeader("Authorization") String token, @RequestBody ShoppingList shoppingList){
        return shoppingListsService.update(token, shoppingList);
    }

    @DeleteMapping(value="/delete/{id}")
    public ResponseEntity<?> delete(@RequestHeader("Authorization") String token, @PathVariable long id){
        shoppingListsService.delete(token, id);
        return ResponseEntity.ok().build();
    }
}