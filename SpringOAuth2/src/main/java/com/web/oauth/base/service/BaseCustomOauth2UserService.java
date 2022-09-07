package com.web.oauth.base.service;

import java.util.Collections;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.web.oauth.base.dao.BaseAuthUserRepository;
import com.web.oauth.base.dto.OAuthAttributes;
import com.web.oauth.base.dto.SessionUser;
import com.web.oauth.base.model.BaseAuthUser;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BaseCustomOauth2UserService 
	implements OAuth2UserService<OAuth2UserRequest, OAuth2User>{
	
	@Autowired
	private final BaseAuthUserRepository baseAuthUserRepository;
	
	@Autowired
	private final HttpSession httpSession;
	
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) 
			throws OAuth2AuthenticationException {
		
		//인증값을 받아옴
		OAuth2UserService<OAuth2UserRequest, OAuth2User> oauthUserService = 
				new DefaultOAuth2UserService();
		
		OAuth2User oauth2User = oauthUserService.loadUser(userRequest);
		
		//간편 로그인을 진행하는 플랫폼(google,kakao,apple,facebook,naver)
		String registrationId = userRequest.getClientRegistration().getRegistrationId();
		
		//OAuth2로그인 진행 시 key가 되는 필드값
		//google:sub, naver:response, kakao:id
		String userNameAttributeName = 
				userRequest.getClientRegistration().getProviderDetails()
				.getUserInfoEndpoint().getUserNameAttributeName();
		
		System.out.println(userNameAttributeName);//sub,response,id
		
		//로그인을 통해 가져온 Oauth2User의 속성을 담아두는 of메소드
		OAuthAttributes attributes =
				OAuthAttributes.of(registrationId, userNameAttributeName, oauth2User.getAttributes());
		
		//응답 받은 속성(JSON)
		System.out.println(attributes.getAttributes());
		
		//응답 받은 속성을 authUser 객체에 입력
		BaseAuthUser authUser = saveOrUpdate(attributes);
		
		//세션에 사용자 정보 저장
		httpSession.setAttribute("user", new SessionUser(authUser));
		
		return new DefaultOAuth2User(
				Collections.singleton(new SimpleGrantedAuthority(authUser.getRoleKey())), 
				attributes.getAttributes(), attributes.getNameAttributeKey());
	}
	
	//google의 user 정보가 update 되었을 때 사용되는 메소드(람다식)
	//사용자의 이름이나 프로필 사진이 변경되면 User Entity에도 반영된다
	private BaseAuthUser saveOrUpdate(OAuthAttributes attributes) {
		
		BaseAuthUser authUser = 
				baseAuthUserRepository.findByEmail(attributes.getEmail())
				.map(entity -> entity.update(attributes.getName(), attributes.getPicture()))
				.orElse(attributes.toEntity());
		
		return baseAuthUserRepository.save(authUser);
	}
	
}
