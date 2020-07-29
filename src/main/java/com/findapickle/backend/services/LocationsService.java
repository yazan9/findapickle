package com.findapickle.backend.services;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.findapickle.backend.entities.LocationEntity;
import com.findapickle.backend.exceptions.ForbiddenException;
import com.findapickle.backend.exceptions.InternalServerErrorException;
import com.findapickle.backend.exceptions.NotFoundException;
import com.findapickle.backend.models.dto.Location;
import com.findapickle.backend.repositories.LocationsRepository;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class LocationsService {

    @Autowired
    private LocationsRepository locationsRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private  AdminService adminService;

    private final Logger logger = LoggerFactory.getLogger(LocationsService.class);

    public List<Location> getStoreLocations(Long storeId) {
        List<LocationEntity> locations = locationsRepository.findByStoreId(storeId).orElseThrow(NotFoundException::new);
        return modelMapper.map(locations, new TypeToken<List<Location>>() {}.getType());
    }

    public Location findById(Long id){
        LocationEntity location = locationsRepository.findById(id).orElseThrow(NotFoundException::new);
        return modelMapper.map(location, Location.class);
    }

    public Location save(String token, Location location) {
        if(!adminService.isAdmin(token))
            throw new ForbiddenException();
        try {
            LocationEntity newLocation = modelMapper.map(location, LocationEntity.class);
            this.locationsRepository.save(newLocation);
            return modelMapper.map(newLocation, Location.class);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new InternalServerErrorException();
        }
    }

    public Location update(String token, Location location) {
        if(!adminService.isAdmin(token))
            throw new ForbiddenException();
        try {
            LocationEntity updatedLocation = locationsRepository.findById(location.getId()).orElseThrow(NotFoundException::new);
            updatedLocation.setAddress(location.getAddress());
            updatedLocation.setName(location.getName());
            this.locationsRepository.save(updatedLocation);
            return modelMapper.map(updatedLocation, Location.class);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new InternalServerErrorException();
        }
    }

    public void delete(String token, Long id) {
        if(!adminService.isAdmin(token))
            throw new ForbiddenException();
        try {
            LocationEntity foundLocation = this.locationsRepository.findById(id).orElseThrow(NotFoundException::new);
            this.locationsRepository.delete(foundLocation);
        } catch (NotFoundException e) {
            throw e;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new InternalServerErrorException();
        }
    }
}