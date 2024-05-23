package com.haygroup.leap.hrms.payhub;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.net.util.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.haygroup.leap.client.RestProxy;
import com.haygroup.leap.client.RestTemplateProxy;
import com.haygroup.leap.common.HGLeapConstants;
import com.haygroup.leap.common.LeapUtil;
import com.haygroup.leap.common.PropertiesUtil;

@Controller
@RequestMapping(value="/hrms/payhub/actions") 
public class PayHubActionsController {
	
	
private static final Logger logger = LoggerFactory.getLogger(PayHubActionsController.class);
	
	@Autowired
	private LeapUtil leapUtil;
	@Autowired
	private RestProxy restProxy;
	@Autowired
	private RestTemplateProxy restTemplate;

	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/addmarket/sendemail", method = RequestMethod.POST)
	public void sendemail(
			HttpServletRequest request, HttpServletResponse response) {

		restProxy.stream("sendaddmarketemail", request, response, new String[] {});

	}	
	
	
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/exportkfreferencegrades", method = RequestMethod.GET)
	public void getKFReferenceGrades(
			 @RequestParam(value = "userId", defaultValue = "") String userId,
			 @RequestParam(value = "clientId", defaultValue = "") String clientId,
			 @RequestParam(value = "locale", defaultValue = "") String locale,
			 HttpServletRequest request,HttpServletResponse response)  {
		
		restProxy.stream("getKFReferenceGrades", request, response, new String[] {userId,clientId,locale});
		
	}
	
	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/useraccesscountry", method = RequestMethod.GET)
	public void useraccesscountry(@RequestParam(value = "module",defaultValue="") String module,
			HttpServletRequest request, HttpServletResponse response) {

		restProxy.stream("getuseraccesscountry", request, response, new String[] {module});

	}	

	/**
	 * @param type
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/companysearch", method = RequestMethod.GET)
	public void searchCompaniesInCountryDB(
			@RequestParam(value = "type", defaultValue = "") String type,
			@RequestParam(value = "countryCode", defaultValue = "") String countryCode,
			@RequestParam(value = "searchColumn", defaultValue = "") String searchColumn,
			@RequestParam(value = "searchString", defaultValue = "") String searchString,
			@RequestParam(value = "sortColumn", defaultValue = "") String sortColumn,
			@RequestParam(value = "sortBy", defaultValue = "") String sortBy,
			@RequestParam(value = "filterBy", defaultValue = "") String filterBy,
			@RequestParam(value = "filterValues", defaultValue = "") String filterValues,							
			@RequestParam(value = "pageIndex", defaultValue = "") String pageIndex,
			@RequestParam(value = "pageSize", defaultValue = "") String pageSize,
		   HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("searchCompaniesInCountryDB", request, response, new String[] {  type,countryCode,searchColumn,searchString,sortColumn,sortBy,filterBy,filterValues, pageIndex, pageSize });

	}
	

	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/config", method = RequestMethod.GET)
	public void getCompaniesMetadata(
			@RequestParam(value = "outputType", defaultValue = "") String outputType,
			@RequestParam(value = "countryCode", defaultValue = "") String countryCode,
			HttpServletRequest request, HttpServletResponse response) {

		restProxy.stream("getCompaniesMetadata", request, response, new String[] {outputType,countryCode});

	}	
	

	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/datadensity", method = RequestMethod.POST)
	public void calcPeerGroupDataDensity(
			
			HttpServletRequest request, HttpServletResponse response) {

		restProxy.stream("calcPeerGroupDataDensity", request, response, new String[] {});

	}	
	
	@PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "/exportDataDensity", method = RequestMethod.GET)
    public void exportDataDensity( 
			@RequestParam(value = "moduleId", defaultValue = "") String moduleId,
			@RequestParam(value = "stgPeerGroupId", defaultValue = "") String stgPeerGroupId,
			 @RequestParam(value = "clientId", defaultValue = "") String clientId,
			 @RequestParam(value = "userId", defaultValue = "") String userId,
			 @RequestParam(value = "locale", defaultValue = "") String locale,
			 HttpServletRequest request, HttpServletResponse response) {
		
		restProxy.stream("exportDataDensity", request, response, new String[] {moduleId,stgPeerGroupId,clientId,userId,locale});
		
	}
	

	
	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/savereportsdetails", method = RequestMethod.POST)
	public void saverequestedreportdetails(
			
			HttpServletRequest request, HttpServletResponse response) {

		restProxy.stream("saverequestedreportdetails", request, response, new String[] {});

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/reports", method = RequestMethod.GET)
	public void getlistofreports(
			
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("getlistofreports", request, response, new String[] { });

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/exportmarketoverview", method = RequestMethod.GET)
	public void exportmarketoverview(
         	@RequestParam(value = "module", defaultValue = "") String module,
		    @RequestParam(value = "countryId", defaultValue = "") String countryId,
			 @RequestParam(value = "marketYear", defaultValue = "") String marketYear,
			 @RequestParam(value = "packageId", defaultValue = "") String packageId,
			 @RequestParam(value = "reporttype", defaultValue = "") String reporttype,
			 @RequestParam(value = "reportname", defaultValue = "") String reportname,
			
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("exportmarketoverview", request, response, new String[] {module ,countryId,marketYear,packageId,reporttype,reportname});

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/exportreport", method = RequestMethod.GET)
	public void exportreport(
         	@RequestParam(value = "exportReportId", defaultValue = "") String exportReportId,
		    @RequestParam(value = "clientId", defaultValue = "") String clientId,
			 @RequestParam(value = "userId", defaultValue = "") String userId,
			 @RequestParam(value = "locale", defaultValue = "") String locale,
			
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("exportreport", request, response, new String[] {userId,clientId,locale,exportReportId });

	}
	
	
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/exportstaticreport", method = RequestMethod.GET)
	public void exportstaticreport(
         	@RequestParam(value = "exportReportName", defaultValue = "") String exportReportName,
         	@RequestParam(value = "fileextension", defaultValue = "") String fileextension,
		    HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("exportstaticreport", request, response, new String[] {exportReportName,fileextension});

	}
	
	
	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/lastviewed", method = RequestMethod.POST)
	public void postViewed(
			HttpServletRequest request, HttpServletResponse response) {

		restProxy.stream("postViewed", request, response, new String[] {});

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/marketinsight", method = RequestMethod.GET)
	public void getMarketInsight(
			@RequestParam(value = "countryId", defaultValue = "") String countryId,
			@RequestParam(value = "applicationName", defaultValue = "PAY") String applicationName,
			@RequestParam(value = "type", defaultValue = "MARKETSIGHT") String type,
			@RequestParam(value = "locale", defaultValue = "en") String locale,
			HttpServletRequest request, HttpServletResponse response) 
	{
		String userId = leapUtil.getUserId(request.getHeader(HGLeapConstants.AUTH_TOKEN));
		restProxy.stream("getMarketInsight", request, response, new String[] { userId, countryId ,applicationName,type,locale});
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/marketinsight", method = RequestMethod.POST)
	public void postMarketInsight(
			HttpServletRequest request, HttpServletResponse response) {

		restProxy.stream("postMarketInsight", request, response, new String[] {});
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/transferdatasourcefile", method = RequestMethod.POST)
	public void transferDataSourceFile(
			HttpServletRequest request, HttpServletResponse response) {
		
		restProxy.stream("transferDataSourceFile", request, response, new String[] {});
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/kfregion", method = RequestMethod.GET)
	public void getRegionBasedOnCountry(
			@RequestParam(value = "countryCode", defaultValue = "") String countryCode,
			@RequestParam(value = "preferredClientId", defaultValue = "") String preferredClientId,
			@RequestParam(value = "LCID", defaultValue = "") String LCID,
			
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("getRegionBasedOnCountry", request, response, new String[] {countryCode, preferredClientId,LCID});

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/kfcompanypolicy", method = RequestMethod.GET)
	public void getCompanyPolicy(
			@RequestParam(value = "countryCode", defaultValue = "") String countryCode,
			@RequestParam(value = "preferredClientId", defaultValue = "") String preferredClientId,
			@RequestParam(value = "successProfileId", defaultValue = "") String successProfileId,
			@RequestParam(value = "regionId", defaultValue = "") String regionId,
			@RequestParam(value = "LCID", defaultValue = "") String LCID,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("getCompanyPolicy", request, response, new String[] {countryCode, preferredClientId,successProfileId,regionId,LCID});

	}
	

	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/currency", method = RequestMethod.GET)
	public void getCurrency(
			@RequestParam(value = "module", defaultValue = "") String module,
			@RequestParam(value = "countryCode", defaultValue = "") String countryCode,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("getCurrency", request, response, new String[] { module,countryCode});


    }
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/pricedresults/region", method = RequestMethod.GET)
	public void getPricedResultsForRegion(
			@RequestParam(value = "jobRoleTypeId", defaultValue = "") String jobRoleTypeId,
			@RequestParam(value = "standardHayGrade", defaultValue = "") String standardHayGrade,
			@RequestParam(value = "countryId", defaultValue = "") String countryId,
			HttpServletRequest request, HttpServletResponse response) 
	{
		restProxy.stream("getPricedResultsForRegion", request, response, new String[] { jobRoleTypeId, standardHayGrade, countryId });
	}	
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/pricingcriteria", method = RequestMethod.GET)
	public void getPricingcriteria(
			@RequestParam(value = "jobRoleTypeId", defaultValue = "") String jobRoleTypeId,
			@RequestParam(value = "standardHayGrade", defaultValue = "") String standardHayGrade,
			@RequestParam(value = "countryId", defaultValue = "") String countryId,
			@RequestParam(value = "jobFamilyId", defaultValue = "") String jobFamilyId,
			@RequestParam(value = "jobSubFamilyId", defaultValue = "") String jobSubFamilyId,
			HttpServletRequest request, HttpServletResponse response) 
	{
		restProxy.stream("getPricingcriteria", request, response, new String[] { jobRoleTypeId, standardHayGrade, countryId, jobFamilyId, jobSubFamilyId});
	}
	
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/landscapereports", method = RequestMethod.GET)
	public void getlistoflandscapereports(
			
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("getlistoflandscapereports", request, response, new String[] { });

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/exportlandscapereport", method = RequestMethod.GET)
	public void exportlandscapereport(
         	@RequestParam(value = "exportReportId", defaultValue = "") String exportReportId,
		    HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("exportlandscapereport", request, response, new String[] {exportReportId });

	}
	
	
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/userdetails", method = RequestMethod.POST)
	public void postUserReportDetail(
			@RequestParam(value = "reportType", defaultValue = "") String reportType,
			HttpServletRequest request, HttpServletResponse response) {
         
		restProxy.stream("postUserReportDetail", request, response, new String[] {reportType});
	}
	

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/bulkexportmarketoverview", method = RequestMethod.POST)
	public void bulkExportMarketOverview(
			@RequestParam(value = "module", defaultValue = "") String module,
			HttpServletRequest request, HttpServletResponse response) {
         
		restProxy.stream("bulkExportMarketOverview", request, response, new String[] {module});
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/payaccess", method = RequestMethod.GET)
	public void getPayEntitlementAccess(
			@RequestParam(value = "userName", defaultValue = "") String userName,
			@RequestParam(value = "preferredClientId", defaultValue = "") String preferredClientId,
			HttpServletRequest request, HttpServletResponse response) {
         
		restProxy.stream("getPayEntitlementAccess", request, response, new String[] {userName,preferredClientId});
	}
	
	
	


}
