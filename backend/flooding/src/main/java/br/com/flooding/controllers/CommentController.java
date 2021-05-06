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

import br.com.flooding.entities.dto.CommentDTO;
import br.com.flooding.services.CommentService;

@RestController
@RequestMapping(value = "/comments")
public class CommentController {

	@Autowired
	private CommentService commentService;

	@GetMapping(value = "/{id}/notice")
	public ResponseEntity<List<CommentDTO>> findAll(@PathVariable Long id) {
		try {
			List<CommentDTO> comments = commentService.findAll(id);
			return ResponseEntity.status(200).body(comments);
		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(404).build();
		}
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<CommentDTO> findById(@PathVariable Long id) {
		try {
			CommentDTO comment = commentService.findById(id);
			return ResponseEntity.status(200).body(comment);
		} catch (NoSuchElementException e) {
			return ResponseEntity.status(404).build();
		}
	}

	@PostMapping(value = "/{id_user}/user/{id_notice}/notice")
	public ResponseEntity<CommentDTO> insert(@PathVariable Long id_user, @PathVariable Long id_notice,
			@RequestBody CommentDTO dto) {
		try {
			CommentDTO comment = commentService.insert(id_user, id_notice, dto);
			return ResponseEntity.status(201).body(comment);
		} catch (NoSuchElementException e) {
			return ResponseEntity.status(404).build();
		}
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<CommentDTO> update(@PathVariable Long id, @RequestBody CommentDTO dto) {
		try {
			CommentDTO comment = commentService.update(id, dto);
			return ResponseEntity.status(200).body(comment);
		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(404).build();
		}
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		try {
			commentService.delete(id);
			return ResponseEntity.status(200).build();
		} catch (EmptyResultDataAccessException e) {
			return ResponseEntity.status(404).build();
		}
	}
}