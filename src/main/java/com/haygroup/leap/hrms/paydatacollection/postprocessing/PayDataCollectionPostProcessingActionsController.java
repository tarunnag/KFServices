package com.haygroup.leap.hrms.paydatacollection.postprocessing;

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
@RequestMapping(value="/hrms/paydatacollection/postprocessing/actions") 
public class PayDataCollectionPostProcessingActionsController 
{

	private static final Logger logger = LoggerFactory.getLogger(PayDataCollectionPostProcessingActionsController.class);
	
	@Autowired
	private LeapUtil leapUtil;
	@Autowired
	private RestProxy restProxyForUPM;
	@Autowired
	private RestProxy restProxyForNoILBIS;
	

	
	@PreAuthorize("hasRole('ROLE_USER') and hasRole('ROLE_INTERNAL_USER')")
	@RequestMapping(value = "/queue", method = RequestMethod.GET)
	public void getQueue(
			@RequestParam(value = "queueType", defaultValue = "") String queueType,
			@RequestParam(value = "searchString", defaultValue = "") String searchString,
			@RequestParam(value = "searchColumn", defaultValue = "") String searchColumn	,
			@RequestParam(value = "sortColumn", defaultValue = "") String sortColumn,
			@RequestParam(value = "sortBy", defaultValue = "") String sortBy,
			@RequestParam(value = "filterBy", defaultValue = "") String filterBy,
			@RequestParam(value = "filterValues", defaultValue = "") String filterValues,
			@RequestParam(value = "pageIndex", defaultValue = "") String pageIndex,	
			@RequestParam(value = "pageSize", defaultValue = "") String pageSize,	
			HttpServletRequest request,	HttpServletResponse response)
	{	
		
		restProxyForUPM.stream("getQueue", request, response, 
		new String[] {queueType, searchString, searchColumn, sortColumn, sortBy, filterBy, filterValues, pageIndex, pageSize});

	}

	@PreAuthorize("hasRole('ROLE_USER') and hasRole('ROLE_INTERNAL_USER')")
	@RequestMapping(value = "/queue", method = RequestMethod.POST)
	public void addQueue(
			
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxyForUPM.stream("addQueue", request, response, 
		new String[] {});

	}
	
	
	@Deprecated	
	@PreAuthorize("hasRole('ROLE_USER') and hasRole('ROLE_INTERNAL_USER')")
	@RequestMapping(value = "/analysisqueue", method = RequestMethod.GET)
	public void getAnalysisQueue(
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxyForUPM.stream("getAnalysisQueue", request, response, 
		new String[] {});

	}
		
	@Deprecated
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/denormdatabasequeue", method = RequestMethod.GET)
	public void getDeNormDatabaseQueue(
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxyForUPM.stream("getDeNormDatabaseQueue", request, response, 
		new String[] {});

	}
	

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/analysis/downloadreports", method = RequestMethod.GET)
	public void getAnalysisReportDownload(
			@RequestParam(value = "queue_id") String queue_id,
			@RequestParam(value = "reportType") String reportType,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxyForUPM.stream("getAnalysisReportDownload", request, response, 
		new String[] {queue_id,reportType});

	}
	
	//TODO:Change the IS layer to have the same type
	@PreAuthorize("hasRole('ROLE_USER') and hasRole('ROLE_INTERNAL_USER')")
	@RequestMapping(value = "/analysisattributes", method = RequestMethod.GET)
	public void getAttributesByCountryCode(
			@RequestParam(value = "countryCode",defaultValue="") String countryCode,
			@RequestParam(value = "attributeType",defaultValue="") String attributeType,
			@RequestParam(value = "moduleType",defaultValue="") String moduleType,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxyForUPM.stream("getAttributesByCountryCode", request, response, 
		new String[] {countryCode,attributeType, moduleType});

	}
	
	
	@PreAuthorize("hasRole('ROLE_USER') and hasRole('ROLE_INTERNAL_USER')")
	@RequestMapping(value = "/countries", method = RequestMethod.GET)
	public void getPostProcessingCountries(
			@RequestParam(value = "moduleType",defaultValue="") String moduleType,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxyForUPM.stream("getPostProcessingCountries", request, response, new String[] {moduleType});

	}
	
	@PreAuthorize("hasRole('ROLE_USER') and hasRole('ROLE_INTERNAL_USER')")
	@RequestMapping(value = "/metadata", method = RequestMethod.GET)
	public void getPostProcessingMetadata(
			@RequestParam(value = "moduleType",defaultValue="") String moduleType,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxyForUPM.stream("getPostProcessingMetadata", request, response, new String[] {moduleType});

	}
	
	
	@PreAuthorize("hasRole('ROLE_USER') and hasRole('ROLE_INTERNAL_USER')")
	@RequestMapping(value = "/config", method = RequestMethod.GET)
	public void getPostProcessingConfigData(
			@RequestParam(value = "moduleType",defaultValue="") String moduleType,
			@RequestParam(value = "applicationName",defaultValue="") String applicationName,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxyForUPM.stream("getPostProcessingConfigData", request, response, new String[] {moduleType, applicationName});

	}
	
	
	@PreAuthorize("hasRole('ROLE_USER') and hasRole('ROLE_INTERNAL_USER')")
	@RequestMapping(value = "/reference", method = RequestMethod.GET)
	public void getPostProcessingReferenceData(
			@RequestParam(value = "applicationName",defaultValue="") String applicationName,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxyForUPM.stream("getPostProcessingReferenceData", request, response, new String[] {applicationName});

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/dcttemplate", method = RequestMethod.GET)
	public void getDCTTemplate(
			@RequestParam(value = "countryCode",defaultValue="") String countryCode,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxyForUPM.stream("getDCTTemplate", request, response, new String[] {countryCode});

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/previousyearsubmission/deferredreport", method = RequestMethod.POST)
	public void addPreviousYearSubmissionForDeferredReport(
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxyForUPM.stream("addPreviousYearSubmissionForDeferredReport", request, response, new String[] {});

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/previousyearsubmission/exportreport", method = RequestMethod.POST)
	public void downloadPreviousYearSubmissionReport(
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxyForNoILBIS.stream("downloadPreviousYearSubmissionReport", request, response, new String[] {});

	}
	/*
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/previousyearsubmission/checkaccess", method = RequestMethod.GET)
	public void checkaccesstodownloadpreviousyeardata(
			@RequestParam(value = "countryCode",defaultValue="") String countryCode,
			@RequestParam(value = "companyOrgcode",defaultValue="") String companyOrgcode,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxyForUPM.stream("checkaccesstodownloadpreviousyeardata", request, response, new String[] {countryCode,companyOrgcode});

	}
	*/
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/reset", method = RequestMethod.POST)
	public void addPaySubmissionReset(
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxyForUPM.stream("addPaySubmissionReset", request, response, new String[] {});

	}
	
}
