package com.haygroup.leap.hrms.talentacquisition;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
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
@RequestMapping(value="/hrms/edw/actions")  
public class EDWAssessmentsController {
	
private static final Logger logger = LoggerFactory.getLogger(EDWAssessmentsController.class);
	
	@Autowired
	private LeapUtil leapUtil;
	@Autowired
	private RestProxy restProxy;
	

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/assessments", method = RequestMethod.POST)
	public void postAssessments(
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("postAssessments", request, response, new String[] { });

	}
	

	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/assessmentspii", method = RequestMethod.POST)
	public void postAssessmentsPii(
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("postAssessmentsPii", request, response, new String[] { });

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/personalanonymization", method = RequestMethod.POST)
	public void postPersonAnonymization(
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("postPersonAnonymization", request, response, new String[] { });

	}
		
	
	
		
}
