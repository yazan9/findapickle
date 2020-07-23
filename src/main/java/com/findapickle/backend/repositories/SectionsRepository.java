package com.findapickle.backend.repositories;

import com.findapickle.backend.entities.SectionEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface SectionsRepository extends JpaRepository<SectionEntity, Long> {}