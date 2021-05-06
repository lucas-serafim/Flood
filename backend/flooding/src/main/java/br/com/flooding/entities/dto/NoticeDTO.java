package br.com.flooding.entities.dto;

import java.io.Serializable;
import java.time.Instant;

import br.com.flooding.entities.Notice;

public class NoticeDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String title;
	private String body;
	private Integer likes;
	private Instant createdAt;
	
	public NoticeDTO() {
	}
	
	public NoticeDTO(Notice obj) {
		id = obj.getId();
		title = obj.getTitle();
		body = obj.getBody();
		likes = obj.getLikes();
		createdAt = obj.getCreatedAt();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Integer getLikes() {
		return likes;
	}

	public void setLikes(Integer likes) {
		this.likes = likes;
	}
	
	public Instant getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}
}