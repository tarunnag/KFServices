package com.haygroup.leap;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.haygroup.leap.client.RestProxy;
import com.haygroup.leap.common.HGLeapConstants;
import com.haygroup.leap.common.LeapUtil;
import com.haygroup.leap.common.PropertiesUtil;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value="/objects")  
public class ResourceObjectsController {
	
	private static final Logger logger = LoggerFactory.getLogger(ResourceObjectsController.class);
	@Autowired
	private LeapUtil leapUtil;
	@Autowired
	private RestProxy restProxy;
	
	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "", method = RequestMethod.GET)
	public void getResource(
			@RequestParam(value = "authToken", defaultValue = "") String authToken,
			@RequestParam(value = "preferredLocale", defaultValue = "") String preferredLocale,
			@RequestParam(value = "objectId", defaultValue = "") String objectId,
			@RequestParam(value = "objectType", defaultValue = "") String objectType,
			@RequestParam(value = "countryId", defaultValue = "") String countryId,
			@RequestParam(value = "type", defaultValue = "") String type,
			HttpServletRequest request,
			HttpServletResponse response) {

		//TODO: Doing it here since the authToken is coming in url request parameter instead of header.  Other cases the RestProxy appends it by default
		//
		String loggedInUserId 	= leapUtil.getUserAttribute(authToken,HGLeapConstants.USER_ID);
		String clientId		 	= leapUtil.getUserAttribute(authToken,HGLeapConstants.CLIENT_ID);
		restProxy.stream("getResource", request, response, new String[] { objectId,objectType,preferredLocale,countryId,authToken,loggedInUserId,clientId,type});

	}
	
	
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "payanalyticsimage", method = RequestMethod.POST)
	public void addPayAnalyticsImage(
			String clientId,String countryId, String reportType, String objectType,String preferredLocale,
			HttpServletRequest request, HttpServletResponse response) 
	{
		restProxy.stream("addPayAnalyticsImage", request, response, new String[] {clientId,countryId,reportType,objectType,preferredLocale});
	}	
	
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "payanalyticsimage", method = RequestMethod.PUT)
	public void updatePayAnalyticsImage(
			String clientId,String countryId, String reportType, String objectType,
			String preferredLocale,
			HttpServletRequest request, HttpServletResponse response) 
	{
		restProxy.stream("updatePayAnalyticsImage", request, response, new String[] {clientId,countryId,reportType,objectType,preferredLocale });
	}	
	

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "payanalyticsimage", method = RequestMethod.DELETE)
	public void deletePayAnalyticsImage(
			String preferredLocale, String objectId, String objectType,
			HttpServletRequest request, HttpServletResponse response) 
	{
		restProxy.stream("deletePayAnalyticsImage", request, response, new String[] {objectId,objectType,preferredLocale });

	}	
	
	
	
}
