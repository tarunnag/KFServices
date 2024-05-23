package com.haygroup.leap.hrms.paydatacollection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.haygroup.leap.client.RestProxy;
import com.haygroup.leap.common.LeapUtil;

@Controller
@RequestMapping(value="/hrms/paydatacollection") 
public class PayDataCollectionController2 
{

	private static final Logger logger = LoggerFactory.getLogger(PayDataCollectionController2.class);
	
	@Autowired
	private LeapUtil leapUtil;
	@Autowired
	private RestProxy restProxy; 
	
	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/onboardingsurvey",
	method = RequestMethod.GET)
	public void getOnBoardingSurvey(
			@RequestParam(value = "mock", defaultValue = "") String mock,
			HttpServletRequest request,
			HttpServletResponse response) {

		restProxy.stream("getOnBoardingSurvey", request, response, new String[] { mock});

	}
	
	
	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/addonboardingtask", method = RequestMethod.POST)
	public void addOnboardingTask(
			HttpServletRequest request, HttpServletResponse response) {

		restProxy.stream("addOnboardingTask", request, response, new String[] {});

	}	

	
	
	/**
	 * @param clientId
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/companies/subscriptions", method = RequestMethod.GET)
	public void getPaySubscriptions(
			 @RequestParam(value = "pdcClientId", defaultValue = "") String pdcClientId,
			HttpServletRequest request, HttpServletResponse response) {

		restProxy.stream("getPaySubscriptions", request, response, new String[] {pdcClientId });

	}
	
	
	/**
	 * @param year
	 * @param clientId
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/companies/onboardingsurvey", method = RequestMethod.GET)
	public void getOrganizationOnboardingResponses(
			@RequestParam(value = "year", defaultValue = "") String year,
			@RequestParam(value = "pdcClientId", defaultValue = "") String pdcClientId,
			@RequestParam(value = "mock", defaultValue = "") String mock,
			HttpServletRequest request, HttpServletResponse response) {

		restProxy.stream("getOrganizationOnboardingResponses", request, response, new String[] {year,pdcClientId,mock });

	}
	
		
	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/companies/onboardingsurvey", method = RequestMethod.PUT)
	public void modifyOrganizationOnboardingResponses(
			HttpServletRequest request, HttpServletResponse response) {

		restProxy.stream("modifyOrganizationOnboardingResponses", request, response, new String[] {});

	}	
	
	
	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/companies/ltisurveys", method = RequestMethod.PUT)
	public void updateLtiSurveys(
			HttpServletRequest request, HttpServletResponse response) {

		restProxy.stream("updateLtiSurveys", request, response, new String[] {});

	}	
	
	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/successprofiles/kfgrade", method = RequestMethod.GET)
	public void getSuccessProfileKFGrade(
			@RequestParam(value = "levelsBelowCEO", defaultValue = "") String levelsBelowCEO,
			@RequestParam(value = "pdcClientId", defaultValue = "") String pdcClientId,
			@RequestParam(value = "gradeCalcType", defaultValue = "") String gradeCalcType,
			@RequestParam(value = "subId", defaultValue = "") String subId,
			HttpServletRequest request, HttpServletResponse response) {

		restProxy.stream("getSuccessProfileKFGrade", request, response, new String[] {levelsBelowCEO,pdcClientId,gradeCalcType,subId});

	}
	
	
	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/successprofiles/{successprofileId}", method = RequestMethod.GET)
	public void getSuccessProfileForPDC(
			@PathVariable String successprofileId,
			HttpServletRequest request, HttpServletResponse response) {

		restProxy.stream("getSuccessProfileForPDC", request, response, new String[] {successprofileId});

	}
	
	
	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/datasubmissions/tier3", method = RequestMethod.GET)
	public void getTier3Submissions(
			HttpServletRequest request, HttpServletResponse response) {

		restProxy.stream("getTier3Submissions", request, response, new String[] {});

	}
	
	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/datasubmissions/tier3/clients", method = RequestMethod.GET)
	public void getTier3SubmissionsClients(
			@RequestParam(value = "countryCode", defaultValue = "") String countryCode,
			HttpServletRequest request, HttpServletResponse response) {

		restProxy.stream("getTier3SubmissionsClients", request, response, new String[] {countryCode});

	}
	
	
//	/**
//	 * @param request
//	 * @param response
//	 */
//	@PreAuthorize("hasRole('ROLE_USER')")
//	@RequestMapping(value = "/datasubmissions/tier3/{jmuuid}", method = RequestMethod.POST)
//	public void setReadyForTier3(
//			@PathVariable String jmuuid,
//			HttpServletRequest request, HttpServletResponse response) {
//
//		restProxy.stream("setReadyForTier3", request, response, new String[] {jmuuid});
//
//	}
	
	
	
	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/datasubmissions/reports", method = RequestMethod.GET)
	public void getTier3Reports(
			HttpServletRequest request, HttpServletResponse response) {

		restProxy.stream("getTier3Reports", request, response, new String[] {});

	}
	
	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/datasubmissions/reports/download", method = RequestMethod.GET)
	public void getTier3ReportDownload(
			@RequestParam(value = "reportCode", defaultValue = "") String reportCode,
			@RequestParam(value = "countryCode", defaultValue = "") String countryCode,
			@RequestParam(value = "clientId", defaultValue = "") String clientId,
			@RequestParam(value = "clientOrgCode", defaultValue = "") String clientOrgCode,
			@RequestParam(value = "status", defaultValue = "") String status,
			HttpServletRequest request, HttpServletResponse response) {

		restProxy.stream("getTier3ReportDownload", request, response, new String[] {reportCode,countryCode,clientId,clientOrgCode,status});

	}
	
	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/companies/jobs", method = RequestMethod.GET)
	public void getClientJobs(
			@RequestParam(value = "clientId", defaultValue = "") String clientId,
			@RequestParam(value = "clientOrgCode", defaultValue = "") String clientOrgCode,
			@RequestParam(value = "countryCode", defaultValue = "") String countryCode,
			HttpServletRequest request, HttpServletResponse response) {

		restProxy.stream("getClientJobs", request, response, new String[] {clientId, clientOrgCode, countryCode});

	}
	
	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/companies/jobmodels", method = RequestMethod.GET)
	public void getClientJobModel(
			@RequestParam(value = "clientId", defaultValue = "") String clientId,
			@RequestParam(value = "clientOrgCode", defaultValue = "") String clientOrgCode,
			@RequestParam(value = "countryCode", defaultValue = "") String countryCode,
			HttpServletRequest request, HttpServletResponse response) {

		restProxy.stream("getClientJobModels", request, response, new String[] {clientId, clientOrgCode, countryCode});

	}
	
	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/companies/jobmodels/entities", method = RequestMethod.GET)
	public void getClientJobModelsEntities(
			@RequestParam(value = "entityType", defaultValue = "") String entityType,
			@RequestParam(value = "entityId", defaultValue = "") String entityId,
			@RequestParam(value = "clientId", defaultValue = "") String clientId,
			@RequestParam(value = "clientOrgCode", defaultValue = "") String clientOrgCode,
			@RequestParam(value = "countryCode", defaultValue = "") String countryCode,
			HttpServletRequest request, HttpServletResponse response) {

		restProxy.stream("getClientJobModelsEntities", request, response, new String[] {entityType, entityId, clientId, clientOrgCode, countryCode});

	}
	
}
