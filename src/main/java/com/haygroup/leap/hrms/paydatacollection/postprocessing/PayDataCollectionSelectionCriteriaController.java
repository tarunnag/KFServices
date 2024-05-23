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
public class PayDataCollectionSelectionCriteriaController 
{

	private static final Logger logger = LoggerFactory.getLogger(PayDataCollectionSelectionCriteriaController.class);
	
	@Autowired
	private LeapUtil leapUtil;
	@Autowired
	private RestProxy restProxyForUPM;
	
	//TODO: IS
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/selectioncriterias", method = RequestMethod.GET)
	public void getSelectionCriterias(
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
		
		restProxyForUPM.stream("getSelectionCriterias", request, response, 
		new String[] {searchString,searchColumn,sortBy,sortColumn,filterBy,filterValues,pageIndex,pageSize,countryCode });

	}
	

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/selectioncriterias/{id}", method = RequestMethod.GET)
	public void getSelectionCriteriasById(
			@PathVariable String id,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxyForUPM.stream("getSelectionCriteriasById", request, response, 
		new String[] {id});

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/selectioncriterias", method = RequestMethod.POST)
	public void addSelectioncriteria(
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxyForUPM.stream("addSelectioncriteria", request, response, 
		new String[] {});

	}
		

	
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/selectioncriterias/{id}", method = RequestMethod.PUT)
	public void updateSelectioncriteriaById(
			@PathVariable String id,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxyForUPM.stream("updateSelectioncriteriaById", request, response, 
		new String[] {id});

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/selectioncriterias/{id}", method = RequestMethod.DELETE)
	public void deleteSelectionCriteriaById(
			@PathVariable String id,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxyForUPM.stream("deleteSelectionCriteriaById", request, response, 
		new String[] {id});

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/selectioncriterias/counts/{id}", method = RequestMethod.GET)
	public void getCountsById(
			@PathVariable String id,
			@RequestParam(value = "countryCode", defaultValue = "") String countryCode,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxyForUPM.stream("getCountsById", request, response, 
		new String[] {id,countryCode});

	}

}
