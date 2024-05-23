package com.haygroup.leap.hrms.paydatacollection;

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

@Controller
@RequestMapping(value="/hrms/paydatacollection/references") 
public class PayDataCollectionReferencesController 
{

	private static final Logger logger = LoggerFactory.getLogger(PayDataCollectionReferencesController.class);
	
//	@Autowired
//	private LeapUtil leapUtil;
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
	@RequestMapping(value = "/families", method = RequestMethod.GET)
	public void getFamilies(@RequestParam(value = "modelIds", defaultValue = "") String modelIds,
							@RequestParam(value = "familyIds", defaultValue = "") String familyIds,
			@RequestParam(value = "preferredLocale", defaultValue = "") String preferredLocale,	
			@RequestParam(value = "countryCode", defaultValue = "") String countryCode,	
			@RequestParam(value = "idc", defaultValue = "") String idc,	
			@RequestParam(value = "referenceLevel", defaultValue = "") String referenceLevel,
			@RequestParam(value = "sector", defaultValue = "") String sector,
			@RequestParam(value = "familyAndLevel", defaultValue = "") String familyAndLevel,
			HttpServletRequest request, HttpServletResponse response) 
	{
	
		restProxy.stream("getPaydataCollectionFamilies", request, response, new String[] { modelIds,familyIds,countryCode,idc,referenceLevel,sector,familyAndLevel,preferredLocale});

	}

	/**
	 * 
	 * Service is used to get all sub families given a family Id
	 * 
	 * @param model
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/families/{familyId}/subfamilies", method = RequestMethod.GET)
	public void getSubFamilies(@PathVariable String familyId, HttpServletRequest request,
			@RequestParam(value = "preferredLocale", defaultValue = "") String preferredLocale,	
			@RequestParam(value = "countryCode", defaultValue = "") String countryCode,	
			@RequestParam(value = "idc", defaultValue = "") String idc,	
			@RequestParam(value = "referenceLevel", defaultValue = "") String referenceLevel,
			@RequestParam(value = "sector", defaultValue = "") String sector,
			HttpServletResponse response) {

		restProxy.stream("getPaydataCollectionSubFamiliesFromFamilyId", request, response, new String[] { familyId,countryCode,idc,referenceLevel,sector,preferredLocale });

	}

	/**
	 * 
	 * Service is used to get all sub families given a family Id
	 * 
	 * @param model
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/subfamilies", method = RequestMethod.GET)
	public void getSubFamilies(HttpServletRequest request,
			@RequestParam(value = "preferredLocale", defaultValue = "") String preferredLocale,	
			@RequestParam(value = "subFamilyIds", defaultValue = "") String subFamilyIds,
			@RequestParam(value = "countryCode", defaultValue = "") String countryCode,	
			@RequestParam(value = "idc", defaultValue = "") String idc,	
			@RequestParam(value = "referenceLevel", defaultValue = "") String referenceLevel,	
			@RequestParam(value = "sector", defaultValue = "") String sector,
			@RequestParam(value = "boxCodes", defaultValue = "") String boxCodes,
			HttpServletResponse response) {

		restProxy.stream("getPaydataCollectionSubFamiliesFromSubFamilyIds", request, response, new String[] { subFamilyIds,countryCode,idc,referenceLevel,sector,boxCodes, preferredLocale });

	}
	
	/**
	 * 
	 * Service is used to get all Types for a Sub-Family
	 * 
	 * @param model
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/subfamilies/{subFamilyId}/jobs", method = RequestMethod.GET)
	public void getJobTypes(@PathVariable String subFamilyId, 
			@RequestParam(value = "preferredLocale", defaultValue = "") String preferredLocale,	
			@RequestParam(value = "countryCode", defaultValue = "") String countryCode,	
			@RequestParam(value = "idc", defaultValue = "") String idc,	
			@RequestParam(value = "referenceLevel", defaultValue = "") String referenceLevel,	
			@RequestParam(value = "sector", defaultValue = "") String sector,
			HttpServletRequest request,
			HttpServletResponse response) {

		//String locale = leapUtil.getLocale(request.getHeader(HGLeapConstants.AUTH_TOKEN));
		restProxy.stream("getPaydataCollectionJobsFromSubFamilyId", request, response, new String[] { subFamilyId,countryCode,idc,referenceLevel,sector, preferredLocale});

	}

	/**
	 * 
	 * Service is used to get all Types for a Sub-Family
	 * 
	 * @param model
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/jobs", method = RequestMethod.GET)
	public void getJobTypes(HttpServletRequest request,
			@RequestParam(value = "preferredLocale", defaultValue = "") String preferredLocale,	
			@RequestParam(value = "jobIds", defaultValue = "") String jobIds,
			@RequestParam(value = "countryCode", defaultValue = "") String countryCode,	
			@RequestParam(value = "idc", defaultValue = "") String idc,	
			@RequestParam(value = "referenceLevel", defaultValue = "") String referenceLevel,
			@RequestParam(value = "sector", defaultValue = "") String sector,
			@RequestParam(value = "outputType", defaultValue = "") String outputType,
			
			HttpServletResponse response) {

		//String locale = leapUtil.getLocale(request.getHeader(HGLeapConstants.AUTH_TOKEN));
		restProxy.stream("getPaydataCollectionJobsFromJobIds", request, response, new String[] { jobIds,countryCode,idc,referenceLevel,sector,preferredLocale,outputType});

	}
	

	/**
	 * @param clientId
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/industries", method = RequestMethod.GET)
	public void getIndustriesReference(
			HttpServletRequest request, HttpServletResponse response) {
		
	  	restProxy.stream("getIndustriesReference", request, response, new String[] {});

	}	
	
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/boxcodes", method = RequestMethod.GET)
	public void getBoxCodes(
			@RequestParam(value = "boxCodes", defaultValue = "") String boxCodes,	
			@RequestParam(value = "countryCode", defaultValue = "") String countryCode,	
			@RequestParam(value = "idc", defaultValue = "") String idc,	
			@RequestParam(value = "sector", defaultValue = "") String sector,
			@RequestParam(value = "preferredLocale", defaultValue = "") String preferredLocale,
			HttpServletRequest request, HttpServletResponse response) {
		
	  	restProxy.stream("getPaydataCollectionBoxCodes", request, response, new String[] {boxCodes, countryCode, idc, sector, preferredLocale});

	}
	
	
	
}