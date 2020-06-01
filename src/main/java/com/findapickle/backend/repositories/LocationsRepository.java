package com.findapickle.backend.repositories;

import java.util.List;

import com.findapickle.backend.entities.Location;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationsRepository extends JpaRepository<Location, Long>{
    public List<Location> findByStoreId(Long storeId);
}

