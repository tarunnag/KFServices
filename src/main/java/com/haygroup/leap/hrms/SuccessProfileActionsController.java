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
@RequestMapping(value="/hrms/successprofiles/actions")  
public class SuccessProfileActionsController {
	
private static final Logger logger = LoggerFactory.getLogger(SuccessProfileActionsController.class);
	
	@Autowired
	private LeapUtil leapUtil;
	@Autowired
	private RestProxy restProxy;
	@Autowired
	private RestProxy talentArchitectRestProxy;
	
		
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/calculateprofilegrade", method = RequestMethod.POST)
	public void calculateprofilegrade(
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("calculateprofilegrade", request, response, new String[] {  });

	}
	
	
	@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_INTELLIGENCE_CLOUD')")
	@RequestMapping(value = "/jobfactors", method = RequestMethod.GET)
	public void getSuccessProfileJobFactors(
			@RequestParam(value = "jobRoleTypeId", defaultValue = "") String jobRoleTypeId,
			@RequestParam(value = "successProfileId", defaultValue = "") String successProfileId,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("getSuccessProfileJobFactors", request, response, new String[] { jobRoleTypeId,successProfileId });

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/calculatehaypoints", method = RequestMethod.POST)
	public void calculatehaypoints(
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("calculatehaypoints", request, response, new String[] {  });

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/getrelatedprofiles", method = RequestMethod.GET)
	public void getJobRelatedProfiles(
			@RequestParam(value = "sourceJobId", defaultValue = "") String sourceJobId,
			@RequestParam(value = "targetJobIds", defaultValue = "") String targetJobIds,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("getJobRelatedProfiles", request, response, new String[] { sourceJobId, targetJobIds });

	}
	
	
	
	
}
