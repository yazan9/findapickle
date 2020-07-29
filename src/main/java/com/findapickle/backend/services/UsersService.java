package com.findapickle.backend.services;

import com.findapickle.backend.entities.UserEntity;
import com.findapickle.backend.exceptions.DuplicateEntryException;
import com.findapickle.backend.exceptions.ForbiddenException;
import com.findapickle.backend.exceptions.InternalServerErrorException;
import com.findapickle.backend.exceptions.NotFoundException;
import com.findapickle.backend.models.dto.User;
import com.findapickle.backend.repositories.UsersRepository;

import com.findapickle.backend.security.JWTTokenUtil;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
  @Autowired
  private UsersRepository usersRepository;

  @Autowired
  @Qualifier("UsersMapper")
  private ModelMapper modelMapper;

  @Autowired
  private JWTTokenUtil jwtTokenUtil;

  @Autowired AuthService authService;

  private final Logger logger = LoggerFactory.getLogger(UsersService.class);

  public User findById(String token, Long id) {
    UserEntity user = usersRepository.findById(id).orElseThrow(NotFoundException::new);
    if(authService.isAuthorizedOnUser(token, user))
      return modelMapper.map(user, User.class);
    else throw new ForbiddenException();
  }

  public User update(String token, User user) {
    try {
      UserEntity updatedUser = usersRepository.findByEmail(jwtTokenUtil.getEmailFromToken(token)).orElseThrow(NotFoundException::new);
      if(!authService.isAuthorizedOnUser(token, updatedUser))
        throw new ForbiddenException();

      updatedUser.setAvatar(user.getAvatar());
      updatedUser.setUsername(user.getUsername());
      this.usersRepository.save(updatedUser);
      return modelMapper.map(updatedUser, User.class);
    } catch (DataIntegrityViolationException e) {
      throw new DuplicateEntryException("A user with the same email already exists");
    } catch (Exception e) {
      logger.error(e.getMessage());
      throw new InternalServerErrorException();
    }
  }
}