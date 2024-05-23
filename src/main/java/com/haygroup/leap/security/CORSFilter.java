
package com.haygroup.leap.security;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpUtils;

import org.apache.commons.lang.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.util.HtmlUtils;

import com.haygroup.leap.common.PropertiesUtil;
import com.haygroup.leap.domain.CORSAttributes;
import org.apache.commons.lang.StringEscapeUtils;

public class CORSFilter implements Filter {
	private static final Logger logger 	= LoggerFactory.getLogger(CORSFilter.class);
	private static CORSAttributes corsattrs;
	
	public static void setCorsattrs(CORSAttributes corsAttributes) {
		logger.debug("Setting CORS attributes");
		CORSFilter.corsattrs = corsAttributes;
	}

	@Override
	public void destroy() {

	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException,
			ServletException {
			
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
	//	CORSAttributes corsattrs = null;
		logger.info("cors filter start " + Thread.currentThread().getId());
	//	logger.info(corsattrs.getAllowedHeaders());
		
		String reqOrigin 		= httpRequest.getHeader("Origin");

		String allowOrigin 		= "*";



	//	if(corsattrs==null)
	//	{
//			corsattrs = new CORSAttributes("");

		//String allowedOrigins 			= PropertiesUtil.getProperty(corsattrs.getAllowedOrigins());
		String allowedOrigins 			= corsattrs.getAllowedOrigins();
			
		logger.debug("Req Origin is [{}], alloweds origin is [{}]", StringEscapeUtils.escapeJava(reqOrigin),StringEscapeUtils.escapeJava(allowedOrigins));
		logger.debug("Remote host [{}],request url [{}] :"+StringEscapeUtils.escapeJava(httpRequest.getRemoteHost()),StringEscapeUtils.escapeJava(httpRequest.getRequestURL().toString()));
		
		if(reqOrigin!=null && allowedOrigins!=null)
		{

			String [] arr 		= allowedOrigins.split(",");
			List<String> listAllowedOrigins = Arrays.asList(arr);
			
			if(listAllowedOrigins.contains(reqOrigin))
			{
				logger.debug("Using [{}] for Allow Origin", StringEscapeUtils.escapeJava( reqOrigin));
				httpResponse.addHeader("Access-Control-Allow-Origin",reqOrigin==null?null:StringEscapeUtils.escapeJava(reqOrigin.trim()));
				httpResponse.addHeader("Vary", "Origin");
			}
			else
			{
				logger.warn("Request Origin {} not in allowed list, not adding",StringEscapeUtils.escapeJava(reqOrigin));
			}
			
		}
		else
		{
			httpResponse.addHeader("Access-Control-Allow-Origin", "*");
		}
		
		// Adding CORS Headers to All Requests
		httpResponse.addHeader("Access-Control-Allow-Headers",corsattrs.getAllowedHeaders());
		httpResponse.addHeader("Access-Control-Allow-Methods",corsattrs.getAllowedMethods());
		httpResponse.addHeader("Access-Control-Expose-Headers", corsattrs.getExposedHeaders());
		httpResponse.addHeader("Cache-Control", corsattrs.getCacheControl());
		
/*
        // Set the HttpOnly attribute
        String cookieName = "aws-waf-token";
        String cookieValue = "yourTokenValue";

        // Manually construct Set-Cookie header with HttpOnly attribute
        String cookieHeaderValue = cookieName+"; HttpOnly";

        // Add the cookie to the response header
        httpResponse.addHeader("Set-Cookie", cookieHeaderValue);	
     */   
        httpResponse.setHeader("Content-Security-Policy", "default-src 'self'");

		filterChain.doFilter(request, response);


		logger.info("cors filter end "+ Thread.currentThread().getId());
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
