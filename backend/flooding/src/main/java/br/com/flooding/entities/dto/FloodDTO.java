package br.com.flooding.entities.dto;

import java.io.Serializable;
import java.time.Instant;

import br.com.flooding.entities.Flood;

public class FloodDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Double latitude;
	private Double longitude;
	private Instant createdAt;
	
	public FloodDTO() {
	}
	
	public FloodDTO(Flood obj) {
		id = obj.getId();
		latitude = obj.getLatitude();
		longitude = obj.getLongitude();
		createdAt = obj.getCreatedAt();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Instant getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}
}