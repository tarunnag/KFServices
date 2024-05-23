package com.haygroup.leap.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;

import com.haygroup.leap.common.HGLeapConstants;

/**
 * Filter to authenticate requests
 * 
 */
public class AuthenticationFilter extends LeapFilter {

	private static String USER_URL = "/users/";
	private static int USER_URL_LENGTH = 7;
	
	private static final Logger logger = LoggerFactory.getLogger(AuthenticationFilter.class);

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException,
			ServletException {
		

		HttpServletRequest httpRequest = (HttpServletRequest) request;

		// get AuthToken
		String authToken = httpRequest.getHeader("authToken");
		if (authToken == null) {
			authToken = httpRequest.getParameter("authToken");
		}
		logger.debug("authToken - {}", StringEscapeUtils.escapeJava(authToken));

		// url
		String url = httpRequest.getRequestURI();
		logger.debug("URL - {}", StringEscapeUtils.escapeJava(url));
		
		
		// authenticate
		if (cacheFactory.getInstance().needsAuthentication(url) &&
				!httpRequest.getMethod().equalsIgnoreCase(HttpMethod.OPTIONS.toString())) {
			//get the browse
			if (cacheFactory.getInstance().isAuthenticated(url, authToken)) {
				
				if(isAuthorized(url, cacheFactory.getInstance().get(authToken).toString())){
					logger.debug("AuthToken {} is valid", StringEscapeUtils.escapeJava(authToken));
					httpRequest.setAttribute(HGLeapConstants.AUTH_TOKEN, authToken);
					filterChain.doFilter(request, response);
				}else{
					logger.debug("AuthToken {} is not authorized", StringEscapeUtils.escapeJava(authToken));
					httpRequest.getRequestDispatcher("/error").forward(request, response);	
				}
			} else {
				logger.debug("AuthToken {} is not valid", StringEscapeUtils.escapeJava(authToken));
				httpRequest.getRequestDispatcher("/error").forward(request, response);
			}
		} else {
			logger.debug("URL {} is Anonymous", StringEscapeUtils.escapeJava(url));
			filterChain.doFilter(request, response);
		}

	}
	
	public boolean isAuthorized(String url,String userId){
		
		if(url.contains(USER_URL)){
			int start = url.indexOf(USER_URL) + USER_URL_LENGTH;
			int end = url.indexOf("/",start);
			String urlUserId = (end == -1) ? url.substring(start) :url.substring(start,end);
			logger.debug("User Id extracted from URL {} is {}",StringEscapeUtils.escapeJava(url),StringEscapeUtils.escapeJava(urlUserId));
			return urlUserId.equals(userId);
		}
		
		return true;
		
	}

}
