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
@RequestMapping(value="/hrms/paydatacollection/postprocessing/maintenance/") 
public class PayDataCollectionPostProcessingMaintenanceReportingFieldsController 
{

	private static final Logger logger = LoggerFactory.getLogger(PayDataCollectionPostProcessingMaintenanceReportingFieldsController.class);
	
	@Autowired
	private LeapUtil leapUtil;
	@Autowired
	private RestProxy restProxyForUPM;

	
	@PreAuthorize("hasRole('ROLE_USER') and hasRole('ROLE_INTERNAL_USER')")
	@RequestMapping(value = "reporttemplates", method = RequestMethod.POST)
	public void addReportTemplate(
			HttpServletRequest request,	HttpServletResponse response)
	{

		restProxyForUPM.stream("addReportTemplate", request, response, 
		new String[] {});

	}

		
	@PreAuthorize("hasRole('ROLE_USER') and hasRole('ROLE_INTERNAL_USER')")
	@RequestMapping(value = "/reporttemplates/{id}", method = RequestMethod.PUT)
	public void modifyReportTemplate(
			@PathVariable String id,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxyForUPM.stream("modifyReportTemplate", request, response, 
		new String[] {id});

	}
	
	@PreAuthorize("hasRole('ROLE_USER') and hasRole('ROLE_INTERNAL_USER')")
	@RequestMapping(value = "/reporttemplates/{id}", method = RequestMethod.GET)
	public void getReportTemplateById(
			@PathVariable String id,
			HttpServletRequest request,	HttpServletResponse response)
	{

		restProxyForUPM.stream("getReportTemplateById", request, response, 
		new String[] {id});

	}

	
	@PreAuthorize("hasRole('ROLE_USER') and hasRole('ROLE_INTERNAL_USER')")
	@RequestMapping(value = "/reporttemplates", method = RequestMethod.GET)
	public void getReportTemplates(
			@RequestParam(value = "reportType", defaultValue = "") String reportType,
			@RequestParam(value = "countryCode", defaultValue = "") String countryCode,
			HttpServletRequest request,	HttpServletResponse response)
	{

		restProxyForUPM.stream("getReportTemplates", request, response, 
		new String[] {reportType,countryCode});

	}
	
	@PreAuthorize("hasRole('ROLE_USER') and hasRole('ROLE_INTERNAL_USER')")
	@RequestMapping(value = "/reporttemplates/makeactive/{id}", method = RequestMethod.PUT)
	public void makeReportTemplateActive(
			@PathVariable String id,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxyForUPM.stream("makeReportTemplateActive", request, response, 
		new String[] {id});

	}

	

}
