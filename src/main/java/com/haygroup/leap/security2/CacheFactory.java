package com.haygroup.leap.security2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.haygroup.leap.security.AuthenticationManager;

public class CacheFactory {
	
	private static final Logger logger = LoggerFactory.getLogger(PreAuthenticateRequestFilterChain.class);
	
	private String serverType = null; 
	private AuthenticationManager ehCacheAuthenticationManager;
	private AuthenticationManager redisAuthenticationManager;

	
	public CacheFactory(String serverType, AuthenticationManager ehCacheAuthenticationManager, AuthenticationManager redisAuthenticationManager) {
		this.serverType = serverType; 
		this.ehCacheAuthenticationManager = ehCacheAuthenticationManager;
		this.redisAuthenticationManager = redisAuthenticationManager;
	}
	
	
	public AuthenticationManager getInstance() {
		
		if(null != serverType && serverType.equalsIgnoreCase("redis")) {
			
			logger.debug("redis cache is currently in use");
			
			return redisAuthenticationManager;
		}
		
		logger.debug("ehCache is currently in use");
		return ehCacheAuthenticationManager;
	}

}
