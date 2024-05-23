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
@RequestMapping(value="/hrms/paydatacollection/postprocessing/calcengine/") 
public class PayDataCollectionPostProcessingCETransfer 
{

	private static final Logger logger = LoggerFactory.getLogger(PayDataCollectionPostProcessingCETransfer.class);
	
	@Autowired
	private LeapUtil leapUtil;
	@Autowired
	private RestProxy restProxyForUPM;


	@PreAuthorize("hasRole('ROLE_USER') and hasRole('ROLE_INTERNAL_USER')")
	@RequestMapping(value = "cecountries", method = RequestMethod.GET)
	public void getCECountries(
			@RequestParam(value = "countryCode", defaultValue = "") String countryCode,	
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxyForUPM.stream("getCECountries", request, response, 
		new String[] {countryCode});

	}
	
	@PreAuthorize("hasRole('ROLE_USER') and hasRole('ROLE_INTERNAL_USER')")
	@RequestMapping(value = "/cecountries/dependents", method = RequestMethod.GET)
	public void getCETransferDependents(
			@RequestParam(value = "countryCode", defaultValue = "") String countryCode,	
			@RequestParam(value = "countryId", defaultValue = "") String countryId,	
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxyForUPM.stream("getCETransferDependents", request, response, 
		new String[] {countryCode,countryId});

	}
	
	@PreAuthorize("hasRole('ROLE_USER') and hasRole('ROLE_INTERNAL_USER')")
	@RequestMapping(value = "transfertoupm", method = RequestMethod.POST)
	public void transferFromCEToUPM(
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxyForUPM.stream("transferFromCEToUPM", request, response, 
		new String[] {});

	}
	
}
