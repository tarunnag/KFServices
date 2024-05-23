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
@RequestMapping(value="/hrms/paydatacollection/postprocessing") 
public class PayDataCollectionPostProcessingController 
{

	private static final Logger logger = LoggerFactory.getLogger(PayDataCollectionPostProcessingController.class);
	
	@Autowired
	private LeapUtil leapUtil;
	@Autowired
	private RestProxy restProxyForUPM;
	
	
	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_INTERNAL_USER')")
	@RequestMapping(value = "/tier3/uploads", method = RequestMethod.POST)
	public void uploadTier3File(
			HttpServletRequest request, HttpServletResponse response) {

		restProxyForUPM.stream("uploadTier3File", request, response, new String[] {});

	}
	
	
	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_INTERNAL_USER')")
	@RequestMapping(value = "/tier3/uploads", method = RequestMethod.PUT)
	public void updateTier3File(
			HttpServletRequest request, HttpServletResponse response) {

		restProxyForUPM.stream("updateTier3File", request, response, new String[] {});

	}
	
	
	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_INTERNAL_USER')")
	@RequestMapping(value = "/tier3/uploads", method = RequestMethod.GET)
	public void getTier3Uploads(
			HttpServletRequest request, HttpServletResponse response) {

		restProxyForUPM.stream("getTier3Uploads", request, response, new String[] {});

	}
	
	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/calcengine/publish", method = RequestMethod.POST)
	public void publishToCalcEngine(
			HttpServletRequest request, HttpServletResponse response) {

		restProxyForUPM.stream("publishToCalcEngine", request, response, new String[] {});

	}
	
	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/clients", method = RequestMethod.PUT)
	public void updateTier3Client(
			HttpServletRequest request, HttpServletResponse response) {

		restProxyForUPM.stream("updateTier3Client", request, response, new String[] {});

	}
	
	
	@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_INTERNAL_USER')")
	@RequestMapping(value = "/datasources", method = RequestMethod.GET)
	public void getDataSources(
			@RequestParam(value = "type", defaultValue = "") String type,	
			@RequestParam(value = "sortColumn", defaultValue = "") String sortColumn,	
			@RequestParam(value = "sortBy", defaultValue = "") String sortBy,	
			@RequestParam(value = "searchString", defaultValue = "") String searchString,	
			@RequestParam(value = "searchColumn", defaultValue = "") String searchColumn,	
			@RequestParam(value = "filterBy", defaultValue = "") String filterBy,	
			@RequestParam(value = "filterValues", defaultValue = "") String filterValues,	
			@RequestParam(value = "pageIndex", defaultValue = "") String pageIndex,	
			@RequestParam(value = "pageSize", defaultValue = "") String pageSize,
			@RequestParam(value = "countryId", defaultValue = "") String countryId,
			@RequestParam(value = "dataSourceType", defaultValue = "") String dataSourceType,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxyForUPM.stream("getDataSources", request, response, 
		new String[] {type, sortColumn, sortBy, searchString, searchColumn, filterBy, filterValues, pageIndex, pageSize, countryId, dataSourceType});

	}
	
	@Deprecated
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/runquery", method = RequestMethod.POST)
	public void runQuery(
			@RequestParam(value = "version", defaultValue = "") String version,
			@RequestParam(value = "type", defaultValue = "") String type,
			@RequestParam(value = "queryId", defaultValue = "") String queryId,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxyForUPM.stream("runQuery", request, response, 
		new String[] {version, type, queryId});

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/incumbentuploads/batches", method = RequestMethod.GET)
	public void getIncumbentUploadBatches(
			@RequestParam(value = "countryCode", defaultValue = "") String countryCode,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxyForUPM.stream("getIncumbentUploadBatches", request, response, 
		new String[] {countryCode});

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/incumbentuploads/batches/{batchId}", method = RequestMethod.GET)
	public void getFilesFromBatchId(
			@PathVariable String batchId,
			@RequestParam(value = "countryCode", defaultValue = "") String countryCode,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxyForUPM.stream("getFilesFromBatchId", request, response, 
		new String[] {batchId,countryCode});

	}

	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/deferredreports", method = RequestMethod.POST)
	public void addDeferredReport(
			@RequestParam(value = "reportId", defaultValue = "") String reportId,
			@RequestParam(value = "reportType", defaultValue = "") String reportType,
			
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxyForUPM.stream("addDeferredReport", request, response, 
		new String[] {reportId,reportType});

	}
	
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/datasubmissions", method = RequestMethod.GET)
	public void getDataSubmissions(
			@RequestParam(value = "clientOrgCode", defaultValue = "") String clientOrgCode,
			@RequestParam(value = "countryCode", defaultValue = "") String countryCode,
			@RequestParam(value = "year", defaultValue = "") String year,
			@RequestParam(value = "thresholdReferenceLevel", defaultValue = "") String thresholdReferenceLevel,
			HttpServletRequest request,	HttpServletResponse response)
	{	
		restProxyForUPM.stream("getDataSubmissions", request, response, 
		new String[] {clientOrgCode,countryCode,year,thresholdReferenceLevel});

	}
	
	
	@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_INTERNAL_USER')")
	@RequestMapping(value = "/template", method = RequestMethod.POST)
	public void postDctTemplates(
			HttpServletRequest request,	HttpServletResponse response)
	{	
		restProxyForUPM.stream("postDctTemplates", request, response, new String[] {});

	}


}
