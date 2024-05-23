/**
 * 
 */
package com.haygroup.leap.client;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.HtmlUtils;

import com.haygroup.leap.EncryptionUtil;

/**
 * @author Srujana_Kolishetty
 *
 */
public class RestTemplateImpl implements RestTemplateProxy {
	
	private String userName;
	private String password;
	private String baseUrl;
	private String basicAuth;	
	 EncryptionUtil encryptionUtil=new EncryptionUtil();
	
	private static final Logger logger = LoggerFactory.getLogger(RestTemplateImpl.class);
	
	public RestTemplateImpl(String userName, String password, String baseUrl) {
		
		logger.debug("In Constructor");
		this.userName = encryptionUtil.decryptString(userName);
		this.password = encryptionUtil.decryptString(password);
		this.baseUrl = baseUrl;
	
		String authStr = this.userName + ":" + this.password;
	//	logger.debug("basicAuth="+Base64.getEncoder().encode(authStr.getBytes()));

		this.basicAuth = "Basic " + Base64.getEncoder().encodeToString(authStr.getBytes());
	
	}

	/* (non-Javadoc)
	 * @see com.haygroup.leap.client.RestTemplate#postForEntity(java.lang.String, java.lang.Object, java.lang.Class)
	 */
	@Override
	public String postStringForEntity(String url, String requestJson, Map urlParams, HttpServletRequest request, HttpServletResponse response)
	{
		String responseBody = null;
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
	//	logger.debug("postStringForEntity-->basicAuth="+this.basicAuth);

		headers.add("Authorization", this.basicAuth);

		HttpEntity<String> entity = new HttpEntity<String>(requestJson, headers);
		
		RestTemplate restTemplate = new RestTemplate();
		// Add the Jackson and String message converters
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
		
		String urlString = this.baseUrl+url;
		
		logger.debug("Opening Connection to Target Server URL-{}-", HtmlUtils.htmlEscape(urlString));
		logger.debug("HTTP Method: POST");

		//ResponseEntity<String> responseEntity = restTemplate.postForEntity(this.baseUrl+url, entity, String.class);
		ResponseEntity responseEntity = restTemplate.exchange(urlString, HttpMethod.POST, entity, String.class, urlParams);
		
		if(responseEntity.hasBody()){
			responseBody = responseEntity.getBody().toString();
		}
		
		return responseBody;
	}
	
	/* (non-Javadoc)
	 * @see com.haygroup.leap.client.RestTemplate#postForEntity(java.lang.String, java.lang.Object, java.lang.Class)
	 */
	@Override
	public String postForEntity(String url, Map urlParams, HttpServletRequest request, HttpServletResponse response)
	{
		String responseBody = null;
		
		String requestJson = null;
		
		//get request Body and convert to string
		//requestJson = request.getBody();
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Authorization", this.basicAuth);
		//logger.debug("postForEntity-->basicAuth="+this.basicAuth);

		HttpEntity<String> entity = new HttpEntity<String>(requestJson, headers);
		
		RestTemplate restTemplate = new RestTemplate();
		// Add the Jackson and String message converters
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
		
		String urlString = this.baseUrl+url;
		
		logger.debug("Opening Connection to Target Server URL-{}-", HtmlUtils.htmlEscape(urlString));
		logger.debug("HTTP Method: POST");

		//ResponseEntity<String> responseEntity = restTemplate.postForEntity(this.baseUrl+url, entity, String.class);
		ResponseEntity<String> responseEntity = restTemplate.exchange(urlString, HttpMethod.POST, entity, String.class, urlParams);
		
		if(responseEntity.hasBody()){
			responseBody = responseEntity.getBody().toString();
		}
		
		return responseBody;
	}

	@Override
	public Object getImageForEntity(String url, Map urlParams, HttpServletRequest request, HttpServletResponse response) {
		
		Object responseBody = null;
		
		HttpHeaders headers = new HttpHeaders();
		
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		
		headers.add("Authorization", this.basicAuth);
		
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
		messageConverters.add(new ByteArrayHttpMessageConverter());

		HttpEntity<String> entity = new HttpEntity<String>(headers);
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setMessageConverters(messageConverters);

		String urlString = this.baseUrl + url;
		
		logger.debug("Opening Connection to Target Server URL-{}-", HtmlUtils.htmlEscape(urlString));
		logger.debug("HTTP Method: GET");
		
		ResponseEntity responseEntity = restTemplate.exchange(urlString, HttpMethod.GET, entity, Object.class, urlParams);

		if(responseEntity.hasBody()){
			if(responseEntity.getBody() != null)
			responseBody = responseEntity.getBody();
		}
		
		return responseBody;
	}
	
	@Override
	public String getStringForEntity(String url, Map urlParams, HttpServletRequest request, HttpServletResponse response) {
		
		String responseBody = null;
		
		HttpHeaders headers = new HttpHeaders();
		
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		headers.add("Authorization", this.basicAuth);
	//	logger.debug("getStringForEntity-->basicAuth="+this.basicAuth);

		HttpEntity<String> entity = new HttpEntity<String>(headers);
		
		RestTemplate restTemplate = new RestTemplate();
		
		String urlString = this.baseUrl + url;
		
		logger.debug("Opening Connection to Target Server URL-{}-", HtmlUtils.htmlEscape(urlString));
		logger.debug("HTTP Method: GET");
		
		ResponseEntity responseEntity = restTemplate.exchange(urlString, HttpMethod.GET, entity, String.class, urlParams);

		if(responseEntity.hasBody()){
			if(responseEntity.getBody() != null)
			responseBody = responseEntity.getBody().toString();
		}
		
		return responseBody;
	}


	@Override
	public String putForEntity(String url, String requestJson, Map urlParams, HttpServletRequest request, HttpServletResponse response) {
		
		String responseBody = null;
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Authorization", this.basicAuth);
	//	logger.debug("putForEntity-->basicAuth="+this.basicAuth);

		HttpEntity<String> entity = new HttpEntity<String>(requestJson, headers);
		
		RestTemplate restTemplate = new RestTemplate();
		// Add the Jackson and String message converters
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
		
		String urlString = this.baseUrl+url;
		
		logger.debug("Opening Connection to Target Server URL-{}-", HtmlUtils.htmlEscape(urlString));
		logger.debug("HTTP Method: PUT");

		//ResponseEntity<String> responseEntity = restTemplate.postForEntity(this.baseUrl+url, entity, String.class);
		ResponseEntity responseEntity = restTemplate.exchange(urlString, HttpMethod.PUT, entity, String.class, urlParams);
		
		if(responseEntity.hasBody()){
			responseBody = responseEntity.getBody().toString();
		}
		
		return responseBody;
	}


}
