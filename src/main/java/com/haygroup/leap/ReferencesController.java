/**
 * 
 */
package com.haygroup.leap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
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

/**
 * @author Sanjay_Manchiganti
 * 
 */
@Controller
@RequestMapping(value = "/references")
public class ReferencesController {

	//private static final Logger logger = LoggerFactory.getLogger(ReferencesController.class);

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
	@RequestMapping(value = "/families", method = RequestMethod.GET)
	public void getFamilies(@RequestParam(value = "type", defaultValue = "") String displayType,
			@RequestParam(value = "preferredLocale", defaultValue = "") String preferredLocale,	
			HttpServletRequest request, HttpServletResponse response) 
	{
	
		restProxy.stream("getFamilies", request, response, new String[] { displayType, preferredLocale});

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
	public void getSubFamilies(@PathVariable String familyId, Model model, HttpServletRequest request,
			HttpServletResponse response) {

		//String locale = leapUtil.getLocale(request.getHeader(HGLeapConstants.AUTH_TOKEN));
		restProxy.stream("getSubFamilies", request, response, new String[] { familyId });

	}

	/**
	 * 
	 * Service is used to get all Types for a Sub-Family
	 * 
	 * @param model
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/subfamilies/{subFamilyId}/jobtypes", method = RequestMethod.GET)
	public void getJobTypes(@PathVariable String subFamilyId, Model model, HttpServletRequest request,
			HttpServletResponse response) {

		//String locale = leapUtil.getLocale(request.getHeader(HGLeapConstants.AUTH_TOKEN));
		restProxy.stream("getJobTypes", request, response, new String[] { subFamilyId });

	}

	/**
	 * 
	 * Used to get all the factors
	 * 
	 * @param model
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/jobtypes/{jobTypeId}/jobfactors", method = RequestMethod.GET)
	public void getJobFactors(@PathVariable String jobTypeId,
							@RequestParam(value = "preferredLocale", defaultValue = "") String preferredLocale,
							HttpServletRequest request,
			HttpServletResponse response) {

		restProxy.stream("getJobFactors", request, response, new String[] { jobTypeId,preferredLocale });

	}


	/* 
	 * GET all the 
	 * 
	 * @param id
	 * @param model
	 * @param response
	 
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/industries", method = RequestMethod.GET)
	public void getIndustries( 
			Model model, HttpServletRequest request,
			HttpServletResponse response) {

		restProxy.stream("getIndustries", request, response, new String[] {});

	}
	*/

	/* 
	 * GET all the 
	 * 
	 * @param id
	 * @param model
	 * @param response
	 
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/subfamilies/haygrades", method = RequestMethod.GET)
	public void getSubFamiliesHayGrades( 
			@RequestParam(value = "subFamilyId", defaultValue = "") String subFamilyId,
			HttpServletRequest request,
			HttpServletResponse response) {

		restProxy.stream("getSubFamiliesHayGrades", request, response, new String[] {subFamilyId});

	}
	*/
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/languages", method = RequestMethod.GET)
	public void getReferenceLanguages( 
			@RequestParam(value = "applicationName", defaultValue = "") String applicationName,
			HttpServletRequest request,
			HttpServletResponse response) {

		restProxy.stream("getReferenceLanguages", request, response, new String[] {applicationName});

	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/contentconfig", method = RequestMethod.GET)
	public void getContentConfig( 
			HttpServletRequest request,
			HttpServletResponse response) {

		restProxy.stream("getContentConfig", request, response, new String[] {});

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/countries", method = RequestMethod.GET)
	public void getCountries( 
			HttpServletRequest request,
			HttpServletResponse response) {

		restProxy.stream("getCountries", request, response, new String[] {});

	}
	
	
}
