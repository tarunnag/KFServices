package com.haygroup.leap.hrms.talentmanagement;


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
@RequestMapping(value="/hrms/assessments/talentmanagement")  
public class TMParticipantsController {
	
private static final Logger logger = LoggerFactory.getLogger(TMParticipantsController.class);
	
	@Autowired
	private LeapUtil leapUtil;
	@Autowired
	private RestProxy talentArchitectRestProxy;

	
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/participants/{id}", method = RequestMethod.GET)
	public void getParticipantsById(
			@PathVariable String id,
			@RequestParam(value = "projectId", defaultValue = "") String projectId,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		talentArchitectRestProxy.stream("getTMParticipantsById", request, response, new String[] { id,projectId });

	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/participants", method = RequestMethod.POST)
	public void addTMParticipants(
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		talentArchitectRestProxy.secureStream("addTMParticipants", request, response, new String[] { },RequestMethod.PUT.toString());
	}

	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/participants", method = RequestMethod.PUT)
	public void modifyTMParticipants(
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		talentArchitectRestProxy.secureStream("modifyTMParticipants", request, response, new String[] {  },RequestMethod.POST.toString());

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/participants/{id}", method = RequestMethod.DELETE)
	public void deleteTMParticipants(
			@PathVariable String id,
			@RequestParam(value = "projectId", defaultValue = "") String projectId,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		talentArchitectRestProxy.secureStream("deleteTMParticipants", request, response, new String[] { id,projectId  });

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/participants/status", method = RequestMethod.PUT)
	public void updateTMParticipantStatus(
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		talentArchitectRestProxy.secureStream("updateTMParticipantStatus", request, response, new String[] {  },RequestMethod.POST.toString());

	}	
}
