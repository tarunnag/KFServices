package com.haygroup.leap.hrms.calcenginereporting;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.haygroup.leap.client.RestProxy;

@Controller
@RequestMapping(value="/hrms/cereporting/reports")
public class CalcEngineReportingController {

	@Autowired
	private RestProxy restProxy;

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value="/reportcategory", method=RequestMethod.GET)
	public void getCEReportingReportCategory(
			HttpServletRequest request, HttpServletResponse response) {
		restProxy.stream("getCEReportingReportCategory", request, response, new String[] {});
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/reportbycategory", method = RequestMethod.GET)
	public void getCEReportingReportbyCategory(
			@RequestParam(value = "categoryId", defaultValue = "") String categoryId,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("getCEReportingReportbyCategory", request, response, new String[] {categoryId});

	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/reportparameters", method = RequestMethod.GET)
	public void getCEReportingReportParameters(
			@RequestParam(value = "reportId") String reportId,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("getCEReportingReportParameters", request, response, new String[] {reportId});

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/reportparametervalue", method = RequestMethod.GET)
	public void getCEReportingReportParameterValues(
			@RequestParam(value = "reportId") String reportId,
			@RequestParam(value = "parameterName") String parameterName,
			@RequestParam(value = "inputValues", defaultValue = "") String inputValues,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("getCEReportingReportParameterValues", request, response, new String[] {reportId,parameterName,inputValues });

	}
	
	/**
	 * @param request
	 * @param response
	 */
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/downloadReports", method = RequestMethod.POST)
	public void postCEReportingdownloadReports(
			@RequestParam(value = "reportId", defaultValue = "") String reportId,
			
			HttpServletRequest request, HttpServletResponse response) {

		restProxy.stream("postCEReportingdownloadReports", request, response, new String[] {reportId});

	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/deferredreport", method = RequestMethod.GET)
	public void getCEReportingDeferredReports(
			@RequestParam(value = "searchString", defaultValue = "") String searchString,
			@RequestParam(value = "searchColumn", defaultValue = "") String searchColumn,
			@RequestParam(value = "sortBy", defaultValue = "") String sortBy,
			@RequestParam(value = "sortColumn", defaultValue = "") String sortColumn,
			@RequestParam(value = "filterBy", defaultValue = "") String filterBy,
			@RequestParam(value = "filterValues", defaultValue = "") String filterValues,
			@RequestParam(value = "pageIndex", defaultValue = "") String pageIndex,
			@RequestParam(value = "pageSize", defaultValue = "") String pageSize,

			
			HttpServletRequest request, HttpServletResponse response) 
	{
		restProxy.stream("getCEReportingDeferredReports", request, response, new String[] {searchString,searchColumn,sortBy,sortColumn,filterBy,filterValues,pageIndex,pageSize});	
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value="/deferredreport", method=RequestMethod.POST)
	public void postCEReportingDeferredReport(
			HttpServletRequest request, HttpServletResponse response)
	
	{
		restProxy.stream("postCEReportingDeferredReport", request, response, new String[] {});
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value="/config", method=RequestMethod.GET)
	public void getCEReportingConfig(
			HttpServletRequest request, HttpServletResponse response)
	
	{
		restProxy.stream("getCEReportingConfig", request, response, new String[] {});
	}
}
