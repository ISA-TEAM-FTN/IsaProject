package com.example.isa.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.isa.exception.NotFoundException;
import com.example.isa.model.User;
import com.example.isa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


// Primer kontrolera cijim metodama mogu pristupiti samo autorizovani korisnici
@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class UserController {

	@Autowired
	private UserService userService;

	// Za pristup ovoj metodi neophodno je da ulogovani korisnik ima ADMIN ulogu
	// Ukoliko nema, server ce vratiti gresku 403 Forbidden
	// Korisnik jeste autentifikovan, ali nije autorizovan da pristupi resursu
	@GetMapping("/user/{userId}")
	@PreAuthorize("hasRole('ADMIN')")	
	public User loadById(@PathVariable Long userId) {
		return this.userService.findById(userId).orElseThrow(NotFoundException::new);
	}

	@GetMapping("/user/all")
	@PreAuthorize("hasRole('ADMIN')")
	public List<User> loadAll() {
		return this.userService.findAll();
	}

	@GetMapping("/whoami")
	@PreAuthorize("hasRole('USER')  or hasRole('ADMIN_CENTER') or hasRole('ADMIN_SYSTEM')")
	public User user(Principal user) {
		return this.userService.findByUsername(user.getName());
	}
}
