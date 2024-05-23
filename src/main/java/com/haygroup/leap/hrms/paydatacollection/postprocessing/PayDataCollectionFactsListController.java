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
@RequestMapping(value="/hrms/paydatacollection/postprocessing") 
public class PayDataCollectionFactsListController 
{

	private static final Logger logger = LoggerFactory.getLogger(PayDataCollectionFactsListController.class);
	
	@Autowired
	private LeapUtil leapUtil;
	@Autowired
	private RestProxy restProxyForUPM;
	

	//TODO: IS
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/factlists", method = RequestMethod.GET)
	public void getFactLists(
			@RequestParam(value = "searchString", defaultValue = "") String searchString,
			@RequestParam(value = "searchColumn", defaultValue = "") String searchColumn,	
			@RequestParam(value = "sortBy", defaultValue = "") String sortBy,
			@RequestParam(value = "sortColumn", defaultValue = "") String sortColumn,
			@RequestParam(value = "filterBy", defaultValue = "") String filterBy,
			@RequestParam(value = "filterValues", defaultValue = "") String filterValues,
			@RequestParam(value = "pageIndex", defaultValue = "") String pageIndex,
			@RequestParam(value = "pageSize", defaultValue = "") String pageSize,
			@RequestParam(value = "countryCode", defaultValue = "") String countryCode,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxyForUPM.stream("getFactLists", request, response, 
		new String[] {searchString, searchColumn, sortBy, sortColumn, filterBy, filterValues, pageIndex, pageSize, countryCode});

	}
	

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/factlists/{id}", method = RequestMethod.GET)
	public void getFactListsById(
			@PathVariable String id,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxyForUPM.stream("getFactListsById", request, response, 
		new String[] {id});

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/factlists", method = RequestMethod.POST)
	public void addFactLists(
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxyForUPM.stream("addFactLists", request, response, 
		new String[] {});

	}
		

	
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/factlists/{id}", method = RequestMethod.PUT)
	public void updateFactListsById(
			@PathVariable String id,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxyForUPM.stream("updateFactListsById", request, response, 
		new String[] {id});

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/factlists/{id}", method = RequestMethod.DELETE)
	public void deleteFactListById(
			@PathVariable String id,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxyForUPM.stream("deleteFactListById", request, response, 
		new String[] {id});

	}
	

}
