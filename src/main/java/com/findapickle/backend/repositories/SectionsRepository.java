package com.findapickle.backend.repositories;

import com.findapickle.backend.entities.Section;

import org.springframework.data.repository.CrudRepository;

public interface SectionsRepository extends CrudRepository<Section, Long>{

}