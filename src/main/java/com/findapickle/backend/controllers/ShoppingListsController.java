package com.findapickle.backend.controllers;

import com.findapickle.backend.models.dto.ShoppingListDTO;
import com.findapickle.backend.services.ShoppingListsService;

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
@RequestMapping(path = "/shoppingLists")
public class ShoppingListsController {

    @Autowired
    private ShoppingListsService shoppingListsService;

    @GetMapping(value="/{id}")
    public ShoppingListDTO getById(@PathVariable Long id)
    {
        return shoppingListsService.findById(id);
    }

    @PostMapping(value="/add")
    public ResponseEntity<?> save(@RequestBody String shoppingList){
        return shoppingListsService.save(shoppingList);
    }   

    @PutMapping(value="/edit")
    public ResponseEntity<?> update(@RequestBody String shoppingList){
        return shoppingListsService.update(shoppingList);
    }

    @DeleteMapping(value="/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable long id){
        return shoppingListsService.delete(id);
    }
}