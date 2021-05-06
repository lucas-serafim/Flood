package br.com.flooding.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.flooding.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>{
}