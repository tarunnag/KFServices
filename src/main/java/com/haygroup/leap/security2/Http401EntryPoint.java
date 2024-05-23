package com.haygroup.leap.security2;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.haygroup.leap.HomeController;

public class Http401EntryPoint implements AuthenticationEntryPoint {

	private static final Logger logger = LoggerFactory.getLogger(Http401EntryPoint.class);

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
			throws IOException, ServletException {
		
		
		logger.debug("entry point called. Rejecting access");
        
		request.getRequestDispatcher("/error").forward(request, response);

	}

}
