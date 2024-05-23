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
@RequestMapping(value="/hrms/paydatacollection/postprocessing/queries") 
public class PayDataCollectionPostProcessingQueryController 
{

	private static final Logger logger = LoggerFactory.getLogger(PayDataCollectionPostProcessingQueryController.class);
	
	@Autowired
	private LeapUtil leapUtil;
	@Autowired
	private RestProxy restProxyForUPM;
	

	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "", method = RequestMethod.POST)
	public void runQuery(
			@RequestParam(value = "version", defaultValue = "") String version,
			@RequestParam(value = "type", defaultValue = "") String type,
			@RequestParam(value = "queryId", defaultValue = "") String queryId,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxyForUPM.stream("runQuery", request, response, 
		new String[] {version, type, queryId});

	}
	
	
	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "", method = RequestMethod.GET)
	public void getAllQueries(
			@RequestParam(value = "countryCode", defaultValue = "") String countryCode,	
			@RequestParam(value = "createdBy", defaultValue = "") String createdBy,	
			HttpServletRequest request, HttpServletResponse response) {

		restProxyForUPM.stream("getAllQueries", request, response, new String[] {countryCode,createdBy});

	}
	
	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public void getByQueryId(
			@PathVariable String id,
			HttpServletRequest request, HttpServletResponse response) {

		restProxyForUPM.stream("getByQueryId", request, response, new String[] {id});

	}

	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public void updateQuery(
			@PathVariable String id,
			HttpServletRequest request, HttpServletResponse response) {

		restProxyForUPM.stream("updateQuery", request, response, new String[] {id});

	}
	
	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deleteQueryById(
			@PathVariable String id,
			HttpServletRequest request, HttpServletResponse response) {

		restProxyForUPM.stream("deleteQueryById", request, response, new String[] {id});

	}
	
	
}