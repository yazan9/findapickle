package com.findapickle.backend.repositories;

import com.findapickle.backend.entities.Store;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StoresRepository extends JpaRepository<Store, Long>{

}