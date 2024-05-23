package com.haygroup.leap.client;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Sanjay_Manchiganti
 * 
 */
public interface RestProxy {

	public boolean stream(String urlId, HttpServletRequest request, HttpServletResponse response, String[] urlParameters, String httpMethod);
	public boolean secureStream(String urlId, HttpServletRequest request, HttpServletResponse response, String[] urlParameters, String httpMethod);
	public boolean stream(String urlId, HttpServletRequest request, HttpServletResponse response, String[] urlParameters);
	public byte[] stream(String urlId, HttpServletRequest request, String[] urlParameters, String method);
	public boolean secureStream(String urlId, HttpServletRequest request, HttpServletResponse response, String[] urlParameters);

}
