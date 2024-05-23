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

@Controller
@RequestMapping(value = "/companies")
public class CompaniesController {

	//private static final Logger logger = LoggerFactory.getLogger(CompaniesController.class);

	@Autowired
	private LeapUtil leapUtil;

	@Autowired
	private RestProxy restProxy;

	/**
	 * @param id
	 * @param model
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_JOB_PRICING')")
	@RequestMapping(value = "/{companyId}/customgradesets/{gradeSetId}", method = RequestMethod.GET)
	public void getCompanyGradeSet(@PathVariable long companyId, @PathVariable long gradeSetId, Model model,
			HttpServletRequest request, HttpServletResponse response) {

		restProxy.stream("getCompanyGradeSet", request, response, new String[] { String.valueOf(companyId) });

	}

	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/payanalytics", method = RequestMethod.GET)
	public void getCompanyPayAnalytics(
			@RequestParam(value = "clientId", defaultValue = "") String clientId,
			@RequestParam(value = "preferredLocale", defaultValue = "") String preferredLocale,
			HttpServletRequest request,
			HttpServletResponse response) {

		//TODO: REMOVE userId from the above RequestParam after all the UI changes have been made
		restProxy.stream("getCompanyPayAnalytics", request, response, new String[] { clientId, preferredLocale });

	}
	
	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/payanalyticsv2", method = RequestMethod.GET)
	public void getCompanyPayAnalyticsV2(
			@RequestParam(value = "clientId", defaultValue = "") String clientId,
			@RequestParam(value = "preferredLocale", defaultValue = "") String preferredLocale,
			HttpServletRequest request,
			HttpServletResponse response) {

		//TODO: REMOVE userId from the above RequestParam after all the UI changes have been made
		restProxy.stream("getCompanyPayAnalyticsV2", request, response, new String[] { clientId, preferredLocale });

	}
	
	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/preferences", method = RequestMethod.GET)
	public void getClientPreference(
			@RequestParam(value = "preferredClientId", defaultValue = "") String preferredClientId,
			@RequestParam(value = "preferenceName", defaultValue = "") String preferenceName,
			HttpServletRequest request,
			HttpServletResponse response) {

		
		restProxy.stream("getClientPreference", request, response, new String[] { preferredClientId, preferenceName });

	}
	
}
