package com.findapickle.backend.controllers;

import com.findapickle.backend.models.dto.User;
import com.findapickle.backend.services.UsersService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/users")
public class UsersController {
    @Autowired private UsersService usersService;

    @GetMapping(value="/{id}")
    public User getUserById(@RequestHeader("Authorization") String token, @PathVariable Long id)
    {
        return usersService.findById(token, id);
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<?> update(@RequestHeader("Authorization") String token, @RequestBody User user){
        return usersService.update(token, user);
    }
}