package com.haygroup.leap.domain;

import java.util.HashMap;

public class Result 
{
	private AddressComponent[] address_components;
	private Geometry geometry;

	public AddressComponent[] getAddress_components() {
		return address_components;
	}

	public void setAddress_components(AddressComponent[] address_components) {
		this.address_components = address_components;
	}

	public Geometry getGeometry() {
		return geometry;
	}

	public void setGeometry(Geometry geometry) {
		this.geometry = geometry;
	}
	
	
	
	
}

