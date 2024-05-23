package com.haygroup.leap.hrms.payhub;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.haygroup.leap.client.RestProxy;
import com.haygroup.leap.common.LeapUtil;

@Controller
@RequestMapping(value="/hrms/payhub/dat") 
public class PayHubDatAggController {
	
private static final Logger logger = LoggerFactory.getLogger(PayHubDatAggController.class);
	
	@Autowired
	private LeapUtil leapUtil;
	@Autowired
	private RestProxy restProxy;
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/config/compelements", method = RequestMethod.GET)
	public void getDatConfigComp(
			@RequestParam(value = "countryCode") String countryCode,
			@RequestParam(value = "dataSourceId", defaultValue = "All") String dataSourceId,
		HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("getDatConfigComp", request, response, new String[] {countryCode, dataSourceId });

	}
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/config/grades", method = RequestMethod.GET)
	public void getDatConfigGrades(
			@RequestParam(value = "countryCode") String countryCode,
			@RequestParam(value = "dataSourceId", defaultValue = "All") String dataSourceId,
			@RequestParam(value = "gradeSetId", defaultValue = "1") String gradeSetId,
		HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("getDatConfigGrades", request, response, new String[] {countryCode, dataSourceId,gradeSetId });

	}
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/config/jobfamilies", method = RequestMethod.GET)
	public void getDatConfigJobFamilies(
			@RequestParam(value = "countryCode") String countryCode,
			@RequestParam(value = "dataSourceId", defaultValue = "All") String dataSourceId,
		HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("getDatConfigJobFamilies", request, response, new String[] {countryCode, dataSourceId });

	}
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/config/marketpercentiles", method = RequestMethod.GET)
	public void getDatConfigMarketPercentile(
			@RequestParam(value = "countryCode") String countryCode,
			@RequestParam(value = "dataSourceId", defaultValue = "All") String dataSourceId,
		HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("getDatConfigMarketPercentile", request, response, new String[] {countryCode, dataSourceId });

	}
	/**
	 * @param type
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/searchorgdata", method = RequestMethod.GET)
	public void searchfordat(
			@RequestParam(value = "dataSourceId", defaultValue = "") String dataSourceId,
			@RequestParam(value = "countryCode", defaultValue = "") String countryCode,
			@RequestParam(value = "searchString", defaultValue = "") String searchString,
			@RequestParam(value = "sortColumn", defaultValue = "") String sortColumn,
			@RequestParam(value = "sortBy", defaultValue = "") String sortBy,
			@RequestParam(value = "filterBy", defaultValue = "") String filterBy,
			@RequestParam(value = "filterValues", defaultValue = "") String filterValues,							
			@RequestParam(value = "pageIndex", defaultValue = "") String pageIndex,
			@RequestParam(value = "pageSize", defaultValue = "") String pageSize,
		   HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("searchfordat", request, response, new String[] {dataSourceId, countryCode,searchString,sortColumn,sortBy,filterBy,filterValues, pageIndex, pageSize });

	}
	
	/**
	 * @param type
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/marketdata", method = RequestMethod.GET)
	public void getdatmarketdata(
			@RequestParam(value = "dataSourceId", defaultValue = "All") String dataSourceId,
			@RequestParam(value = "countryCode") String countryCode,
			@RequestParam(value = "compElement") String compElement,
			@RequestParam(value = "incumbentKey") String incumbentKey,
			@RequestParam(value = "percentile") String percentile ,
		    HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("getdatmarketdata", request, response, new String[] {dataSourceId, countryCode,compElement,incumbentKey,percentile });

	}
	
	
	/**
	 * @param dataSourceId
	 * @param gradeSetId
	 * @param compElement
	 * @param percentile
	 * @param jobFamilyId
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/custommarketsummary", method = RequestMethod.GET)
	public void getCustomMarketSummary(
			@RequestParam(value = "dataSourceId", defaultValue = "All") String dataSourceId,
			@RequestParam(value = "gradeSetId") String gradeSetId,
			@RequestParam(value = "compElement") String compElement,
			@RequestParam(value = "percentile") String percentile,
			@RequestParam(value = "jobFamilyId") String jobFamilyId ,
		    HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("getCustomMarketSummary", request, response, new String[] {dataSourceId, gradeSetId,compElement,percentile,jobFamilyId });

	}
	
	
	/**
	 * @param dataSourceId
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/custommarketsummary", method = RequestMethod.POST)
	public void postCustomMarketSummary(
			@RequestParam(value = "dataSourceId", defaultValue = "All") String dataSourceId,
		    HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("postCustomMarketSummary", request, response, new String[] {dataSourceId });

	}
	

}
