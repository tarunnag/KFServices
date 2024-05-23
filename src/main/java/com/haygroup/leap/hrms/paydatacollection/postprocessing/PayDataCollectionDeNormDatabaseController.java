package com.haygroup.leap.hrms.paydatacollection.postprocessing;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.haygroup.leap.client.RestProxy;
import com.haygroup.leap.common.LeapUtil;

@Controller
@RequestMapping(value="/hrms/paydatacollection/postprocessing/denormdatabases") 
public class PayDataCollectionDeNormDatabaseController 
{

	private static final Logger logger = LoggerFactory.getLogger(PayDataCollectionDeNormDatabaseController.class);
	
	@Autowired
	private LeapUtil leapUtil;
	@Autowired
	private RestProxy restProxyForUPM;
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "", method = RequestMethod.GET)
	public void getDenormdatabases(
			@RequestParam(value = "countryCode", defaultValue = "") String countryCode,
			@RequestParam(value = "denormType", defaultValue = "") String denormType,
			@RequestParam(value = "denormStatus", defaultValue = "") String denormStatus,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxyForUPM.stream("getDenormdatabases", request, response, 
		new String[] {countryCode,denormType,denormStatus});

	}
	

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public void getDeNormDatabaseById(
			@PathVariable String id,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxyForUPM.stream("getDeNormDatabaseById", request, response, 
		new String[] {id});

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "", method = RequestMethod.POST)
	public void addDenormdatabase(
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxyForUPM.stream("addDenormdatabase", request, response, 
		new String[] {});

	}
		
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public void updateDenormDatabaseById(
			@PathVariable String id,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxyForUPM.stream("updateDenormDatabaseById", request, response, 
		new String[] {id});

	}
	
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/metadata", method = RequestMethod.GET)
	public void getDenormDatabaseMetadata(
			@RequestParam(value = "countryCode", defaultValue = "") String countryCode,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxyForUPM.stream("getDenormDatabaseMetadata", request, response, 
		new String[] {countryCode});

	}
	
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/updatedata", method = RequestMethod.POST)
	public void updateDenormData(
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxyForUPM.stream("updateDenormData", request, response, 
		new String[] {});

	}

}
