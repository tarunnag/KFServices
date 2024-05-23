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
@RequestMapping(value="/hrms/paydatacollection/postprocessing/datatransformations") 
public class PayDataCollectionPostProcessingDataTransformationController 
{

	private static final Logger logger = LoggerFactory.getLogger(PayDataCollectionPostProcessingDataTransformationController.class);
	
	@Autowired
	private LeapUtil leapUtil;
	@Autowired
	private RestProxy restProxyForUPM;

	
	@PreAuthorize("hasRole('ROLE_USER') and hasRole('ROLE_INTERNAL_USER')")
	@RequestMapping(value = "", method = RequestMethod.GET)
	public void getDataTransformations(
			@RequestParam(value = "countryCode", defaultValue = "") String countryCode,	
			@RequestParam(value = "returnColumnName", defaultValue = "") String returnColumnName,
			@RequestParam(value = "pageIndex", defaultValue = "") String pageIndex,
			@RequestParam(value = "pageSize", defaultValue = "") String pageSize,
			@RequestParam(value = "sortColumn", defaultValue = "") String sortColumn,
			@RequestParam(value = "sortBy", defaultValue = "") String sortBy,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxyForUPM.stream("getDataTransformations", request, response, 
		new String[] {countryCode,returnColumnName,pageIndex,pageSize,sortColumn,sortBy});

	}
	
	@PreAuthorize("hasRole('ROLE_USER') and hasRole('ROLE_INTERNAL_USER')")
	@RequestMapping(value = "", method = RequestMethod.POST)
	public void addDataTransformations(
			HttpServletRequest request,	HttpServletResponse response)
	{

		//TODO: IS NOT IMPLEMENTED
		restProxyForUPM.stream("addDataTransformations", request, response, 
		new String[] {});

	}
//	
//		
//	@PreAuthorize("hasRole('ROLE_USER')")
//	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
//	public void getFunctionById(
//			@PathVariable String id,
//			HttpServletRequest request,	HttpServletResponse response)
//	{
//		
//		restProxyForUPM.stream("getFunctionById", request, response, 
//		new String[] {id});
//
//	}
//
	@PreAuthorize("hasRole('ROLE_USER') and hasRole('ROLE_INTERNAL_USER')")
	@RequestMapping(value = "", method = RequestMethod.PUT)
	public void modifyDataTransformations(
			HttpServletRequest request,	HttpServletResponse response)
	{

		//TODO: IS NOT IMPLEMENTED
		restProxyForUPM.stream("modifyDataTransformations", request, response, 
		new String[] {});

	}
	
	@PreAuthorize("hasRole('ROLE_USER') and hasRole('ROLE_INTERNAL_USER')")
	@RequestMapping(value = "", method = RequestMethod.DELETE)
	public void deleteDataTransformations(
			@RequestParam(value = "countryCode", defaultValue = "") String countryCode,	
			@RequestParam(value = "lookupName", defaultValue = "") String lookupName,
			HttpServletRequest request,	HttpServletResponse response)
	{

		restProxyForUPM.stream("deleteDataTransformations", request, response, new String[] {countryCode, lookupName});

	}


}
