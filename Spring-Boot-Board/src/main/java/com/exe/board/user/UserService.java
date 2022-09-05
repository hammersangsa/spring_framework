package com.exe.board.user;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.exe.board.DataNotFoundException;

import groovyjarjarantlr4.v4.parse.ANTLRParser.throwsSpec_return;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	public SiteUser create(String userName,String email,String password) {
	
		SiteUser user = new SiteUser();
		
		user.setUserName(userName);
		user.setEmail(email);
		
		
		
		//password ��ȣȭ ó�� �� ����
		//BCrypt��� �ؽ� �Լ��� ���
		//BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		user.setPassword(passwordEncoder.encode(password));
		
		userRepository.save(user);
		
		return user;
	}
	
	public SiteUser getUser(String userName) {
		
		Optional<SiteUser> siteUser = userRepository.findByUserName(userName);
		
		if(siteUser.isPresent()) {
			
			return siteUser.get();
		}else {
			throw new DataNotFoundException("����ڰ� �����ϴ�");
		}
		
	}
	
}
