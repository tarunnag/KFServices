package com.haygroup.leap.domain;

import com.google.gson.Gson;

public class LoginResponse extends BaseResponse{

	
	private ISLoginResponse data;
	
	
	
	
	/**
	 * @return the data
	 */
	public ISLoginResponse getData() {
		return data;
	}




	/**
	 * @param data the data to set
	 */
	public void setData(ISLoginResponse data) {
		this.data = data;
	}

	public static void main(String[] args)
	{
		String response = "{\"responseCode\":\"RES.20098\",\"responseMessage\":\"User logged in  successfully\"," +
				"\"data\":{\"username\":\"om_baghel@haygroup.com\",\"userId\":5,\"authToken\":\"b972b764-c5f4-4e80-b01f-4468eb461cf9\",\"expirationDateTime\":1383538648573,\"noOfInvalidAttempts\":0,\"accountStatus\":\"0\",\"idleTimeoutInMinutes\":30}}";
		
		Gson gson = new Gson();
		LoginResponse resp = gson.fromJson(response, LoginResponse.class);
		System.out.println("Response is "+resp.getResponseCode());
		System.out.println("AuthToken is "+resp.getData().getAuthToken());
		
		
	}
	
	
}
