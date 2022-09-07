package com.web.oauth.base.dto;

import java.util.Map;

import com.web.oauth.base.model.BaseAuthRole;
import com.web.oauth.base.model.BaseAuthUser;

import lombok.Builder;
import lombok.Getter;

@Getter
public class OAuthAttributes {

	//Oauth2�� ��ȯ�ϴ� user������ ����
	private Map<String, Object> attributes;
	private String nameAttributeKey;
	private String name;
	private String email;
	private String picture;
	
	@Builder
	public OAuthAttributes(Map<String, Object> attributes,
			String nameAttributeKey,String name,String email,String picture) {
		
		this.attributes = attributes;
		this.nameAttributeKey = nameAttributeKey;
		this.name = name;
		this.email = email;
		this.picture = picture;
	}
	
	//OAuth2User���� ��ȯ�ϴ� ����� ������ MAP�̹Ƿ�
	//ofGoogle �޼ҵ忡�� ��ȯ �۾��� ��
	public static OAuthAttributes of(String registrationId,String userNameAttributeName,
			Map<String, Object> attributes) {
		
		return ofGoogle(userNameAttributeName,attributes); //sub, ����� ����
	}
	
	private static OAuthAttributes ofGoogle(String userNameAttributeName, 
			Map<String, Object> attributes) {
		
		return OAuthAttributes.builder()
				.name((String)attributes.get("name"))
				.email((String)attributes.get("email"))
				.picture((String)attributes.get("picture"))
				.attributes(attributes)
				.nameAttributeKey(userNameAttributeName)
				.build()
				;
	}
	
	public BaseAuthUser toEntity() {
		
		return BaseAuthUser.builder()
				.name(name)
				.email(email)
				.picture(picture)
				.role(BaseAuthRole.GUEST)
				.build()
				;
				
	}
	
}






