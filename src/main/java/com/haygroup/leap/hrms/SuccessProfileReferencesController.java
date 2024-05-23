package com.haygroup.leap.hrms;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;

import com.haygroup.leap.client.RestProxy;
import com.haygroup.leap.common.HGLeapConstants;
import com.haygroup.leap.common.LeapUtil;




@Controller
@RequestMapping(value="/hrms/successprofiles/references")  
public class SuccessProfileReferencesController {
	
private static final Logger logger = LoggerFactory.getLogger(SuccessProfileReferencesController.class);
	
	@Autowired
	private LeapUtil leapUtil;
	@Autowired
	private RestProxy restProxy;
	@Autowired
	private RestProxy talentArchitectRestProxy;
	
	/**
	 * @param type
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/regionalnormgroups", method = RequestMethod.GET)
	public void getRegionalNormGroups(
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("getRegionalNormGroups", request, response, new String[] { });

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/jobevaluation", method = RequestMethod.GET)
	public void getJobEvaluationData(
			@RequestParam(value = "jeLineScoreType", defaultValue = "") String jeLineScoreType,
			@RequestParam(value = "jeLineScoreOptionType", defaultValue = "") String jeLineScoreOptionType,
			@RequestParam(value = "option1", defaultValue = "") String option1,
			@RequestParam(value = "option2", defaultValue = "") String option2,
			@RequestParam(value = "option3", defaultValue = "") String option3,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("getJobEvaluationData", request, response, new String[] {jeLineScoreType, jeLineScoreOptionType, option1, option2, option3 });

	}
	
	
}
