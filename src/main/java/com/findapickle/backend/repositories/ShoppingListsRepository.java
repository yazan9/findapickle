package com.findapickle.backend.repositories;

import com.findapickle.backend.entities.ShoppingListEntity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ShoppingListsRepository extends JpaRepository<ShoppingListEntity, Long>{
    Optional<List<ShoppingListEntity>> findByUserId(Long userId);
}
