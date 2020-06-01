package com.findapickle.backend.repositories;

import com.findapickle.backend.entities.ShoppingList;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingListsRepository extends JpaRepository<ShoppingList, Long>{

}
