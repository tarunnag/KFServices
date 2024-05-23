package com.haygroup.leap.security2;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserData {
	
	private String userid;
	private String authToken;
	private String username;
	private String locale;
	private String roles[];
	private String clientId;
	private boolean privacyConsent;
	private String sessionId;
	private String userAccess;



	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionid) {
		this.sessionId = sessionid;
	}

	/**
	 * @return the privacyConsent
	 */
	public boolean isPrivacyConsent() {
		return privacyConsent;
	}

	/**
	 * @param privacyConsent the privacyConsent to set
	 */
	public void setPrivacyConsent(boolean privacyConsent) {
		this.privacyConsent = privacyConsent;
	}

	/**
	 * @return the clientId
	 */
	public String getClientId() {
		return clientId;
	}

	/**
	 * @param clientId the clientId to set
	 */
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public String[] getRoles() {
		return roles;
	}

	public void setRoles(String[] roles) {
		this.roles = roles;
	}

	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserAccess() {
		return userAccess;
	}

	public void setUserAccess(String userAccess) {
		this.userAccess = userAccess;
	}
	
	
}
