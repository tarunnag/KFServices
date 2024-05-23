package com.haygroup.leap.domain;

public class SubscriptionResponse extends LeapResponse
{

	Subscription[] data;

	/**
	 * @return the data
	 */
	public Subscription[] getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(Subscription[] data) {
		this.data = data;
	} 
	
	
}
