package br.com.flooding.services;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.flooding.entities.Comment;
import br.com.flooding.entities.Notice;
import br.com.flooding.entities.User;
import br.com.flooding.entities.dto.CommentDTO;
import br.com.flooding.repositories.CommentRepository;
import br.com.flooding.repositories.NoticeRepository;
import br.com.flooding.repositories.UserRepository;

@Service
public class CommentService {

	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private NoticeRepository noticeRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Transactional(readOnly = true)
	public List<CommentDTO> findAll(Long id){
		Notice notice = noticeRepository.getOne(id);
		
		List<Comment> comments = notice.getComments();
		return comments.stream().map(x -> new CommentDTO(x)).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public CommentDTO findById(Long id) {
		Optional<Comment> comment = commentRepository.findById(id);
		return new CommentDTO(comment.get());
	}
	
	@Transactional
	public CommentDTO insert(Long id_user, Long id_notice, CommentDTO dto) {
		Optional<User> user = userRepository.findById(id_user);
		Optional<Notice> notice = noticeRepository.findById(id_notice);
		Comment comment = new Comment(null, dto.getMessage(), Instant.now(), notice.get(), user.get());
		commentRepository.save(comment);
		return new CommentDTO(comment);
	}
	
	@Transactional
	public CommentDTO update(Long id, CommentDTO dto) {
		Comment comment = commentRepository.getOne(id);
		
		comment.setMessage(dto.getMessage());
		
		return new CommentDTO(comment);
	}
	
	@Transactional
	public void delete(Long id) {
		commentRepository.deleteById(id);
	}
}