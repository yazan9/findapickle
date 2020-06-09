package com.findapickle.backend.security;

import java.util.ArrayList;

import com.findapickle.backend.entities.User;
import com.findapickle.backend.models.dto.UserDTO;
import com.findapickle.backend.repositories.UsersRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JWTUserDetailsService implements UserDetailsService {

	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = usersRepository.findByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException("The user with the supplied credentials could not be found");
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());
	}

	public User save(UserDTO user) {
		User newUser = new User();
		newUser.setEmail(user.getEmail());
		newUser.setUsername(user.getUsername());
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		return usersRepository.save(newUser);
	}
}