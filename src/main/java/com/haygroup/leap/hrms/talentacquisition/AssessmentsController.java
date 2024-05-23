package com.haygroup.leap.hrms.talentacquisition;


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
@RequestMapping(value="/hrms/assessments")  
public class AssessmentsController {
	
private static final Logger logger = LoggerFactory.getLogger(AssessmentsController.class);
	
	@Autowired
	private LeapUtil leapUtil;
	@Autowired
	private RestProxy restProxy;
	@Autowired
	private RestProxy talentArchitectRestProxy;
	

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/subscriptions", method = RequestMethod.GET)
	public void getAssessmentSubscriptions(
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("getAssessmentSubscriptions", request, response, new String[] { });

	}
	

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/getparticipantreport", method = RequestMethod.GET)
	public void getParticipantReport(
			@RequestParam(value = "participantId", defaultValue = "") String participantId,
			@RequestParam(value = "projectId", defaultValue = "") String projectId,
			@RequestParam(value = "reportType", defaultValue = "") String reportType,	
			@RequestParam(value = "reportLocale", defaultValue = "") String reportLocale,	
			@RequestParam(value = "successProfileId", defaultValue = "") String successProfileId,	
			@RequestParam(value = "o_reportFormat", defaultValue = "") String o_reportFormat,	
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		talentArchitectRestProxy.stream("getParticipantReport", request, response, new String[] {participantId,projectId,reportType, reportLocale, successProfileId, o_reportFormat});

	} 
	
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
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public void getProductsForSuccessProfile(
			@RequestParam(value = "successProfileId", defaultValue = "") String successProfileId,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("getProductsForSuccessProfile", request, response, new String[] {successProfileId});

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/graphql/schema", method = RequestMethod.GET)
	public void getGraphQLSchema(
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		talentArchitectRestProxy.secureStream("getGraphQLSchema", request, response, new String[] {});

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/graphql", method = RequestMethod.POST)
	public void addGraphQLQuery(
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		talentArchitectRestProxy.secureStream("addGraphQLQuery", request, response, new String[] {});

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/clients/reportBranding/{clientId}", method = RequestMethod.GET)
	public void reportBranding(
			@PathVariable String clientId,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		talentArchitectRestProxy.secureStream("reportBranding", request, response, new String[] {clientId});

	}
	
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/kfasdigital/projects", method = RequestMethod.GET)
	public void searchKFASParticipantsProgress(
			@RequestParam(value = "sortColumn", defaultValue = "") String sortColumn,		
			@RequestParam(value = "sortBy", defaultValue = "") String sortBy,		
			@RequestParam(value = "searchString", defaultValue = "") String searchString,		
			@RequestParam(value = "searchColumn", defaultValue = "") String searchColumn,		
			@RequestParam(value = "filterBy", defaultValue = "") String filterBy,		
			@RequestParam(value = "filterValues", defaultValue = "") String filterValues,		
			@RequestParam(value = "pageIndex", defaultValue = "") String pageIndex,		
			@RequestParam(value = "pageSize", defaultValue = "") String pageSize,
			@RequestParam(value = "outputType", defaultValue = "") String outputType,
			@RequestParam(value = "userGroup", defaultValue = "") String userGroup,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		talentArchitectRestProxy.secureStream("searchKFASParticipantsProgress", request, response, 
				new String[] {sortColumn,sortBy,searchString,searchColumn,filterBy,filterValues,pageIndex,pageSize,outputType,userGroup});
	
	}
	
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/talentmanagement/customprojecttypes/{customprojecttypeId}", method = RequestMethod.GET)
	public void getCustomProjectTypeDetails(
			@PathVariable String customprojecttypeId,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		talentArchitectRestProxy.secureStream("getCustomProjectTypeDetails", request, response, new String[] {customprojecttypeId});
	
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/talentmanagement/customprojecttypes", method = RequestMethod.GET)
	public void getCustomProjectTypeList(
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		talentArchitectRestProxy.secureStream("getCustomProjectTypeList", request, response, new String[] {});
	
	}
}
