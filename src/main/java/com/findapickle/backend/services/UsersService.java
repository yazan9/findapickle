package com.findapickle.backend.services;

import com.findapickle.backend.entities.User;
import com.findapickle.backend.exceptions.DuplicateEntryException;
import com.findapickle.backend.exceptions.InternalServerErrorException;
import com.findapickle.backend.exceptions.NotFoundException;
import com.findapickle.backend.models.dto.UserDTO;
import com.findapickle.backend.repositories.UsersRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
  @Autowired
  private UsersRepository usersRepository;

  @Autowired
  private ModelMapper modelMapper;

  @Autowired 
  private ConversionService<User> conversionService;

  public UserDTO findById(Long id) {
    User user = usersRepository.findById(id).orElseThrow(() -> new NotFoundException());
    return this.UserEntityToDto(user);
  }

  public ResponseEntity<?> save(String user) {
    try {
      User newUser = this.conversionService.JsontoEntity(user, User.class);

      this.usersRepository.save(newUser);
      return ResponseEntity.ok().build();
    } catch (DataIntegrityViolationException e) {
      throw new DuplicateEntryException("A user with the same email already exists");
    } catch (Exception e) {
      throw new InternalServerErrorException();
    }
  }

  public ResponseEntity<?> update(String user) {
    try {
      User updatedUser = this.conversionService.JsontoEntity(user, User.class);
      this.usersRepository.save(updatedUser);
      return ResponseEntity.ok().build();
    } catch (DataIntegrityViolationException e) {
      throw new DuplicateEntryException("A user with the same email already exists");
    } catch (Exception e) {
      throw new InternalServerErrorException();
    }
  }

  public UserDTO UserEntityToDto(User user){
    return modelMapper.map(user, UserDTO.class);
  }
}