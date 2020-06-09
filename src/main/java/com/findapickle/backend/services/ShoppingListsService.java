package com.findapickle.backend.services;

import java.util.List;
import java.util.stream.Collectors;

import com.findapickle.backend.entities.ShoppingList;
import com.findapickle.backend.exceptions.InternalServerErrorException;
import com.findapickle.backend.exceptions.NotFoundException;
import com.findapickle.backend.models.dto.ShoppingListDTO;
import com.findapickle.backend.repositories.ShoppingListsRepository;

import org.modelmapper.ModelMapper;
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
    private PickleConversionService<ShoppingList> conversionService;

    public List<ShoppingListDTO> getAllShoppingLists() {
        List<ShoppingList> shoppingLists = shoppingListsRepository.findAll();
        return shoppingLists.stream().map(this::ShoppingListEntityToDto).collect(Collectors.toList());
    }

    public ShoppingListDTO findById(Long id){
        ShoppingList shoppingList = shoppingListsRepository.findById(id).orElseThrow(() -> new NotFoundException());
        return this.ShoppingListEntityToDto(shoppingList);
    }

    public ResponseEntity<?> save(String shoppingList) {
        try {
            ShoppingList newShoppingList = this.conversionService.JsontoEntity(shoppingList, ShoppingList.class);
            this.shoppingListsRepository.save(newShoppingList);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            throw new InternalServerErrorException();
        }
    }

    public ResponseEntity<?> update(String shoppingList) {
        try {
            ShoppingList updatedShoppingList = this.conversionService.JsontoEntity(shoppingList, ShoppingList.class);

            this.shoppingListsRepository.save(updatedShoppingList);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            throw new InternalServerErrorException();
        }
    }

    public ResponseEntity<?> delete(Long id) {
        try {
            ShoppingList foundShoppingList = this.shoppingListsRepository.findById(id).orElseThrow(() -> new NotFoundException());
            this.shoppingListsRepository.delete(foundShoppingList);
            return ResponseEntity.ok().build();
        } catch (NotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new InternalServerErrorException();
        }
    }

    private ShoppingListDTO ShoppingListEntityToDto(ShoppingList store) {
        return modelMapper.map(store, ShoppingListDTO.class);
    }

}