package br.com.flooding.services;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.flooding.entities.Notice;
import br.com.flooding.entities.User;
import br.com.flooding.entities.dto.NoticeDTO;
import br.com.flooding.repositories.CommentRepository;
import br.com.flooding.repositories.NoticeRepository;
import br.com.flooding.repositories.UserRepository;

@Service
public class NoticeService {

	@Autowired
	private NoticeRepository noticeRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Transactional(readOnly = true)
	public List<NoticeDTO> findAll(){
		List<Notice> notices = noticeRepository.findAll();
		return notices.stream().map(x -> new NoticeDTO(x)).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public NoticeDTO findById(Long id) {
		Optional<Notice> notice = noticeRepository.findById(id);
		return new NoticeDTO(notice.get());
	}
	
	@Transactional
	public NoticeDTO insert(Long id, NoticeDTO dto) {
		Optional<User> user = userRepository.findById(id);
		Notice notice = new Notice(null, dto.getTitle(), dto.getBody(), 0, Instant.now(), user.get());
		noticeRepository.save(notice);
		return new NoticeDTO(notice);
	}
	
	@Transactional
	public NoticeDTO update(Long id, NoticeDTO dto) {
		Notice notice = noticeRepository.getOne(id);
		updateData(notice, dto);
		return new NoticeDTO(notice);
	}
	
	@Transactional
	public void delete(Long id) {
		Notice notice = noticeRepository.getOne(id);
		commentRepository.deleteAll(notice.getComments());
		noticeRepository.deleteById(id);
	}
	
	private void updateData(Notice notice, NoticeDTO dto) {
		notice.setTitle(dto.getTitle());
		notice.setBody(dto.getBody());
	}
}