/**
 * 
 */
package com.haygroup.leap;


import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.haygroup.leap.client.RestProxy;
import com.haygroup.leap.cms.client.CMSApiImpl;
import com.haygroup.leap.cms.client.PDFInput;
import com.haygroup.leap.common.HGLeapConstants;
import com.haygroup.leap.common.LeapUtil;
import com.haygroup.leap.domain.LoginResponse;
import com.haygroup.leap.domain.Subscription;
import com.haygroup.leap.domain.SubscriptionProductType;
import com.haygroup.leap.domain.SubscriptionResponse;
import com.haygroup.leap.security.GenericResponseWrapper;
import com.haygroup.leap.security2.CacheFactory;
import com.haygroup.leap.security2.LeapAuthenticationToken;
import com.haygroup.leap.security2.UserData;
//import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.sun.org.apache.xml.internal.security.utils.Base64;



 
/**
 * @author Sanjay_Manchiganti
 * 
 */
@Controller
@RequestMapping(value = "/actions")
public class ActionsController {

	private static final Logger logger = LoggerFactory.getLogger(ActionsController.class);
	@Autowired
	private LeapUtil leapUtil; 
	@Autowired
	private RestProxy restProxy;
	@Autowired
	private CacheFactory cacheFactory;
	@Autowired
	Gson gson;
	@Autowired
	CMSApiImpl cmsClient;
	/**
	 * 
	 * 
	 * @param model
	 * @param username
	 * @param password
	 * @param response
	 * @deprecated
	 */
	
//	@PreAuthorize("hasRole('ROLE_USER')")
//	@RequestMapping(value = "/searchlocations1", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
//	public @ResponseBody
//	String searchLocations1(@RequestParam(value = "userId") String userId,
//			@RequestParam(value = "queryString", defaultValue = "") String queryString, HttpServletResponse response) {
//
//		logger.info("searchLocations with userId,queryString of " + userId + "," + queryString);
//		GMapsClient obj = new GMapsClient();
//		String jsonResponse = obj.getCities(queryString);
//
//		if (jsonResponse == null || jsonResponse.equals("")) {
//			response.setStatus(204);
//			return "";
//		}
//		logger.info("Return json is :" + jsonResponse);
//		return jsonResponse;
//
//	}
	
	
	/**
	 * 
	 * 
	 * @param model
	 * @param username
	 * @param password
	 * @param response
	 */
	// Removed
	/*@PreAuthorize("hasRole('ROLE_USER') and hasRole('ROLE_JOB_PRICING')")
	@RequestMapping(value = "/pricingchart/{resultId}", method = RequestMethod.GET)
	public void getPricingChart(@PathVariable String resultId, Model model,HttpServletRequest request, HttpServletResponse response) {

		restProxy.stream("pricingChart", request, response, new String[] {});

	}*/

	/**
	 * 
	 * 
	 * @param model
	 * @param username
	 * @param password
	 * @param response
	 */
	
	@RequestMapping(value = "/forgotpassword", method = RequestMethod.POST)
	public void forgotPassword(Model model, HttpServletRequest request, HttpServletResponse response) {

		restProxy.stream("forgotPassword", request, response, new String[] {});

	}

	/**
	 * @param model
	 * @param response
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public void oldLogin(Model model, HttpServletRequest request, HttpServletResponse response) {
		
		String sessionId = request.getHeader("ps-session-id");
		
		GenericResponseWrapper wrapper = new GenericResponseWrapper((HttpServletResponse) response);
		restProxy.stream("login", request, wrapper, new String[] {});
		
		Gson gson = new Gson();

		if (wrapper.getStatus() == 200) {
			LoginResponse loginResponse = gson.fromJson(new String(wrapper.getData()), LoginResponse.class);

			String authToken = loginResponse.getData().getAuthToken();
			long userId = loginResponse.getData().getUserId();

			logger.info("Calling Rest Proxy");
			
			byte[] stream = restProxy.stream("getSubscriptions", (HttpServletRequest)request, new String[] {userId+""},"GET");
			

			UserData userData = new  UserData();
			userData.setAuthToken(loginResponse.getData().authToken);
			userData.setUsername(loginResponse.getData().username);
			userData.setUserid(loginResponse.getData().userId + "");
			userData.setLocale(loginResponse.getData().getLocale());
			userData.setClientId(loginResponse.getData().getClientId());
			userData.setPrivacyConsent(loginResponse.getData().isPrivacyConsent());
			userData.setSessionId(sessionId);
			
			List<String> roles = new ArrayList<String>();
			//Adding Internal user Role access
			roles.add(HGLeapConstants.ROLE_USER);
			for (String clientId : HGLeapConstants.CLIENT_IDS) {
			if(loginResponse.getData().getClientId().equals(clientId)) {
				roles.add(HGLeapConstants.ROLE_INTERNAL_USER);
			}
			}
			//logger.info("Stream is :"+stream);
			if(stream!=null)
			{
				SubscriptionResponse subscriptionResponse = gson.fromJson(new String(stream),SubscriptionResponse.class);
			
				if(subscriptionResponse!=null)
				{
					
					Subscription[] subResponseData = subscriptionResponse.getData();
				
					if(subResponseData!=null && subResponseData.length>0)
					{
						SubscriptionProductType[] subscriptionProductTypes = subResponseData[0].getProductTypes();
					
					
						logger.info("Inside Subscription response. Subscription product Types length "+subscriptionProductTypes.length);
						
						for(int index=0;index<subscriptionProductTypes.length;index++)
						{
							
							logger.info("Inside Subscription response. Subscription product:  "+
									StringEscapeUtils.escapeJava(subscriptionProductTypes[index].getName()));
							String prodType = subscriptionProductTypes[index].getName();
							
							if(prodType.equals(HGLeapConstants.PRODUCT_JOB_PRICING) && "true".equalsIgnoreCase(subscriptionProductTypes[index].getAccess()))
							{
								roles.add(HGLeapConstants.ROLE_JOB_PRICING);
							}
							else if(prodType.equals(HGLeapConstants.PRODUCT_JOB_GRADING) && "true".equalsIgnoreCase(subscriptionProductTypes[index].getAccess()))
							{
								roles.add(HGLeapConstants.ROLE_JOB_GRADING);
							}
							else if(prodType.equals(HGLeapConstants.PRODUCT_STYLES_AND_CLIMATE) && "true".equalsIgnoreCase(subscriptionProductTypes[index].getAccess()))
							{
								roles.add(HGLeapConstants.ROLE_STYLES_AND_CLIMATE);
							}
							else if(prodType.equalsIgnoreCase(HGLeapConstants.PRODUCT_JOURNEY) && "true".equalsIgnoreCase(subscriptionProductTypes[index].getAccess()))
							{
								roles.add(HGLeapConstants.ROLE_JOURNEY);
							}
							else if(prodType.equalsIgnoreCase(HGLeapConstants.PRODUCT_SCRIBE) && "true".equalsIgnoreCase(subscriptionProductTypes[index].getAccess()))
							{
								roles.add(HGLeapConstants.ROLE_SCRIBE);
							}
							else if(prodType.equalsIgnoreCase(HGLeapConstants.PRODUCT_EMBARK) && "true".equalsIgnoreCase(subscriptionProductTypes[index].getAccess()))
							{
								roles.add(HGLeapConstants.ROLE_EMBARK);
							}
							
						}
					
					}
					
					logger.info("User Roles: " + roles.size() + StringEscapeUtils.escapeJava(roles.toString()));
					
					userData.setRoles(roles.toArray(new String[roles.size()]));
					String application = request.getHeader("application");

								
					if(application!=null && (application.equalsIgnoreCase(HGLeapConstants.APPLICATION_JOURNEY)
											 || 
											 application.equalsIgnoreCase(HGLeapConstants.APPLICATION_ACTIVATE)
											 ||
											 application.equalsIgnoreCase(HGLeapConstants.APPLICATION_JOB_MAPPING)
											 ))
					{
						cacheFactory.getInstance().addAuthToken(authToken, HGLeapConstants.SESSION_LONG_DURATION,userData); 
					}
					else
					{
						cacheFactory.getInstance().addAuthToken(authToken, HGLeapConstants.SESSION_SHORT_DURATION,userData); 
					}
				}	
			}
		}
		logger.debug("Starting to write response back to the response");
		OutputStream out;
		try 
		{
			out = response.getOutputStream();
			out.write(wrapper.getData());
			out.close();
		}
		catch (IOException e) 
		{
			logger.error("Unable to write to response");
			e.printStackTrace();
		}
		
		
	}

	/**
	 * @param model
	 * @param response
	 */
	@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_INTELLIGENCE_CLOUD')")
	@RequestMapping(value = "/logout", method = RequestMethod.PUT)
	public void logout(Model model, HttpServletRequest request, HttpServletResponse response) {
		
		String authToken = request.getHeader(HGLeapConstants.AUTH_TOKEN);
		if(authToken != null){
			cacheFactory.getInstance().removeAuthToken(authToken);
		}
		restProxy.stream("logout", request, response, new String[] {});
		
	}

	/**
	 * @param model
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/changepassword", method = RequestMethod.POST)
	public void changePassword(
			HttpServletRequest request, HttpServletResponse response) {
		
		restProxy.stream("changePassword", request, response, new String[] {});

	}

	/**
	 * @param model
	 * @param response
	 */
	@RequestMapping(value = "/resetpassword", method = RequestMethod.POST)
	public void resetPassword(HttpServletRequest request, HttpServletResponse response) {
		
		restProxy.stream("resetPassword", request, response, new String[] {});

	}
	
	@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_INTELLIGENCE_CLOUD')")
	@RequestMapping(value = "/istokenvalid", method = RequestMethod.GET)
	public void isTokenValid(HttpServletRequest request, HttpServletResponse response) {

	}
	
	@RequestMapping(value = "/sendemail", method = RequestMethod.POST)
	public void sendGenericEmail(HttpServletRequest request, HttpServletResponse response) 
	{
		restProxy.stream("sendGenericEmail", request, response, new String[] { });
	} 
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/launchpaynet", method = RequestMethod.POST)
	public void postLaunchPayNet(HttpServletRequest request, HttpServletResponse response) 
	{
		
		logger.debug("--postLaunchPayNet"  );
		restProxy.stream("postLaunchPayNet", request, response, new String[] { });
	} 
	
	
	/**
	 * @param model
	 * @param response
	 * Delete once the new method works
	 */
	@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_INTELLIGENCE_CLOUD')")
	@RequestMapping(value = "/proxylogin", method = RequestMethod.POST)
	public void oldProxylogin(Model model, HttpServletRequest request, HttpServletResponse response) {
		//Set by the browser
		String sessionId = request.getHeader("ps-session-id");
		GenericResponseWrapper wrapper = new GenericResponseWrapper((HttpServletResponse) response);
		restProxy.stream("proxylogin", request, wrapper, new String[] {});
		
		Gson gson = new Gson();

		if (wrapper.getStatus() == 200) {
			LoginResponse loginResponse = gson.fromJson(new String(wrapper.getData()), LoginResponse.class);

			String authToken = loginResponse.getData().getAuthToken();
			long userId = loginResponse.getData().getUserId();

			logger.info("Login Response UserID {} AuthToken {}", userId, authToken);
			
			logger.info("Calling Rest Proxy");
			
			byte[] stream = restProxy.stream("getSubscriptions", (HttpServletRequest)request, new String[] {userId+""},"GET");
			
			UserData userData = new  UserData();
			userData.setAuthToken(loginResponse.getData().authToken);
			userData.setUsername(loginResponse.getData().username);
			userData.setUserid(loginResponse.getData().userId + "");
			userData.setLocale(loginResponse.getData().getLocale());
			userData.setClientId(loginResponse.getData().getClientId());
			userData.setSessionId(sessionId);
			userData.setPrivacyConsent(true);//setting as true by default for shadow accounts
			
			//userData.setPrivacyConsent(loginResponse.getData().isPrivacyConsent());
			
			List<String> roles = new ArrayList<String>();
			roles.add(HGLeapConstants.ROLE_USER);
			
			if(stream!=null)
			{
				SubscriptionResponse subscriptionResponse = gson.fromJson(new String(stream),SubscriptionResponse.class);
			
				if(subscriptionResponse!=null)
				{
					
					Subscription[] subResponseData = subscriptionResponse.getData();
				
					if(subResponseData!=null && subResponseData.length>0)
					{
						SubscriptionProductType[] subscriptionProductTypes = subResponseData[0].getProductTypes();
					
					
						logger.info("Inside Subscription response. Subscription product Types length "+subscriptionProductTypes.length);
						
						for(int index=0;index<subscriptionProductTypes.length;index++)
						{
							
							logger.info("Inside Subscription response. Subscription product:  "+
									StringEscapeUtils.escapeJava(subscriptionProductTypes[index].getName()));
							String prodType = subscriptionProductTypes[index].getName();
							
							if(prodType.equals(HGLeapConstants.PRODUCT_JOB_PRICING) && "true".equalsIgnoreCase(subscriptionProductTypes[index].getAccess()))
							{
								roles.add(HGLeapConstants.ROLE_JOB_PRICING);
							}
							else if(prodType.equals(HGLeapConstants.PRODUCT_JOB_GRADING) && "true".equalsIgnoreCase(subscriptionProductTypes[index].getAccess()))
							{
								roles.add(HGLeapConstants.ROLE_JOB_GRADING);
							}
							else if(prodType.equals(HGLeapConstants.PRODUCT_STYLES_AND_CLIMATE) && "true".equalsIgnoreCase(subscriptionProductTypes[index].getAccess()))
							{
								roles.add(HGLeapConstants.ROLE_STYLES_AND_CLIMATE);
							}
							else if(prodType.equalsIgnoreCase(HGLeapConstants.PRODUCT_JOURNEY) && "true".equalsIgnoreCase(subscriptionProductTypes[index].getAccess()))
							{
								roles.add(HGLeapConstants.ROLE_JOURNEY);
							}
							else if(prodType.equalsIgnoreCase(HGLeapConstants.PRODUCT_SCRIBE) && "true".equalsIgnoreCase(subscriptionProductTypes[index].getAccess()))
							{
								roles.add(HGLeapConstants.ROLE_SCRIBE);
							}
							else if(prodType.equalsIgnoreCase(HGLeapConstants.PRODUCT_EMBARK) && "true".equalsIgnoreCase(subscriptionProductTypes[index].getAccess()))
							{
								roles.add(HGLeapConstants.ROLE_EMBARK);
							}
							
						}
					
					}
					
					logger.info("User Roles: " + roles.size() + StringEscapeUtils.escapeJava(roles.toString()));
					
					userData.setRoles(roles.toArray(new String[roles.size()]));
					
					String application = request.getHeader("application");
								
					if(application!=null && (application.equalsIgnoreCase(HGLeapConstants.APPLICATION_JOURNEY)
											 || 
											 application.equalsIgnoreCase(HGLeapConstants.APPLICATION_ACTIVATE)
											 ||
											 application.equalsIgnoreCase(HGLeapConstants.APPLICATION_JOB_MAPPING)
											 ))
					{
						cacheFactory.getInstance().addAuthToken(authToken, HGLeapConstants.SESSION_LONG_DURATION,userData); 
					}
					else
					{
						cacheFactory.getInstance().addAuthToken(authToken, HGLeapConstants.SESSION_SHORT_DURATION,userData); 
					}
				}	
			}
		}
		logger.debug("Starting to write response back to the response");
		OutputStream out;
		try 
		{
			out = response.getOutputStream();
			out.write(wrapper.getData());
			out.close();
		}
		catch (IOException e) 
		{
			logger.error("Unable to write to response");
			e.printStackTrace();
		}
		
		
	}


	
	/**
	 * @param model
	 * @param response
	 * Delete once the new method works
	 */
	@RequestMapping(value = "/newproxylogin", method = RequestMethod.POST)
	public void proxylogin(Model model, HttpServletRequest request, HttpServletResponse response) {
		
		loginHelper(request, response, "proxylogin");
	}


	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/payreferencedata", method = RequestMethod.GET)
	public void getPayReferenceData(
			@RequestParam(value = "isMock", defaultValue = "") String isMock,
			@RequestParam(value = "outputType", defaultValue = "") String outputType,
			
			HttpServletRequest request, HttpServletResponse response) 
	{
		restProxy.stream("getPayReferenceData", request, response, new String[] { isMock,outputType });
	}	
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/pricedresults", method = RequestMethod.GET)
	public void getPricedResults(
			@RequestParam(value = "jobRoleTypeId", defaultValue = "") String jobRoleTypeId,
			@RequestParam(value = "standardHayGrade", defaultValue = "") String standardHayGrade,
			@RequestParam(value = "countryId", defaultValue = "") String countryId,
			@RequestParam(value = "regionId", defaultValue = "") String regionId,
			@RequestParam(value = "jobSubFamilyId", defaultValue = "") String jobSubFamilyId,
			HttpServletRequest request, HttpServletResponse response) 
	{
		restProxy.stream("getPricedResults", request, response, new String[] { jobRoleTypeId, standardHayGrade, countryId, regionId, jobSubFamilyId });
	}	
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/marketinsight", method = RequestMethod.GET)
	public void getMarketInsight(
			@RequestParam(value = "countryId", defaultValue = "") String countryId,
			@RequestParam(value = "applicationName", defaultValue = "PAY") String applicationName,
			@RequestParam(value = "type", defaultValue = "MARKETSIGHT") String type,
			@RequestParam(value = "locale", defaultValue = "en") String locale,
			HttpServletRequest request, HttpServletResponse response) 
	{
		String userId = leapUtil.getUserId(request.getHeader(HGLeapConstants.AUTH_TOKEN));
		restProxy.stream("getMarketInsight", request, response, new String[] { userId, countryId ,applicationName,type,locale});
	}
	
//	@RequestMapping(value = "/generatepdfMock", method = RequestMethod.GET)
//	public void getPricedResults(
//			@RequestParam(value = "contents", defaultValue = "") String contents,
//			HttpServletRequest request, HttpServletResponse response) 
//	{
//		
//		response.setContentType("application/pdf");
//        response.setHeader("Content-disposition", "attachment; filename=" + "report1.pdf");
//        
//        //Generate PDF using prince
//       // Prince pr = new Prince("/usr/local/bin/prince");
//        //Prince pr = new Prince("C:\\Program Files (x86)\\prince\\prince-11.3-win64\\bin\\prince");
//       // try {
//		//	pr.convertString("<html><head></head><body>		<img src=\"https://testjobmapping.haygroup.biz/i/korn-ferry-logo.jpg\">"+
//		//		"</body></html>", response.getOutputStream());
//        	
////		} catch (IOException e1) {
//
//	//		e1.printStackTrace();
//	//	}
//    
//	}	
	
//	@RequestMapping(value = "/generatepdf", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
//	public void generatePDF(
//			HttpEntity<String> httpEntity,HttpServletRequest request, HttpServletResponse response) 
//	{
//		String json = httpEntity.getBody();
//		
//		response.setContentType("application/pdf");
//        response.setHeader("Content-disposition", "attachment; filename=" + "report1.pdf");
//        
//        Gson gson = new Gson();
//        PDFInput input = gson.fromJson(json, PDFInput.class);
//        String data = input.getData(); 
//        
//
//	}
	
	@RequestMapping(value = "/generatepdfV2", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public void generatePDFV2(
			HttpEntity<String> httpEntity,HttpServletRequest request, HttpServletResponse response) 
	{
		String json = httpEntity.getBody();
		
		response.setContentType("application/pdf");
        response.setHeader("Content-disposition", "attachment; filename=" + "report1.pdf");
        
        Gson gson = new Gson();
        PDFInput input = gson.fromJson(json, PDFInput.class);
        String data = input.getData();
        
        try {
			byte[] decodedString = Base64.decode(data);
			String html = new String(decodedString);
			generatePDFFromCMS(request, response,html);
        
		} 
        catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	public void generatePDFFromCMS(HttpServletRequest request, HttpServletResponse response,String html) 
	{

		String token = cmsClient.connect();
		if(token!=null)
		{
			cmsClient.stream(request,response,token,html);
		}
		else
		{
			logger.error("cmsapi token is null");
		}
	}
	

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/getpowerbireporttoken", method = RequestMethod.POST)
	public void getPowerBIReportToken(
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("getPowerBIReportToken", request, response, new String[] { });
		
	}	
	
	/**
	 * @param model
	 * @param response
	 */
	@RequestMapping(value = "/ssologin", method = RequestMethod.POST)
	public void loginSSO(HttpServletRequest request, HttpServletResponse response) {
	//	loginHelper(request, response,"ssologin");
		
		String sessionId = request.getHeader("ps-session-id");
			GenericResponseWrapper wrapper = new GenericResponseWrapper((HttpServletResponse) response);
			restProxy.stream("ssologin", request, wrapper, new String[] {});
			
			Gson gson = new Gson();

			if (wrapper.getStatus() == 200) {
				LoginResponse loginResponse = gson.fromJson(new String(wrapper.getData()), LoginResponse.class);

				String authToken = loginResponse.getData().getAuthToken();
				long userId = loginResponse.getData().getUserId();

				
				logger.info("Calling Rest Proxy");
				
				byte[] stream = restProxy.stream("getSubscriptions", (HttpServletRequest)request, new String[] {userId+""},"GET");
				
				UserData userData = new  UserData();
				userData.setAuthToken(loginResponse.getData().authToken);
				userData.setUsername(loginResponse.getData().username);
				userData.setUserid(loginResponse.getData().userId + "");
				userData.setLocale(loginResponse.getData().getLocale());
				userData.setClientId(loginResponse.getData().getClientId());
				userData.setSessionId(sessionId);
				userData.setPrivacyConsent(loginResponse.getData().isPrivacyConsent());
				userData.setUserAccess(loginResponse.getData().getJwtClientCode());
			
				logger.info("Login Response UserID {} AuthToken {} Username {}", userId,  StringEscapeUtils.escapeJava(authToken));
				logger.info("Before Adding Role"+StringEscapeUtils.escapeJava(loginResponse.getData().getJwtClientCode()));
				List<String> roles = new ArrayList<String>();
				//Adding Internal user Role access
				for (String clientId : HGLeapConstants.CLIENT_IDS) {
					if(loginResponse.getData().getClientId().equals(clientId)) {
						roles.add(HGLeapConstants.ROLE_INTERNAL_USER);
					}
				}
				 if(HGLeapConstants.INTELLIGENCE_CLOUD.equals(loginResponse.getData().getJwtClientCode())) {
					roles.add(HGLeapConstants.ROLE_INTELLIGENCE_CLOUD);
					logger.info("After Adding Role"+StringEscapeUtils.escapeJava(loginResponse.getData().getJwtClientCode()));
				}else {
						roles.add(HGLeapConstants.ROLE_USER);
				}
				if(stream!=null)
				{
					SubscriptionResponse subscriptionResponse = gson.fromJson(new String(stream),SubscriptionResponse.class);
				
					if(subscriptionResponse!=null)
					{
						
						Subscription[] subResponseData = subscriptionResponse.getData();
					
						if(subResponseData!=null && subResponseData.length>0)
						{
							SubscriptionProductType[] subscriptionProductTypes = subResponseData[0].getProductTypes();
						
						
							logger.info("Inside Subscription response. Subscription product Types length "+subscriptionProductTypes.length);
							
							for(int index=0;index<subscriptionProductTypes.length;index++)
							{
								
								logger.info("Inside Subscription response. Subscription product:  "+
										StringEscapeUtils.escapeJava(subscriptionProductTypes[index].getName()));
								String prodType = subscriptionProductTypes[index].getName();
								
								if(prodType.equals(HGLeapConstants.PRODUCT_JOB_PRICING) && "true".equalsIgnoreCase(subscriptionProductTypes[index].getAccess()))
								{
									roles.add(HGLeapConstants.ROLE_JOB_PRICING);
								}
								else if(prodType.equals(HGLeapConstants.PRODUCT_JOB_GRADING) && "true".equalsIgnoreCase(subscriptionProductTypes[index].getAccess()))
								{
									roles.add(HGLeapConstants.ROLE_JOB_GRADING);
								}
								else if(prodType.equals(HGLeapConstants.PRODUCT_STYLES_AND_CLIMATE) && "true".equalsIgnoreCase(subscriptionProductTypes[index].getAccess()))
								{
									roles.add(HGLeapConstants.ROLE_STYLES_AND_CLIMATE);
								}
								else if(prodType.equalsIgnoreCase(HGLeapConstants.PRODUCT_JOURNEY) && "true".equalsIgnoreCase(subscriptionProductTypes[index].getAccess()))
								{
									roles.add(HGLeapConstants.ROLE_JOURNEY);
								}
								else if(prodType.equalsIgnoreCase(HGLeapConstants.PRODUCT_SCRIBE) && "true".equalsIgnoreCase(subscriptionProductTypes[index].getAccess()))
								{
									roles.add(HGLeapConstants.ROLE_SCRIBE);
								}
								else if(prodType.equalsIgnoreCase(HGLeapConstants.PRODUCT_EMBARK) && "true".equalsIgnoreCase(subscriptionProductTypes[index].getAccess()))
								{
									roles.add(HGLeapConstants.ROLE_EMBARK);
								}
								
							}
						
						}
						
						logger.info("User Roles: " + roles.size() +  StringEscapeUtils.escapeJava(roles.toString()));
						
						userData.setRoles(roles.toArray(new String[roles.size()]));
						
						String application = request.getHeader("application");
									
						if(application!=null && (application.equalsIgnoreCase(HGLeapConstants.APPLICATION_JOURNEY)
												 || 
												 application.equalsIgnoreCase(HGLeapConstants.APPLICATION_ACTIVATE)
												 ||
												 application.equalsIgnoreCase(HGLeapConstants.APPLICATION_JOB_MAPPING)
												 ))
						{
							cacheFactory.getInstance().addAuthToken(authToken, HGLeapConstants.SESSION_LONG_DURATION,userData); 
						}
						else
						{
							cacheFactory.getInstance().addAuthToken(authToken, HGLeapConstants.SESSION_SHORT_DURATION,userData); 
						}
					}	
				}
			}
			logger.debug("Starting to write response back to the response");
			OutputStream out;
			try 
			{
				out = response.getOutputStream();
				out.write(wrapper.getData());
				out.close();
			}
			catch (IOException e) 
			{
				logger.error("Unable to write to response");
				e.printStackTrace();
			}
	}

	
	/**
	 * @param model
	 * @param response
	 */
	@RequestMapping(value = "/newlogin", method = RequestMethod.POST)
	public void login(Model model, HttpServletRequest request, HttpServletResponse response) {
		
		loginHelper(request, response,"login");
	}
	
	
	private void loginHelper(HttpServletRequest request, HttpServletResponse response,String loginType)
	{
		//Set by the browser
		String sessionId = request.getHeader("ps-session-id");
		GenericResponseWrapper wrapper = new GenericResponseWrapper((HttpServletResponse) response);
		restProxy.stream(loginType, request, wrapper, new String[] {});
		
		Gson gson = new Gson();

		if (wrapper.getStatus() == 200) {
			LoginResponse loginResponse = gson.fromJson(new String(wrapper.getData()), LoginResponse.class);

			String authToken = loginResponse.getData().getAuthToken();
			long userId = loginResponse.getData().getUserId();

			
			logger.info("Calling Rest Proxy");
			
			byte[] stream = restProxy.stream("getSubscriptions", (HttpServletRequest)request, new String[] {userId+""},"GET");
			
			UserData userData = new  UserData();
			userData.setAuthToken(loginResponse.getData().authToken);
			userData.setUsername(loginResponse.getData().username);
			userData.setUserid(loginResponse.getData().userId + "");
			userData.setLocale(loginResponse.getData().getLocale());
			userData.setClientId(loginResponse.getData().getClientId());
			userData.setSessionId(sessionId);
			userData.setPrivacyConsent(loginResponse.getData().isPrivacyConsent());
			
			logger.info("Login Response UserID {} AuthToken {} Username {}", userId,  StringEscapeUtils.escapeJava(authToken));
			logger.info("Before Adding Role"+StringEscapeUtils.escapeJava(loginResponse.getData().getJwtClientCode()));
			List<String> roles = new ArrayList<String>();
			roles.add(HGLeapConstants.ROLE_USER);
			
			

			if(stream!=null)
			{
				SubscriptionResponse subscriptionResponse = gson.fromJson(new String(stream),SubscriptionResponse.class);
			
				if(subscriptionResponse!=null)
				{
					
					Subscription[] subResponseData = subscriptionResponse.getData();
				
					if(subResponseData!=null && subResponseData.length>0)
					{
						SubscriptionProductType[] subscriptionProductTypes = subResponseData[0].getProductTypes();
					
					
						logger.info("Inside Subscription response. Subscription product Types length "+subscriptionProductTypes.length);
						
						for(int index=0;index<subscriptionProductTypes.length;index++)
						{
							
							logger.info("Inside Subscription response. Subscription product:  "+
									StringEscapeUtils.escapeJava(subscriptionProductTypes[index].getName()));
							String prodType = subscriptionProductTypes[index].getName();
							
							if(prodType.equals(HGLeapConstants.PRODUCT_JOB_PRICING) && "true".equalsIgnoreCase(subscriptionProductTypes[index].getAccess()))
							{
								roles.add(HGLeapConstants.ROLE_JOB_PRICING);
							}
							else if(prodType.equals(HGLeapConstants.PRODUCT_JOB_GRADING) && "true".equalsIgnoreCase(subscriptionProductTypes[index].getAccess()))
							{
								roles.add(HGLeapConstants.ROLE_JOB_GRADING);
							}
							else if(prodType.equals(HGLeapConstants.PRODUCT_STYLES_AND_CLIMATE) && "true".equalsIgnoreCase(subscriptionProductTypes[index].getAccess()))
							{
								roles.add(HGLeapConstants.ROLE_STYLES_AND_CLIMATE);
							}
							else if(prodType.equalsIgnoreCase(HGLeapConstants.PRODUCT_JOURNEY) && "true".equalsIgnoreCase(subscriptionProductTypes[index].getAccess()))
							{
								roles.add(HGLeapConstants.ROLE_JOURNEY);
							}
							else if(prodType.equalsIgnoreCase(HGLeapConstants.PRODUCT_SCRIBE) && "true".equalsIgnoreCase(subscriptionProductTypes[index].getAccess()))
							{
								roles.add(HGLeapConstants.ROLE_SCRIBE);
							}
							else if(prodType.equalsIgnoreCase(HGLeapConstants.PRODUCT_EMBARK) && "true".equalsIgnoreCase(subscriptionProductTypes[index].getAccess()))
							{
								roles.add(HGLeapConstants.ROLE_EMBARK);
							}
							
						}
					
					}
					
					logger.info("User Roles: " + roles.size() +  StringEscapeUtils.escapeJava(roles.toString()));
					
					userData.setRoles(roles.toArray(new String[roles.size()]));
					
					String application = request.getHeader("application");
								
					if(application!=null && (application.equalsIgnoreCase(HGLeapConstants.APPLICATION_JOURNEY)
											 || 
											 application.equalsIgnoreCase(HGLeapConstants.APPLICATION_ACTIVATE)
											 ||
											 application.equalsIgnoreCase(HGLeapConstants.APPLICATION_JOB_MAPPING)
											 ))
					{
						cacheFactory.getInstance().addAuthToken(authToken, HGLeapConstants.SESSION_LONG_DURATION,userData); 
					}
					else
					{
						cacheFactory.getInstance().addAuthToken(authToken, HGLeapConstants.SESSION_SHORT_DURATION,userData); 
					}
				}	
			}
		}
		logger.debug("Starting to write response back to the response");
		OutputStream out;
		try 
		{
			out = response.getOutputStream();
			out.write(wrapper.getData());
			out.close();
		}
		catch (IOException e) 
		{
			logger.error("Unable to write to response");
			e.printStackTrace();
		}
		
		
	}
	
	/**
	* @param model
	* @param response
	* @throws IOException 
	* @throws ServletException 
	*/
	@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_INTELLIGENCE_CLOUD')")
	@RequestMapping(value = "/token/metadata", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public void getTokenMetaData(Model model, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userId = leapUtil.getUserAttribute(request.getHeader(HGLeapConstants.AUTH_TOKEN),HGLeapConstants.USER_ID);
		String locale = leapUtil.getUserAttribute(request.getHeader(HGLeapConstants.AUTH_TOKEN),HGLeapConstants.LOCALE);
		String clientId = leapUtil.getUserAttribute(request.getHeader(HGLeapConstants.AUTH_TOKEN), HGLeapConstants.CLIENT_ID);
		
		// get AuthToken
		String authToken = request.getHeader("authToken");
		if (authToken == null) {
		authToken = request.getParameter("authToken");
		}
		logger.debug("authToken - {}", StringEscapeUtils.escapeJava(authToken));
	
		// url
		String url = request.getRequestURI();
		logger.debug("URL - {}", StringEscapeUtils.escapeJava(url));
		UserData userData = new UserData();
		
		if(cacheFactory.getInstance().isAuthenticated(url, authToken)){
			logger.debug("AuthToken {} is valid", StringEscapeUtils.escapeJava(authToken));
			request.setAttribute(HGLeapConstants.AUTH_TOKEN, authToken);
			userData.setAuthToken(authToken);
			userData.setUserid(userId);
			userData.setLocale(locale);
			userData.setClientId(clientId);
		}
		else{
			logger.debug("AuthToken {} is not authorized", StringEscapeUtils.escapeJava(authToken));
			request.getRequestDispatcher("/error").forward(request, response);
		 
		}
		
		Gson gson = new Gson();
		String outputJson = gson.toJson(userData);
		logger.debug("Starting to write response back to the response");
		
		OutputStream out;
		try 
		{
			out = response.getOutputStream();
			out.write(outputJson.getBytes());
			out.close();
		}
		catch (IOException e) 
		{
			logger.error("Unable to write to response");
			e.printStackTrace();
		}
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/supportedlanguages", method = RequestMethod.GET)
	public void getSupportedLanguages(
			@RequestParam(value = "productId", defaultValue = "") String productId, 
			HttpServletRequest request, HttpServletResponse response) {
		
		restProxy.stream("getSupportedLanguages", request, response, new String[] {productId});

	}
	
	/**
	* @param applicationName
	* @author Harsha 
	*/
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/getappconfig", method = RequestMethod.GET)
	public void getAppConfigurations(
			@RequestParam(value = "applicationName", defaultValue = "") String applicationName, 
			HttpServletRequest request, HttpServletResponse response) {
		restProxy.stream("getAppConfigurations", request, response, new String[] {applicationName});
	}
	
	/**
	* @param model
	* @param response
	* @throws IOException 
	* @throws ServletException 
	*/
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/refreshlocale", method = RequestMethod.POST)
	public void refreshUserLocale(@RequestParam(value = "userLocale", defaultValue = "") String userLocale,
			Model model, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		logger.debug("refreshLocale API to reset the cache with the new user language");
		
		// get AuthToken
		String authToken = request.getHeader("authToken");
		if (authToken == null) {
		authToken = request.getParameter("authToken");
		}
		logger.debug("authToken - {}", StringEscapeUtils.escapeJava(authToken));
	
		// url
		String url = request.getRequestURI();
		logger.debug("URL - {}", StringEscapeUtils.escapeJava(url));
		
		LeapAuthenticationToken leapTokenObj = (LeapAuthenticationToken) cacheFactory.getInstance().get(authToken);
		UserData userData = (UserData) leapTokenObj.getUserData();
		
		if(cacheFactory.getInstance().isAuthenticated(url, authToken)){
			logger.debug("AuthToken is valid",  StringEscapeUtils.escapeJava(authToken));
			request.setAttribute(HGLeapConstants.AUTH_TOKEN, authToken);
			
			logger.debug("User locale is reset in the UserData");
			userData.setLocale(userLocale);
			
			logger.debug("Remove current object from cache to reset data");
			cacheFactory.getInstance().removeAuthToken(authToken);
			
			//set the user data back to the cache
			logger.debug("Reset the user data object back to the cache");
			cacheFactory.getInstance().addAuthToken(authToken, HGLeapConstants.SESSION_SHORT_DURATION, userData);
		}
		else{
			logger.debug("AuthToken is not valid. Exiting!", StringEscapeUtils.escapeJava(authToken));
			request.getRequestDispatcher("/error").forward(request, response);
		 
		}
	}
	
	@RequestMapping(value = "/genderanalysis/{id}", method = RequestMethod.GET)
	public void getGenderAnalysis(
			@PathVariable String id,
			Model model, HttpServletRequest request, HttpServletResponse response) {

		restProxy.stream("getGenderAnalysis", request, response, new String[] {id});

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/privacypolicy/accepted", method = RequestMethod.PUT)
	public void updatePrivacyPolicy( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		logger.debug("updatePrivacyPolicy API to reset the cache with the new user privacy consent flag");

		// get AuthToken
		String authToken = request.getHeader("authToken");
		if (authToken == null) {
		authToken = request.getParameter("authToken");
		}
		logger.debug("authToken - {}", StringEscapeUtils.escapeJava(authToken));

		// url
		String url = request.getRequestURI();
		logger.debug("URL - {}", StringEscapeUtils.escapeJava(url));

		LeapAuthenticationToken leapTokenObj = (LeapAuthenticationToken) cacheFactory.getInstance().get(authToken);
		UserData userData = (UserData) leapTokenObj.getUserData();

		if(cacheFactory.getInstance().isAuthenticated(url, authToken)){
			logger.debug("AuthToken is valid", StringEscapeUtils.escapeJava(authToken));
			request.setAttribute(HGLeapConstants.AUTH_TOKEN, authToken);

			logger.debug("User privacy consent is reset in the UserData");
			userData.setPrivacyConsent(true);

			logger.debug("Remove current object from cache to reset data");
			cacheFactory.getInstance().removeAuthToken(authToken);

			//set the user data back to the cache
			logger.debug("Reset the user data object back to the cache");
			cacheFactory.getInstance().addAuthToken(authToken, HGLeapConstants.SESSION_SHORT_DURATION, userData);
		}
		else{
			logger.debug("AuthToken is not valid. Exiting!",  StringEscapeUtils.escapeJava(authToken));
			request.getRequestDispatcher("/error").forward(request, response);
		}

	}
	
	@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_INTELLIGENCE_CLOUD')")
	@RequestMapping(value = "/generatetijwttoken", method = RequestMethod.POST)
	public void generateTIJWTTtoken(
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("generateTIJWTTtoken", request, response, new String[] { });		
	}
	 
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/validateauthtoken", method = RequestMethod.POST)
	public void validateAndGetUserDetailsByAuthToken(
			HttpServletRequest request, HttpServletResponse response) {
		restProxy.stream("validateAndGetUserDetailsByAuthToken", request, response, new String[] {});
	}
}
	