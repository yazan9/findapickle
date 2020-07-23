package com.findapickle.backend.services;

import com.findapickle.backend.entities.ShoppingListEntity;
import com.findapickle.backend.entities.UserEntity;
import com.findapickle.backend.security.JWTTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    JWTTokenUtil jwtTokenUtil;

    @Autowired
    AdminService adminService;

    public boolean isAuthorizedOnUser(String token, UserEntity user){
        return jwtTokenUtil.getEmailFromToken(token).equals(user.getEmail()) || adminService.isAdmin(token);
    }

    public boolean shoppingListBelongsToUser(String token, ShoppingListEntity shoppingList){
        return jwtTokenUtil.getEmailFromToken(token).equals(shoppingList.getUser().getEmail()) || adminService.isAdmin(token);
    }
}
