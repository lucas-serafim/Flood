package br.com.flooding.entities.dto;

import java.io.Serializable;
import java.time.Instant;

import br.com.flooding.entities.Comment;

public class CommentDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String message;
	private Instant createdAt;
	
	public CommentDTO() {
	}
	
	public CommentDTO(Comment obj) {
		id = obj.getId();
		message = obj.getMessage();
		createdAt = obj.getCreatedAt();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Instant getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}
}