/**
 * 
 */
package com.haygroup.leap.hrms.payhub;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.haygroup.leap.client.RestProxy;
import com.haygroup.leap.common.LeapUtil;


@Controller
@RequestMapping(value="/hrms/payhub/references") 
public class PayHubReferencesController {
	
	private static final Logger logger = LoggerFactory.getLogger(PayHubReferencesController.class);
	
	@Autowired
	private LeapUtil leapUtil;
	@Autowired
	private RestProxy restProxy;
	/**
	 * 
	 * Service is used to get All families
	 * 
	 * @param model
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/industrypapers", method = RequestMethod.GET)
	public void getIndustryPapers(@RequestParam(value = "userId") String userId,
			@RequestParam(value = "clientId", defaultValue = "") String clientId,
			@RequestParam(value = "locale", defaultValue = "") String locale,	
			HttpServletRequest request, HttpServletResponse response) 
	{
	
		restProxy.stream("getIndustryPapers", request, response, new String[] { userId,clientId,locale});

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
	
	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/marketpercentile", method = RequestMethod.GET)
	public void getMarketpercentile(
			@RequestParam(value = "countryId") String countryId,
			@RequestParam(value = "dataSourceId") String dataSourceId,
			HttpServletRequest request, HttpServletResponse response) {
		
           restProxy.stream("getMarketpercentile", request, response, new String[] {countryId,dataSourceId});

	}
	
	
	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/askexperts", method = RequestMethod.GET)
	public void getAskExperts(
		HttpServletRequest request, HttpServletResponse response) {

		restProxy.stream("getAskExperts", request, response, new String[] {});

	}
	

	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/askexperts", method = RequestMethod.POST)
	public void addAskExperts(
		HttpServletRequest request, HttpServletResponse response) {

		restProxy.stream("addAskExperts", request, response, new String[] {});

	}	
	

	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/askexperts", method = RequestMethod.PUT)
	public void updateAskExperts(
		HttpServletRequest request, HttpServletResponse response) {

		restProxy.stream("updateAskExperts", request, response, new String[] {});

	}
	
	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/askexperts/{id}", method = RequestMethod.DELETE)
	public void deleteAskExperts(
			@PathVariable String id,
			@RequestParam(value = "countryCode", defaultValue = "") String countryCode,
		HttpServletRequest request, HttpServletResponse response) {

		restProxy.stream("deleteAskExperts", request, response, new String[] {id, countryCode});

	}
	
	
	
	
	


}
