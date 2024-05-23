package com.haygroup.leap.domain;

import java.util.List;

import org.springframework.stereotype.Component;

import com.haygroup.leap.security.CORSFilter;
public class CORSAttributes
{
	private String allowedHeaders;
	private String allowedMethods;
	private String exposedHeaders;
	private String cacheControl;
	private String allowedOrigins;


	public void initFilter() {
		CORSFilter.setCorsattrs(this);
	}
	/**
	 * @return the allowedOrigins
	 */
	public String getAllowedOrigins() {
		return allowedOrigins;
	}

	/**
	 * @param allowedOrigins the allowedOrigins to set
	 */
	public void setAllowedOrigins(String allowedOrigins) {
		this.allowedOrigins = allowedOrigins;
	}

	public String getAllowedMethods() {
		return allowedMethods;
	}

	public void setAllowedMethods(String allowedMethods) {
		this.allowedMethods = allowedMethods;
	}

	public String getExposedHeaders() {
		return exposedHeaders;
	}

	public void setExposedHeaders(String exposedHeaders) {
		this.exposedHeaders = exposedHeaders;
	}

	public String getCacheControl() {
		return cacheControl;
	}

	public void setCacheControl(String cacheControl) {
		this.cacheControl = cacheControl;
	}

	public CORSAttributes(String allowedHeaders)
	{
		this.allowedHeaders = allowedHeaders;
	}
	
	public String getAllowedHeaders() {
		return allowedHeaders;
	}

	public void setAllowedHeaders(String allowedHeaders) {
		this.allowedHeaders = allowedHeaders;
	}

	public CORSAttributes(String allowedHeaders, String allowedMethods, String exposedHeaders, String cacheControl,
			String allowedOrigins) {
		super();
		this.allowedHeaders = allowedHeaders;
		this.allowedMethods = allowedMethods;
		this.exposedHeaders = exposedHeaders;
		this.cacheControl = cacheControl;
		this.allowedOrigins = allowedOrigins;
	}


}
