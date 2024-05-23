package com.haygroup.leap.security2;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.HtmlUtils;

import com.haygroup.leap.common.AuthPropertiesUtil;
import com.haygroup.leap.common.HGLeapConstants;

public class PicPreAuthenticateRequestFilterChain extends OncePerRequestFilter {

	private CacheFactory cacheFactory;
	
	private static final Logger logger = LoggerFactory.getLogger(PicPreAuthenticateRequestFilterChain.class);
	private static HashMap<String, LeapAuthenticationToken> staticTokens;
	
	public PicPreAuthenticateRequestFilterChain(CacheFactory cacheFactory)
	{
		super();
		this.cacheFactory = cacheFactory;
		logger.debug("Creating LeapAuthenticationTokens for staticToken");
		staticTokens = new HashMap<String, LeapAuthenticationToken>();
		
		Map<String, String> staticKeys = AuthPropertiesUtil.getStaticKeys();
		
		if(staticKeys != null){
			Iterator<String> keys = staticKeys.keySet().iterator();
			LeapAuthenticationToken leapAuthToken;
			UserData obj;
			
			while (keys.hasNext()) {
				String key = keys.next();
				String authToken = staticKeys.get(key);
				
				obj = new UserData();
				obj.setAuthToken(authToken);
				obj.setUsername("anon");
				obj.setUserid("0");
				obj.setRoles(new String[]{HGLeapConstants.ROLE_USER});
				
				leapAuthToken = new LeapAuthenticationToken(obj);
				staticTokens.put(authToken, leapAuthToken);
		
				logger.debug("LeapAuthenticationTokens for {} with role ",StringEscapeUtils.escapeJava(authToken), leapAuthToken.getAuthorities());
			}
		}
		logger.debug("finished creating static tokens");
		
	}
	
	@Override
	protected void initFilterBean() throws ServletException {
		super.initFilterBean();
	}

	@Override
	public void destroy() {
		
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
  				
  		String url = request.getRequestURI();
		String authToken = request.getParameter(HGLeapConstants.AUTH_TOKEN);
		logger.debug("PicPre-Authentication URL - {} for AuthToken - {}" , StringEscapeUtils.escapeJava(url), StringEscapeUtils.escapeJava(authToken));
		if(authToken==null)
		{
			logger.debug("No authToken in request parameter.  Getting from header");
			authToken = request.getHeader(HGLeapConstants.AUTH_TOKEN);	
		}
		
				
		if(staticTokens.containsKey(authToken)){
			logger.debug("authToken {} is valid. Setting Spring Security Context",StringEscapeUtils.escapeJava(authToken));
			SecurityContextHolder.getContext().setAuthentication(staticTokens.get(authToken));
		}
		else if(cacheFactory.getInstance().isAuthenticated(url, authToken))
		{
			LeapAuthenticationToken token = (LeapAuthenticationToken) cacheFactory.getInstance().get(authToken);
			SecurityContextHolder.getContext().setAuthentication(token);
		}
		else
		{
			logger.debug("authToken {} is not valid", StringEscapeUtils.escapeJava(authToken));
		}
		
		filterChain.doFilter(request, response);

	}

}
