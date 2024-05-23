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
public class PayDataCollectionAnalysisController 
{

	private static final Logger logger = LoggerFactory.getLogger(PayDataCollectionAnalysisController.class);
	
	@Autowired
	private LeapUtil leapUtil;
	@Autowired
	private RestProxy restProxyForUPM;
	
	
	@PreAuthorize("hasRole('ROLE_USER') and hasRole('ROLE_INTERNAL_USER')")
	@RequestMapping(value = "/analysis/metadata", method = RequestMethod.GET)
	public void getAnalysisMetadataByCountryCode(
			@RequestParam(value = "countryCode", defaultValue = "") String countryCode,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxyForUPM.stream("getAnalysisMetadataByCountryCode", request, response, 
		new String[] {countryCode});

	}
	
	@PreAuthorize("hasRole('ROLE_USER') and hasRole('ROLE_INTERNAL_USER')")
	@RequestMapping(value = "/analysis", method = RequestMethod.POST)
	public void addAnalysis(
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxyForUPM.stream("addAnalysis", request, response, 
		new String[] {});

	}
		
	
	@PreAuthorize("hasRole('ROLE_USER') and hasRole('ROLE_INTERNAL_USER')")
	@RequestMapping(value = "/analysis", method = RequestMethod.GET)
	public void getAllAnalysis(
			@RequestParam(value = "countryCode", defaultValue = "") String countryCode,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxyForUPM.stream("getAllAnalysis", request, response, 
		new String[] {countryCode});

	}
	
	
	@PreAuthorize("hasRole('ROLE_USER') and hasRole('ROLE_INTERNAL_USER')")
	@RequestMapping(value = "/analysis/{id}", method = RequestMethod.GET)
	public void getAnalysisById(
			@PathVariable String id,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxyForUPM.stream("getAnalysisById", request, response, 
		new String[] {id});

	}
	

	@PreAuthorize("hasRole('ROLE_USER') and hasRole('ROLE_INTERNAL_USER')")
	@RequestMapping(value = "/analysis/{id}", method = RequestMethod.DELETE)
	public void deleteAnalysis(
			@PathVariable String id,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxyForUPM.stream("deleteAnalysis", request, response, 
		new String[] {id});

	}
	
	@PreAuthorize("hasRole('ROLE_USER') and hasRole('ROLE_INTERNAL_USER')")
	@RequestMapping(value = "/analysis/{id}", method = RequestMethod.PUT)
	public void updateAnalysisById(
			@PathVariable String id,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxyForUPM.stream("updateAnalysisById", request, response, 
		new String[] {id});

	}
	
	
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/percentilelist/items", method = RequestMethod.GET)
	public void getPercentileListItems(
			@RequestParam(value = "percentileListId", defaultValue = "") String percentileListId,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxyForUPM.stream("getPercentileListItems", request, response, 
		new String[] {percentileListId});

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/analysis/downloadreports", method = RequestMethod.GET)
	public void getAnalysisReportDownload(
			@RequestParam(value = "queueId", defaultValue = "") String queue_id,
			@RequestParam(value = "reportType", defaultValue = "") String reportType,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxyForUPM.stream("getAnalysisReportDownload", request, response, 
		new String[] {queue_id,reportType});

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/factslist/items", method = RequestMethod.GET)
	public void getFactListItems(
			@RequestParam(value = "countryCode", defaultValue = "") String countryCode,
			@RequestParam(value = "factListId", defaultValue = "") String factListId,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxyForUPM.stream("getFactListItems", request, response, 
		new String[] {countryCode,factListId});

	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/groupingcriterias", method = RequestMethod.GET)
	public void getGroupingCriteriasByAttributeName(
			@RequestParam(value = "attributeName", defaultValue = "") String attributeName,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxyForUPM.stream("getGroupingCriteriasByAttributeName", request, response, 
		new String[] {attributeName});

	}
	
	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER') and hasRole('ROLE_INTERNAL_USER')")
	@RequestMapping(value = "/analysis/batches/items", method = RequestMethod.GET)
	public void getBatchItems(
			@RequestParam(value = "attributeId", defaultValue = "") String attributeId,
			@RequestParam(value = "denormId", defaultValue = "") String denormId,
			@RequestParam(value = "marketDefinitionId", defaultValue = "") String marketDefinitionId,
			HttpServletRequest request, HttpServletResponse response) {

		restProxyForUPM.stream("getBatchItems", request, response, new String[] {attributeId,denormId, marketDefinitionId});

	}
	
	
	@PreAuthorize("hasRole('ROLE_USER') and hasRole('ROLE_INTERNAL_USER')")
	@RequestMapping(value = "/analysis/groupingdata", method = RequestMethod.GET)
	public void getGroupingDataByDataSource(
			@RequestParam(value = "countryCode", defaultValue = "") String countryCode,
			@RequestParam(value = "dataSourceId", defaultValue = "") String dataSourceId,
			HttpServletRequest request, HttpServletResponse response) {

		restProxyForUPM.stream("getGroupingDataByDataSource", request, response, new String[] {countryCode,dataSourceId});

	}

}
