package com.web.oauth.base.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BaseAuthRole {

	GUEST("ROLE_GUEST","�մ�"),
	USER("ROLE_USER","�Ϲ� �����");
	
	private final String Key;
	private final String title;
}
