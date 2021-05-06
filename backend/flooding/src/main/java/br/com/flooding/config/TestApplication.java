package br.com.flooding.config;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.flooding.encryption.PBKDF2_encrypt;
import br.com.flooding.entities.Comment;
import br.com.flooding.entities.Flood;
import br.com.flooding.entities.Notice;
import br.com.flooding.entities.User;
import br.com.flooding.repositories.CommentRepository;
import br.com.flooding.repositories.FloodRepository;
import br.com.flooding.repositories.NoticeRepository;
import br.com.flooding.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestApplication implements CommandLineRunner {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private FloodRepository floodRepository;
	
	@Autowired
	private NoticeRepository noticeRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Override
	public void run(String... args) throws Exception {
		User user = new User(null, "test", "test@test.com", PBKDF2_encrypt.generateStrongPasswordHash("123"), "test.jpg", Instant.now());
		
		Flood flood = new Flood(null, 1010101.1, -1010101.1, Instant.now(), user);
		
		Notice notice = new Notice(null, "test", "test", 0, Instant.now(), user);
		
		Comment comment = new Comment(null, "test", Instant.now(), notice, user);
		
		userRepository.save(user);
		floodRepository.save(flood);
		noticeRepository.save(notice);
		commentRepository.save(comment);
	}
}