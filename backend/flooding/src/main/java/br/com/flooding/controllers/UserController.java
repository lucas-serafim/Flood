package br.com.flooding.controllers;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.NoSuchElementException;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.flooding.entities.dto.UserDTO;
import br.com.flooding.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable Long id){
		try {
			UserDTO user = userService.findById(id);
			return ResponseEntity.status(200).body(user);
		} catch(NoSuchElementException e) {
			return ResponseEntity.status(404).build();
		}
		
		// NoSuchElementException = not found - findById
	}
	
	@PostMapping
	public ResponseEntity<UserDTO> insert(@RequestBody UserDTO dto){
		try {
			UserDTO user = userService.insert(dto);
			return ResponseEntity.status(200).body(user);
		} catch(NoSuchAlgorithmException e) {
			return ResponseEntity.status(500).build();
		} catch(InvalidKeySpecException e) {
			return ResponseEntity.status(500).build();
		}
		
		// user, email and password = not null *
		// NoSuchAlgorithmException, InvalidKeySpecException
	}
	
	@PostMapping(value = "/login")	
	public ResponseEntity<UserDTO> login(@RequestBody UserDTO dto){
		try {
			UserDTO user = userService.login(dto);
			return ResponseEntity.status(200).body(user);
		} catch(NullPointerException e) {
			return ResponseEntity.status(404).build();
		} catch(NoSuchAlgorithmException e) {	
			return ResponseEntity.status(500).build();
		} catch(InvalidKeySpecException e) {
			return ResponseEntity.status(500).build();
		}
		
		// NullPointerException = User not exists
		// NoSuchAlgorithmException, InvalidKeySpecException
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<UserDTO> update(@PathVariable Long id, @RequestBody UserDTO dto){
		try {
			UserDTO user = userService.update(id, dto);
			return ResponseEntity.status(200).body(user);
		} catch(EntityNotFoundException e) {
			return ResponseEntity.status(404).build();
		} catch(NoSuchAlgorithmException e) {
			return ResponseEntity.status(500).build();
		} catch(InvalidKeySpecException e) {
			return ResponseEntity.status(500).build();
		}
		
		// EntityNotFoundException = not found - getOne
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		try {
			userService.delete(id);
			return ResponseEntity.status(200).build();
		} catch(EmptyResultDataAccessException e) {
			return ResponseEntity.status(404).build();
		}
		
		// EmptyResultDataAccessException = not found - deleteById
	}
}