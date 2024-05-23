package com.haygroup.leap.domain;

public class Subscription
{
	String id;
	String name;
	
	SubscriptionProductType[] productTypes;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the productTypes
	 */
	public SubscriptionProductType[] getProductTypes() {
		return productTypes;
	}

	/**
	 * @param productTypes the productTypes to set
	 */
	public void setProductType(SubscriptionProductType[] productTypes) {
		this.productTypes = productTypes;
	}
}
