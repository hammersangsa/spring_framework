package com.exe.board.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserSecurityService implements UserDetailsService{

	private final UserRepository userRepository;
	
	//����ڸ����� ��й�ȣ�� ��ȸ�ؼ� return�ϴ� �޼ҵ�
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		
		//����ڸ����� SiteUser ��ü�� ��ȸ
		Optional<SiteUser> searchUser = 
				userRepository.findByUserName(userName);
		
		if(!searchUser.isPresent()) {
			throw new UsernameNotFoundException("����ڸ� ã�� �� �����ϴ�");
		}
		
		SiteUser siteUser = searchUser.get();
		//������ �������ϼ��� �����Ƿ� List���
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		if("admin".equals(userName)) {
			authorities.add(new SimpleGrantedAuthority(UserRole.ADMIN.getValue()));
		}else {
			authorities.add(new SimpleGrantedAuthority(UserRole.USER.getValue()));
		}
		//����ڸ�,��й�ȣ,������ �Է��Ͽ� SpringSecurity�� 
		//user��ü�� return
		return new User(siteUser.getUserName(),siteUser.getPassword(),authorities);
	}

}
