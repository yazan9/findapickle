package com.findapickle.backend.controllers;

import com.findapickle.backend.entities.UserEntity;
import com.findapickle.backend.exceptions.BadRequestException;
import com.findapickle.backend.exceptions.DuplicateEntryException;
import com.findapickle.backend.exceptions.InternalServerErrorException;
import com.findapickle.backend.exceptions.NotFoundException;
import com.findapickle.backend.models.dto.User;
import com.findapickle.backend.repositories.UsersRepository;
import com.findapickle.backend.security.JWTRequest;
import com.findapickle.backend.security.JWTResponse;
import com.findapickle.backend.security.JWTTokenUtil;
import com.findapickle.backend.security.JWTUserDetailsService;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/auth")
public class AuthenticationController {

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    JWTTokenUtil jwtTokenUtil;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JWTUserDetailsService userDetailsService;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public JWTResponse authenticate(@RequestBody JWTRequest authenticationRequest) throws Exception {
        doAuthenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());
        final UserEntity userDetails = usersRepository.findByEmail(authenticationRequest.getEmail()).orElseThrow(NotFoundException::new);
        final String accessToken = jwtTokenUtil.generateToken(userDetails);
        return new JWTResponse(accessToken);
    }

    private void doAuthenticate(String email, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
        catch(Exception e){
            throw new Exception("Internal error");
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public JWTResponse register(@RequestBody User user) throws Exception {
        try {
            UserEntity savedUser = userDetailsService.save(user);
            doAuthenticate(savedUser.getEmail(), savedUser.getPassword());
            final UserEntity userDetails = usersRepository.findByEmail(savedUser.getEmail()).orElseThrow(NotFoundException::new);;
            final String accessToken = jwtTokenUtil.generateToken(userDetails);
            return new JWTResponse(accessToken);
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateEntryException("A user with the same email already exists");
        } catch (ConstraintViolationException e) {
            throw new BadRequestException(
                    "Could not register the user. Please make sure that all required fields are present");
        } catch (Exception e) {
            throw new InternalServerErrorException();
        }
    }
}