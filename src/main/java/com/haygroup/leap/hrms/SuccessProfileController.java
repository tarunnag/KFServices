package com.haygroup.leap.hrms;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
@RequestMapping(value="/hrms/successprofiles")  
public class SuccessProfileController {
	
private static final Logger logger = LoggerFactory.getLogger(SuccessProfileController.class);
	
	@Autowired
	private LeapUtil leapUtil;
	@Autowired
	private RestProxy restProxy;
	@Autowired
	private RestProxy talentArchitectRestProxy;
	@Autowired
	private RestProxy restProxyForCriticalExp;
	
	@Autowired
	@Qualifier("restProxyForNodeAPI")
	private RestProxy restProxyForNodeAPI;
	
	/**
	 * @param type
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_INTELLIGENCE_CLOUD')")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public void searchSuccessProfiles(
			@RequestParam(value = "outputType", defaultValue = "") String outputType,
			@RequestParam(value = "type", defaultValue = "") String type,
			@RequestParam(value = "searchColumn", defaultValue = "") String searchColumn,
			@RequestParam(value = "searchString", defaultValue = "") String searchString,
			@RequestParam(value = "sortColumn", defaultValue = "") String sortColumn,
			@RequestParam(value = "sortBy", defaultValue = "") String sortBy,
			@RequestParam(value = "filterBy", defaultValue = "") String filterBy,
			@RequestParam(value = "filterValues", defaultValue = "") String filterValues,							
			@RequestParam(value = "pageIndex", defaultValue = "") String pageIndex,
			@RequestParam(value = "pageSize", defaultValue = "") String pageSize,
			@RequestParam(value = "jobCode", defaultValue = "") String jobCode,
			@RequestParam(value = "preferredClientId", defaultValue = "") String preferredClientId,
			@RequestParam(value = "jobRoleTypeId", defaultValue = "") String jobRoleTypeId,
			@RequestParam(value = "successprofileIds", defaultValue = "") String successprofileIds,
			@RequestParam(value = "standardHayGrade", defaultValue = "") String standardHayGrade,
			@RequestParam(value = "profileType", defaultValue = "") String profileType,
			@RequestParam(value = "jobRoleName", defaultValue = "") String jobRoleName,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("searchSuccessProfiles", request, response, new String[] { outputType, type,searchColumn,searchString,sortColumn,sortBy,filterBy,filterValues,
				 pageIndex, pageSize, jobCode, preferredClientId, jobRoleTypeId, successprofileIds,standardHayGrade, profileType, jobRoleName });

	}
		
	
	/**
	 * @param type
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_INTELLIGENCE_CLOUD')")
	@RequestMapping(value = "/{successProfileId}", method = RequestMethod.GET)
	//also used for /tools, /technologies, /tasks
	public void getSuccessProfile(
			@PathVariable String successProfileId,
			@RequestParam(value = "clientId", defaultValue = "") String clientId,
			@RequestParam(value = "preferredLocale", defaultValue = "") String preferredLocale,
			@RequestParam(value = "isMock", defaultValue = "") String isMock,
			@RequestParam(value = "searchString", defaultValue = "") String searchString,
			@RequestParam(value = "type", defaultValue = "") String type,
			@RequestParam(value = "reportType", defaultValue = "") String reportType,
			@RequestParam(value = "outputType", defaultValue = "") String outputType,
			@RequestParam(value = "returnAllTraits", defaultValue = "") String returnAllTraits,
			@RequestParam(value = "wcToggle", defaultValue = "") String wcToggle,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("getSuccessProfile", request, response, new String[] { successProfileId, preferredLocale, isMock, clientId, searchString, type, reportType, outputType, returnAllTraits, wcToggle });

	}
	
	
	
	/**
	 * @param type
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_INTELLIGENCE_CLOUD')")
	@RequestMapping(value = "", method = RequestMethod.POST)
	public void postSuccessProfile(
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("postSuccessProfile", request, response, new String[] {  });

	}
	
	@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_INTELLIGENCE_CLOUD')")
	@RequestMapping(value = "/{successProfileId}", method = RequestMethod.PUT)
	public void putSuccessProfile(@PathVariable String successProfileId,
			
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("putSuccessProfile", request, response, new String[] { successProfileId });

	}
	
	@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_INTELLIGENCE_CLOUD')")
	@RequestMapping(value = "/descriptions", method = RequestMethod.GET)
	public void searchDescriptions(
			@RequestParam(value = "type", defaultValue = "") String type,	
			@RequestParam(value = "searchString", defaultValue = "") String searchString,
			@RequestParam(value = "subCategoryIds", defaultValue = "") String subCategoryIds,
			@RequestParam(value = "preferredLocale", defaultValue = "") String preferredLocale,
			@RequestParam(value = "successProfileId", defaultValue = "") String successProfileId,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("searchSuccessProfileDescriptions", request, response, new String[] { type, searchString,subCategoryIds,preferredLocale,successProfileId });

	}
	
	@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_INTELLIGENCE_CLOUD')")
	@RequestMapping(value = "/descriptions/{id}", method = RequestMethod.GET)
	public void getDescription(
			@PathVariable String id,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("getSuccessProfileDescription", request, response, new String[] { id });

	}
	
	/**
	 * @param type
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_INTELLIGENCE_CLOUD')")
	@RequestMapping(value = "/descriptions", method = RequestMethod.POST)
	public void addSPDescriptions(
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("addSPDescriptions", request, response, new String[] {  });

	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/jobassessmentrequirements", method = RequestMethod.GET)
	public void getJobAssessmentRequirements(
			@RequestParam(value = "jobRoleTypeId", defaultValue = "") String jobRoleTypeId,
			@RequestParam(value = "standardHayGrade", defaultValue = "") String standardHayGrade,
			@RequestParam(value = "assessmentIds", defaultValue = "") String assessmentIds,
			@RequestParam(value = "successProfileId", defaultValue = "") String successProfileId,
			@RequestParam(value = "preferredClientId", defaultValue = "") String preferredClientId,
			@RequestParam(value = "preferredLocale", defaultValue = "") String preferredLocale,
			@RequestParam(value = "normCountry", defaultValue = "") String normCountry,
			@RequestParam(value = "normVersion", defaultValue = "") String normVersion,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("getJobAssessmentRequirements", request, response, new String[] { jobRoleTypeId,standardHayGrade,assessmentIds,successProfileId, preferredClientId, preferredLocale,normCountry,normVersion });

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/interviewguide", method = RequestMethod.GET)
	public void getInterviewGuide(
			@RequestParam(value = "jobId", defaultValue = "") String jobId,
			@RequestParam(value = "reportType", defaultValue = "") String reportType,
			@RequestParam(value = "reportLocale", defaultValue = "") String reportLocale,
			@RequestParam(value = "reportClientId", defaultValue = "") String reportClientId,
			@RequestParam(value = "jobType", defaultValue = "") String jobType,	
			@RequestParam(value = "o_competencies", defaultValue = "") String o_competencies,
			@RequestParam(value = "o_reportFormat", defaultValue = "") String o_reportFormat,
			HttpServletRequest request,	HttpServletResponse response)
	{

		talentArchitectRestProxy.stream("getInterviewGuide", request, response, new String[] { jobId,reportType,reportLocale,reportClientId,jobType,o_competencies,o_reportFormat});

	}
	
	
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/interviewguide", method = RequestMethod.POST)
	public void interviewguide(HttpServletRequest request,	HttpServletResponse response)
	{
		
		//talentArchitectRestProxy.stream("addInterviewguide", request, response, new String[] {});
		restProxyForCriticalExp.stream("addInterviewguide", request, response, new String[] {});


	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/interviewguide/list", method = RequestMethod.GET)
	public void getInterviewGuideList(HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxyForCriticalExp.stream("getInterviewGuideList", request, response, new String[] {});

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/interviewguide/list/{id}", method = RequestMethod.GET)
	public void getInterviewGuideListById(HttpServletRequest request,	HttpServletResponse response,
			@PathVariable String id)
	{
			
		restProxyForCriticalExp.stream("getInterviewGuideListById", request, response, new String[] {id});
	
	}
	
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/marketinsights", method = RequestMethod.GET)
	public void getMarketInsights(
			@RequestParam(value = "successProfileId", defaultValue = "") String successProfileId,
			@RequestParam(value = "countryId", defaultValue = "") String countryId,
			@RequestParam(value = "insightType", defaultValue = "") String insightType,
			@RequestParam(value = "clientId", defaultValue = "") String clientId,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("getSPMarketInsights", request, response, new String[] { successProfileId, countryId, insightType, clientId });

	}
	
	/**
	 * @param type
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/{successProfileId}", method = RequestMethod.DELETE)
	public void deleteSuccessProfile(
			@PathVariable String successProfileId,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("deleteSuccessProfile", request, response, new String[] { successProfileId });

	}
	
	/**
	 * @param type
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/actions/computecompetencies", method = RequestMethod.POST)
	public void computeCompetencies(
			@RequestParam(value = "jobId", defaultValue = "") String jobId,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("computeCompetencies", request, response, new String[] { jobId });

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/actions/profilelevelmappings", method = RequestMethod.GET)
	public void getProfileLevelMappings(
			HttpServletRequest request,	HttpServletResponse response)
	{

		restProxy.stream("getProfileLevelMappings", request, response, new String[] { });

	}
	
	
	@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_INTELLIGENCE_CLOUD')")
	@RequestMapping(value = "/technologies", method = RequestMethod.GET)
	public void searchTechnologies(
			@RequestParam(value = "type", defaultValue = "") String type,	
			@RequestParam(value = "searchString", defaultValue = "") String searchString,
			@RequestParam(value = "preferredLocale", defaultValue = "") String preferredLocale,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("searchTechnologies", request, response, new String[] {searchString,type,preferredLocale });

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/technologies/{id}", method = RequestMethod.GET)
	public void getTechnologies(
			@PathVariable String id,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("getTechnologies", request, response, new String[] { id });

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/tasks", method = RequestMethod.GET)
	public void searchTasks(
			@RequestParam(value = "type", defaultValue = "") String type,	
			@RequestParam(value = "searchString", defaultValue = "") String searchString,
			@RequestParam(value = "preferredLocale", defaultValue = "") String preferredLocale,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("searchTasks", request, response, new String[] {searchString,type,preferredLocale });

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/tasks/{id}", method = RequestMethod.GET)
	public void getTasks(
			@PathVariable String id,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("getTasks", request, response, new String[] { id });

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/tools", method = RequestMethod.GET)
	public void searchTools(
			@RequestParam(value = "type", defaultValue = "") String type,	
			@RequestParam(value = "searchString", defaultValue = "") String searchString,
			@RequestParam(value = "preferredLocale", defaultValue = "") String preferredLocale,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("searchTools", request, response, new String[] {searchString,type,preferredLocale });

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/tools/{id}", method = RequestMethod.GET)
	public void getTools(
			@PathVariable String id,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("getTools", request, response, new String[] { id });

	}
	
	/**
	 * @param type
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/permissions", method = RequestMethod.GET)
	public void getSuccessProfilePermission(
			@RequestParam(value = "successProfileId", defaultValue = "") String successProfileId,
			@RequestParam(value = "jobDescriptionId", defaultValue = "") String jobDescriptionId,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("getSuccessProfilePermission", request, response, new String[] { successProfileId, jobDescriptionId});

	}	
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/families", method = RequestMethod.GET)
	public void getClientFamilies(
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("getClientFamilies", request, response, new String[] { });

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/profileversions", method = RequestMethod.GET)
	public void getProfileVersions(
			@RequestParam(value = "successProfileId", defaultValue = "") String successProfileId,	
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("getProfileVersions", request, response, new String[] {successProfileId });

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/jobevaluation/{successProfileId}", method = RequestMethod.GET)
	public void getJobEvaluationDetails(
			@PathVariable(value = "successProfileId") String successProfileId,	
			@RequestParam(value = "jeLineGrade", defaultValue = "") String jeLineGrade,
			@RequestParam(value = "countryId", defaultValue = "") String countryId,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("getJobEvaluationDetails", request, response, new String[] {successProfileId, jeLineGrade,countryId});

	}
	/*
	 * SP-13065
	 */
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/summary", method = RequestMethod.GET)
	public void getSPSummary(
			@RequestParam(value = "type", defaultValue = "") String type,
			@RequestParam(value = "filterBy", defaultValue = "") String filterBy,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxyForCriticalExp.stream("getSPSummary", request, response, new String[] {type, filterBy});
	//	restProxy.stream("getSPSummary", request, response, new String[] {type, filterBy});

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/dependents", method = RequestMethod.GET)
	public void getSubCategoryDependents(
			@RequestParam(value = "subCategoryIds", defaultValue = "") String subCategoryIds,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("getSubCategoryDependents", request, response, new String[] {subCategoryIds});

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/learningcontent", method = RequestMethod.GET)
	public void getSubCategoryLearningContent(
			@RequestParam(value = "successProfileId", defaultValue = "") String successProfileId,
			@RequestParam(value = "subCategoryIds", defaultValue = "") String subCategoryIds,
			@RequestParam(value = "spCodes", defaultValue = "") String spCodes,
			@RequestParam(value = "outputType", defaultValue = "") String outputType,
			@RequestParam(value = "clientId", defaultValue = "") String clientId,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		//restProxy.stream("getSubCategoryLearningContent", request, response, new String[] {successProfileId, subCategoryIds});
		restProxyForCriticalExp.stream("getSPLearningContent", request, response, new String[] {successProfileId, spCodes, outputType, subCategoryIds});
	}
	
	/**
	 * @author Harsha vardhan moback
	 */
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/learningcontent", method = RequestMethod.POST)
	public void postSubCategoryLearningContent(
			HttpServletRequest request,	HttpServletResponse response)
	{
		restProxyForCriticalExp.stream("postSPLearningContent", request, response, new String[] {});
	}
	
	/**
	 * @param Request Param
	 * @param response
	 * @author Harsha vardhan moback
	 * @apiNote Adding Critical Experience API's
	 * @updatedBy Srujana Kolishetty
	 */
	
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/criticalexperiences", method = RequestMethod.GET)
	public void getCriticalExperiences(
			@RequestParam(value = "successProfileId", defaultValue = "") String successProfileId,
			@RequestParam(value = "outputType", defaultValue = "") String outputType,
			@RequestParam(value = "type", defaultValue = "") String type,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxyForCriticalExp.stream("getCriticalExperiences", request, response, new String[] {successProfileId, type, outputType});

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/criticalexperiences", method = RequestMethod.POST)
	public void addCriticalExperience(
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxyForCriticalExp.stream("addCriticalExperience", request, response, new String[] {});

	}	
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/criticalexperiences", method = RequestMethod.PUT)
	public void updateCriticalExperience(
			@RequestParam(value = "successProfileId", defaultValue = "") String successProfileId,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxyForCriticalExp.stream("updateCriticalExperience", request, response, new String[] {successProfileId});

	}
	
	@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_INTELLIGENCE_CLOUD')")
	@RequestMapping(value = "/skills", method = RequestMethod.GET)
	public void getSuccessProfileSkills(
			@RequestParam(value = "type", defaultValue = "") String type,
			@RequestParam(value = "topCount", defaultValue = "") String topCount,
			@RequestParam(value = "countryId", defaultValue = "") String countryId,
			@RequestParam(value = "spName", defaultValue = "") String spName,
			@RequestParam(value = "clientNames", defaultValue = "") String clientNames,
			@RequestParam(value = "industryId", defaultValue = "") String industryId,
			@RequestParam(value = "peerGroupIds", defaultValue = "") String peerGroupIds,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxyForCriticalExp.stream("getSuccessProfileSkills", request, response, new String[] {type, topCount, countryId, spName, clientNames, industryId, peerGroupIds});

	}
	
	
	@PreAuthorize("hasAnyRole('ROLE_USER')")
	@RequestMapping(value = "/skills/modelversion", method = RequestMethod.GET)
	public void getSPSkillsModelVersion(
			@RequestParam(value = "clientId", defaultValue = "") String clientId,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxyForCriticalExp.stream("getSPSkillsModelVersion", request, response, new String[] {clientId});

	}
	
	@PreAuthorize("hasAnyRole('ROLE_USER')")
	@RequestMapping(value = "/skills/model", method = RequestMethod.GET)
	public void getSPSkillsModel(
			@RequestParam(value = "clientId", defaultValue = "") String clientId,
			@RequestParam(value = "modelGUID", defaultValue = "") String modelGUID,
			@RequestParam(value = "modelVersion", defaultValue = "") String modelVersion,
			@RequestParam(value = "topComponents", defaultValue = "") String topComponents,
			@RequestParam(value = "searchType", defaultValue = "") String searchType,
			@RequestParam(value = "searchString", defaultValue = "") String searchString,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxyForCriticalExp.stream("getSPSkillsModel", request, response, new String[] {clientId, modelGUID, modelVersion, topComponents, searchType, searchString});

	}
	
	@PreAuthorize("hasAnyRole('ROLE_USER')")
	@RequestMapping(value = "/skills/{action}", method = RequestMethod.PUT)
	public void updateSPSkillsAction(
			@PathVariable String action,
			@RequestParam(value = "clientId", defaultValue = "") String clientId,
			@RequestParam(value = "modelGUID", defaultValue = "") String modelGUID,
			@RequestParam(value = "modelVersion", defaultValue = "") String modelVersion,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxyForCriticalExp.stream("updateSPSkillsAction", request, response, new String[] {action, clientId, modelGUID, modelVersion});

	}
	
	@PreAuthorize("hasAnyRole('ROLE_USER')")
	@RequestMapping(value = "/skills/components", method = RequestMethod.GET)
	public void getSearchSkillComponents(
			@RequestParam(value = "clientId", defaultValue = "") String clientId,
			@RequestParam(value = "searchString", defaultValue = "") String searchString,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxyForCriticalExp.stream("getSearchSkillComponents", request, response, new String[] {clientId, searchString});

	}

	@PreAuthorize("hasAnyRole('ROLE_USER')")
	@RequestMapping(value = "/skills/components", method = RequestMethod.POST)
	public void postCreateSkillComponents(
			@RequestParam(value = "clientId", defaultValue = "") String clientId,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxyForCriticalExp.stream("postCreateSkillComponents", request, response, new String[] {clientId});

	}
	
	@PreAuthorize("hasAnyRole('ROLE_USER')")
	@RequestMapping(value = "/skills/{id}", method = RequestMethod.GET)
	public void getSingleSkillDetails(
			@PathVariable String id,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxyForCriticalExp.stream("getSingleSkillDetails", request, response, new String[] {id});

	}
	
	@PreAuthorize("hasAnyRole('ROLE_USER')")
	@RequestMapping(value = "/skills/{id}/components", method = RequestMethod.GET)
	public void getSingleSkillComponentDetails(
			@PathVariable String id,
			@RequestParam(value = "searchString", defaultValue = "") String searchString,
			@RequestParam(value = "standardSkillComponent", defaultValue = "") String standardSkillComponent,
			@RequestParam(value = "pageIndex", defaultValue = "") String pageIndex,
			@RequestParam(value = "pageSize", defaultValue = "") String pageSize,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxyForCriticalExp.stream("getSingleSkillComponentDetails", request, response, new String[] {id, searchString, standardSkillComponent, pageIndex,pageSize });

	}
	
	
	@PreAuthorize("hasAnyRole('ROLE_USER')")
	@RequestMapping(value = "/skills/parent", method = RequestMethod.GET)
	public void getSPParentSkills(
			@RequestParam(value = "clientId", defaultValue = "") String clientId,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxyForCriticalExp.stream("getSPParentSkills", request, response, new String[] {clientId});

	}
	
	@PreAuthorize("hasAnyRole('ROLE_USER')")
	@RequestMapping(value = "/skills/{id}/{action}", method = RequestMethod.PUT)
	public void insertSPSkillsActionById(
			@PathVariable String id,
			@PathVariable String action,
			@RequestParam(value = "clientId", defaultValue = "") String clientId,
			@RequestParam(value = "modelGUID", defaultValue = "") String modelGUID,
			@RequestParam(value = "modelVersion", defaultValue = "") String modelVersion,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxyForCriticalExp.stream("insertSPSkillsActionById", request, response, new String[] {id,action,clientId, modelGUID, modelVersion});

	}
	
	@PreAuthorize("hasAnyRole('ROLE_USER')")
	@RequestMapping(value = "/skills/components/{id}/{action}", method = RequestMethod.PUT)
	public void insertSPSkillComponentsActionById(
			@PathVariable String id,
			@PathVariable String action,
			@RequestParam(value = "clientId", defaultValue = "") String clientId,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxyForCriticalExp.stream("insertSPSkillComponentsActionById", request, response, new String[] {id,action,clientId});

	}
	
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/industries", method = RequestMethod.GET)
	public void getSuccessProfileIndustries(
			@RequestParam(value = "type", defaultValue = "") String type,
			HttpServletRequest request,	HttpServletResponse response)
	{

		restProxyForCriticalExp.stream("getSuccessProfileIndustries", request, response, new String[] {type});

	}
	
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/alternatejobtitles", method = RequestMethod.GET)
	public void getAlternateTitles(
			@RequestParam(value = "topCount", defaultValue = "") String topCount,
			@RequestParam(value = "spName", defaultValue = "") String spName,
			@RequestParam(value = "countryId", defaultValue = "") String countryId,
			HttpServletRequest request,	HttpServletResponse response)
	{

		restProxyForCriticalExp.stream("getAlternateTitles", request, response, new String[] {topCount,spName,countryId});

	}
	
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/peergroups", method = RequestMethod.GET)
	public void getSuccessProfilePeerGroups(
			@RequestParam(value = "searchString", defaultValue = "") String searchString,
			@RequestParam(value = "topCount", defaultValue = "") String topCount,
			HttpServletRequest request,	HttpServletResponse response)
	{

		restProxyForCriticalExp.stream("getSuccessProfilePeerGroups", request, response, new String[] {searchString,topCount});

	}
	
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/profilesforcompare", method = RequestMethod.GET)
	public void getSuccessProfilesForCompare(
			@RequestParam(value = "successprofileIds", defaultValue = "") String successprofileIds,
			HttpServletRequest request,	HttpServletResponse response)
	{

		restProxyForCriticalExp.stream("getSuccessProfilesForCompare", request, response, new String[] {successprofileIds});

	}
	
	@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_INTELLIGENCE_CLOUD')")
	@RequestMapping(value = "/compareskills", method = RequestMethod.POST)
	public void postCompareSkills(
			HttpServletRequest request,	HttpServletResponse response)
	{

		restProxyForCriticalExp.stream("postCompareSkills", request, response, new String[] {});

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/compareskills/v1", method = RequestMethod.POST)
	public void postCompareSkillsV1(
			HttpServletRequest request,	HttpServletResponse response)
	{

		restProxyForCriticalExp.stream("postCompareSkillsV1", request, response, new String[] {});

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/compareskills/profiles", method = RequestMethod.POST)
	public void postCompareSkillsProfiles(
			HttpServletRequest request,	HttpServletResponse response)
	{

		restProxyForCriticalExp.stream("postCompareSkillsProfiles", request, response, new String[] {});

	}
	
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/roles", method = RequestMethod.GET)
	public void getSuccessProfileRoles(
				@RequestParam (value="sortColumn", defaultValue = "") String sortColumn,
				@RequestParam (value="sortBy", defaultValue = "") String sortBy,
				@RequestParam (value="searchString", defaultValue = "") String searchString,
				@RequestParam (value="filterBy", defaultValue = "") String filterBy,
				@RequestParam (value="filterValues", defaultValue = "") String filterValues,
				@RequestParam (value="pageIndex", defaultValue = "") String pageIndex,
				@RequestParam (value="pageSize", defaultValue = "") String pageSize,
				@RequestParam (value="boxProfile", defaultValue = "") String boxProfile,
				HttpServletRequest request,	HttpServletResponse response)
	{

		restProxyForCriticalExp.stream("getSuccessProfileRoles", request, response, new String[] {sortColumn, sortBy, searchString, filterBy, filterValues, pageIndex, pageSize, boxProfile});

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/clientjobmodel", method = RequestMethod.GET)
	public void getSuccessProfileJobmodel(
				@RequestParam (value="type", defaultValue = "") String type,
				@RequestParam (value="clientId", defaultValue = "") String clientId,
				HttpServletRequest request,	HttpServletResponse response)
	{

		restProxy.stream("getSuccessProfileJobmodel", request, response, new String[] {type,clientId});

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/cmslanguages", method = RequestMethod.GET)
	public void getCMSLanguages(
				HttpServletRequest request,	HttpServletResponse response)
	{

		restProxy.stream("getCMSLanguages", request, response, new String[] {});

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/certification", method = RequestMethod.GET)
	public void getCertificates(
				@RequestParam (value="clientJobId", defaultValue = "") String clientJobId,
				HttpServletRequest request,	HttpServletResponse response)
	{

		restProxyForCriticalExp.stream("getCertificates", request, response, new String[] {clientJobId});

	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/certification", method = RequestMethod.POST)
	public void createCertificates(
				@RequestParam (value="clientJobId", defaultValue = "") String clientJobId,
				HttpServletRequest request,	HttpServletResponse response)
	{

		restProxyForCriticalExp.stream("createCertificates", request, response, new String[] {clientJobId});

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/interviewguide/competencies", method = RequestMethod.GET)
	public void getInterviewGuideCompetencies(HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxyForCriticalExp.stream("getInterviewGuideCompetencies", request, response, new String[] {});

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/interviewguide/competencies/{id}", method = RequestMethod.GET)
	public void getInterviewGuideCompetenciesById(
			@PathVariable(value = "id") String id,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxyForCriticalExp.stream("getInterviewGuideCompetenciesById", request, response, new String[] {id});

	}	
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/interviewguide/competencies/{id}", method = RequestMethod.PUT)
	public void updateInterviewGuideCompetencyDraft(
			@PathVariable(value = "id") String id,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxyForCriticalExp.stream("updateInterviewGuideCompetencyDraft", request, response, new String[] {id});

	}	
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/interviewguide/competencies/original", method = RequestMethod.PUT)
	public void publishInterviewGuideDraft(
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxyForCriticalExp.stream("publishInterviewGuideDraft", request, response, new String[] {});

	}	
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/interviewguide/competencies/original/{id}", method = RequestMethod.GET)
	public void getInterviewGuideCompetencyOriginal(
			@PathVariable(value = "id") String id,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxyForCriticalExp.stream("getInterviewGuideCompetencyOriginal", request, response, new String[] {id});

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/pmjobsvisibilitystatus", method = RequestMethod.GET)
	public void getJobVisibilityStatus(
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxyForCriticalExp.stream("getJobVisibilityStatus", request, response, new String[] {});

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/pmjobsvisibilitystatus", method = RequestMethod.PUT)
	public void updateJobVisibilityStatus(
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxyForCriticalExp.stream("updateJobVisibilityStatus", request, response, new String[] {});

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/interviewguide/competencies/custom", method = RequestMethod.PUT)
	public void customInterviewGuideCompetencies(
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxyForCriticalExp.stream("customInterviewGuideCompetencies", request, response, new String[] {});

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/interviewguide/export/bulk/{action}", method = RequestMethod.POST)
	public void exportBulkInterviewguide(
			@PathVariable String action,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxyForCriticalExp.stream("exportBulkInterviewguide", request, response, new String[] {action});

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/bulkmapping", method = RequestMethod.GET)
	public void getBulkMappingSPData(
			@RequestParam (value="searchColumn", defaultValue = "") String searchColumn,
			@RequestParam (value="searchString", defaultValue = "") String searchString,
			@RequestParam (value="filterBy", defaultValue = "") String filterBy,
			@RequestParam (value="filterValues", defaultValue = "") String filterValues,
			@RequestParam (value="sortColumn", defaultValue = "") String sortColumn,
			@RequestParam (value="sortBy", defaultValue = "") String sortBy,
			@RequestParam (value="pageIndex", defaultValue = "") String pageIndex,
			@RequestParam (value="pageSize", defaultValue = "") String pageSize,
				HttpServletRequest request,	HttpServletResponse response)
	{

		restProxyForCriticalExp.stream("getBulkMappingSPData", request, response, new String[] {searchColumn, searchString, filterBy, filterValues, sortColumn, sortBy, pageIndex, pageSize});

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/bulkmapping/ids", method = RequestMethod.GET)
	public void getBulkMappingSPList(
				HttpServletRequest request,	HttpServletResponse response)
	{

		restProxyForCriticalExp.stream("getBulkMappingSPList", request, response, new String[] {});

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/bulkmapping/filters", method = RequestMethod.GET)
	public void getBulkMappingSPFilters(
				HttpServletRequest request,	HttpServletResponse response)
	{

		restProxyForCriticalExp.stream("getBulkMappingSPFilters", request, response, new String[] {});

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/bulkmapping/modelversion", method = RequestMethod.GET)
	public void getBulkMappingModelVersion(
				HttpServletRequest request,	HttpServletResponse response)
	{

		restProxyForCriticalExp.stream("getBulkMappingModelVersion", request, response, new String[] {});

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/bulkmapping/competencies", method = RequestMethod.GET)
	public void getBulkMappingCompetencies(
				@RequestParam (value="modelguid" ,defaultValue="") String modelguid,
				@RequestParam (value="modelversion" ,defaultValue="") String modelversion,
				HttpServletRequest request,	HttpServletResponse response)
	{

		restProxyForCriticalExp.stream("getBulkMappingCompetencies", request, response, new String[] {modelguid, modelversion});

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/publishstatus", method = RequestMethod.GET)
	public void getSuccessProfilePublishStatus(
				@RequestParam (value="pageIndex", defaultValue = "") String pageIndex,
				@RequestParam (value="pageSize", defaultValue = "") String pageSize,
				@RequestParam (value="sortColumn", defaultValue = "") String sortColumn,
				@RequestParam (value="sortBy", defaultValue = "") String sortBy,
				@RequestParam (value="publishType", defaultValue = "") String publishType,
				HttpServletRequest request,	HttpServletResponse response)
	{

		restProxyForCriticalExp.stream("getSuccessProfilePublishStatus", request, response, new String[] {pageIndex, pageSize, sortColumn, sortBy, publishType});

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/publishstatus/{action}", method = RequestMethod.PUT)
	public void rePublishSPMaps(
				@PathVariable String action,
				HttpServletRequest request,	HttpServletResponse response)
	{

		restProxyForCriticalExp.stream("rePublishSPMaps", request, response, new String[] {action});

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/bulkmapping/competencies/levels", method = RequestMethod.GET)
	public void getBulkMappingCompetenciesLevels(
				@RequestParam (value="subCategoryIds" ,defaultValue="") String subCategoryIds,
				HttpServletRequest request,	HttpServletResponse response)
	{

		restProxyForCriticalExp.stream("getBulkMappingCompetenciesLevels", request, response, new String[] {subCategoryIds});

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/bulkmapping", method = RequestMethod.POST)
	public void publishBulkMappingSP(
				HttpServletRequest request,	HttpServletResponse response)
	{

		restProxyForCriticalExp.stream("publishBulkMappingSP", request, response, new String[] {});

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/json/full", method = RequestMethod.GET)
	public void getSuccessProfileJson(
			@RequestParam(value="id", defaultValue = "") String id,
			@RequestParam(value="clientId", defaultValue = "") String clientId,
			@RequestParam(value="type", defaultValue = "") String type,

				HttpServletRequest request,	HttpServletResponse response)
	{

		restProxyForCriticalExp.stream("getSuccessProfileJson", request, response, new String[] {id, clientId, type});

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/json/full", method = RequestMethod.POST)
	public void saveSuccessProfileJson(
				HttpServletRequest request,	HttpServletResponse response)
	{

		restProxyForCriticalExp.stream("saveSuccessProfileJson", request, response, new String[] {});

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/json/full", method = RequestMethod.DELETE)
	public void deleteSuccessProfileJson(
			@RequestParam(value="ids", defaultValue = "") String ids,
			@RequestParam(value="clientId", defaultValue = "") String clientId,
			HttpServletRequest request,	HttpServletResponse response)
	{

		restProxyForCriticalExp.stream("deleteSuccessProfileJson", request, response, new String[] {ids, clientId});

	}
	
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public void getSuccessProfileSearch(
				@RequestParam (value="type", defaultValue = "") String type,
				@RequestParam (value="searchColumn", defaultValue = "") String searchColumn,
				@RequestParam (value="searchString", defaultValue = "") String searchString,
				@RequestParam (value="filterBy", defaultValue = "") String filterBy,
				@RequestParam (value="filterValues", defaultValue = "") String filterValues,
				@RequestParam (value="sortColumn", defaultValue = "") String sortColumn,
				@RequestParam (value="sortBy", defaultValue = "") String sortBy,
				@RequestParam (value="pageIndex", defaultValue = "") String pageIndex,
				@RequestParam (value="pageSize", defaultValue = "") String pageSize,
				HttpServletRequest request,	HttpServletResponse response)
	{

		restProxyForCriticalExp.stream("getSuccessProfileSearch", request, response, new String[] {type, searchColumn, searchString, filterBy, filterValues, sortColumn, sortBy, pageIndex, pageSize});

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/exporthealthcheck", method = RequestMethod.POST)
	public void exportHealthCheck(
				HttpServletRequest request,	HttpServletResponse response)
	{

		restProxyForCriticalExp.stream("exportHealthCheck", request, response, new String[] {});

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/export", method = RequestMethod.GET)
	public void getSuccessProfileExport(
			@RequestParam(value="id", defaultValue = "") String id,
			@RequestParam(value="clientId", defaultValue = "") String clientId,
			@RequestParam(value="type", defaultValue = "") String type,
			@RequestParam(value="settings", defaultValue = "") String settings,

				HttpServletRequest request,	HttpServletResponse response)
	{

		restProxyForCriticalExp.stream("getSuccessProfileExport", request, response, new String[] {id, clientId, type, settings});

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/details", method = RequestMethod.GET)
	public void getSuccessProfileDetails(
			@RequestParam(value="spId", defaultValue = "") String spId,
				HttpServletRequest request,	HttpServletResponse response)
	{

		restProxyForCriticalExp.stream("getSuccessProfileDetails", request, response, new String[] {spId});

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/details", method = RequestMethod.POST)
	public void saveSuccessProfileDetails(
				HttpServletRequest request,	HttpServletResponse response)
	{

		restProxyForCriticalExp.stream("saveSuccessProfileDetails", request, response, new String[] {});

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/details", method = RequestMethod.PUT)
	public void updateSuccessProfileDetails(
				HttpServletRequest request,	HttpServletResponse response)
	{

		restProxyForCriticalExp.stream("updateSuccessProfileDetails", request, response, new String[] {});

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/jobdescriptions/details", method = RequestMethod.GET)
	public void getSuccessProfileJobDescriptionsDetails(
			@RequestParam(value="jdId", defaultValue="") String jdId,
				HttpServletRequest request,	HttpServletResponse response)
	{

		restProxyForCriticalExp.stream("getSuccessProfileJobDescriptionsDetails", request, response, new String[] {jdId});

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/jobdescriptions/details", method = RequestMethod.POST)
	public void saveSuccessProfileJobDescriptionsDetails(
				HttpServletRequest request,	HttpServletResponse response)
	{

		restProxyForCriticalExp.stream("saveSuccessProfileJobDescriptionsDetails", request, response, new String[] {});

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/jobdescriptions/details", method = RequestMethod.PUT)
	public void updateSuccessProfileJobDescriptionsDetails(
				HttpServletRequest request,	HttpServletResponse response)
	{

		restProxyForCriticalExp.stream("updateSuccessProfileJobDescriptionsDetails", request, response, new String[] {});

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/export/bulk/{action}", method = RequestMethod.POST)
	public void addBulkSuccessProfileExport(
			@PathVariable String action,
				HttpServletRequest request,	HttpServletResponse response)
	{

		restProxyForCriticalExp.stream("addBulkSuccessProfileExport", request, response, new String[] {action});

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/export/ondemand/{reportType}/{action}", method = RequestMethod.POST)
	public void postSPExportOndemand(
			@PathVariable String reportType,
			@PathVariable String action,
			HttpServletRequest request,	HttpServletResponse response)
	{
	
		restProxyForCriticalExp.secureStream("postSPExportOndemand", request, response, new String[] {reportType, action});
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/matrix", method = RequestMethod.GET)
	public void getSPMatrixPage(
			@RequestParam (value="type", defaultValue = "") String type,
			@RequestParam (value="searchColumn", defaultValue = "") String searchColumn,
			@RequestParam (value="searchString", defaultValue = "") String searchString,
			@RequestParam (value="filterBy", defaultValue = "") String filterBy,
			@RequestParam (value="filterValues", defaultValue = "") String filterValues,
			@RequestParam (value="sortColumn", defaultValue = "") String sortColumn,
			@RequestParam (value="sortBy", defaultValue = "") String sortBy,
			@RequestParam (value="pageIndex", defaultValue = "") String pageIndex,
			@RequestParam (value="pageSize", defaultValue = "") String pageSize,
				HttpServletRequest request,	HttpServletResponse response)
	{

		restProxyForCriticalExp.stream("getSPMatrixPage", request, response, new String[] {type,searchColumn, searchString, filterBy, filterValues, sortColumn, sortBy, pageIndex, pageSize});

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/intelligencecloud/{action}", method = RequestMethod.POST)
	public void addIntelligencecloudData(
				@PathVariable String action,
				HttpServletRequest request,	HttpServletResponse response)
	{

		restProxyForCriticalExp.stream("addIntelligencecloudData", request, response, new String[] {action});

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/intelligencecloud/{action}", method = RequestMethod.PUT)
	public void updateIntelligencecloudData(
				@PathVariable String action,
				HttpServletRequest request,	HttpServletResponse response)
	{

		restProxyForCriticalExp.stream("updateIntelligencecloudData", request, response, new String[] {action});

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")

	@RequestMapping(value = "/demo/notificationdate", method = RequestMethod.GET)
	public void getDemoNotificationDate(
			@RequestParam (value="userId", defaultValue = "") String userId,
			@RequestParam (value="clientId", defaultValue = "") String clientId,
				HttpServletRequest request,	HttpServletResponse response)
	{

		restProxyForCriticalExp.stream("getDemoNotificationDate", request, response, new String[] {userId,clientId});

	}
	
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/demo/notificationdate", method = RequestMethod.POST)
	public void postDemoNotificationDate(
			@RequestParam (value="userId", defaultValue = "") String userId,
			@RequestParam (value="clientId", defaultValue = "") String clientId,
				HttpServletRequest request,	HttpServletResponse response)
	{

		restProxyForCriticalExp.stream("postDemoNotificationDate", request, response, new String[] {userId,clientId});

	}
	@RequestMapping(value = "/intelligencecloud/permissions", method = RequestMethod.GET)
	public void getIntelligencecloudPermissions(
				@RequestParam(value="clientId", defaultValue = "") String clientId,
				HttpServletRequest request,	HttpServletResponse response)
	{

		restProxyForCriticalExp.stream("getIntelligencecloudPermissions", request, response, new String[] {clientId});

	}
	
	@RequestMapping(value = "/intelligencecloud/skills_library", method = RequestMethod.POST)
	public void insertIntelligencecloudSkillLibrary(
				@RequestParam (value="userId", defaultValue = "") String userId,
				@RequestParam(value="clientId", defaultValue = "") String clientId,
				HttpServletRequest request,	HttpServletResponse response)
	{

		restProxyForCriticalExp.stream("insertIntelligencecloudSkillLibrary", request, response, new String[] {clientId,userId});

	}
	
	@RequestMapping(value = "/intelligencecloud/skills/search", method = RequestMethod.GET)
	public void getSuccessProfileSkillsSearch(
				@RequestParam(value="searchstring", defaultValue = "") String searchstring,
				HttpServletRequest request,	HttpServletResponse response)
	{

		restProxyForCriticalExp.stream("getSuccessProfileSkillsSearch", request, response, new String[] {searchstring});

	}
	
	@RequestMapping(value = "/intelligencecloud/skills/recommendations", method = RequestMethod.GET)
	public void getSuccessProfileSkillsRecommendation(
				@RequestParam(value="profileId", defaultValue = "") String profileId,
				HttpServletRequest request,	HttpServletResponse response)
	{

		restProxyForCriticalExp.stream("getSuccessProfileSkillsRecommendation", request, response, new String[] {profileId});

	}
	
	@RequestMapping(value = "/intelligencecloud/skill", method = RequestMethod.POST)
	public void addIntelligencecloudSkills(
				HttpServletRequest request,	HttpServletResponse response)
	{

		restProxyForCriticalExp.stream("addIntelligencecloudSkills", request, response, new String[] {});

	}
}
