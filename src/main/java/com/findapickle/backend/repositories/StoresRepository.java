package com.findapickle.backend.repositories;

import com.findapickle.backend.entities.StoreEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StoresRepository extends JpaRepository<StoreEntity, Long>{}