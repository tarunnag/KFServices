package com.haygroup.leap.hrms.employeeexperience;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.haygroup.leap.client.RestProxy;

@Controller
@RequestMapping(value="/employeeexperience")
public class EmployeeExperienceController {
	
	@Autowired
	private RestProxy talentArchitectRestProxy;
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value="/participantstatus/{assessmentId}", method=RequestMethod.GET)
	public void ICAssessmentGetStatus(
			@PathVariable String assessmentId,
			@RequestParam(value="clientId", defaultValue = "") String clientId,
			HttpServletRequest request,	HttpServletResponse response) {
		
		talentArchitectRestProxy.stream("ICAssessmentGetStatus", request, response, new String[] {assessmentId, clientId});
		
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value="/data/{assessmentId}", method=RequestMethod.POST)
	public void ICAssessmentGetData(
			@PathVariable String assessmentId,
			@RequestParam(value="clientId", defaultValue = "") String clientId,
			HttpServletRequest request,	HttpServletResponse response) {
		
		talentArchitectRestProxy.stream("ICAssessmentGetData", request, response, new String[] {assessmentId, clientId});
		
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value="/report/{assessmentId}", method=RequestMethod.GET)
	public void ICAssessmentReport(
			@PathVariable String assessmentId,
			@RequestParam(value="clientId", defaultValue = "") String clientId,
			@RequestParam(value="reportType", defaultValue = "") String reportType,
			HttpServletRequest request,	HttpServletResponse response) {
		
		talentArchitectRestProxy.stream("ICAssessmentReport", request, response, new String[] {assessmentId, clientId, reportType});
		
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value="/completeassessments", method=RequestMethod.POST)
	public void ICCompleteAssessment(
			@RequestParam(value="clientId", defaultValue = "") String clientId,
			HttpServletRequest request,	HttpServletResponse response) {
		
		talentArchitectRestProxy.stream("ICCompleteAssessment", request, response, new String[] {clientId});
		
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value="/participantsvalidationbatch", method=RequestMethod.POST)
	public void participantsValidationBatch(
			@RequestParam(value="clientId", defaultValue = "") String clientId,
			HttpServletRequest request,	HttpServletResponse response) {
		
		talentArchitectRestProxy.stream("participantsValidationBatch", request, response, new String[] {clientId});
		
	}
	

}
