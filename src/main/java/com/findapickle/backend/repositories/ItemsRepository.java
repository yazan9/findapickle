package com.findapickle.backend.repositories;

import com.findapickle.backend.entities.Item;

import org.springframework.data.repository.CrudRepository;

public interface ItemsRepository extends CrudRepository<Item, Long>{

}