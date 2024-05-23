package com.haygroup.leap.activity;

import java.io.Serializable;
import java.util.HashMap;

public interface Activity extends Serializable {

	public abstract String getUrl();

	public abstract void setUrl(String url);

	public abstract String getRequestData();

	public abstract void setRequestData(String requestData);

	public abstract String getResponseData();

	public abstract void setResponseData(String responseData);

	public abstract long getRequestTimeStamp();

	public abstract void setRequestTimeStamp(long requestTimeStamp);
	
	public abstract long getResponseTimeStamp();

	public abstract void setResponseTimeStamp(long responseTimeStamp);

	public abstract String getAuthToken();

	public abstract void setAuthToken(String authToken);

	public abstract String getServerId();

	public abstract void setServerId(String serverId);

	public String getMethod();

	public void setMethod(String method);

	public String getRemoteIP();

	public void setRemoteIP(String remoteIP);

	public String getRemoteHost();

	public void setRemoteHost(String remoteHost);

	public String getRemotePort();

	public void setRemotePort(String remotePort);
	
	public HashMap<String, String> getUrlParameters();
	
	public void setUrlParameters(HashMap<String, String> urlParameters);
}