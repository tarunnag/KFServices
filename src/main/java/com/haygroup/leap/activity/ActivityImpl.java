package com.haygroup.leap.activity;

import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ActivityImpl implements Activity{
	
	String url;
	String requestData;
	String responseData;
	long requestTimeStamp;
	long responseTimeStamp;
	long totalTimeTaken;
	String authToken;
	String serverId;
	String method;
	String remoteIP;
	String remoteHost;
	String remotePort;
	HashMap<String, String> urlParameters;
	HashMap<String, String> headers;
	
	
	private static final Logger logger = LoggerFactory.getLogger(ActivityImpl.class);

	public ActivityImpl(){
		
	}
	
	public ActivityImpl(HttpServletRequest request){
		this.url = request.getRequestURI();
		this.authToken = (String) request.getAttribute("authToken");
		this.method = request.getMethod();
		this.remoteIP = request.getRemoteAddr();
		this.remoteHost = request.getRemoteHost();
		this.remotePort = request.getRemotePort() + "";
		this.requestTimeStamp = new Date().getTime();

		this.urlParameters = new HashMap<String, String>();
		Enumeration enumParams = request.getParameterNames();
		while (enumParams.hasMoreElements()) {
			String name = (String) enumParams.nextElement();
			urlParameters.put(name, request.getParameter(name));
		}
		
		this.headers = new HashMap<String, String>();
		Enumeration headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String header = (String) headerNames.nextElement();
			headers.put(header, request.getHeader(header));
		}
	}
	
	
	/* (non-Javadoc)
	 * @see com.haygroup.leap.activity.Activity#getUrl()
	 */
	@Override
	public String getUrl() {
		return url;
	}
	/* (non-Javadoc)
	 * @see com.haygroup.leap.activity.Activity#setUrl(java.lang.String)
	 */
	@Override
	public void setUrl(String url) {
		this.url = url;
	}
	/* (non-Javadoc)
	 * @see com.haygroup.leap.activity.Activity#getRequestData()
	 */
	@Override
	public String getRequestData() {
		return requestData;
	}
	/* (non-Javadoc)
	 * @see com.haygroup.leap.activity.Activity#setRequestData(java.lang.String)
	 */
	@Override
	public void setRequestData(String requestData) {
		this.requestData = requestData;
	}
	/* (non-Javadoc)
	 * @see com.haygroup.leap.activity.Activity#getResponseData()
	 */
	@Override
	public String getResponseData() {
		return responseData;
	}
	/* (non-Javadoc)
	 * @see com.haygroup.leap.activity.Activity#setResponseData(java.lang.String)
	 */
	@Override
	public void setResponseData(String responseData) {
		this.responseData = responseData;
	}
	/* (non-Javadoc)
	 * @see com.haygroup.leap.activity.Activity#getRequestTimeStamp()
	 */
	@Override
	public long getRequestTimeStamp() {
		return requestTimeStamp;
	}
	/* (non-Javadoc)
	 * @see com.haygroup.leap.activity.Activity#setRequestTimeStamp(java.util.Date)
	 */
	@Override
	public void setRequestTimeStamp(long requestTimeStamp) {
		this.requestTimeStamp = (requestTimeStamp == 0)?new Date().getTime():requestTimeStamp;
	}
	/* (non-Javadoc)
	 * @see com.haygroup.leap.activity.Activity#getResponseTimeStamp()
	 */
	@Override
	public long getResponseTimeStamp() {
		return responseTimeStamp;
	}
	/* (non-Javadoc)
	 * @see com.haygroup.leap.activity.Activity#setResponseTimeStamp(java.util.Date)
	 */
	@Override
	public void setResponseTimeStamp(long responseTimeStamp) {
		this.responseTimeStamp = (responseTimeStamp == 0)?new Date().getTime():responseTimeStamp;
	}
	/* (non-Javadoc)
	 * @see com.haygroup.leap.activity.Activity#getAuthToken()
	 */
	@Override
	public String getAuthToken() {
		return authToken;
	}
	/* (non-Javadoc)
	 * @see com.haygroup.leap.activity.Activity#setAuthToken(java.lang.String)
	 */
	@Override
	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}
	/* (non-Javadoc)
	 * @see com.haygroup.leap.activity.Activity#getServerId()
	 */
	@Override
	public String getServerId() {
		return serverId;
	}
	/* (non-Javadoc)
	 * @see com.haygroup.leap.activity.Activity#setServerId(java.lang.String)
	 */
	@Override
	public void setServerId(String serverId) {
		this.serverId = serverId;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getRemoteIP() {
		return remoteIP;
	}

	public void setRemoteIP(String remoteIP) {
		this.remoteIP = remoteIP;
	}

	public String getRemoteHost() {
		return remoteHost;
	}

	public void setRemoteHost(String remoteHost) {
		this.remoteHost = remoteHost;
	}

	public String getRemotePort() {
		return remotePort;
	}

	public void setRemotePort(String remotePort) {
		this.remotePort = remotePort;
	}

	public HashMap<String, String> getUrlParameters() {
		return urlParameters;
	}

	public void setUrlParameters(HashMap<String, String> urlParameters) {
		this.urlParameters = urlParameters;
	}

	
}
