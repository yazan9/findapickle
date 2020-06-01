package com.findapickle.backend.services;

import java.util.List;
import java.util.stream.Collectors;

import com.findapickle.backend.entities.Store;
import com.findapickle.backend.exceptions.InternalServerErrorException;
import com.findapickle.backend.exceptions.NotFoundException;
import com.findapickle.backend.models.dto.StoreDTO;
import com.findapickle.backend.repositories.StoresRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class StoresService {
    @Autowired
    private StoresRepository storesRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ConversionService<Store> conversionService;

    public List<StoreDTO> getAllStores() {
        List<Store> stores = storesRepository.findAll();
        return stores.stream().map(this::StoreEntityToDto).collect(Collectors.toList());
    }

    public StoreDTO findById(Long id){
        Store store = storesRepository.findById(id).orElseThrow(() -> new NotFoundException());
        return this.StoreEntityToDto(store);
    }

    public ResponseEntity<?> save(String store) {
        try {
            Store newStore = this.conversionService.JsontoEntity(store, Store.class);
            this.storesRepository.save(newStore);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            throw new InternalServerErrorException();
        }
    }

    public ResponseEntity<?> update(String store) {
        try {
            Store updatedStore = this.conversionService.JsontoEntity(store, Store.class);
            this.storesRepository.save(updatedStore);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            throw new InternalServerErrorException();
        }
    }

    public ResponseEntity<?> delete(Long id) {
        try {
            Store foundStore = this.storesRepository.findById(id).orElseThrow(() -> new NotFoundException());
            this.storesRepository.delete(foundStore);
            return ResponseEntity.ok().build();
        } catch (NotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new InternalServerErrorException();
        }
    }

    private StoreDTO StoreEntityToDto(Store store) {
        return modelMapper.map(store, StoreDTO.class);
    }
}