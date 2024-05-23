package com.haygroup.leap.security2;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;

public class LeapAuthenticationToken extends AbstractAuthenticationToken {

	UserData userData;
	
	public UserData getUserData() {
		return userData;
	}

	public LeapAuthenticationToken(UserData userData){

		super(AuthorityUtils.createAuthorityList(userData.getRoles()));
		this.userData = userData;
		this.setAuthenticated(true);
	
	}
	
	@Override
	public Object getCredentials() {
		return userData.getAuthToken();
	}

	@Override
	public Object getPrincipal() {
		return userData.getUsername();
	}


}
