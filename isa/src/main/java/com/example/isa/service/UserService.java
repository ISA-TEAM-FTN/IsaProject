package com.example.isa.service;

import com.example.isa.dto.RegisterDto;
import com.example.isa.model.Role;
import com.example.isa.model.User;
import com.example.isa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleService roleService;

	public User findByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findByEmail(username);
	}

	public Optional<User> findById(Long id) throws AccessDeniedException {
		return userRepository.findById(id);
	}

	public List<User> findAll() throws AccessDeniedException {
		return userRepository.findAll();
	}

	public User save(RegisterDto registerDto) {
		User u = new User();
		u.setEmail(registerDto.getUsername());
		
		// pre nego sto postavimo lozinku u atribut hesiramo je kako bi se u bazi nalazila hesirana lozinka
		// treba voditi racuna da se koristi isi password encoder bean koji je postavljen u AUthenticationManager-u kako bi koristili isti algoritam
		u.setPassword(passwordEncoder.encode(registerDto.getPassword()));
		
		u.setFirstName(registerDto.getFirstname());
		u.setLastName(registerDto.getLastname());
		u.setEnabled(true);
		u.setEmail(registerDto.getEmail());

		// u primeru se registruju samo obicni korisnici i u skladu sa tim im se i dodeljuje samo rola USER
		List<Role> roles = roleService.findByName("ROLE_USER");
		u.setRoles(roles);
		
		return this.userRepository.save(u);
	}

}
