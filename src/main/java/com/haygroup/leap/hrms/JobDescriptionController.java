package com.haygroup.leap.hrms;


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
import org.springframework.web.bind.annotation.PathVariable;

import com.haygroup.leap.client.RestProxy;
import com.haygroup.leap.common.HGLeapConstants;
import com.haygroup.leap.common.LeapUtil;




@Controller
@RequestMapping(value="/hrms/jobdescriptions")  
public class JobDescriptionController {
	
private static final Logger logger = LoggerFactory.getLogger(JobDescriptionController.class);
	
	@Autowired
	private LeapUtil leapUtil;
	@Autowired
	private RestProxy restProxy;
	@Autowired
	private RestProxy talentArchitectRestProxy;
	
	/**
	 * @param type
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public void searchJobDescriptions(
			@RequestParam(value = "outputType", defaultValue = "") String outputType,
			@RequestParam(value = "type", defaultValue = "") String type,
			@RequestParam(value = "searchColumn", defaultValue = "") String searchColumn,
			@RequestParam(value = "searchString", defaultValue = "") String searchString,
			@RequestParam(value = "sortColumn", defaultValue = "") String sortColumn,
			@RequestParam(value = "sortBy", defaultValue = "") String sortBy,
			@RequestParam(value = "filterBy", defaultValue = "") String filterBy,
			@RequestParam(value = "filterValues", defaultValue = "") String filterValues,							
			@RequestParam(value = "pageIndex", defaultValue = "") String pageIndex,
			@RequestParam(value = "pageSize", defaultValue = "") String pageSize,
			@RequestParam(value = "isMock", defaultValue = "") String isMock,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("searchJobDescriptions", request, response, new String[] { outputType, type,searchColumn,searchString,sortColumn,sortBy,filterBy,filterValues,
				 pageIndex, pageSize, isMock });
	}
		
	
	/**
	 * @param type
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/{jobDescriptionId}", method = RequestMethod.GET)
	public void getJobDescription(
			@PathVariable String jobDescriptionId,
			@RequestParam(value = "preferredLocale", defaultValue = "") String preferredLocale,
			@RequestParam(value = "isMock", defaultValue = "") String isMock,
			@RequestParam(value = "clientId", defaultValue = "") String clientId,
			@RequestParam(value = "reportType", defaultValue = "") String reportType,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("getJobDescription", request, response, new String[] { jobDescriptionId, preferredLocale, isMock, clientId, reportType});

	}
	
	/**
	 * @param jobDescriptionId
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/{jobDescriptionId}", method = RequestMethod.DELETE)
	public void deleteJobDescription(
			@PathVariable String jobDescriptionId,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("deleteJobDescription", request, response, new String[] { jobDescriptionId});

	}
	
	
	/**
	 * @param type
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "", method = RequestMethod.POST)
	public void postJobDescription(
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("postJobDescription", request, response, new String[] {  });

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/{jobDescriptionId}", method = RequestMethod.PUT)
	public void putJobDescription(@PathVariable String jobDescriptionId,
			
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("putJobDescription", request, response, new String[] { jobDescriptionId });

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/interviewguide", method = RequestMethod.GET)
	public void getJDInterviewGuide(
			@RequestParam(value = "jobId", defaultValue = "") String jobId,
			@RequestParam(value = "reportType", defaultValue = "") String reportType,
			@RequestParam(value = "reportLocale", defaultValue = "") String reportLocale,
			@RequestParam(value = "reportClientId", defaultValue = "") String reportClientId,
			HttpServletRequest request,	HttpServletResponse response)
	{

		talentArchitectRestProxy.stream("getJDInterviewGuide", request, response, new String[] { jobId,reportType,reportLocale,reportClientId });

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/companydetails", method = RequestMethod.GET)
	public void getJDAboutTheCompanyText(
			@RequestParam(value = "outputType", defaultValue = "") String outputType,
			HttpServletRequest request,	HttpServletResponse response)
	{

		restProxy.stream("getJDAboutTheCompanyText", request, response, new String[] { outputType });

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/companydetails", method = RequestMethod.PUT)
	public void saveJDAboutTheCompanyText(
			HttpServletRequest request,	HttpServletResponse response)
	{

		restProxy.stream("saveJDAboutTheCompanyText", request, response, new String[] { });

	}
		
}
