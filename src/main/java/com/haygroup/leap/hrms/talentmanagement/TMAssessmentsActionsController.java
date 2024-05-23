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
@RequestMapping(value="/hrms/assessments/talentmanagement/actions")  
public class TMAssessmentsActionsController {
	
private static final Logger logger = LoggerFactory.getLogger(TMAssessmentsActionsController.class);
	
	@Autowired
	private LeapUtil leapUtil;
	@Autowired
	private RestProxy talentArchitectRestProxy;
	@Autowired
	private RestProxy restProxy;
	
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/participantsprogressmetadata", method = RequestMethod.GET)
	public void searchTMParticipantsProgressMetadata(
			@RequestParam(value = "outputType", defaultValue = "") String type,			
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		talentArchitectRestProxy.secureStream("searchTMParticipantsProgressMetadata", request, response, new String[] {type});
	
	}
		
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/participantsprogress/{id}", method = RequestMethod.GET)
	public void searchTMParticipantsProgress(
			@PathVariable String id,
			@RequestParam(value = "sortColumn", defaultValue = "") String sortColumn,		
			@RequestParam(value = "sortBy", defaultValue = "") String sortBy,		
			@RequestParam(value = "searchString", defaultValue = "") String searchString,		
			@RequestParam(value = "searchColumn", defaultValue = "") String searchColumn,		
			@RequestParam(value = "filterBy", defaultValue = "") String filterBy,		
			@RequestParam(value = "filterValues", defaultValue = "") String filterValues,		
			@RequestParam(value = "pageIndex", defaultValue = "") String pageIndex,		
			@RequestParam(value = "pageSize", defaultValue = "") String pageSize,
			@RequestParam(value = "successProfileId", defaultValue = "") String successProfileId,
			@RequestParam(value = "exCandidateId", defaultValue = "") String exCandidateId,
			@RequestParam(value = "exEmployeeId", defaultValue = "") String exEmployeeId,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		talentArchitectRestProxy.secureStream("searchTMParticipantsProgress", request, response, 
				new String[] { id,sortColumn,sortBy,searchString,searchColumn,filterBy,filterValues,pageIndex,pageSize,successProfileId,exCandidateId,exEmployeeId});
	
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/inviteparticipants", method = RequestMethod.POST)
	public void inviteTMParticipants(	
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		talentArchitectRestProxy.secureStream("inviteTMParticipants", request, response, new String[] {});
	
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/calculateassessmentscoringattributes", method = RequestMethod.POST)
	public void calculateTMAssessmentScoringAttributes(
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("calculateTMAssessmentScoringAttributes", request, response, new String[] {});
	
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/participantsuploadtemplates", method = RequestMethod.GET)
	public void getTMParticipantsUploadTemplates(
			@RequestParam(value = "projectId", defaultValue = "") String projectId,
			@RequestParam(value = "preferredClientId", defaultValue = "") String preferredClientId,
			@RequestParam(value = "authToken", defaultValue = "") String authToken,
			@RequestParam(value = "preferredLocale", defaultValue = "") String preferredLocale,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		talentArchitectRestProxy.stream("getTMParticipantsUploadTemplates", request, response, new String[] {projectId,preferredClientId,authToken,preferredLocale});
	
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/participantsuploadfile", method = RequestMethod.POST)
	public void uploadTMParticipantsFile(
			HttpServletRequest request,	HttpServletResponse response,
			@RequestParam(value = "projectId", defaultValue = "") String projectId,
			@RequestParam(value = "preferredClientId", defaultValue = "") String preferredClientId,
			@RequestParam(value = "userGroup", defaultValue = "") String userGroup,
			@RequestParam(value = "isKFAssess2", defaultValue = "") String isKFAssess2
			)
	{
		
		talentArchitectRestProxy.stream("uploadTMParticipantsFile", request, response, new String[] { projectId,preferredClientId, userGroup, isKFAssess2});
	
	}
	
	

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/uploadparticipantsvalidationerrors", method = RequestMethod.GET)
	public void getTMParticipantsValidationErrors(
			HttpServletRequest request,	HttpServletResponse response,
			@RequestParam(value = "projectId", defaultValue = "") String projectId,
			@RequestParam(value = "fileGuid", defaultValue = "") String fileGuid,
			@RequestParam(value = "preferredClientId", defaultValue = "") String preferredClientId,
			@RequestParam(value = "authToken", defaultValue = "") String authToken
			)
	{
		
		talentArchitectRestProxy.stream("getTMParticipantsValidationErrors", request, response, new String[] { projectId,fileGuid,preferredClientId,authToken });
	
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/participantsuploadpreview", method = RequestMethod.GET)
	public void getTMParticipantsUploadPreview(
			HttpServletRequest request,	HttpServletResponse response,
			@RequestParam(value = "projectId", defaultValue = "") String projectId,
			@RequestParam(value = "fileGuid", defaultValue = "") String fileGUId,
			@RequestParam(value = "preferredClientId", defaultValue = "") String preferredClientId			
			)
	{
		
		talentArchitectRestProxy.stream("getTMParticipantsUploadPreview", request, response, new String[] { projectId,fileGUId,preferredClientId });
	
	}
	
	
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/participantsupload", method = RequestMethod.PUT)
	public void uploadTMParticipant(
			HttpServletRequest request,	HttpServletResponse response//,
			//@RequestParam(value = "projectId", defaultValue = "") String projectId,
			//@RequestParam(value = "fileGuid", defaultValue = "") String fileGuid,
			//@RequestParam(value = "preferredClientId", defaultValue = "") String preferredClientId			
			)
	{
		
		talentArchitectRestProxy.stream("uploadTMParticipant", request, response, new String[] { });
	
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/resetassessments", method = RequestMethod.POST) 
	public void resetTMAssessments(
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		talentArchitectRestProxy.stream("resetTMAssessments", request, response, new String[] {  });
	
	}	
	
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/reenableassessments", method = RequestMethod.POST)
	public void reEnableTMAssessments(
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		talentArchitectRestProxy.stream("reEnableTMAssessments", request, response, new String[] {  });
	
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/participantUploadStatus", method = RequestMethod.GET)
	public void getTMParticipantsUploadStatus(
			HttpServletRequest request,	HttpServletResponse response,
			@RequestParam(value = "projectId", defaultValue = "") String projectId,
			@RequestParam(value = "fileId", defaultValue = "") String fileId,
			@RequestParam(value = "preferredClientId", defaultValue = "") String preferredClientId			
			)
	{
		
		talentArchitectRestProxy.stream("getTMParticipantsUploadStatus", request, response, new String[] { projectId,preferredClientId,fileId });
	} 
	 
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/extract", method = RequestMethod.GET)
	public void extractPotentialGrid(
	                    HttpServletRequest request,       HttpServletResponse response,
	                    @RequestParam(value = "projectId", defaultValue = "") String projectId,
	                    @RequestParam(value = "searchString", defaultValue = "") String searchString,
	                    @RequestParam(value = "searchColumn", defaultValue = "") String searchColumn,
	                    @RequestParam(value = "filterBy", defaultValue = "") String filterBy,
	                    @RequestParam(value = "filterValues", defaultValue = "") String filterValues,
	                    @RequestParam(value = "extractType", defaultValue = "") String extractType,
	                    @RequestParam(value = "o_tl", defaultValue = "") String o_tl,
	                    @RequestParam(value = "o_riskfactors", defaultValue = "") String o_riskfactors,
	                    @RequestParam(value = "o_spTargets", defaultValue = "") String o_spTargets,
	                    @RequestParam(value = "o_spId", defaultValue = "") String o_spId
	                    )
	       {
	            
	              talentArchitectRestProxy.stream("extractPotentialGrid", request, response, new String[] { projectId, searchString, searchColumn, filterBy, filterValues, extractType, o_tl, o_riskfactors,o_spTargets, o_spId});
	       }
	 
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/projectparticipantreminder", method = RequestMethod.POST)
	public void sendTMProjectParticipantReminder(
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		talentArchitectRestProxy.stream("sendTMProjectParticipantReminder", request, response, new String[] {  });
	
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/dashboardprojects", method = RequestMethod.GET)
	public void getDashboardProjects(
			HttpServletRequest request,	HttpServletResponse response,
			@RequestParam(value = "clientId", defaultValue = "") String clientId,
			@RequestParam(value = "userGroup", defaultValue = "") String userGroup,
			@RequestParam(value = "initiatedById", defaultValue = "") String initiatedById,
			@RequestParam(value = "projectScope", defaultValue = "") String projectScope,
			@RequestParam(value = "activeWithInDays", defaultValue = "") String activeWithInDays,
			@RequestParam(value = "projectTypes", defaultValue = "") String projectTypes
			)
	{
		
		talentArchitectRestProxy.stream("getTMDashboardProjects", request, response, new String[] { clientId, userGroup, initiatedById, projectScope, activeWithInDays, projectTypes});
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/dashboardparticipants", method = RequestMethod.GET)
	public void getDashboardParticipants(
			HttpServletRequest request,	HttpServletResponse response,
			@RequestParam(value = "clientId", defaultValue = "") String clientId,
			@RequestParam(value = "userGroup", defaultValue = "") String userGroup,
			@RequestParam(value = "initiatedById", defaultValue = "") String initiatedById,
			@RequestParam(value = "projectScope", defaultValue = "") String projectScope,
			@RequestParam(value = "projectTypes", defaultValue = "") String projectTypes
			)
	{
		
		talentArchitectRestProxy.stream("getTMDashboardParticipants", request, response, new String[] { clientId, userGroup, initiatedById, projectScope, projectTypes});
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/participantreports/{id}", method = RequestMethod.GET)
	public void getParticipantReports(
			@PathVariable String id,
			@RequestParam(value = "projectId", defaultValue = "") String projectId,
			HttpServletRequest request,	HttpServletResponse response
			)
	{
		
		talentArchitectRestProxy.stream("getTMParticipantReports", request, response, new String[] {id, projectId});
	}
	
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/successprofileusage/{jobId}", method = RequestMethod.GET)
	public void getTMSuccessProfileUsage(
			@PathVariable String jobId,
			@RequestParam(value = "clientId", defaultValue = "") String clientId,
			@RequestParam(value = "userGroup", defaultValue = "") String userGroup,
			HttpServletRequest request,	HttpServletResponse response
			)
	{
		
		talentArchitectRestProxy.stream("getTMSuccessProfileUsage", request, response, new String[] {jobId, clientId, userGroup});
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/sanctionsAudit", method = RequestMethod.PUT)
	public void logSanctionsAudit(
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		talentArchitectRestProxy.stream("logSanctionsAudit", request, response, new String[] {});
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/sanctionsConfirmation", method = RequestMethod.POST)
	public void recordSanctionsConfirmation(
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		talentArchitectRestProxy.stream("recordSanctionsConfirmation", request, response, new String[] {});
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/sanctionsConfirmation/{projectId}", method = RequestMethod.GET)
	public void getSanctionsConfirmation(
			@PathVariable String projectId,
			@RequestParam(value = "adminId", defaultValue = "") String adminId,
			HttpServletRequest request,	HttpServletResponse response
			)
	{
		
		talentArchitectRestProxy.stream("getSanctionsConfirmation", request, response, new String[] {projectId,adminId});
	}
	
}
