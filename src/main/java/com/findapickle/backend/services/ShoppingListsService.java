package com.findapickle.backend.services;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.findapickle.backend.entities.ShoppingListEntity;
import com.findapickle.backend.entities.UserEntity;
import com.findapickle.backend.exceptions.ForbiddenException;
import com.findapickle.backend.exceptions.InternalServerErrorException;
import com.findapickle.backend.exceptions.NotFoundException;
import com.findapickle.backend.models.dto.ShoppingList;
import com.findapickle.backend.repositories.ShoppingListsRepository;

import com.findapickle.backend.repositories.UsersRepository;
import com.findapickle.backend.security.JWTTokenUtil;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ShoppingListsService {
    @Autowired
    private ShoppingListsRepository shoppingListsRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AdminService adminService;

    @Autowired
    private AuthService authService;

    @Autowired
    private JWTTokenUtil jwtTokenUtil;

    @Autowired
    private UsersRepository usersRepository;

    private final Logger logger = LoggerFactory.getLogger(ShoppingListsService.class);

    public List<ShoppingList> getAllShoppingLists(String token) {
        if(!adminService.isAdmin(token))
            throw new ForbiddenException();
        List<ShoppingListEntity> shoppingLists = shoppingListsRepository.findAll();
        return Collections.singletonList(modelMapper.map(shoppingLists, ShoppingList.class));
    }

    public ShoppingList findById(String token, Long id){
        ShoppingListEntity shoppingList = shoppingListsRepository.findById(id).orElseThrow(NotFoundException::new);
        if(!authService.shoppingListBelongsToUser(token, shoppingList))
            throw new ForbiddenException();
        return modelMapper.map(shoppingList, ShoppingList.class);
    }

    public List<ShoppingList> findMyLists(String token){
        UserEntity user = usersRepository.findByEmail(jwtTokenUtil.getEmailFromToken(token)).orElseThrow(ForbiddenException::new);
        List<ShoppingListEntity> myLists = shoppingListsRepository.findByUserId(user.getId()).orElseThrow(NotFoundException::new);
        return Collections.singletonList(modelMapper.map(myLists, ShoppingList.class));
    }

    public ShoppingList save(String token, ShoppingList shoppingList) {
        try {
            UserEntity user = usersRepository.findByEmail(jwtTokenUtil.getEmailFromToken(token)).orElseThrow(ForbiddenException::new);
            shoppingList.setUserId(user.getId());
            ShoppingListEntity newShoppingList = modelMapper.map(shoppingList, ShoppingListEntity.class);
            this.shoppingListsRepository.save(newShoppingList);
            return modelMapper.map(newShoppingList, ShoppingList.class);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new InternalServerErrorException();
        }
    }

    public ShoppingList update(String token, ShoppingList shoppingList) {
        try {
            ShoppingListEntity updatedShoppingList = shoppingListsRepository.findById(shoppingList.getId()).orElseThrow(NotFoundException::new);

            if(!authService.shoppingListBelongsToUser(token, updatedShoppingList))
                throw new ForbiddenException();

            UserEntity user = usersRepository.findByEmail(jwtTokenUtil.getEmailFromToken(token)).orElseThrow(ForbiddenException::new);

            shoppingList.setUserId(user.getId());
            updatedShoppingList.setDescription(shoppingList.getDescription());
            updatedShoppingList.setName(shoppingList.getName());

            this.shoppingListsRepository.save(updatedShoppingList);
            return modelMapper.map(updatedShoppingList, ShoppingList.class);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new InternalServerErrorException();
        }
    }

    public void delete(String token, Long id) {
        try {
            ShoppingListEntity foundShoppingList = this.shoppingListsRepository.findById(id).orElseThrow(NotFoundException::new);

            if(!authService.shoppingListBelongsToUser(token, foundShoppingList))
                throw new ForbiddenException();

            this.shoppingListsRepository.delete(foundShoppingList);
        } catch (NotFoundException e) {
            throw e;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new InternalServerErrorException();
        }
    }

}