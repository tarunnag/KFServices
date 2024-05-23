package com.haygroup.leap.ucp;

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

/**
 * @author Harsha
 * 
 */

@Controller
@RequestMapping(value = "/hrms/ucp")
public class UCPController {

	private static final Logger logger = LoggerFactory.getLogger(UCPController.class);
	
	@Autowired
	private RestProxy restProxy;
	
	@Autowired
	private RestProxy restProxyForUCP;
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/sendemail", method = RequestMethod.POST)
	public void sendUCPEmailNotification(HttpServletRequest request, HttpServletResponse response) {
		restProxy.stream("sendUCPEmailNotification", request, response, new String[] {});
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/senducpemail", method = RequestMethod.POST)
	public void sendUCPEmail(HttpServletRequest request, HttpServletResponse response) {
		restProxyForUCP.stream("sendUCPEmail", request, response, new String[] {});
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/savestakeholders", method = RequestMethod.PUT)
	public void saveStakeHolders(HttpServletRequest request, HttpServletResponse response)
	{
		restProxyForUCP.stream("saveStakeHolders", request, response, new String[] {  });
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/deleteparticipants", method = RequestMethod.POST)
	public void deleteUCPParticipants(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "collabId", defaultValue = "") String collabId)

	{
		restProxyForUCP.stream("deleteUCPParticipants", request, response, new String[] { collabId });
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/getuserpermissionsbyemail", method = RequestMethod.GET)
	public void getUCPUserPermissionsByEmail(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "emailId", defaultValue = "") String emailId,
			@RequestParam(value = "sourceId", defaultValue = "") String sourceId,
			@RequestParam(value = "clientId", defaultValue = "") String clientId)

	{
		restProxyForUCP.stream("getUCPUserPermissionsByEmail", request, response, new String[] { emailId, sourceId, clientId});
	}

//	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/getsurveyparticipantsbycollabid", method = RequestMethod.GET)
	public void getSurveyParticipantsByCollabId(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "collabId", defaultValue = "") String collabId)

	{
		restProxyForUCP.stream("getSurveyParticipantsByCollabId", request, response, new String[] { collabId });
	}


//	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/getsurveyoutputparticipantsbycollabid", method = RequestMethod.GET)
	public void getSurveyOutputParticipantsByCollabId(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "collabId", defaultValue = "") String collabId)

	{
		restProxyForUCP.stream("getSurveyOutputParticipantsByCollabId", request, response, new String[] { collabId });
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/getallparticipantbycollabid", method = RequestMethod.GET)
	public void getAllParticipantByCollabId(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "collabId", defaultValue = "") String collabId)

	{
		restProxyForUCP.stream("getAllParticipantByCollabId", request, response, new String[] { collabId });
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/updatepermissions", method = RequestMethod.PUT)
	public void updatePermissions(HttpServletRequest request, HttpServletResponse response)
	{
		restProxyForUCP.stream("updatePermissions", request, response, new String[] {  });
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/savepermissions", method = RequestMethod.POST)
	public void savePermissions(HttpServletRequest request, HttpServletResponse response)
	{
		restProxyForUCP.stream("savePermissions", request, response, new String[] {  });
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/addadditionalpermissions", method = RequestMethod.POST)
	public void addAdditionalPermissions(HttpServletRequest request, HttpServletResponse response)
	{
		restProxyForUCP.stream("addAdditionalPermissions", request, response, new String[] {  });
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/getrolespermissions", method = RequestMethod.GET)
	public void getRolesPermissions(HttpServletRequest request, HttpServletResponse response)
	{
		restProxyForUCP.stream("getRolesPermissions", request, response, new String[] {  });
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/getparticipantpermissions", method = RequestMethod.GET)
	public void getParticipantPermissions(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "collabId", defaultValue = "") String collabId,
			@RequestParam(value = "participantId", defaultValue = "") String participantId)
	{
		restProxyForUCP.stream("getParticipantPermissions", request, response, new String[] {  });
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/getallparticipantpermissionsbyid", method = RequestMethod.GET)
	public void getAllParticipantPermissionsById(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "collabId", defaultValue = "") String collabId)
	{
		restProxyForUCP.stream("getAllParticipantPermissionsById", request, response, new String[] {collabId});
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/removepermissions", method = RequestMethod.DELETE)
	public void removePermissions(HttpServletRequest request, HttpServletResponse response)
	{
		restProxyForUCP.stream("removePermissions", request, response, new String[] {  });
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/removepermissionsbyid", method = RequestMethod.DELETE)
	public void removePermissionsById(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "collabId", defaultValue = "") String collabId,
			@RequestParam(value = "participantId", defaultValue = "") String participantId)
	{
		restProxyForUCP.stream("removePermissionsById", request, response, new String[] {collabId, participantId});
	}
	
//	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/updateidashboardjobanalysisdata", method = RequestMethod.POST)
	public void updateIDashboardJobAnalysisData(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "collabId", defaultValue = "") String collabId,
			@RequestParam(value = "participantId", defaultValue = "") String participantId)
	{
		restProxyForUCP.stream("updateIDashboardJobAnalysisData", request, response, new String[] {collabId, participantId});
	}
	
//	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/updateidashboardcompanyculturedata", method = RequestMethod.POST)
	public void updateIDashboardCompanyCultureData(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "collabId", defaultValue = "") String collabId,
			@RequestParam(value = "participantId", defaultValue = "") String participantId)
	{
		restProxyForUCP.stream("updateIDashboardCompanyCultureData", request, response, new String[] {collabId, participantId});
	}
	
//	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/updateidashboardcompdata", method = RequestMethod.POST)
	public void updateIDashboardCompData(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "collabId", defaultValue = "") String collabId,
			@RequestParam(value = "participantId", defaultValue = "") String participantId)
	{
		restProxyForUCP.stream("updateIDashboardCompData", request, response, new String[] {collabId, participantId});
	}
	
//	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/submitcompanyculture", method = RequestMethod.POST)
	public void submitCompanyCulture(HttpServletRequest request, HttpServletResponse response)
	{
		restProxyForUCP.stream("submitCompanyCulture", request, response, new String[] {});
	}
	
//	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/saverolerequirementslider", method = RequestMethod.POST)
	public void saveRoleRequirementSlider(HttpServletRequest request, HttpServletResponse response)
	{
		restProxyForUCP.stream("saveRoleRequirementSlider", request, response, new String[] {});
	}
	
//	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/savecompetency", method = RequestMethod.POST)
	public void saveCompetency(HttpServletRequest request, HttpServletResponse response)
	{
		restProxyForUCP.stream("saveCompetency", request, response, new String[] {});
	}
	
//	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/savecompanyculture", method = RequestMethod.POST)
	public void saveCompanyCulture(HttpServletRequest request, HttpServletResponse response)
	{
		restProxyForUCP.stream("saveCompanyCulture", request, response, new String[] {});
	}
	
//	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/completerolerequirementslider", method = RequestMethod.POST)
	public void completeRoleRequirementSlider(HttpServletRequest request, HttpServletResponse response)
	{
		restProxyForUCP.stream("completeRoleRequirementSlider", request, response, new String[] {});
	}
	
//	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/completecompetencysurvey", method = RequestMethod.POST)
	public void completeCompetencySurvey(HttpServletRequest request, HttpServletResponse response)
	{
		restProxyForUCP.stream("completeCompetencySurvey", request, response, new String[] {});
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/getcompetencybyid", method = RequestMethod.GET)
	public void getCompetencyById(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "id", defaultValue = "") String id)
	{
		restProxyForUCP.stream("getCompetencyById", request, response, new String[] {id});
	}
	
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/sendemailtoparticipant", method = RequestMethod.POST)
	public void sendEmailToParticipant(HttpServletRequest request, HttpServletResponse response)
	{
		restProxyForUCP.stream("sendEmailToParticipant", request, response, new String[] {});
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/emailtemplate", method = RequestMethod.GET)
	public void ucpEmailTemplate(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "templateId", defaultValue = "") String templateId,
			@RequestParam(value = "language", defaultValue = "") String language)
	{
		restProxyForUCP.stream("ucpEmailTemplate", request, response, new String[] {templateId, language});
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/emailtemplate", method = RequestMethod.POST)
	public void saveUCPEmailTemplate(HttpServletRequest request, HttpServletResponse response)
	{
		restProxyForUCP.stream("saveUCPEmailTemplate", request, response, new String[] {});
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/emailtemplate", method = RequestMethod.PUT)
	public void updateUCPEmailTemplate(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "templateId", defaultValue = "") String templateId)
	{
		restProxyForUCP.stream("updateUCPEmailTemplate", request, response, new String[] {templateId});
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/emailtemplate", method = RequestMethod.DELETE)
	public void deleteUCPEmailTemplate(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "templateId", defaultValue = "") String templateId)
	{
		restProxyForUCP.stream("deleteUCPEmailTemplate", request, response, new String[] {templateId});
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/savespconfigoptionvalue", method = RequestMethod.POST)
	public void saveSPConfigOptionValue(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "collabId", defaultValue = "") String collabId,
			@RequestParam(value = "spId", defaultValue = "") String spId,
			@RequestParam(value = "clientId", defaultValue = "") String clientId,
			@RequestParam(value = "participantId", defaultValue = "") String participantId,
			@RequestParam(value = "firstName", defaultValue = "") String firstName,
			@RequestParam(value = "lastName", defaultValue = "") String lastName,
			@RequestParam(value = "sourceSysId", defaultValue = "") String  sourceSysId)
	{
		restProxyForUCP.stream("saveSPConfigOptionValue", request, response, new String[] {collabId, spId, clientId, participantId, firstName, lastName, sourceSysId});
	}
	
//	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/publishsp", method = RequestMethod.POST)
	public void publishSp(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(name = "participantId", required = false) String participantId,
			@RequestParam(value = "collabId", defaultValue = "") String collabId)
	{
		restProxyForUCP.stream("publishSp", request, response, new String[] {collabId,participantId});
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/notificationassess", method = RequestMethod.POST)
	public void notificationAssess(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "spId", defaultValue = "") String spId)
	{
		restProxyForUCP.stream("notificationAssess", request, response, new String[] {spId});
	}
	
//	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/getsuccessprofilebyid", method = RequestMethod.GET)
	public void getSuccessProfileById(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "collabId", defaultValue = "") String collabId,
			@RequestParam(value = "type", defaultValue = "") String type,
			@RequestParam(value = "participantId", defaultValue = "") String participantId)
	{
		restProxyForUCP.stream("getSuccessProfileById", request, response, new String[] {collabId, type, participantId});
	}
	
//	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/getsprolereqslidervalbyid", method = RequestMethod.GET)
	public void getSPRoleReqSliderValById(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "collabId", defaultValue = "") String collabId,
			@RequestParam(value = "participantId", defaultValue = "") String participantId)
	{
		restProxyForUCP.stream("getSPRoleReqSliderValById", request, response, new String[] {collabId, participantId});
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/getprojectassessment", method = RequestMethod.GET)
	public void getUCPProjectAssessment(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "successProfileId", defaultValue = "") String successProfileId,
			@RequestParam(value = "projectType", defaultValue = "") String projectType)
	{
		restProxyForUCP.stream("getUCPProjectAssessment", request, response, new String[] {successProfileId, projectType});
	}
	
//	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/getcompanyculture", method = RequestMethod.GET)
	public void getCompanyCulture(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "collabId", defaultValue = "") String collabId,
			@RequestParam(value = "participantId", defaultValue = "") String participantId)
	{
		restProxyForUCP.stream("getCompanyCulture", request, response, new String[] {collabId, participantId});
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/getallconfig", method = RequestMethod.GET)
	public void getAllConfig(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "collabId", defaultValue = "") String collabId,
			@RequestParam(value = "spId", defaultValue = "") String spId,
			@RequestParam(value = "configType", defaultValue = "") String configType,
			@RequestParam(value = "clientId", defaultValue = "") String clientId)

	{
		restProxyForUCP.stream("getAllConfig", request, response, new String[] {collabId, spId, configType, clientId});
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/stopsurvey", method = RequestMethod.GET)
	public void stopSurvey(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "collabId", defaultValue = "") String collabId,
			@RequestParam(value = "participantId", defaultValue = "") String participantId)
	{
		restProxyForUCP.stream("stopSurvey", request, response, new String[] {collabId, participantId});
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/createsp", method = RequestMethod.POST)
	public void createSP(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(name = "participantId", required = false) String participantId,
			@RequestParam(value = "collabId", defaultValue = "") String collabId)
	{
		restProxyForUCP.stream("createSP", request, response, new String[] {collabId, participantId});
	}
	
//	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/saveconsent", method = RequestMethod.POST)
	public void saveConsent(HttpServletRequest request, HttpServletResponse response)
	{
		restProxyForUCP.stream("saveConsent", request, response, new String[] {});
	}
	
	
//	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/getconsentstatus", method = RequestMethod.GET)
	public void getConsentStatus(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "collabId", defaultValue = "") String collabId,
			@RequestParam(value = "participantId", defaultValue = "") String participantId)
	{
		restProxyForUCP.stream("getConsentStatus", request, response, new String[] {collabId, participantId});
	}
	
//	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/getuserpermissionexists", method = RequestMethod.POST)
	public void getUserPermissionExists(HttpServletRequest request, HttpServletResponse response)
	{
		restProxyForUCP.stream("getUserPermissionExists", request, response, new String[] {});
	}
	
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/generatetoken", method = RequestMethod.POST)
	public void generateToken(HttpServletRequest request, HttpServletResponse response)
	{
		restProxyForUCP.stream("generateToken", request, response, new String[] {});
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public void authenticate(HttpServletRequest request, HttpServletResponse response)
	{
		restProxyForUCP.stream("authenticate", request, response, new String[] {});
	}
	
//	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/token/{token}", method = RequestMethod.GET)
	public void getUCPToken(HttpServletRequest request, HttpServletResponse response,
			@PathVariable String token)
	{
		restProxyForUCP.stream("getUCPToken", request, response, new String[] {token});
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/addcollabmainconfiguration", method = RequestMethod.POST)
	public void addCollabMainConfiguration(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "authToken", defaultValue = "") String authToken)
	{
		restProxyForUCP.stream("addCollabMainConfiguration", request, response, new String[] {authToken});
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/getcollabmainbyid", method = RequestMethod.GET)
	public void getCollabMainById(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "collabId", defaultValue = "") String collabId)
	{
		restProxyForUCP.stream("getCollabMainById", request, response, new String[] {collabId});
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/getallcollabmain", method = RequestMethod.GET)
	public void getAllCollabMain(HttpServletRequest request, HttpServletResponse response)	{
		restProxyForUCP.stream("getAllCollabMain", request, response, new String[] {});
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/removecollabmain", method = RequestMethod.DELETE)
	public void removeCollabMain(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "collabId", defaultValue = "") String collabId)
	{
		restProxyForUCP.stream("removeCollabMain", request, response, new String[] {collabId});
	}
	

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/checkcollabidbyspid", method = RequestMethod.GET)
	public void checkCollabIdBySPId(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "spId", defaultValue = "") String spId)
	{
		restProxyForUCP.stream("checkCollabIdBySPId", request, response, new String[] {spId});
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/createspcollabaration", method = RequestMethod.GET)
	public void createSPCollabaration(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "spId", defaultValue = "") String spId,
			@RequestParam(value = "userEmail", defaultValue = "") String userEmail,
			@RequestParam(value = "clientId", defaultValue = "") String clientId,
			@RequestParam(value = "sourceSystemId", defaultValue = "") String sourceSystemId,
			@RequestParam(value = "projectType", defaultValue = "") String projectType)
	{
		restProxyForUCP.stream("createSPCollabaration", request, response, new String[] {spId, userEmail,clientId, sourceSystemId, projectType});
	}
	
//	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/userstage", method = RequestMethod.GET)
	public void ucpUserStage(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "collabId", defaultValue = "") String collabId,
			@RequestParam(value = "participantId", defaultValue = "") String participantId)
	{
		restProxyForUCP.stream("ucpUserStage", request, response, new String[] {collabId, participantId});
	}
	
//	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/jobanalysis", method = RequestMethod.GET)
	public void ucpJobAnalysis(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "collabId", defaultValue = "") String collabId,
			@RequestParam(value = "participantId", defaultValue = "") String participantId)
	{
		restProxyForUCP.stream("ucpJobAnalysis", request, response, new String[] {collabId, participantId});
	}
	
//	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/culturesortdetails", method = RequestMethod.GET)
	public void cultureSortDetails(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "collabId", defaultValue = "") String collabId,
			@RequestParam(value = "participantId", defaultValue = "") String participantId)
	{
		restProxyForUCP.stream("cultureSortDetails", request, response, new String[] {collabId, participantId});
	}
	
//	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/competencydetails", method = RequestMethod.GET)
	public void competencyDetails(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "collabId", defaultValue = "") String collabId,
			@RequestParam(value = "participantId", defaultValue = "") String participantId)
	{
		restProxyForUCP.stream("competencyDetails", request, response, new String[] {collabId, participantId});
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/getuserpermissionsbyemailforms", method = RequestMethod.GET)
	public void getUserPermissionsByEmailForms(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "emailId", defaultValue = "") String emailId,
			@RequestParam(value = "collabId", defaultValue = "") String collabId)
	{
		restProxyForUCP.stream("getUserPermissionsByEmailForms", request, response, new String[] {emailId,collabId});
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/savespconfigbyspid", method = RequestMethod.POST)
	public void saveSPConfigBySPId(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "spId", defaultValue = "") String spId,
			@RequestParam(value = "sourceSysId", defaultValue = "") String sourceSysId,
			@RequestParam(value = "clientId", defaultValue = "") String clientId,
			@RequestParam(value = "firstName", defaultValue = "") String firstName,
			@RequestParam(value = "lastName", defaultValue = "") String lastName,
			@RequestParam(value = "participantId", defaultValue = "") String participantId,
			@RequestParam(value = "projectId", defaultValue = "") String projectId)

	{
		restProxyForUCP.stream("saveSPConfigBySPId", request, response, new String[] {spId, sourceSysId, clientId, firstName, lastName, participantId,projectId});
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/savespconfigbycollabid", method = RequestMethod.POST)
	public void saveSPConfigByCollabId(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "collabId", defaultValue = "") String collabId,
			@RequestParam(value = "sourceSysId", defaultValue = "") String sourceSysId,
			@RequestParam(value = "clientId", defaultValue = "") String clientId,
			@RequestParam(value = "firstName", defaultValue = "") String firstName,
			@RequestParam(value = "lastName", defaultValue = "") String lastName,
			@RequestParam(value = "participantId", defaultValue = "") String participantId)

	{
		restProxyForUCP.stream("saveSPConfigByCollabId", request, response, new String[] {collabId, sourceSysId, clientId, firstName, lastName, participantId});
	}
	
	
}
