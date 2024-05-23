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
@RequestMapping(value="/hrms/payhub/appendix") 
public class PayHubAppendixController {

	
private static final Logger logger = LoggerFactory.getLogger(PayHubAppendixController.class);
	
	@Autowired
	private LeapUtil leapUtil;
	@Autowired
	private RestProxy restProxy;
	@Autowired
	private RestTemplateProxy restTemplate;
	
	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/kfreference", method = RequestMethod.GET)
	public void getAppKFReference(
			HttpServletRequest request, HttpServletResponse response) {
		
           restProxy.stream("getAppKFReference", request, response, new String[] {});

	}
	
	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/marketsourcedetail", method = RequestMethod.GET)
	public void getAppMakretSourceDetail(
			@RequestParam(value = "countryId") String countryId,
			@RequestParam(value = "datasourceId", defaultValue = "") String datasourceId,
			HttpServletRequest request, HttpServletResponse response) {
		
           restProxy.stream("getAppMakretSourceDetail", request, response, new String[] {countryId,datasourceId });

	}
	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/marketparticipants", method = RequestMethod.GET)
	public void getAppMarketParticipants(
			@RequestParam(value = "countryId") String countryId,
			@RequestParam(value = "datasourceId",  defaultValue = "") String datasourceId,
			@RequestParam(value = "searchString",  defaultValue = "") String searchString,
			@RequestParam(value = "searchColumn",  defaultValue = "") String searchColumn,
			@RequestParam(value = "sortColumn",  defaultValue = "") String sortColumn,
			@RequestParam(value = "sortBy",  defaultValue = "") String sortBy,
			@RequestParam(value = "pageIndex",  defaultValue = "") String pageIndex,
			@RequestParam(value = "pageSize",  defaultValue = "") String pageSize,
			HttpServletRequest request, HttpServletResponse response) {
		
           restProxy.stream("getAppMarketParticipants", request, response, new String[] {countryId,datasourceId,searchString,searchColumn,sortColumn,sortBy,pageIndex,pageSize});

	}

	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/compensationelements", method = RequestMethod.GET)
	public void getAppFacts(
			@RequestParam(value = "countryId") String countryId,
			@RequestParam(value = "dataSourceId") String dataSourceId,
			HttpServletRequest request, HttpServletResponse response) {
		
           restProxy.stream("getAppFacts", request, response, new String[] {countryId,dataSourceId});

	}
	

}
