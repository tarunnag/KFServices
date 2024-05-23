package com.haygroup.leap.hrms.payequity;

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
/**
 * @author Harsha
 *
 */ 
@Controller
@RequestMapping(value="/hrms/payequity/reports") 
public class PayEquityActionController {
private static final Logger logger = LoggerFactory.getLogger(PayEquityActionController.class);
	
	@Autowired
	private RestProxy restProxy;

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/config", method = RequestMethod.POST)
	public void addReportConfig(
			 @RequestParam(value = "reportType", defaultValue = "") String reportType,
			 HttpServletRequest request,HttpServletResponse response)  {
		
		restProxy.stream("addReportConfig", request, response, new String[] {reportType});
		
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/filters", method = RequestMethod.POST)
	public void addReportFilters(
			 @RequestParam(value = "reportType", defaultValue = "") String reportType,
			 HttpServletRequest request,HttpServletResponse response)  {
		
		restProxy.stream("addReportFilters", request, response, new String[] {reportType});
		
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/reports", method = RequestMethod.POST)
	public void addReportDetails(
			 @RequestParam(value = "reportType", defaultValue = "") String reportType,
			 HttpServletRequest request,HttpServletResponse response)  {
		
		restProxy.stream("addReportDetails", request, response, new String[] {reportType});
		
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/usersettings", method = RequestMethod.GET)
	public void getPEReportVariables(
			 @RequestParam(value = "reportType", defaultValue = "") String reportType,
			 @RequestParam(value = "countryId", defaultValue = "") String countryId,
			 HttpServletRequest request,HttpServletResponse response)  {
		
		restProxy.stream("getPEReportVariables", request, response, new String[] {reportType, countryId});
		
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/usersettings", method = RequestMethod.POST)
	public void addPEReportVariables(
			 @RequestParam(value = "reportType", defaultValue = "") String reportType,
			 HttpServletRequest request,HttpServletResponse response)  {
		
		restProxy.stream("addPEReportVariables", request, response, new String[] {reportType});
		
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/usersettings", method = RequestMethod.PUT)
	public void updatePEReportVariables(
			 @RequestParam(value = "reportType", defaultValue = "") String reportType,
			 HttpServletRequest request,HttpServletResponse response)  {
		
		restProxy.stream("updatePEReportVariables", request, response, new String[] {reportType});
		
	}
}
