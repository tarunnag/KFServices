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
@RequestMapping(value="/hrms/assessments")  
public class ProjectsController {
	
private static final Logger logger = LoggerFactory.getLogger(ProjectsController.class);
	
	@Autowired
	private LeapUtil leapUtil;
	@Autowired
	private RestProxy talentArchitectRestProxy;
	@Autowired
	private RestProxy restProxy;
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/projectsmetadata", method = RequestMethod.GET)
	public void searchAssessmentProjectsMetadata(
			@RequestParam(value = "outputType", defaultValue = "") String type,			
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		talentArchitectRestProxy.secureStream("searchAssessmentProjectsMetadata", request, response, new String[] { type});

	}
		
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/projects", method = RequestMethod.GET)
	public void searchAssessmentProjects(
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
		
		restProxy.stream("searchAssessmentProjects", request, response, 
				new String[] {sortColumn,sortBy,searchString,searchColumn,filterBy,filterValues,pageIndex,pageSize,outputType,userGroup});

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/projects/{id}", method = RequestMethod.GET)
	public void getAssessmentProjectsById(
			@PathVariable String id,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("getAssessmentProjectsById", request, response, new String[] { id });

	}

	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/projects", method = RequestMethod.POST)
	public void addAssessmentProjects(
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		talentArchitectRestProxy.secureStream("addAssessmentProjects", request, response, new String[] { },RequestMethod.PUT.toString());
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/projects", method = RequestMethod.PUT)
	public void modifyAssessmentProjects(
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		talentArchitectRestProxy.secureStream("modifyAssessmentProjects", request, response, new String[] {  },RequestMethod.POST.toString());

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/projects/profiles", method = RequestMethod.POST)
	public void addAssessmentProjectProfiles(
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		talentArchitectRestProxy.secureStream("addAssessmentProjectProfiles", request, response, new String[] { });
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/projects/availablereports", method = RequestMethod.GET)
	public void getAssessmentProjectAvailableReports(
			@RequestParam(value = "projectType", defaultValue = "") String projectType,
			@RequestParam(value = "successProfileId", defaultValue = "") String successProfileId, 
			@RequestParam(value = "assessments", defaultValue = "") String assessments, 
			@RequestParam(value = "sjt", defaultValue = "") String sjt,
			@RequestParam(value = "platform", defaultValue = "") String platform,
			@RequestParam(value = "includeTargetLevels", defaultValue = "") String includeTargetLevels,
			@RequestParam(value = "projectId", defaultValue = "") String projectId,
			HttpServletRequest request, HttpServletResponse response) {
		talentArchitectRestProxy.secureStream("getAssessmentProjectAvailableReports", request, response, new String[] { projectType, successProfileId, assessments, sjt, platform, includeTargetLevels, projectId });
	}
	
	
@PreAuthorize("hasRole('ROLE_USER')")
@RequestMapping(value = "/projectstatm", method = RequestMethod.GET)
public void getProjectSearchTATM(
		@RequestParam(value = "sortColumn", defaultValue = "") String sortColumn,		
		@RequestParam(value = "sortBy", defaultValue = "") String sortBy,		
		@RequestParam(value = "searchString", defaultValue = "") String searchString,		
		@RequestParam(value = "searchColumn", defaultValue = "") String searchColumn,		
		@RequestParam(value = "filterBy", defaultValue = "") String filterBy,		
		@RequestParam(value = "filterValues", defaultValue = "") String filterValues,		
		@RequestParam(value = "pageIndex", defaultValue = "") String pageIndex,		
		@RequestParam(value = "pageSize", defaultValue = "") String pageSize,
		@RequestParam(value = "userGroup", defaultValue = "") String userGroup,
		HttpServletRequest request,	HttpServletResponse response)
{
		
	restProxy.stream("getProjectSearchTATM", request, response, 
			new String[] {sortColumn,sortBy,searchString,searchColumn,filterBy,filterValues,pageIndex,pageSize,userGroup});

}

@PreAuthorize("hasRole('ROLE_USER')")
@RequestMapping(value = "/projectstatmmetadata", method = RequestMethod.GET)
public void getProjectSearchMetadataTATM(
		@RequestParam(value = "outputType", defaultValue = "") String outputType,			
		HttpServletRequest request,	HttpServletResponse response)
{
	
	restProxy.secureStream("getProjectSearchMetadataTATM", request, response, new String[] { outputType });

}

	
	
}
