package com.haygroup.leap.security;

import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.haygroup.leap.security2.CacheFactory;

/**
 * Base Filter
 */
public abstract class LeapFilter implements Filter {

	CacheFactory cacheFactory;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
		//DI not available outside Spring framework. So manually retrieving the authenticationManager bean
		ServletContext servletContext = filterConfig.getServletContext();
		WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		AutowireCapableBeanFactory autowireCapableBeanFactory = webApplicationContext.getAutowireCapableBeanFactory();
		autowireCapableBeanFactory.configureBean(this, "cacheFactory");
		cacheFactory = (CacheFactory) autowireCapableBeanFactory.getBean("cacheFactory");

	}

	@Override
	public void destroy() {
		cacheFactory = null;
		
	}
	
	

}
