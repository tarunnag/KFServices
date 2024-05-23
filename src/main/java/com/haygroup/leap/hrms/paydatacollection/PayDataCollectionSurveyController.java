package com.haygroup.leap.hrms.paydatacollection;

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
@RequestMapping(value="/hrms/paydatacollection/companies") 
public class PayDataCollectionSurveyController 
{

	private static final Logger logger = LoggerFactory.getLogger(PayDataCollectionSurveyController.class);
	
	@Autowired
	private LeapUtil leapUtil;
	@Autowired
	private RestProxy restProxy;

	/**
	 * @param year
	 * @param clientId
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/surveys", method = RequestMethod.GET)
	public void getSurveyResponse(
			@RequestParam(value = "year", defaultValue = "") String year,
			@RequestParam(value = "pdcClientId", defaultValue = "") String pdcClientId,
			@RequestParam(value = "type", defaultValue = "") String type,
			@RequestParam(value = "countryCode", defaultValue = "") String countryCode,
			@RequestParam(value = "planId", defaultValue = "") String planId,
			HttpServletRequest request, HttpServletResponse response) {

		restProxy.stream("getSurveyResponse", request, response, new String[] {year,pdcClientId,type,countryCode,planId});

	}
	
	
	/**
	 * @param year
	 * @param clientId
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/surveys", method = RequestMethod.POST)
	public void addSurvey(
			HttpServletRequest request, HttpServletResponse response) {

		restProxy.stream("addSurvey", request, response, new String[] {});

	}	
	
	
	/**
	 * @param year
	 * @param clientId
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/surveys", method = RequestMethod.PUT)
	public void modifySurvey(
			@RequestParam(value = "gradingScenario", defaultValue = "false") String gradingScenario,
			HttpServletRequest request, HttpServletResponse response) {

		restProxy.stream("modifySurvey", request, response, new String[] {gradingScenario});

	}	


	
}
