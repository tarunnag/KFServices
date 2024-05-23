package com.haygroup.leap.responses;

import com.haygroup.leap.domain.Location;

public class LocationResponse
{
	private String responseCode;
	private String responseMessage;
	
	private Location[] data;

	/**
	 * @return the responseCode
	 */
	public String getResponseCode() {
		return responseCode;
	}

	/**
	 * @param responseCode the responseCode to set
	 */
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	/**
	 * @return the responseMessage
	 */
	public String getResponseMessage() {
		return responseMessage;
	}

	/**
	 * @param responseMessage the responseMessage to set
	 */
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	/**
	 * @return the data
	 */
	public Location[] getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(Location[] data) {
		this.data = data;
	} 
}
