package com.findapickle.backend.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import com.findapickle.backend.entities.User;
import com.findapickle.backend.exceptions.InternalServerErrorException;
import com.findapickle.backend.exceptions.NotFoundException;
import com.findapickle.backend.models.dto.UserDTO;
import com.findapickle.backend.repositories.UsersRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private UsersService usersService;

    public List<UserDTO> getAllUsers() {
        List<User> users = usersRepository.findAll();
        return users.stream().map(usersService::UserEntityToDto).collect(Collectors.toList());        
    }

    public ResponseEntity<?> delete(User user){
        try{
            this.usersRepository.delete(user);
            return ResponseEntity.ok().build();
        }
        catch(EntityNotFoundException e){
            throw e;
        }
        catch(Exception e){
            throw new InternalServerErrorException();
        }
    } 

    public ResponseEntity<?> deleteById(Long id){
        try{
            User foundUser = this.usersRepository.findById(id).orElseThrow(() -> new NotFoundException());
            this.usersRepository.delete(foundUser);
            return ResponseEntity.ok().build();
        }
        catch(NotFoundException e){
            throw e;
        }
        catch(Exception e){
            throw new InternalServerErrorException();
        }
    }

    
}