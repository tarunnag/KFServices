package com.haygroup.leap.hrms.paydatacollection;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.haygroup.leap.client.RestProxy;
import com.haygroup.leap.common.LeapUtil;
import com.haygroup.leap.common.PropertiesUtil;

import org.apache.commons.net.util.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import com.haygroup.leap.client.RestProxy;
import com.haygroup.leap.client.RestTemplateProxy;



@Controller
@RequestMapping(value="/hrms/reports/") 
public class PayDataReportController {
	
	private static final Logger logger = LoggerFactory.getLogger(PayDataReportController.class);
	@Autowired
	private LeapUtil leapUtil;
	@Autowired
	private RestProxy restProxy;
	@Autowired
	private RestProxy restProxyForNoILBIS;

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/reportcategory", method = RequestMethod.GET)
	public void getReportCategory(
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("getReportCategory", request, response, new String[] {});

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/reportbycategory", method = RequestMethod.GET)
	public void getReportbyCategory(
			@RequestParam(value = "categoryId", defaultValue = "") String categoryId,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("getReportbyCategory", request, response, new String[] {categoryId});

	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/reportparameters", method = RequestMethod.GET)
	public void getReportParameters(
			@RequestParam(value = "reportId") String reportId,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("getReportParameters", request, response, new String[] {reportId});

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/reportparametervalue", method = RequestMethod.GET)
	public void getReportParameterValues(
			@RequestParam(value = "reportId") String reportId,
			@RequestParam(value = "parameterName") String parameterName,
			@RequestParam(value = "inputValues", defaultValue = "") String inputValues,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("getReportParameterValues", request, response, new String[] {reportId,parameterName,inputValues });

	}
	
	/**
	 * @param request
	 * @param response
	 */
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/downloadReports", method = RequestMethod.POST)
	public void downloadReports(
			@RequestParam(value = "reportId", defaultValue = "") String reportId,
			@RequestParam(value = "deferredReport", defaultValue = "") String deferredReport,
			@RequestParam(value = "queueId", defaultValue = "") String queueId,
			HttpServletRequest request, HttpServletResponse response) {

		restProxyForNoILBIS.stream("downloadReports", request, response, new String[] {reportId,deferredReport,queueId});

	}

}
