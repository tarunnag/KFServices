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
import com.haygroup.leap.client.RestTemplateProxy;
import com.haygroup.leap.common.LeapUtil;

@Controller
@RequestMapping(value="/hrms/payhub/reports") 
public class PayHubReportsController {
	private static final Logger logger = LoggerFactory.getLogger(PayHubReportsController.class);

	@Autowired
	private LeapUtil leapUtil;
	@Autowired
	private RestProxy restProxy;
	@Autowired
	private RestTemplateProxy restTemplate;
	
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/filters", method = RequestMethod.POST)
	public void postReportFilters(
		   	@RequestParam(value = "reportType", defaultValue = "") String reportType,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("postReportFilters", request, response, new String[] { reportType});

	}
	

		@PreAuthorize("hasRole('ROLE_USER')")
		@RequestMapping(value = "/config", method = RequestMethod.POST)
		public void postReportConfig(
			   	@RequestParam(value = "reportType", defaultValue = "") String reportType,
				HttpServletRequest request,	HttpServletResponse response)
		{
			
			restProxy.stream("postReportConfig", request, response, new String[] { reportType});

		}
		
		@PreAuthorize("hasRole('ROLE_USER')")
		@RequestMapping(value = "/reportdetails", method = RequestMethod.POST)
		public void postReportDetails(
			   	@RequestParam(value = "reportType", defaultValue = "") String reportType,
				HttpServletRequest request,	HttpServletResponse response)
		{
			
			restProxy.stream("postReportDetails", request, response, new String[] { reportType});

		}
		
	
	
	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/payperformance", method = RequestMethod.POST)
	public void postpayperformance(
			
			HttpServletRequest request, HttpServletResponse response) {

		restProxy.stream("postpayperformance", request, response, new String[] {});

	}
	
	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/headcount", method = RequestMethod.POST)
	public void postheadcount(
			
			HttpServletRequest request, HttpServletResponse response) {

		restProxy.stream("postheadcount", request, response, new String[] {});

	}


/**
 * @param request
 * @param response
 */
@PreAuthorize("hasRole('ROLE_USER')")
@RequestMapping(value = "/genderanalysis", method = RequestMethod.POST)
public void postgenderanalysis(
		
		HttpServletRequest request, HttpServletResponse response) {

	restProxy.stream("postgenderanalysis", request, response, new String[] {});

}


/**
 * @param request
 * @param response
 */
@PreAuthorize("hasRole('ROLE_USER')")
@RequestMapping(value = "/payperformanceovertime", method = RequestMethod.POST)
public void postpayperformanceovertime(
		
		HttpServletRequest request, HttpServletResponse response) {

	restProxy.stream("postpayperformanceovertime", request, response, new String[] {});

}

}


