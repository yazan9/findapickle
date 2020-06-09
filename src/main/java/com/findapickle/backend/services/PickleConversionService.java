package com.findapickle.backend.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.findapickle.backend.exceptions.UnprocessableEntityException;

import org.springframework.stereotype.Service;

@Service
public class PickleConversionService<T> {
    public T JsontoEntity(String dto, Class<T> target) {
        try {
          ObjectMapper mapper = new ObjectMapper();
          return mapper.readValue(dto, target);
        } catch (Exception e) {
          throw new UnprocessableEntityException();
        }
      }
}