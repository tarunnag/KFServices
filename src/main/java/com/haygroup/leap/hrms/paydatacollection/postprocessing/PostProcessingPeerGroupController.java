package com.haygroup.leap.hrms.paydatacollection.postprocessing;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.haygroup.leap.client.RestProxy;
import com.haygroup.leap.common.LeapUtil;

@Controller
@RequestMapping(value="/hrms/postprocessing/peergroups") 
public class PostProcessingPeerGroupController {
	
	private static final Logger logger = LoggerFactory.getLogger(PostProcessingPeerGroupController.class);

	
	@Autowired
	private LeapUtil leapUtil;
	@Autowired
	private RestProxy restProxyForUPM;



	@PreAuthorize("hasRole('ROLE_USER') and hasRole('ROLE_INTERNAL_USER')")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public void getPostProcessingPeerGroups(
			@RequestParam(value = "countryCode", defaultValue = "") String countryCode,
			@RequestParam(value = "sortColumn", defaultValue = "") String sortColumn,
			@RequestParam(value = "sortBy", defaultValue = "") String sortBy,
			@RequestParam(value = "searchString", defaultValue = "") String searchString,
			@RequestParam(value = "searchColumn", defaultValue = "") String searchColumn,
			@RequestParam(value = "filterBy", defaultValue = "") String filterBy,
			@RequestParam(value = "filterValues", defaultValue = "") String filterValues,
			@RequestParam(value = "pageIndex", defaultValue = "") String pageIndex,
			@RequestParam(value = "pageSize", defaultValue = "") String pageSize,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxyForUPM.stream("getPostProcessingPeerGroups", request, response, new String[] {countryCode,sortColumn,
				sortBy,searchString,searchColumn,filterBy,filterValues,pageIndex,pageSize});
				 
	}
	
	@PreAuthorize("hasRole('ROLE_USER') and hasRole('ROLE_INTERNAL_USER')")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public void getPostProcessingPeerGroupDetails(
			@PathVariable String id,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxyForUPM.stream("getPostProcessingPeerGroupDetails", request, response, new String[] { id });

	}
	
	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER') and hasRole('ROLE_INTERNAL_USER')")
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public void addPostProcessingPeerGroupDetails(
			HttpServletRequest request, HttpServletResponse response) {

		restProxyForUPM.stream("postPostProcessingPeerGroupDetails", request, response, new String[] {});

	}	
	
	@PreAuthorize("hasRole('ROLE_USER') and hasRole('ROLE_INTERNAL_USER')")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deleteByPeerGroupId(
			@PathVariable String id,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxyForUPM.stream("deleteByPeerGroupId", request, response, new String[] { id });

	}
	
	
}
