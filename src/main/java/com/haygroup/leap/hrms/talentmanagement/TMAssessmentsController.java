package com.haygroup.leap.hrms.talentmanagement;


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
@RequestMapping(value="/hrms/assessments/talentmanagement")  
public class TMAssessmentsController {
	
private static final Logger logger = LoggerFactory.getLogger(TMAssessmentsController.class);
	
	@Autowired
	private LeapUtil leapUtil;
	@Autowired
	private RestProxy restProxy;
	@Autowired
	private RestProxy talentArchitectRestProxy;
	

	/**@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/subscriptions", method = RequestMethod.GET)
	public void getAssessmentSubscriptions(
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("getAssessmentSubscriptions", request, response, new String[] { });

	}*/
	

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/getparticipantreport", method = RequestMethod.GET)
	public void getTMParticipantReport(
			@RequestParam(value = "participantId", defaultValue = "") String participantId,
			@RequestParam(value = "projectId", defaultValue = "") String projectId,
			@RequestParam(value = "reportType", defaultValue = "") String reportType,	
			@RequestParam(value = "reportLocale", defaultValue = "") String reportLocale,	
			@RequestParam(value = "o_targetLevel", defaultValue = "") String o_targetLevel,	
			@RequestParam(value = "successProfileId", defaultValue = "") String successProfileId,
			@RequestParam(value = "o_reportFormat", defaultValue = "") String o_reportFormat,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		talentArchitectRestProxy.stream("getTMParticipantReport", request, response, new String[] {participantId, projectId, reportType, reportLocale, o_targetLevel, successProfileId, o_reportFormat});

	}
	
	/**
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/projectassessments", method = RequestMethod.GET)
	public void getProjectAssessments(
			@RequestParam(value = "successProfileId", defaultValue = "") String successProfileId,
			@RequestParam(value = "projectType", defaultValue = "") String projectType,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("getProjectAssessments", request, response, new String[] {successProfileId, projectType});

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/references/projects", method = RequestMethod.GET)
	public void getAssessmentReferences(
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("getAssessmentReferences", request, response, new String[] {});

	}
	*/
	
	
	
}
