package com.haygroup.leap.hrms.talentmanagement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.haygroup.leap.client.RestProxy;
import com.haygroup.leap.common.LeapUtil;

@Controller
@RequestMapping(value = "/hrms/assessments/talentmanagement")
public class TMProjectsController {

	private static final Logger logger = LoggerFactory.getLogger(TMProjectsController.class);

	@Autowired
	private LeapUtil leapUtil;
	@Autowired
	private RestProxy talentArchitectRestProxy; // Direct access to KFAS
	@Autowired
	private RestProxy restProxy; // Wrapper around KFAS to populate keys e.g. success profile name, admin names etc.

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/projectsmetadata", method = RequestMethod.GET)
	public void searchAssessmentProjectsMetadata(@RequestParam(value = "outputType", defaultValue = "") String type, HttpServletRequest request, HttpServletResponse response) {
		talentArchitectRestProxy.secureStream("searchTMAssessmentProjectsMetadata", request, response, new String[] { type });
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
			HttpServletRequest request,
			HttpServletResponse response) {
		restProxy.stream(
				"searchTMAssessmentProjects",
				request,
				response,
				new String[] { sortColumn, sortBy, searchString, searchColumn, filterBy, filterValues, pageIndex, pageSize, outputType, userGroup });
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/projects/{id}", method = RequestMethod.GET)
	public void getAssessmentProjectsById(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) {
		restProxy.stream("getTMAssessmentProjectsById", request, response, new String[] { id });
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/projects", method = RequestMethod.POST)
	public void addAssessmentProjects(HttpServletRequest request, HttpServletResponse response) {
		talentArchitectRestProxy.secureStream("addTMAssessmentProjects", request, response, new String[] {}, RequestMethod.PUT.toString());
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/projects", method = RequestMethod.PUT)
	public void modifyAssessmentProjects(HttpServletRequest request, HttpServletResponse response) {
		talentArchitectRestProxy.secureStream("modifyTMAssessmentProjects", request, response, new String[] {}, RequestMethod.POST.toString());
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/projects/profiles", method = RequestMethod.POST)
	public void addTMAssessmentProjectProfiles(HttpServletRequest request, HttpServletResponse response) {
		talentArchitectRestProxy.secureStream("addTMAssessmentProjectProfiles", request, response, new String[] {});
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/projects/availablereports", method = RequestMethod.GET)
	public void TMProjectAvailableReports(
			@RequestParam(value = "projectType", defaultValue = "") String projectType,
			@RequestParam(value = "successProfileId", defaultValue = "") String successProfileId, 
			@RequestParam(value = "assessments", defaultValue = "") String assessments, 
			@RequestParam(value = "sjt", defaultValue = "") String sjt,
			@RequestParam(value = "platform", defaultValue = "") String platform,
			@RequestParam(value = "includeTargetLevels", defaultValue = "") String includeTargetLevels,
			@RequestParam(value = "projectId", defaultValue = "") String projectId,
			@RequestParam(value = "customProjectTypeId", defaultValue = "") String customProjectTypeId,
			HttpServletRequest request, HttpServletResponse response) {
		talentArchitectRestProxy.secureStream("getTMProjectAvailableReports", request, response, new String[] { projectType, successProfileId, assessments, sjt, platform, includeTargetLevels, projectId, customProjectTypeId });
	}
}
