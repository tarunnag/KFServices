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
@RequestMapping(value="/hrms/paydatacollection/postprocessing/functions") 
public class PayDataCollectionPostProcessingFunctionsController 
{

	private static final Logger logger = LoggerFactory.getLogger(PayDataCollectionPostProcessingFunctionsController.class);
	
	@Autowired
	private LeapUtil leapUtil;
	@Autowired
	private RestProxy restProxyForUPM;

	
	@PreAuthorize("hasRole('ROLE_USER') and hasRole('ROLE_INTERNAL_USER')")
	@RequestMapping(value = "", method = RequestMethod.POST)
	public void addFunction(
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxyForUPM.stream("addFunction", request, response, 
		new String[] {});

	}
	
		
	@PreAuthorize("hasRole('ROLE_USER') and hasRole('ROLE_INTERNAL_USER')")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public void getFunctionById(
			@PathVariable String id,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxyForUPM.stream("getFunctionById", request, response, 
		new String[] {id});

	}

	@PreAuthorize("hasRole('ROLE_USER') and hasRole('ROLE_INTERNAL_USER')")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public void modifyFunction(
			@PathVariable String id,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxyForUPM.stream("modifyFunction", request, response, 
		new String[] {id});

	}

	
	@PreAuthorize("hasRole('ROLE_USER') and hasRole('ROLE_INTERNAL_USER')")
	@RequestMapping(value = "", method = RequestMethod.GET)
	public void getAllFunctionsByCountryCode(
			@RequestParam(value = "searchString", defaultValue = "") String searchString,	
			@RequestParam(value = "searchColumn", defaultValue = "") String searchColumn,	
			@RequestParam(value = "sortBy", defaultValue = "") String sortBy,	
			@RequestParam(value = "sortColumn", defaultValue = "") String sortColumn,	
			@RequestParam(value = "filterBy", defaultValue = "") String filterBy,	
			@RequestParam(value = "filterValues", defaultValue = "") String filterValues,	
			@RequestParam(value = "pageIndex", defaultValue = "") String pageIndex,	
			@RequestParam(value = "pageSize", defaultValue = "") String pageSize, 
			
			//@RequestParam (value="countryCode", defaultValue = "") String countryCode,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxyForUPM.stream("getAllFunctionsByCountryCode", request, response, new String[] {searchString, searchColumn, sortBy, sortColumn, filterBy, filterValues, pageIndex, pageSize});
        //new String[] {countryCode});
	}
	
	
	@PreAuthorize("hasRole('ROLE_USER') and hasRole('ROLE_INTERNAL_USER')")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deleteFunction(
			@PathVariable String id,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxyForUPM.stream("deleteFunction", request, response, 
		new String[] {id});

	}
	
}
