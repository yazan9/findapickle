package com.findapickle.backend.repositories;

import java.util.List;
import java.util.Optional;

import com.findapickle.backend.entities.LocationEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationsRepository extends JpaRepository<LocationEntity, Long>{
    public Optional<List<LocationEntity>> findByStoreId(Long storeId);
}

