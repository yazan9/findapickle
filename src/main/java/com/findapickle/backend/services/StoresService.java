package com.findapickle.backend.services;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.findapickle.backend.entities.StoreEntity;
import com.findapickle.backend.exceptions.ForbiddenException;
import com.findapickle.backend.exceptions.InternalServerErrorException;
import com.findapickle.backend.exceptions.NotFoundException;
import com.findapickle.backend.models.dto.Store;
import com.findapickle.backend.repositories.StoresRepository;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class StoresService {
    @Autowired
    private StoresRepository storesRepository;

    @Autowired
    private AdminService adminService;

    @Autowired
    private ModelMapper modelMapper;

    private final Logger logger = LoggerFactory.getLogger(StoresService.class);

    public List<Store> getAllStores() {
        List<StoreEntity> stores = storesRepository.findAll();
        return modelMapper.map(stores, new TypeToken<List<Store>>() {}.getType());
    }

    public Store findById(Long id){
        StoreEntity store = storesRepository.findById(id).orElseThrow(NotFoundException::new);
        return modelMapper.map(store, Store.class);
    }

    public Store save(String token, Store store) {
        if(!adminService.isAdmin(token))
            throw new ForbiddenException();
        try {
            StoreEntity newStore = modelMapper.map(store, StoreEntity.class);
            this.storesRepository.save(newStore);
            return modelMapper.map(newStore, Store.class);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new InternalServerErrorException();
        }
    }

    public Store update(String token, Store store) {
        if(!adminService.isAdmin(token))
            throw new ForbiddenException();
        try {
            StoreEntity updatedStore = storesRepository.findById(store.getId()).orElseThrow(NotFoundException::new);
            updatedStore.setName(store.getName());
            this.storesRepository.save(updatedStore);
            return modelMapper.map(updatedStore, Store.class);
        } catch(NotFoundException e){
            throw e;
        }
        catch (Exception e) {
            logger.error(e.getMessage());
            throw new InternalServerErrorException();
        }
    }

    public ResponseEntity<?> delete(String token, Long id) {
        if(!adminService.isAdmin(token))
            throw new ForbiddenException();
        try {
            StoreEntity foundStore = this.storesRepository.findById(id).orElseThrow(NotFoundException::new);
            this.storesRepository.delete(foundStore);
            return ResponseEntity.ok().build();
        } catch (NotFoundException e) {
            throw e;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new InternalServerErrorException();
        }
    }
}