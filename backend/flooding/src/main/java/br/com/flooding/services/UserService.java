package br.com.flooding.services;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.time.Instant;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.flooding.encryption.PBKDF2_encrypt;
import br.com.flooding.entities.User;
import br.com.flooding.entities.dto.UserDTO;
import br.com.flooding.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Transactional(readOnly = true)
	public UserDTO findById(Long id) {
		Optional<User> user = userRepository.findById(id);
		return new UserDTO(user.get());
	}
	
	@Transactional
	public UserDTO insert(UserDTO dto) throws NoSuchAlgorithmException, InvalidKeySpecException {
		User user = new User(null, dto.getName(), dto.getEmail(), PBKDF2_encrypt.generateStrongPasswordHash(dto.getPassword()), dto.getUrlImage(), Instant.now());
		userRepository.save(user);
		return new UserDTO(user);
	}
	
	@Transactional
	public UserDTO login(UserDTO dto) throws NoSuchAlgorithmException, InvalidKeySpecException {
		User user = userRepository.login(dto.getEmail());
		
		boolean matched = PBKDF2_encrypt.validatePassword(dto.getPassword(), user.getPassword());
		
		if (matched) {
			return new UserDTO(user);
		} else {
			throw new NullPointerException();
		}
	}
	
	@Transactional
	public UserDTO update(Long id, UserDTO dto) throws NoSuchAlgorithmException, InvalidKeySpecException {
		User user = userRepository.getOne(id);
		updateData(user, dto);
		return new UserDTO(userRepository.save(user));
	}
	
	@Transactional
	public void delete(Long id) {
		userRepository.deleteById(id);
	}
	
	private void updateData(User user, UserDTO dto) throws NoSuchAlgorithmException, InvalidKeySpecException {
		user.setName(dto.getName());
		user.setEmail(dto.getEmail());
		user.setPassword(PBKDF2_encrypt.generateStrongPasswordHash(dto.getPassword()));
		user.setUrlImage(dto.getUrlImage());
	}
}