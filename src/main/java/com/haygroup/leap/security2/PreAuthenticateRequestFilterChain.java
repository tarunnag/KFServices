package com.haygroup.leap.security2;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.haygroup.leap.common.HGLeapConstants;

public class PreAuthenticateRequestFilterChain extends OncePerRequestFilter {

	private CacheFactory cacheFactory;

	private static final Logger logger = LoggerFactory.getLogger(PreAuthenticateRequestFilterChain.class);
	
	@Override
	protected void initFilterBean() throws ServletException {
		super.initFilterBean();
	}

	@Override
	public void destroy() {
		
	}

	public PreAuthenticateRequestFilterChain(CacheFactory cacheFactory){
		super();
		this.cacheFactory = cacheFactory;
	}
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		
		String authToken = request.getHeader(HGLeapConstants.AUTH_TOKEN);
		String url = request.getRequestURI();
		logger.debug("Pre-Authentication URL - {} for AuthToken - {}" , StringEscapeUtils.escapeJava(url), StringEscapeUtils.escapeJava(authToken));
		LeapAuthenticationToken token;
		boolean isPrivacyConsent = false;

		if(authToken != null )
		{
			//Is the authToken valid
			if (cacheFactory.getInstance().isAuthenticated(url, authToken)) 
			{
				logger.debug("authToken {} is valid. Setting Spring Security Context",StringEscapeUtils.escapeJava(authToken));
				token = (LeapAuthenticationToken) cacheFactory.getInstance().get(authToken);
				
				if(url.contains("/actions/privacypolicy/accepted") || url.contains("/actions/token/metadata")){

					isPrivacyConsent = true; //exceptions for new users
				}
				else{

					isPrivacyConsent = token.getUserData().isPrivacyConsent(); //check for the flag in the cache
				}

				//check for user privacy policy status 'true' to allow access
				if(isPrivacyConsent){
				
				String browserSessionId 	= request.getHeader(HGLeapConstants.PS_SESSION_ID);
				String initialSessionId 	= token.getUserData().getSessionId();
					
				//is the browser sessionid exists and it is equal to the session id stored initially during login
				if(browserSessionId==null || 
				   (initialSessionId!=null && initialSessionId.equalsIgnoreCase(browserSessionId))
				  )
				{
					//make sure the userid from authToken is the same as userid in the url
					if(isAuthorized(url, token.getUserData().getUserid()))
					{		
						SecurityContextHolder.getContext().setAuthentication(token);
					}
					
				}
				else
				{
					logger.debug("authTokenSessionId {} AND browser sessionid {} don't match ",StringEscapeUtils.escapeJava(initialSessionId),StringEscapeUtils.escapeJava(browserSessionId));
				}
				
			}
			else
			{
				logger.debug("User has not accepted privacy policy!");
			}

			}
		}
		else
		{
			logger.debug("AuthToken is null.  calling do filter");
		}
		
		filterChain.doFilter(request, response);

	}
	
	public boolean isAuthorized(String url,String userId){
		
		if(url.contains(HGLeapConstants.USER_URL)){
			int start = url.indexOf(HGLeapConstants.USER_URL) + HGLeapConstants.USER_URL_LENGTH;
			int end = url.indexOf("/",start);
			String urlUserId = (end == -1) ? url.substring(start) :url.substring(start,end);
			logger.debug("AuthToken userid is {}",StringEscapeUtils.escapeJava(userId));
			logger.debug("User Id extracted from URL {} is {}",StringEscapeUtils.escapeJava(url),StringEscapeUtils.escapeJava(urlUserId));
			return urlUserId.equals(userId);
		}
		
		return true;
		
	}

}
