package com.haygroup.leap.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.Gson;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ISLoginResponse
{
	public long userId;
	public String authToken;
	public String username;
	private String locale; 
	private String clientId;
	private boolean privacyConsent;
	private String jwtClientCode;
	
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
	/**
	 * @return the locale
	 */
	public String getLocale() {
		return locale;
	}
	/**
	 * @param locale the locale to set
	 */
	public void setLocale(String locale) {
		this.locale = locale;
	}
	/**
	 * @return the userId
	 */
	public long getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}
	/**
	 * @return the authToken
	 */
	public String getAuthToken() {
		return authToken;
	}
	/**
	 * @param authToken the authToken to set
	 */
	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	/*
	 * public String getUserAccess() { return userAccess; } public void
	 * setUserAccess(String userAccess) { this.userAccess = userAccess; }
	 */
	public String getJwtClientCode() {
		return jwtClientCode;
	}
	public void setJwtClientCode(String jwtClientCode) {
		this.jwtClientCode = jwtClientCode;
	}
}
