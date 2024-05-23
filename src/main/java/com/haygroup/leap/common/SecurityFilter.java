package com.haygroup.leap.common;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SecurityFilter implements javax.servlet.Filter
{

	private static final Logger logger = LoggerFactory.getLogger(SecurityFilter.class);

	@Override
	public void destroy()
	{
		// TODO Auto-generated method stub
 
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException
	{
/*
		HttpServletRequest req = (HttpServletRequest) arg0;
		String propToken = PropertiesUtil.getProperty("authToken"); 

		//LeapUtil.printSessionTokens();
		String authToken = req.getHeader("authToken");
		logger.info("token:======" + authToken);
		
		String url=req.getRequestURI();

		logger.info("Url of the request is "+url);
		
		((HttpServletResponse)arg1).addHeader("Access-Control-Allow-Origin", "*");
		((HttpServletResponse)arg1).addHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept,authToken,locale");
		((HttpServletResponse)arg1).addHeader("Access-Control-Allow-Methods","PUT, POST, GET, DELETE");
		
		
		if( !(url.contains("login") || url.contains("forgotpassword") || url.contains("resetpassword") || url.contains("/configs/application")
				|| url.contains("changepassword")))
		{	
			logger.info("Is token valid? "+LeapUtil.isSessionValid(authToken));
			if(authToken == null || LeapUtil.isSessionValid(authToken)==false)
			{
					authToken = req.getParameter("authToken");
					logger.info("authToken from parameter is "+authToken);
					if (authToken == null || LeapUtil.isSessionValid(authToken)==false)
					{
						logger.info("Not a valid auth token from parameter or token is null from parameter");
						req.getRequestDispatcher("/error").forward(arg0, arg1);
					}
					else
					{
						logger.info("Forwarding to home page with parameter from request");
						req.setAttribute(HGLeapConstants.AUTH_TOKEN, authToken);
						arg2.doFilter(arg0, arg1);
					}
			}
			else
			{
				req.setAttribute(HGLeapConstants.AUTH_TOKEN, authToken);
				arg2.doFilter(arg0, arg1);
			}
		}
		else
		{
			logger.info("Not Checking for any tokens");
			arg2.doFilter(arg0, arg1);			
		}

*/
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException
	{
		// TODO Auto-generated method stub

	}

}
