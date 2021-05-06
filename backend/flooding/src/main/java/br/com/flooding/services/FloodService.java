package br.com.flooding.services;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.flooding.entities.Flood;
import br.com.flooding.entities.User;
import br.com.flooding.entities.dto.FloodDTO;
import br.com.flooding.repositories.FloodRepository;
import br.com.flooding.repositories.UserRepository;

@Service
public class FloodService {
	
	@Autowired
	private FloodRepository floodRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Transactional(readOnly = true)
	public List<FloodDTO> findAll(){
		List<Flood> floods = floodRepository.findAll();
		return floods.stream().map(x -> new FloodDTO(x)).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public FloodDTO findById(Long id) {
		Optional<Flood> flood = floodRepository.findById(id);
		return new FloodDTO(flood.get());
	}
	
	@Transactional
	public FloodDTO insert(Long id, FloodDTO dto) {
		Optional<User> user = userRepository.findById(id);
		Flood flood = new Flood(null, dto.getLatitude(), dto.getLongitude(), Instant.now(), user.get());
		floodRepository.save(flood);
		return new FloodDTO(flood);
	}
	
	@Transactional
	public FloodDTO update(Long id, FloodDTO dto) {
		Flood flood = floodRepository.getOne(id);
		updateData(flood, dto);
		return new FloodDTO(floodRepository.save(flood));
	}
	
	@Transactional
	public void delete(Long id) {
		floodRepository.deleteById(id);
	}
	
	private void updateData(Flood flood, FloodDTO dto) {
		flood.setLatitude(dto.getLatitude());
		flood.setLongitude(dto.getLongitude());
	}
}