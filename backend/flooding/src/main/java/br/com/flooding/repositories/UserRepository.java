package br.com.flooding.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.flooding.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	@Query("SELECT obj FROM User obj WHERE(obj.email = :email)")
	User login(String email);
}