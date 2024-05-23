package com.haygroup.leap.security2;

import java.util.Iterator;
import java.util.Map;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import org.apache.commons.lang.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.HtmlUtils;

import com.haygroup.leap.common.AuthPropertiesUtil;
import com.haygroup.leap.common.HGLeapConstants;
import com.haygroup.leap.security.AuthenticationManager;

/**
 * EhCache implementation of AuthenticationManager
 */
public class DistEhCacheAuthenticationManagerImpl implements AuthenticationManager {

	private Cache authenticationCache;
	RestTemplate restTemplate; 
	private String endpoint;
	
	private static final Logger logger = LoggerFactory.getLogger(DistEhCacheAuthenticationManagerImpl.class);

	public DistEhCacheAuthenticationManagerImpl(Cache authenticationCache, String endpoint) {
		
		logger.debug("initializing");
		
		this.authenticationCache = authenticationCache;
		this.endpoint = endpoint;
		
		this.restTemplate = new RestTemplate();
		this.restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		this.restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
		
	}

	@Override
	public boolean isAuthenticated(String url, String authToken) {
		
		return get(authToken) == null ? false : true;
	
	}

	@Override
	public void addAuthToken(String authToken, String duration, Object obj) {
		
		logger.debug("Adding AuthToken,duration - {} {}",StringEscapeUtils.escapeJava(authToken),StringEscapeUtils.escapeJava(duration));
		
		addCachedObject((UserData) obj,duration);
		
		LeapAuthenticationToken leapAuthToken = new LeapAuthenticationToken((UserData) obj);
		Element element = new Element(authToken, leapAuthToken);
		authenticationCache.put(element);
		
	}

	@Override
	public boolean removeAuthToken(String authToken) {

		logger.debug("Removing AuthToken - {}",StringEscapeUtils.escapeJava(authToken));
		deleteCachedObject(authToken);
		return authenticationCache.remove(authToken);
	}

	@Override
	public boolean needsAuthentication(String url) {
		return !AuthPropertiesUtil.isIgnoreURL(url);
	}

	@Override
	public Object get(String authToken) {


		logger.debug("Getting AuthToken - {}",StringEscapeUtils.escapeJava(authToken));
		
		Element cachedObj = authenticationCache.get(authToken);
		
		if(cachedObj == null){

			logger.debug("AuthToken not found in local cached. Going remote");
			
			CachedObject result = getCachedObject(authToken);
			if("1".equals(result.getCode())){
				logger.debug("Found AuthToken in remote cache !!!");
				LeapAuthenticationToken leapAuthToken = new LeapAuthenticationToken(result.getData());
				authenticationCache.put(new Element(authToken, leapAuthToken));
				return leapAuthToken;
			}else{
				logger.debug("AuthToken not found in remote cache as well");
				return null;
			}
			
		}
		
		logger.debug("Found AuthToken in local cache !!!");
		return cachedObj.getObjectValue();
	
	}
	
	private CachedObject getCachedObject(String authToken){
		
		return restTemplate.getForObject( endpoint + "{authToken}", CachedObject.class, authToken);
	}
	
	private CachedObject addCachedObject(UserData userData,String duration)
	{
		return restTemplate.postForObject(endpoint+"?duration="+duration, userData, CachedObject.class);
	}

	private CachedObject modifyCachedObject(String authToken){
		return restTemplate.getForObject(endpoint + "{authToken}", CachedObject.class, authToken);
	}

	private void deleteCachedObject(String authToken){
		
		restTemplate.delete(endpoint + "{authToken}", authToken);
	}
	

}
