package com.findapickle.backend.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import com.findapickle.backend.entities.UserEntity;
import com.findapickle.backend.exceptions.ForbiddenException;
import com.findapickle.backend.exceptions.InternalServerErrorException;
import com.findapickle.backend.exceptions.NotFoundException;
import com.findapickle.backend.models.dto.User;
import com.findapickle.backend.repositories.UsersRepository;

import com.findapickle.backend.security.JWTTokenUtil;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private UsersService usersService;

    @Autowired
    private JWTTokenUtil jwtTokenUtil;

    @Autowired
    @Qualifier("UsersMapper")
    private ModelMapper usersMapper;

    private final Logger logger = LoggerFactory.getLogger(AdminService.class);

    public boolean isAdmin(String token){
        String email = jwtTokenUtil.getEmailFromToken(token);
        UserEntity user = usersRepository.findByEmail(email).orElse(null);

        if(user == null)
            return false;

        return user.isAdmin();
    }

    public List<User> getAllUsers(String token) {
        if(!isAdmin(token))
            throw new ForbiddenException();
        List<UserEntity> users = usersRepository.findAll();
        return usersMapper.map(users, new TypeToken<List<User>>() {}.getType());
    }

    public void deleteUser(UserEntity user){
        try{
            this.usersRepository.delete(user);
        }
        catch(EntityNotFoundException e){
            throw e;
        }
        catch(Exception e){
            logger.error(e.getMessage());
            throw new InternalServerErrorException();
        }
    } 

    public void deleteUserById(String token, Long id){
        if(!isAdmin(token))
            throw new ForbiddenException();
        try{
            UserEntity foundUser = this.usersRepository.findById(id).orElseThrow(NotFoundException::new);
            this.usersRepository.delete(foundUser);
        }
        catch(NotFoundException e){
            throw e;
        }
        catch(Exception e){
            logger.error(e.getMessage());
            throw new InternalServerErrorException();
        }
    }
}