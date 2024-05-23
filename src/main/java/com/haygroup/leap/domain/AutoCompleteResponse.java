package com.haygroup.leap.domain;

import com.google.gson.Gson;

public class AutoCompleteResponse 
{

	private Predictions[] predictions; 
	
	public Predictions[] getPredictions() {
		return predictions;
	}

	public void setPredictions(Predictions[] predictions) {
		this.predictions = predictions;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String json = "{   \"predictions\":[{\"description\": \"Kingston, ON, Canada\"}]}";
		Gson gson = new Gson();
		AutoCompleteResponse response = gson.fromJson(json, AutoCompleteResponse.class);
		System.out.println(response.getPredictions()[0].getDescription());
	}

}
