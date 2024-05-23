package com.haygroup.leap.security;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.haygroup.leap.common.AuthPropertiesUtil;
import com.haygroup.leap.common.HGLeapConstants;

/**
 * EhCache implementation of AuthenticationManager
 */
public class EhCacheAuthenticationManagerImpl implements AuthenticationManager {

	private Cache authenticationCache;
	private static final Logger logger = LoggerFactory.getLogger(EhCacheAuthenticationManagerImpl.class);

	public EhCacheAuthenticationManagerImpl(Cache authenticationCache) {
		logger.info("authenticationCache {} ", System.identityHashCode(authenticationCache));
		this.authenticationCache = authenticationCache;
	}

	@Override
	public boolean isAuthenticated(String url, String authToken) {
		
		if(url.contains(HGLeapConstants.PICTURE_URL)){
			return AuthPropertiesUtil.isStaticAuthToken(authToken);
		}else{
			return authenticationCache.get(authToken) == null ? false : true;
		}
	}

	@Override
	public void addAuthToken(String authToken, String duration, Object obj) {
		Element element = new Element(authToken, obj);
		authenticationCache.put(element);
	}

	@Override
	public boolean removeAuthToken(String authToken) {
		return authenticationCache.remove(authToken);
	}

	@Override
	public boolean needsAuthentication(String url) {
		return !AuthPropertiesUtil.isIgnoreURL(url);
	}

	@Override
	public Object get(String authToken) {

		return authenticationCache.get(authToken).getObjectValue();

	}
	
	

}
