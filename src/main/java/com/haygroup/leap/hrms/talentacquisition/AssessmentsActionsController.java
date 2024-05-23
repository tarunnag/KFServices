package com.haygroup.leap.hrms.talentacquisition;


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
@RequestMapping(value="/hrms/assessments/actions")  
public class AssessmentsActionsController { 
	
private static final Logger logger = LoggerFactory.getLogger(AssessmentsActionsController.class);
	
	@Autowired
	private LeapUtil leapUtil;
	@Autowired
	private RestProxy talentArchitectRestProxy;
	@Autowired
	private RestProxy restProxy;
	
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/participantsprogressmetadata", method = RequestMethod.GET)
	public void searchParticipantsProgressMetadata(
			@RequestParam(value = "outputType", defaultValue = "") String type,			
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		talentArchitectRestProxy.secureStream("searchParticipantsProgressMetadata", request, response, new String[] { type});
	
	} 
		
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/participantsprogress/{id}", method = RequestMethod.GET)
	public void searchParticipantsProgress(
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
		
		talentArchitectRestProxy.secureStream("searchParticipantsProgress", request, response, 
				new String[] { id,sortColumn,sortBy,searchString,searchColumn,filterBy,filterValues,pageIndex,pageSize,successProfileId,exCandidateId,exEmployeeId});
	
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/inviteparticipants", method = RequestMethod.POST)
	public void inviteParticipants(	
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		talentArchitectRestProxy.secureStream("inviteParticipants", request, response, new String[] {  });
	
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/calculateassessmentscoringattributes", method = RequestMethod.POST)
	public void calculateAssessmentScoringAttributes(
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("calculateAssessmentScoringAttributes", request, response, new String[] {  });
	
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/resetassessments", method = RequestMethod.POST)
	public void resetAssessments(
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		talentArchitectRestProxy.stream("resetAssessments", request, response, new String[] {  });
	
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/reenableassessments", method = RequestMethod.POST)
	public void reEnableAssessments(
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		talentArchitectRestProxy.stream("reEnableAssessments", request, response, new String[] {  });
	
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/exporttalentgrid", method = RequestMethod.GET)
	public void exportTalentGrid(
			HttpServletRequest request,	HttpServletResponse response,
			@RequestParam(value = "projectId", defaultValue = "") String projectId,			
			@RequestParam(value = "searchString", defaultValue = "") String searchString,		
			@RequestParam(value = "searchColumn", defaultValue = "") String searchColumn,		
			@RequestParam(value = "filterBy", defaultValue = "") String filterBy,		
			@RequestParam(value = "filterValues", defaultValue = "") String filterValues,		
			@RequestParam(value = "extractType", defaultValue = "") String extractType,
			@RequestParam(value = "o_spId", defaultValue = "") String o_spId,
			@RequestParam(value = "o_riskfactors", defaultValue = "") String o_riskfactors,
	    @RequestParam(value = "o_fitScoreAsDecimal", defaultValue = "") String o_fitScoreAsDecimal,
	    @RequestParam(value = "o_tl", defaultValue = "") String o_tl,
    	@RequestParam(value = "o_includeNonBillable", defaultValue = "") String o_includeNonBillable,
			@RequestParam(value = "o_include_completed_details", defaultValue = "") String o_include_completed_details)
	{
		
		talentArchitectRestProxy.stream("exportTalentGrid", request, response, new String[] { projectId, searchString, searchColumn, filterBy, filterValues, extractType, o_spId, o_riskfactors, o_fitScoreAsDecimal,o_tl,o_includeNonBillable,o_include_completed_details});
	
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/participantsuploadtemplates", method = RequestMethod.GET)
	public void getParticipantsUploadTemplates(
			HttpServletRequest request,	HttpServletResponse response,
			@RequestParam(value = "projectId", defaultValue = "") String projectId,
			@RequestParam(value = "preferredClientId", defaultValue = "") String preferredClientId,
			@RequestParam(value = "authToken", defaultValue = "") String authToken,
			@RequestParam(value = "preferredLocale", defaultValue = "") String preferredLocale
			)
	{
		
		talentArchitectRestProxy.stream("getParticipantsUploadTemplates", request, response, new String[] { projectId,preferredClientId,authToken,preferredLocale });
		
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/participantsuploadfile", method = RequestMethod.POST)
	public void uploadParticipantsFile(
			HttpServletRequest request,	HttpServletResponse response,
			@RequestParam(value = "projectId", defaultValue = "") String projectId,
			@RequestParam(value = "preferredClientId", defaultValue = "") String preferredClientId,
			@RequestParam(value = "userGroup", defaultValue = "") String userGroup
			)
	{
		 
		talentArchitectRestProxy.stream("uploadParticipantsFile", request, response, new String[] { projectId,preferredClientId, userGroup });
	
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/uploadparticipantsvalidationerrors", method = RequestMethod.GET)
	public void getParticipantsValidationErrors(
			HttpServletRequest request,	HttpServletResponse response,
			@RequestParam(value = "projectId", defaultValue = "") String projectId,
			@RequestParam(value = "fileGuid", defaultValue = "") String fileGUId,
			@RequestParam(value = "preferredClientId", defaultValue = "") String preferredClientId,
			@RequestParam(value = "authToken", defaultValue = "") String authToken
			)
	{
		
		talentArchitectRestProxy.stream("getParticipantsValidationErrors", request, response, new String[] { projectId,fileGUId,preferredClientId, authToken, });
	
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/participantsuploadpreview", method = RequestMethod.GET)
	public void getParticipantsUploadPreview(
			HttpServletRequest request,	HttpServletResponse response,
			@RequestParam(value = "projectId", defaultValue = "") String projectId,
			@RequestParam(value = "fileGuid", defaultValue = "") String fileGUId,
			@RequestParam(value = "preferredClientId", defaultValue = "") String preferredClientId			
			)
	{
		
		talentArchitectRestProxy.stream("getParticipantsUploadPreview", request, response, new String[] { projectId,fileGUId,preferredClientId });
	}
	
	
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/participantsupload", method = RequestMethod.PUT)  
	public void uploadParticipant(
			HttpServletRequest request,	HttpServletResponse response//,
			//@RequestParam(value = "projectId", defaultValue = "") String projectId,
			//@RequestParam(value = "fileGuid", defaultValue = "") String fileGuid,
			//@RequestParam(value = "preferredClientId", defaultValue = "") String preferredClientId			
			)
	{
		
		talentArchitectRestProxy.stream("uploadParticipant", request, response, new String[] {  });
	
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/resetsjtassessments", method = RequestMethod.POST)
	public void resetsjtassessments(
			HttpServletRequest request,	HttpServletResponse response	
			)
	{
		
		talentArchitectRestProxy.stream("resetsjtassessments", request, response, new String[] {  });
	
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/participantUploadStatus", method = RequestMethod.GET)
	public void getParticipantsUploadStatus(
			HttpServletRequest request,	HttpServletResponse response,
			@RequestParam(value = "projectId", defaultValue = "") String projectId,
			@RequestParam(value = "fileId", defaultValue = "") String fileId,
			@RequestParam(value = "preferredClientId", defaultValue = "") String preferredClientId			
			)
	{
		
		talentArchitectRestProxy.stream("getParticipantsUploadStatus", request, response, new String[] { projectId,preferredClientId,fileId });
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/emailtemplates", method = RequestMethod.GET)
	public void getEmailTemplates(
			HttpServletRequest request,	HttpServletResponse response,
			@RequestParam(value = "templateId", defaultValue = "") String templateId,
			@RequestParam(value = "preferredClientId", defaultValue = "") String preferredClientId			
			)
	{
		
		talentArchitectRestProxy.stream("getEmailTemplates", request, response, new String[] { templateId,preferredClientId });
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/emailtemplates", method = RequestMethod.POST)
	public void createEmailTemplates(	
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		talentArchitectRestProxy.secureStream("createEmailTemplates", request, response, new String[] {  });
	
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/emailtemplates", method = RequestMethod.PUT)
	public void updateEmailTemplates(	
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		talentArchitectRestProxy.secureStream("updateEmailTemplates", request, response, new String[] {  });
	
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/emailtemplates", method = RequestMethod.DELETE)
	public void deleteEmailTemplates(
			HttpServletRequest request,	HttpServletResponse response,
			@RequestParam(value = "templateId", defaultValue = "") String templateId,
			@RequestParam(value = "preferredClientId", defaultValue = "") String preferredClientId			
			)
	{
		
		talentArchitectRestProxy.stream("deleteEmailTemplates", request, response, new String[] { templateId,preferredClientId });
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/projectparticipantreminder", method = RequestMethod.POST)
	public void sendProjectParticipantReminder(
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		talentArchitectRestProxy.stream("sendProjectParticipantReminder", request, response, new String[] {  });
	
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
		
		talentArchitectRestProxy.stream("getDashboardProjects", request, response, new String[] { clientId, userGroup, initiatedById, projectScope, activeWithInDays, projectTypes});
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
		
		talentArchitectRestProxy.stream("getDashboardParticipants", request, response, new String[] { clientId, userGroup, initiatedById, projectScope, projectTypes});
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/participantreports/{id}", method = RequestMethod.GET)
	public void getParticipantReports(
			@PathVariable String id,
			@RequestParam(value = "projectId", defaultValue = "") String projectId,
			HttpServletRequest request,	HttpServletResponse response
			)
	{
		
		talentArchitectRestProxy.stream("getParticipantReports", request, response, new String[] {id, projectId});
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/successprofileusage/{jobId}", method = RequestMethod.GET)
	public void getSuccessProfileUsage(
			@PathVariable String jobId,
			@RequestParam(value = "clientId", defaultValue = "") String clientId,
			@RequestParam(value = "userGroup", defaultValue = "") String userGroup,
			HttpServletRequest request,	HttpServletResponse response
			)
	{
		
		talentArchitectRestProxy.stream("getSuccessProfileUsage", request, response, new String[] {jobId, clientId, userGroup});
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/exportunitusagereport", method = RequestMethod.GET)
	public void getUsageReportExtract(
			@RequestParam(value = "o_startDate", defaultValue = "") String startDate,
			@RequestParam(value = "o_endDate", defaultValue = "") String endDate,
			@RequestParam(value = "o_clientIds", defaultValue = "") String clientIds,
			HttpServletRequest request,	HttpServletResponse response
			)
	{
		
		talentArchitectRestProxy.stream("getUsageReportExtract", request, response, new String[] {startDate, endDate, clientIds});
	}
		
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/participantmatch", method = RequestMethod.POST)
	public void postParticipantMatch(
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		talentArchitectRestProxy.stream("postParticipantMatch", request, response, new String[] {});
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/participantbatch", method = RequestMethod.POST)
	public void postParticipantBatch(
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		talentArchitectRestProxy.stream("postParticipantBatch", request, response, new String[] {});
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/clientparticipantsearch", method = RequestMethod.GET)
	public void clientParticipantSearch(
			@RequestParam(value = "sortColumn", defaultValue = "") String sortColumn,		
			@RequestParam(value = "sortBy", defaultValue = "") String sortBy,		
			@RequestParam(value = "searchString", defaultValue = "") String searchString,		
			@RequestParam(value = "searchColumn", defaultValue = "") String searchColumn,		
			@RequestParam(value = "filterBy", defaultValue = "") String filterBy,		
			@RequestParam(value = "filterValues", defaultValue = "") String filterValues,		
			@RequestParam(value = "pageIndex", defaultValue = "") String pageIndex,		
			@RequestParam(value = "pageSize", defaultValue = "") String pageSize,
			@RequestParam(value = "userGroup", defaultValue = "") String userGroup,
			@RequestParam(value = "exCandidateId", defaultValue = "") String exCandidateId,
			@RequestParam(value = "exEmployeeId", defaultValue = "") String exEmployeeId,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		talentArchitectRestProxy.stream("clientParticipantSearch", request, response, new String[] {sortColumn,sortBy,searchString,searchColumn,filterBy,filterValues,pageIndex,pageSize,userGroup,exCandidateId,exEmployeeId});
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/addexistingparticipantsearch", method = RequestMethod.GET)
	public void addExistingParticipantSearch(
			@RequestParam(value = "projectIdCurrent", defaultValue = "") String projectIdCurrent,		
			@RequestParam(value = "searchString", defaultValue = "") String searchString,		
			@RequestParam(value = "filterBy", defaultValue = "") String filterBy,		
			@RequestParam(value = "filterValues", defaultValue = "") String filterValues,		
			@RequestParam(value = "pageSize", defaultValue = "") String pageSize,
			@RequestParam(value = "pageIndex", defaultValue = "") String pageIndex,
			@RequestParam(value = "sortColumn", defaultValue = "") String sortColumn,
			@RequestParam(value = "sortDirection", defaultValue = "") String sortDirection,
			@RequestParam(value = "userGroup", defaultValue = "") String userGroup,	
			@RequestParam(value = "exCandidateId", defaultValue = "") String exCandidateId,
			@RequestParam(value = "exEmployeeId", defaultValue = "") String exEmployeeId,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		talentArchitectRestProxy.stream("addExistingParticipantSearch", request, response, new String[] {projectIdCurrent,searchString,filterBy,filterValues,pageSize,pageIndex,sortColumn,sortDirection,userGroup,exCandidateId,exEmployeeId});
	}
	
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/resetassessmentsv1.1", method = RequestMethod.POST)
	public void postResetassessments(
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		talentArchitectRestProxy.stream("postResetassessments", request, response, new String[] {});
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/resetsjtassessmentsv1.1", method = RequestMethod.POST)
	public void postResetjtassessments(
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		talentArchitectRestProxy.stream("postResetjtassessments", request, response, new String[] {});
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/clientparticipantsearchmetadata", method = RequestMethod.GET)
	public void clientParticipantSearchmetadata(
			@RequestParam(value = "userGroup", defaultValue = "") String userGroup,			
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		talentArchitectRestProxy.secureStream("clientParticipantSearchmetadata", request, response, new String[] { userGroup});
	
	} 
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/exporttalentgridmulti", method = RequestMethod.GET)
	public void getMultiProjectExtracts(
			@RequestParam(value = "projectIds", defaultValue = "") String projectIds,	
			@RequestParam(value = "searchString", defaultValue = "") String searchString,	
			@RequestParam(value = "searchColumn", defaultValue = "") String searchColumn,	
			@RequestParam(value = "filterBy", defaultValue = "") String filterBy,
			@RequestParam(value = "filterValues", defaultValue = "") String filterValues,
			@RequestParam(value = "extractType", defaultValue = "") String extractType,
			@RequestParam(value = "clientId", defaultValue = "") String clientId,
			@RequestParam(value = "o_include_completed_details", defaultValue = "") String o_include_completed_details,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		talentArchitectRestProxy.secureStream("getMultiProjectExtracts", request, response, new String[] { projectIds, searchString, searchColumn, filterBy, filterValues,extractType, clientId,o_include_completed_details});
	
	} 
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/normupdatepreview/{projectId}", method = RequestMethod.POST)
	public void normUpdatePreview(
			@PathVariable String projectId,
			@RequestParam(value = "locale", defaultValue = "") String locale,
			@RequestParam(value = "clientId", defaultValue = "") String clientId,	
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		talentArchitectRestProxy.secureStream("normUpdatePreview", request, response, new String[] {projectId, locale,clientId });
	
	} 
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/reportrelease", method = RequestMethod.POST)
	public void reportRelease(
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		talentArchitectRestProxy.secureStream("reportRelease", request, response, new String[] {});
	
	} 
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/reportbranding/{clientId}", method = RequestMethod.GET)
	public void clientReportBranding(
			@PathVariable String clientId,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		talentArchitectRestProxy.secureStream("clientReportBranding", request, response, new String[] {clientId});
	
	} 
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/emailsetting/{emailSettingId}", method = RequestMethod.GET)
	public void getEmailScheduleById(
			@PathVariable String emailSettingId,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		talentArchitectRestProxy.secureStream("getEmailScheduleById", request, response, new String[] {emailSettingId});
	
	} 
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/emailsetting/project/{projectId}", method = RequestMethod.GET)
	public void getAllEmailSchedulesByProjectId(
			@PathVariable String projectId,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		talentArchitectRestProxy.secureStream("getAllEmailSchedulesByProjectId", request, response, new String[] {projectId});
	
	} 
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/emailsetting", method = RequestMethod.POST)
	public void createOrUpdateEmailSchedules(
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		talentArchitectRestProxy.secureStream("createOrUpdateEmailSchedules", request, response, new String[] {});
	
	} 
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/emailsetting/{emailSettingId}", method = RequestMethod.DELETE)
	public void deleteEmailScheduleById(
			@PathVariable String emailSettingId,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		talentArchitectRestProxy.secureStream("deleteEmailScheduleById", request, response, new String[] {emailSettingId});
	
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/participantsHiredBatch", method = RequestMethod.POST)
	public void participantHiredBatch(
			@RequestParam(value = "clientId", defaultValue = "") String clientId,	
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		talentArchitectRestProxy.secureStream("participantHiredBatch", request, response, new String[] {clientId });
	
	} 
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/successprofilepublished", method = RequestMethod.POST)
	public void successprofilepublished(
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		talentArchitectRestProxy.secureStream("successprofilepublished", request, response, new String[] { });
	
	} 
	
}
