package br.com.flooding.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.flooding.entities.Notice;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
}