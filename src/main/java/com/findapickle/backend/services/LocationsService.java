package com.findapickle.backend.services;

import java.util.List;
import java.util.stream.Collectors;

import com.findapickle.backend.entities.Location;
import com.findapickle.backend.exceptions.InternalServerErrorException;
import com.findapickle.backend.exceptions.NotFoundException;
import com.findapickle.backend.models.dto.LocationDTO;
import com.findapickle.backend.repositories.LocationsRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class LocationsService {

    @Autowired
    private LocationsRepository locationsRepository;

    @Autowired 
    private PickleConversionService<Location> conversionService;

    @Autowired
    private ModelMapper modelMapper;

    public List<LocationDTO> getStoreLocations(Long storeId) {
        List<Location> locations = locationsRepository.findByStoreId(storeId);
        return locations.stream().map(this::LocationEntityToDto).collect(Collectors.toList());
    }

    public LocationDTO findById(Long id){
        Location location = locationsRepository.findById(id).orElseThrow(() -> new NotFoundException());
        return this.LocationEntityToDto(location);
    }

    public ResponseEntity<?> save(String location) {
        try {
            Location newLocation = this.conversionService.JsontoEntity(location, Location.class);
            this.locationsRepository.save(newLocation);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            throw new InternalServerErrorException();
        }
    }

    public ResponseEntity<?> update(String location) {
        try {
            Location updatedLocation = this.conversionService.JsontoEntity(location, Location.class);
            this.locationsRepository.save(updatedLocation);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            throw new InternalServerErrorException();
        }
    }

    public ResponseEntity<?> delete(Long id) {
        try {
            Location foundLocation = this.locationsRepository.findById(id).orElseThrow(() -> new NotFoundException());
            this.locationsRepository.delete(foundLocation);
            return ResponseEntity.ok().build();
        } catch (NotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new InternalServerErrorException();
        }
    }

    private LocationDTO LocationEntityToDto(Location location) {
        return modelMapper.map(location, LocationDTO.class);
    }
}