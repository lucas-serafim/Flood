package br.com.flooding.controllers;

import java.util.List;
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

import br.com.flooding.entities.dto.FloodDTO;
import br.com.flooding.services.FloodService;

@RestController
@RequestMapping(value = "/floods")
public class FloodController {

	@Autowired
	private FloodService floodService;
	
	@GetMapping
	public ResponseEntity<List<FloodDTO>> findAll(){
		List<FloodDTO> floods = floodService.findAll();
		return ResponseEntity.status(200).body(floods);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<FloodDTO> findById(@PathVariable Long id){
		try {
			FloodDTO flood = floodService.findById(id);
			return ResponseEntity.status(200).body(flood);
		} catch(NoSuchElementException e) {
			return ResponseEntity.status(404).build();
		}
	}
	
	@PostMapping(value = "/{id}")
	public ResponseEntity<FloodDTO> insert(@PathVariable Long id, @RequestBody FloodDTO dto){
		try {
			FloodDTO flood = floodService.insert(id, dto);
			return ResponseEntity.status(200).body(flood);
		} catch(NoSuchElementException e) {
			return ResponseEntity.status(404).build();
		}
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<FloodDTO> update(@PathVariable Long id, @RequestBody FloodDTO dto){
		try {
			FloodDTO flood = floodService.update(id, dto);
			return ResponseEntity.status(200).body(flood);
		} catch(EntityNotFoundException e) {
			return ResponseEntity.status(404).build();
		}
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		try {
			floodService.delete(id);
			return ResponseEntity.status(200).build();
		} catch(EmptyResultDataAccessException e) {
			return ResponseEntity.status(404).build();
		}
	}
}