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
@RequestMapping(value="/hrms/paydatacollection/postprocessing") 
public class PayDataCollectionMarketDefinitionsController 
{

	private static final Logger logger = LoggerFactory.getLogger(PayDataCollectionMarketDefinitionsController.class);
	
	@Autowired
	private LeapUtil leapUtil;
	@Autowired
	private RestProxy restProxyForUPM;
	
	//TODO: IS
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/marketdefinitions", method = RequestMethod.GET)
	public void getMarketDefinitions(
			@RequestParam(value = "countryCode", defaultValue = "") String countryCode,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxyForUPM.stream("getMarketDefinitions", request, response, 
		new String[] {countryCode});

	}
	

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/marketdefinitions/{id}", method = RequestMethod.GET)
	public void getMarketDefinitionsById(
			@PathVariable String id,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxyForUPM.stream("getMarketDefinitionsById", request, response, 
		new String[] {id});

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/marketdefinitions", method = RequestMethod.POST)
	public void addMarketDefinitions(
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxyForUPM.stream("addMarketDefinitions", request, response, 
		new String[] {});

	}
		

	
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/marketdefinitions/{id}", method = RequestMethod.PUT)
	public void updateMarketDefinitionById(
			@PathVariable String id,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxyForUPM.stream("updateMarketDefinitionById", request, response, 
		new String[] {id});

	}
	
}
