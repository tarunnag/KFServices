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
@RequestMapping(value="/hrms/paydatacollection/actions") 
public class PayDataCollectionActionsController 
{

	private static final Logger logger = LoggerFactory.getLogger(PayDataCollectionActionsController.class);
	
	@Autowired
	private LeapUtil leapUtil;
	@Autowired
	private RestProxy restProxy;


	
	
	/**
	 * @param year
	 * @param clientId
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/leaderboard/assign", method = RequestMethod.POST)
	public void assignCompanyLeaderboard(
			HttpServletRequest request, HttpServletResponse response) {

		restProxy.stream("assignCompanyLeaderboard", request, response, new String[] {});

	}	
	
	/**
	 * @param year
	 * @param clientId
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/leaderboard/addcountry", method = RequestMethod.POST)
	public void addLeaderboardCountry(
			HttpServletRequest request, HttpServletResponse response) {

		restProxy.stream("addLeaderboardCountry", request, response, new String[] {});

	}
	
	/**
	 * @param year
	 * @param clientId
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/leaderboard/addregion", method = RequestMethod.POST)
	public void addRegion(
			HttpServletRequest request, HttpServletResponse response) {

		restProxy.stream("addRegion", request, response, new String[] {});

	}
	
	
	
	/**
	 * @param year
	 * @param clientId
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/surveystatus", method = RequestMethod.GET)
	public void getCompanySurveyStatus(
			@RequestParam(value = "year", defaultValue = "") String year,
			@RequestParam(value = "pdcClientId", defaultValue = "") String pdcClientId,
			HttpServletRequest request, HttpServletResponse response) {

		restProxy.stream("getCompanySurveyStatus", request, response, new String[] {year,pdcClientId});

	}
	

	/**
	 * @param year
	 * @param clientId
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/survey", method = RequestMethod.GET)
	public void getSurvey(
			@RequestParam(value = "type", defaultValue = "") String type,
			HttpServletRequest request, HttpServletResponse response) {

		restProxy.stream("getSurvey", request, response, new String[] {type});

	}
	
	@Deprecated
	/**
	 * @param type
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/searchcountries", method = RequestMethod.GET)
	public void searchCountries(@RequestParam(value = "type", defaultValue = "") String type,
			@RequestParam(value = "searchString", defaultValue = "") String searchString,
			@RequestParam(value = "searchColumn", defaultValue = "") String searchColumn,
			@RequestParam(value = "jobRoleTypeId", defaultValue = "") String jobRoleTypeId,
			@RequestParam(value = "standardHayGrade", defaultValue = "") String standardHayGrade,
			@RequestParam(value = "sortColumn", defaultValue = "") String sortColumn,
			@RequestParam(value = "sortBy", defaultValue = "") String sortBy,
			@RequestParam(value = "pageIndex", defaultValue = "") String pageIndex,
			@RequestParam(value = "pageSize", defaultValue = "") String pageSize,
			@RequestParam(value = "displayAllLanguages", defaultValue = "") String displayAllLanguages,
			@RequestParam(value = "preferredLocale", defaultValue = "") String preferredLocale,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("searchCountries", request, response, new String[] { type,searchString,searchColumn,jobRoleTypeId,standardHayGrade,
				sortColumn, sortBy, pageIndex, pageSize,displayAllLanguages,preferredLocale });

	}
	
	/**
	 * @param year
	 * @param clientId
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/movecountries", method = RequestMethod.POST)
	public void moveCountries(
			HttpServletRequest request, HttpServletResponse response) {

		restProxy.stream("moveCountries", request, response, new String[] {});

	}

	/**
	 * @param year
	 * @param clientId
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/countries", method = RequestMethod.GET)
	public void getPayCountries(
			HttpServletRequest request, HttpServletResponse response) {

		restProxy.stream("getPayCountries", request, response, new String[] {});

	}
	
	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/leaderboard/updatecontentstatus", method = RequestMethod.PUT)
	public void updatecontentstatus(
			@RequestParam(value = "pdcClientId", defaultValue = "") String pdcClientId,
			HttpServletRequest request, HttpServletResponse response) {

		restProxy.stream("updatecontentstatus", request, response, new String[] {pdcClientId});

	}	
		
	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/leaderboard/updateregion", method = RequestMethod.PUT)
	public void updateregion(
			HttpServletRequest request, HttpServletResponse response) {

		restProxy.stream("updateregion", request, response, new String[] {});

	}	
	
	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/leaderboard/sendemail", method = RequestMethod.POST)
	public void sendemail(
			HttpServletRequest request, HttpServletResponse response) {

		restProxy.stream("sendemail", request, response, new String[] {});

	}	

	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/surveys/assign", method = RequestMethod.POST)
	public void assignToSurvey(
			HttpServletRequest request, HttpServletResponse response) {

		restProxy.stream("assignToSurvey", request, response, new String[] {});

	}	
	
	
	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/surveys/remove", method = RequestMethod.POST)
	public void removeSurvey(
			HttpServletRequest request, HttpServletResponse response) {

		restProxy.stream("removeSurvey", request, response, new String[] {});

	}	

	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/surveys/changestatus", method = RequestMethod.POST)
	public void changeStatus(
			HttpServletRequest request, HttpServletResponse response) {

		restProxy.stream("changeStatus", request, response, new String[] {});

	}	
	
	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/leaderboard/remove", method = RequestMethod.POST)
	public void removeFromLeaderboard(
			HttpServletRequest request, HttpServletResponse response) {

		restProxy.stream("removeFromLeaderboard", request, response, new String[] {});

	}
	
	
	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/leaderboard/updateleaderboard", method = RequestMethod.POST)
	public void updateLeaderboard(
			HttpServletRequest request, HttpServletResponse response) {

		restProxy.stream("updateLeaderboard", request, response, new String[] {});

	}
	
	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/referencelevels", method = RequestMethod.GET)
	public void getReferenceLevels(
			HttpServletRequest request, HttpServletResponse response) {

		restProxy.stream("getReferenceLevels", request, response, new String[] {});

	}
	
	
	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/leaderboard/activityreport", method = RequestMethod.GET)
	public void getLeaderboardActivityReport(
			@RequestParam(value = "countryCode", defaultValue = "") String countryCode,
			@RequestParam(value = "preferredClientId", defaultValue = "") String preferredClientId,
			@RequestParam(value = "module", defaultValue = "") String module,
			@RequestParam(value = "clientType", defaultValue = "") String clientType,
			HttpServletRequest request, HttpServletResponse response) {

		restProxy.stream("getLeaderboardActivityReport", request, response, new String[] {countryCode,preferredClientId, module, clientType});

	}
	
	/**
	 * @param clientId
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/surveys/export", method = RequestMethod.GET)
	public void getSurveyExport(
			@RequestParam(value = "preferredClientId", defaultValue = "") String preferredClientId,
			HttpServletRequest request, HttpServletResponse response) {
		
	  	restProxy.stream("getSurveyExport", request, response, new String[] {preferredClientId});

	}	

	/**
	 * @param year
	 * @param clientId
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/leaderboard/idc", method = RequestMethod.PUT)
	public void setUpIDCLeaderboard(
			@RequestParam(value = "preferredClientId", defaultValue = "") String preferredClientId,
			HttpServletRequest request, HttpServletResponse response) {

		restProxy.stream("setUpIDCLeaderboard", request, response, new String[] {preferredClientId});

	}
	
	/**
	 * @param clientId
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/uploadtemplate", method = RequestMethod.GET)
	public void getUploadTemplate(
			HttpServletRequest request, HttpServletResponse response) {
		
	  	restProxy.stream("getUploadTemplate", request, response, new String[] {});

	}	
	
	
	/**
	 * @param clientId
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/uploadtemplate/metadata", method = RequestMethod.GET)
	public void getUploadTemplateMetadata(
			@RequestParam(value = "adhocId", defaultValue = "") String adhocId,
			HttpServletRequest request, HttpServletResponse response) {
		
	  	restProxy.stream("getUploadTemplateMetadata", request, response, new String[] {adhocId});

	}	
	
	
	/**
	 * @param clientId
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/uploadfile", method = RequestMethod.POST)
	public void uploadFile(
			@RequestParam(value = "adhocId", defaultValue = "") String adhocId,
			HttpServletRequest request, HttpServletResponse response) {
		
	  	restProxy.stream("uploadFile", request, response, new String[] {adhocId});

	}	

	
	/**
	 * @param clientId
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/leaderboard/countryduedates", method = RequestMethod.GET)
	public void getCountryDueDates(
			@RequestParam(value = "countryCode", defaultValue = "") String countryCode,
			HttpServletRequest request, HttpServletResponse response) {
		
	  	restProxy.stream("getCountryDueDates", request, response, new String[] {countryCode});

	}
	
	
	/**
	 * @param clientId
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/leaderboard/countryduedates", method = RequestMethod.PUT)
	public void updateCountryDueDate(
			HttpServletRequest request, HttpServletResponse response) {
		
	  	restProxy.stream("updateCountryDueDate", request, response, new String[] {});

	}	

	
	/**
	 * @param clientId
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/leaderboard/updateleaderboardcountrydate", method = RequestMethod.PUT)
	public void updateLeaderboardCountryDueDate(
			HttpServletRequest request, HttpServletResponse response) {
		
	  	restProxy.stream("updateLeaderboardCountryDueDate", request, response, new String[] {});

	}	

	
	/**
	 * @param clientId
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/countrytemplates/updateemailaddress", method = RequestMethod.PUT)
	public void updateCountryTemplateEmailAddress(
			HttpServletRequest request, HttpServletResponse response) {
		
	  	restProxy.stream("updateCountryTemplateEmailAddress", request, response, new String[] {});

	}	
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/surveytransform", method = RequestMethod.PUT)
	public void surveyTransform(
			@RequestParam(value = "clientName", defaultValue = "") String clientName,
			@RequestParam(value = "buyerName", defaultValue = "") String buyerName,
			@RequestParam(value = "buyerEmail", defaultValue = "") String buyerEmail,
			HttpServletRequest request, HttpServletResponse response) {
		restProxy.stream("surveyTransform", request, response, new String[]{clientName,buyerName,buyerEmail});
	}
	
	/**
	 * @param clientId
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/saveclientsubmittedext", method = RequestMethod.POST)
	public void saveclientsubmittedext(
			HttpServletRequest request, HttpServletResponse response) {
		
	  	restProxy.stream("saveclientsubmittedext", request, response, new String[] {});

	}	
	

	
	/**
	 * @param clientId
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/pgclientcenabledountries", method = RequestMethod.GET)
	public void pgclientEnabledCountries(
			HttpServletRequest request, HttpServletResponse response) {
		
	  	restProxy.stream("pgclientEnabledCountries", request, response, new String[] {});

	}	
	
	/**
	 * @param clientId
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/countriesforvalidation", method = RequestMethod.GET)
	public void pgclientEnabledCountriesfort2validation(
			@RequestParam(value = "countryCode", defaultValue = "") String countryCode,
			@RequestParam(value = "preferredClientId", defaultValue = "") String preferredClientId,	
			HttpServletRequest request, HttpServletResponse response) {
		
	  	restProxy.stream("pgclientEnabledCountriesfort2validation", request, response, new String[] {countryCode,preferredClientId});

	}	
	
	
	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/resetorgdata", method = RequestMethod.POST)
	public void postResetOrgData(HttpServletRequest request, HttpServletResponse response) {
		restProxy.stream("postResetOrgData", request, response, new String[] {});

	}
	
	/**
	 * @param clientId
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/resetorgdate/config", method = RequestMethod.GET)
	public void getResetConfig(
			HttpServletRequest request, HttpServletResponse response) {
		
	  	restProxy.stream("getResetConfig", request, response, new String[] {});

	}	
	
	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/leaderboard/updateceograde", method = RequestMethod.POST)
	public void updateCEOGradeLeaderboard(
			HttpServletRequest request, HttpServletResponse response) {
		
	  	restProxy.stream("updateCEOGradeLeaderboard", request, response, new String[] {});

	}
	
	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/leaderboard/uploadauditlogs", method = RequestMethod.GET)
	public void getAuditErrorUploadLogs(
			@RequestParam(value="searchString", defaultValue = "") String searchString,
			@RequestParam(value="searchColumn", defaultValue = "") String searchColumn,
			@RequestParam(value="filterBy", defaultValue = "") String filterBy,
			@RequestParam(value="filterValues", defaultValue = "") String filterValues,
			@RequestParam(value="pageIndex", defaultValue = "") String pageIndex,
			@RequestParam(value="pageSize", defaultValue = "") String pageSize,
			HttpServletRequest request, HttpServletResponse response) {
		
	  	restProxy.stream("getAuditErrorUploadLogs", request, response, new String[] {searchString, searchColumn, filterBy, filterValues, pageIndex, pageSize});

	}
	
	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/leaderboard/uploadauditlogs", method = RequestMethod.POST)
	public void updateAuditErrorUploadLogs(
			HttpServletRequest request, HttpServletResponse response) {
		
	  	restProxy.stream("updateAuditErrorUploadLogs", request, response, new String[] {});

	}
	
	
	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/leaderboard/modulepercentage", method = RequestMethod.GET	)
	public void getModulePercentage(
			@RequestParam(value="clientId", defaultValue = "") String clientId,
			@RequestParam(value="leaderboardId", defaultValue = "") String leaderboardId,
			@RequestParam(value="countryCode", defaultValue = "") String countryCode,
			@RequestParam(value="contentType", defaultValue = "") String contentType,
			@RequestParam(value="clientOrgCode", defaultValue = "") String clientOrgCode,
			@RequestParam(value="idcFlag", defaultValue = "") String idcFlag,
			HttpServletRequest request, HttpServletResponse response) {
		
	  	restProxy.stream("getModulePercentage", request, response, new String[] {clientId, leaderboardId, countryCode, contentType, clientOrgCode, idcFlag});

	}
	
	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/leaderboard/auditconfigdata", method = RequestMethod.GET	)
	public void getauditConfigData(
			@RequestParam(value="dateInHours", defaultValue = "") String dateInHours,
			HttpServletRequest request, HttpServletResponse response) {
		
	  	restProxy.stream("getauditConfigData", request, response, new String[] {dateInHours});

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value="/surveys/onboardingsurveyextract", method = RequestMethod.GET)
	public void getOnboardingSurveyExtract(
			@RequestParam(value="countryCode", defaultValue = "") String countryCode,
			@RequestParam(value="module", defaultValue = "") String module,
			@RequestParam(value="preferredClientId", defaultValue = "") String preferredClientId,
			HttpServletRequest request, HttpServletResponse response) {
		
		
		restProxy.stream("getOnboardingSurveyExtract", request, response, new String[] {countryCode, module, preferredClientId});

	}
	
	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/leaderboard/emailnotification", method = RequestMethod.POST)
	public void updateEmailNotification(
			HttpServletRequest request, HttpServletResponse response) {
		
	  	restProxy.stream("updateEmailNotification", request, response, new String[] {});

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/leaderboard/previousyeardatasubmission", method = RequestMethod.POST)
	public void updatePreviousYearDataSubmission(
			HttpServletRequest request, HttpServletResponse response) {
		
	  	restProxy.stream("updatePreviousYearDataSubmission", request, response, new String[] {});

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/subscribeupdates", method = RequestMethod.POST)
	public void subscribeUpdates(
			HttpServletRequest request, HttpServletResponse response) {
		
	  	restProxy.stream("subscribeUpdates", request, response, new String[] {});

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/previousyearsubmission/checkaccess", method = RequestMethod.GET)
	public void checkaccesstodownloadpreviousyeardata(
			@RequestParam(value = "countryCode",defaultValue="") String countryCode,
			@RequestParam(value = "companyOrgcode",defaultValue="") String companyOrgcode,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("checkaccesstodownloadpreviousyeardata", request, response, new String[] {countryCode,companyOrgcode});

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/confirmit", method = RequestMethod.GET)
	public void getConfirmItDCTClientById(
			@RequestParam(value = "outputType",defaultValue="") String outputType,
			@RequestParam(value = "module",defaultValue="") String module,
			@RequestParam(value = "pdcClientId",defaultValue="") String pdcClientId,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("getConfirmItDCTClientById", request, response, new String[] {outputType,module,pdcClientId });

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/confirmit/assign", method = RequestMethod.POST)
	public void addConfirmItDCTAssignment(
			
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("addConfirmItDCTAssignment", request, response, new String[] {});

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/confirmit/access", method = RequestMethod.POST)
	public void addConfirmItDCTAccess(
			
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("addConfirmItDCTAccess", request, response, new String[] {});

	}
}
