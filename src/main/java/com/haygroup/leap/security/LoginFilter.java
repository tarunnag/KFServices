package com.haygroup.leap.security;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.haygroup.leap.domain.LoginResponse;
import com.haygroup.leap.security2.UserData;

/**
 * Filter for Login to retrieve AuthToken from response
 */
public class LoginFilter extends LeapFilter {

	private static final Logger logger = LoggerFactory.getLogger(LoginFilter.class);

	Gson gson = new Gson();

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException,
			ServletException {
//		logger.info("start " + Thread.currentThread().getId());
//		// Wrapper to hold response
//		GenericResponseWrapper wrapper = new GenericResponseWrapper((HttpServletResponse) response);
//
//		filterChain.doFilter(request, wrapper);
//
//		if (wrapper.getStatus() == 200) {
//			
//			LoginResponse loginResponse = gson.fromJson(new String(wrapper.getData()), LoginResponse.class);
//
//			String authToken = loginResponse.getData().getAuthToken();
//			String userId = loginResponse.getData().getUserId() + "";
//
//			logger.info("Login Response UserID {} AuthToken {}", userId, authToken);
//			UserData userData = new UserData();
//			userData.setUserid(userId);
//			userData.setAuthToken(authToken);
//			userData.setUsername(loginResponse.getData().getUsername());
//			userData.setRoles(new String[]{"ROLE_USER", "ROLE_PRICING"});
//			
//			authenticationManager.addAuthToken(authToken, userData);
//			
//		}
//
//		OutputStream out = response.getOutputStream();
//		out.write(wrapper.getData());
//		out.close();
//
//		logger.info("end " + Thread.currentThread().getId());
	}

}
