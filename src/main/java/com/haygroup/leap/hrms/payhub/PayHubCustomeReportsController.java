package com.haygroup.leap.hrms.payhub;

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
@RequestMapping(value="/hrms/payhub/cusreports") 
public class PayHubCustomeReportsController {
	
private static final Logger logger = LoggerFactory.getLogger(PayHubCustomeReportsController.class);
	
	@Autowired
	private LeapUtil leapUtil;
	@Autowired
	private RestProxy restProxy;

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/config", method = RequestMethod.GET)
	public void getCustomReportsConfig(
		HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("getCustomReportsConfig", request, response, new String[] { });

	}
	
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/year", method = RequestMethod.GET)
	public void getCustomReportsByYear(
		HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("getCustomReportsByYear", request, response, new String[] { });

	}
	
	/**
	 * @param type
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public void searchCustomReports(
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
		
		restProxy.stream("searchCustomReports", request, response, new String[] {  searchColumn,searchString,sortColumn,sortBy,filterBy,filterValues, pageIndex, pageSize });

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public void getCustomReports(
			@PathVariable String id,
		HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("getCustomReports", request, response, new String[] { id});

	}
	
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/", method = RequestMethod.DELETE)
	public void deleteCustomReports(
			@RequestParam(value = "reportId", defaultValue = "") String reportId,
		HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("deleteCustomReports", request, response, new String[] { reportId});

	}
		
	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public void addCustomReports(
			@RequestParam(value = "reportTypeId", defaultValue = "") String reportTypeId,
			@RequestParam(value = "fileExtensionType", defaultValue = "") String fileExtensionType,
			@RequestParam(value = "clientIds", defaultValue = "") String clientIds,
			@RequestParam(value = "countryId", defaultValue = "") String countryId,
			@RequestParam(value = "displayName", defaultValue = "") String displayName,
			@RequestParam(value = "reportLocale", defaultValue = "") String reportLocale,
			@RequestParam(value = "reportYearId", defaultValue = "") String reportYearId,
			HttpServletRequest request, HttpServletResponse response) {

		restProxy.stream("addCustomReports", request, response, new String[] {reportTypeId,fileExtensionType,clientIds,countryId,displayName,reportLocale,reportYearId});

	}	


	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public void updateCustomReports(
			@RequestParam(value = "reportId", defaultValue = "") String reportId,
			HttpServletRequest request, HttpServletResponse response) {

		restProxy.stream("updateCustomReports", request, response, new String[] {reportId});

	}	
	
}
