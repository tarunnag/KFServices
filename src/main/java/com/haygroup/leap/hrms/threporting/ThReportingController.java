package com.haygroup.leap.hrms.threporting;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.haygroup.leap.client.RestProxy;
import com.haygroup.leap.client.RestTemplateProxy;
import com.haygroup.leap.common.LeapUtil;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;



@Controller
@RequestMapping(value="/hrms/threporting/reports") 
public class ThReportingController {
	private static final Logger logger = LoggerFactory.getLogger(ThReportingController.class);

	@Autowired
	private LeapUtil leapUtil;
	@Autowired
	private RestProxy restProxy;
	@Autowired
	private RestTemplateProxy restTemplate;
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/reportcategory", method = RequestMethod.GET)
	public void getThReportingReportCategory(
		    HttpServletRequest request,
			HttpServletResponse response) {

				restProxy.stream("getThReportingReportCategory", request, response, new String[] { });

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/reportbycategory", method = RequestMethod.GET)
	public void getThReportingReportbyCategory(
			@RequestParam(value = "categoryId", defaultValue = "") String categoryId,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("getThReportingReportbyCategory", request, response, new String[] {categoryId});

	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/reportparameters", method = RequestMethod.GET)
	public void getThReportingReportParameters(
			@RequestParam(value = "reportId") String reportId,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("getThReportingReportParameters", request, response, new String[] {reportId});

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/reportparametervalue", method = RequestMethod.GET)
	public void getThReportingReportParameterValues(
			@RequestParam(value = "reportId") String reportId,
			@RequestParam(value = "parameterName") String parameterName,
			@RequestParam(value = "inputValues", defaultValue = "") String inputValues,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("getThReportingReportParameterValues", request, response, new String[] {reportId,parameterName,inputValues });

	}
	
	/**
	 * @param request
	 * @param response
	 */
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/downloadReports", method = RequestMethod.POST)
	public void postThReportingdownloadReports(
			@RequestParam(value = "reportId", defaultValue = "") String reportId,
			
			HttpServletRequest request, HttpServletResponse response) {

		restProxy.stream("postThReportingdownloadReports", request, response, new String[] {reportId});

	}
}
