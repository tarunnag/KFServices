package com.haygroup.leap.client;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Srujana_Kolishetty
 *
 */

public interface RestTemplateProxy {
	
	public String postForEntity(String url, Map urlParams, HttpServletRequest request, HttpServletResponse response);
	
	public Object getImageForEntity(String url, Map urlParams, HttpServletRequest request, HttpServletResponse response);
	
	public String getStringForEntity(String url, Map urlParams, HttpServletRequest request, HttpServletResponse response);
	
	public String putForEntity(String url, String requestJson, Map urlParams, HttpServletRequest request, HttpServletResponse response);

	String postStringForEntity(String url, String requestJson, Map urlParams,
			HttpServletRequest request, HttpServletResponse response);

}
