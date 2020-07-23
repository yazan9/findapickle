package com.findapickle.backend.repositories;

import com.findapickle.backend.entities.ItemEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ItemsRepository extends JpaRepository<ItemEntity, Long> {

}