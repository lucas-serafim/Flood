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

import br.com.flooding.entities.dto.NoticeDTO;
import br.com.flooding.services.NoticeService;

@RestController
@RequestMapping(value = "/notices")
public class NoticeController {

	@Autowired
	private NoticeService noticeService;
	
	@GetMapping
	public ResponseEntity<List<NoticeDTO>> findAll(){
		List<NoticeDTO> notices = noticeService.findAll();
		return ResponseEntity.status(200).body(notices);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<NoticeDTO> findById(@PathVariable Long id){
		try {
			NoticeDTO notice = noticeService.findById(id);
			return ResponseEntity.status(200).body(notice);
		} catch(NoSuchElementException e) {
			return ResponseEntity.status(404).build();
		}
	}
	
	@PostMapping(value = "/{id}/user")
	public ResponseEntity<NoticeDTO> insert(@PathVariable Long id, @RequestBody NoticeDTO dto){
		try {
			NoticeDTO notice = noticeService.insert(id, dto);
			return ResponseEntity.status(201).body(notice);
		} catch(NoSuchElementException e) {
			return ResponseEntity.status(404).build();
		}
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<NoticeDTO> update(@PathVariable Long id, @RequestBody NoticeDTO dto){
		try {
			NoticeDTO notice = noticeService.update(id, dto);
			return ResponseEntity.status(200).body(notice);
		} catch(EntityNotFoundException e) {
			return ResponseEntity.status(404).build();
		}
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		try {
			noticeService.delete(id);
			return ResponseEntity.status(200).build();
		} catch(EmptyResultDataAccessException e) {
			return ResponseEntity.status(404).build();
		} catch(EntityNotFoundException e) {
			return ResponseEntity.status(404).build();
		}
	}
}