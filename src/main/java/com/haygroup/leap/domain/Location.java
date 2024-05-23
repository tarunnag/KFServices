package com.haygroup.leap.domain;

/**
 * @author Sanjay_Manchiganti
 *
 */
public class Location 
{

	private String locality;
	private String county;
	private String state;
	private String countryCode;
	private String longitude;
	private String latitude;
	private String description;

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	
	/**
	 * @return
	 */
	public String getLocality() {
		return locality;
	}

	
	/**
	 * @param locality
	 */
	public void setLocality(String locality) {
		this.locality = locality;
	}

	
	/**
	 * @return
	 */
	public String getCounty() {
		return county;
	}

	
	/**
	 * @param county
	 */
	public void setCounty(String county) {
		this.county = county;
	}

	
	/**
	 * @return
	 */
	public String getState() {
		return state;
	}

	
	/**
	 * @param state
	 */
	public void setState(String state) {
		this.state = state;
	}

	
	/**
	 * @return the countryCode
	 */
	public String getCountryCode() {
		return countryCode;
	}

	/**
	 * @param countryCode the countryCode to set
	 */
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	/**
	 * @return
	 */
	public String getLongitude() {
		return longitude;
	}

	
	/**
	 * @param longitude
	 */
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	
	/**
	 * @return
	 */
	public String getLatitude() {
		return latitude;
	}

	
	/**
	 * @param latitude
	 */
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

}
