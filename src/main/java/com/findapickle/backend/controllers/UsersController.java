package com.findapickle.backend.controllers;

import com.findapickle.backend.models.dto.UserDTO;
import com.findapickle.backend.services.UsersService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/users")
public class UsersController {
    @Autowired private UsersService usersService;

    @GetMapping(value="/{id}")
    public UserDTO getUserById(@PathVariable Long id)
    {
        return usersService.findById(id);
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<?> update(@RequestBody String user){
        return usersService.update(user);
    }
}