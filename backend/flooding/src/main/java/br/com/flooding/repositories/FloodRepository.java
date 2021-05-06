package br.com.flooding.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.flooding.entities.Flood;

public interface FloodRepository extends JpaRepository<Flood, Long> {
}