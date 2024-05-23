/**
 * 
 */
package com.haygroup.pdf;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.haygroup.leap.cms.client.CMSApi;

/**
 * @author manchigs
 *
 */
public class CMSApiImpl implements CMSApi {

	private static final Logger logger = LoggerFactory.getLogger(CMSApiImpl.class);
	private String serverEndpoint;
	private String accessKey;
	private String loginEndpoint;
	private String createPdfFromHtmlEndoint;
	@Autowired
	Gson gson;
	
	public CMSApiImpl(String serverEndpoint, String loginEndpoint,String accessKey, String createPdfFromHtmlEndoint)
	{
		this.serverEndpoint				= serverEndpoint;
		this.loginEndpoint				= loginEndpoint;
		this.accessKey					= accessKey;
		this.createPdfFromHtmlEndoint	= createPdfFromHtmlEndoint;
	}
	
	


	/**
	 * @return the serverEndpoint
	 */
	public String getServerEndpoint() {
		return serverEndpoint;
	}




	/**
	 * @param serverEndpoint the serverEndpoint to set
	 */
	public void setServerEndpoint(String serverEndpoint) {
		this.serverEndpoint = serverEndpoint;
	}




	/**
	 * @return the loginEndpoint
	 */
	public String getLoginEndpoint() {
		return loginEndpoint;
	}




	/**
	 * @param loginEndpoint the loginEndpoint to set
	 */
	public void setLoginEndpoint(String loginEndpoint) {
		this.loginEndpoint = loginEndpoint;
	}




	/**
	 * @return the createPdfFromHtmlEndoint
	 */
	public String getCreatePdfFromHtmlEndoint() {
		return createPdfFromHtmlEndoint;
	}




	/**
	 * @param createPdfFromHtmlEndoint the createPdfFromHtmlEndoint to set
	 */
	public void setCreatePdfFromHtmlEndoint(String createPdfFromHtmlEndoint) {
		this.createPdfFromHtmlEndoint = createPdfFromHtmlEndoint;
	}




	@Override
	public  String connect() {
		String retVal = null;

        try {
          	
        		URL url = new URL(getServerEndpoint() + getLoginEndpoint());

//        		try {
//				//	HttpsURLConnection con = HttpsClient.getConnection(true, url);
//				} catch (KeyManagementException e) {
//					e.printStackTrace();
//				} catch (NoSuchAlgorithmException e) {
//					e.printStackTrace();
//				}
    			
    			HttpsURLConnection uc = (HttpsURLConnection) url.openConnection();
    			uc.setConnectTimeout(64000);
    			uc.setReadTimeout(64000);
    			uc.setDoOutput(true);
    			uc.setRequestMethod("POST");

    				
    			uc.setRequestProperty("Content-Type", "application/json");
    			uc.setRequestProperty("Cache-Control", "no-cache");
    			uc.setRequestProperty("Accept", "*/*");
    			
  				ByteArrayOutputStream tempOS = new ByteArrayOutputStream();
				String postBody = "{\"accessKey\" : \""+getAccessKey() +"\"}";				
				int data;
				InputStream is = new ByteArrayInputStream(postBody.getBytes(StandardCharsets.UTF_8));
				if (is != null) {
					while ((data = is.read()) != -1) {
						uc.getOutputStream().write(data);
					}
				
				}
				else{
					logger.debug("Nothing to read from Source-Input");
				}
				uc.getOutputStream().flush();
				uc.getOutputStream().close();
 
    			uc.connect();
    			
    			logger.debug("Completed sending request to Target Server. Begining processing output from Target Server");
     			
    			
    			logger.debug("ResponseCode " + uc.getResponseCode());
    			if (uc.getResponseCode() == 200) {
    				is = (InputStream) uc.getInputStream();
    			} 
    			else {
    				is = (InputStream) uc.getErrorStream(); 
    			}

    			//Reading from Target-Input and Writing to Source-Output and temporary output
    			if (is != null) {
    				logger.debug("Reading from Target-Input and Writing to Source-Output");
    				tempOS = new ByteArrayOutputStream();
    				while ((data = is.read()) != -1) {
    					tempOS.write(data);
    				}
    				String outputJSON = tempOS.toString();
    				//TODO: Autowire this
    				gson = new Gson();
    				CMSTokenOutput tokenOutput = gson.fromJson(outputJSON, CMSTokenOutput.class);
    				if(tokenOutput!=null && tokenOutput.getSuccess().equalsIgnoreCase("true"))
    				{
    					retVal = tokenOutput.getToken();
    				}
    				else
    				{
    					retVal = null;
    				}
    				
    				is.close();
    			}
    			

        } catch (IOException e) {

			e.printStackTrace();
		}
        finally
        {
        	
        }
        return retVal;
		
	}

	public boolean stream(HttpServletRequest request, HttpServletResponse response,String token,String html)
	{
	    try {
	      	
	 			URL url = new URL(getServerEndpoint() + getCreatePdfFromHtmlEndoint());
	
	 			
	 			HttpsURLConnection uc = (HttpsURLConnection) url.openConnection();
	 			uc.setConnectTimeout(64000);
	 			uc.setReadTimeout(64000);
	 			uc.setDoOutput(true);
	 			uc.setRequestMethod("POST");
	
	 				
	 			uc.setRequestProperty("Content-Type", "application/json");
	 			uc.setRequestProperty("Cache-Control", "no-cache");
	 			uc.setRequestProperty("Accept", "application/pdf");
	 			uc.setRequestProperty("Authorization", token);
	 			logger.debug("Reading from Source-Input and Writing to Target-Output");
				ByteArrayOutputStream tempOS = new ByteArrayOutputStream();
				String postBody = "{\"input\": \""+html+"\"}";
				int data;				
				InputStream is = new ByteArrayInputStream(postBody.getBytes(StandardCharsets.UTF_8));
				if (is != null) {
					while ((data = is.read()) != -1) {
						uc.getOutputStream().write(data);
						//tempOS.write(data);
					}
				
				}else{
					logger.debug("Nothing to read from Source-Input");
				}
				uc.getOutputStream().flush();
				uc.getOutputStream().close();
	
	 			uc.connect();
	 			
	 			logger.debug("Completed sending request to Target Server. Begining processing output from Target Server");
	 			response.setContentType(uc.getContentType());
	 			response.setStatus(uc.getResponseCode());
	 			
	 			
	 			logger.debug("ResponseCode " + uc.getResponseCode());
	 			if (uc.getResponseCode() == 200) {
	 				is = (InputStream) uc.getInputStream();
	 			} else {
	 				is = (InputStream) uc.getErrorStream(); 
	 			}
	
	 			//Reading from Target-Input and Writing to Source-Output and temporary output
	 			if (is != null) {
	 				logger.debug("Reading from Target-Input and Writing to Source-Output");
	 				tempOS = new ByteArrayOutputStream();
	 				while ((data = is.read()) != -1) {
	 					response.getOutputStream().write(data);
	 					tempOS.write(data);
	 				}
	 				is.close();
	 			}
				
			} 
			 catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
				try{
					response.setStatus(200);
					response.setContentType("application/json");
					response.getOutputStream().print("{\"responseCode\" : \"RES.500\", \"responseMessage\" : \"System Error\"}");
				}catch(Exception ex){
					ex.printStackTrace();
				}
			} finally{
				
			}
	    return true;
	}
	
	public String getAccessKey() {
		return accessKey;
	}


	public void setAccessKey(String key) {
		this.accessKey = key;
	}


	/* (non-Javadoc)
	 * @see com.haygroup.pdf.CMSApi#getPDFFromHtml(java.lang.String)
	 */
	@Override
	public void getPDFFromHtml(String html) {
		

	}

}
