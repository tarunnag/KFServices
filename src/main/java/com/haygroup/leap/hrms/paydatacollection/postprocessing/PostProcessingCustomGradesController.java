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
@RequestMapping(value="/hrms/postprocessing/customgrades") 
public class PostProcessingCustomGradesController {
	
private static final Logger logger = LoggerFactory.getLogger(PostProcessingCustomGradesController.class);
	
	@Autowired
	private LeapUtil leapUtil;
	@Autowired
	private RestProxy restProxyForUPM;

	
	@PreAuthorize("hasRole('ROLE_USER') and hasRole('ROLE_INTERNAL_USER')")
	@RequestMapping(value = "/customgradesets", method = RequestMethod.GET)
	public void getPostProcessingCustomGradesets(
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
		
		restProxyForUPM.stream("getPostProcessingCustomGradesets", request, response, new String[] { countryCode,
				sortColumn,sortBy,searchString,searchColumn,filterBy,filterValues,pageIndex,pageSize});

	}
	
	@PreAuthorize("hasRole('ROLE_USER') and hasRole('ROLE_INTERNAL_USER')")
	@RequestMapping(value = "/customgradesetdetails/{id}", method = RequestMethod.GET)
	public void getPostProcessingCustomGradesetDetails(
			@PathVariable String id,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxyForUPM.stream("getPostProcessingCustomGradesetDetails", request, response, new String[] { id });

	}
	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER') and hasRole('ROLE_INTERNAL_USER')")
	@RequestMapping(value = "/customgradesetdetails", method = RequestMethod.POST)
	public void addCustomGRadesetDetails(
			HttpServletRequest request, HttpServletResponse response) {

		restProxyForUPM.stream("postPostProcessingCustomGradesetDetails", request, response, new String[] {});

	}	

	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER') and hasRole('ROLE_INTERNAL_USER')")
	@RequestMapping(value = "/customgradesetdetails", method = RequestMethod.PUT)
	public void updateCustomGRadesetDetails(
			HttpServletRequest request, HttpServletResponse response) {

		restProxyForUPM.stream("updatePostProcessingCustomGradesetDetails", request, response, new String[] {});

	}	
	
	@PreAuthorize("hasRole('ROLE_USER') and hasRole('ROLE_INTERNAL_USER')")
	@RequestMapping(value = "/customgradesetdetails/{id}", method = RequestMethod.DELETE)
	public void deleteCustomGRadesetDetails(
			@PathVariable String id,
			HttpServletRequest request,	HttpServletResponse response)
	{
		restProxyForUPM.stream("deletePostProcessingCustomGradesetDetails", request, response, new String[] {id});

	}
	

}
