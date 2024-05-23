package com.haygroup.leap.hrms.paydatacollection.postprocessing;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.haygroup.leap.client.RestProxy;

@Controller
@RequestMapping(value="/hrms/paydatacollection/postprocessing/usermanagement") 
public class PayDataCollectionPostProcessingUserManagementController {
	
	@Autowired
	private RestProxy restProxyForUPM;
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/thuser", method = RequestMethod.GET)
	public void getThUser(
			@RequestParam(value = "applicationName", defaultValue = "") String applicationName,
			HttpServletRequest request,	HttpServletResponse response)
	{
		restProxyForUPM.stream("getThUser", request, response, new String[] {applicationName});
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/thuser/{id}", method = RequestMethod.GET)
	public void getThUserDetails(
			@PathVariable String id,
			@RequestParam(value = "applicationName", defaultValue = "") String applicationName,
			HttpServletRequest request,	HttpServletResponse response)
	{
		restProxyForUPM.stream("getThUserDetails", request, response, new String[] {id, applicationName});
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/thuser", method = RequestMethod.POST)
	public void addThUserDetails(
			@RequestParam(value = "applicationName", defaultValue = "") String applicationName,
			HttpServletRequest request,	HttpServletResponse response)
	{
		restProxyForUPM.stream("addThUserDetails", request, response, new String[] {applicationName});
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/thuser", method = RequestMethod.PUT)
	public void updateThUserDetails(
			@RequestParam(value = "applicationName", defaultValue = "") String applicationName,
			@RequestParam(value = "appUserId", defaultValue = "") String appUserId,
			HttpServletRequest request,	HttpServletResponse response)
	{
		restProxyForUPM.stream("updateThUserDetails", request, response, new String[] {applicationName, appUserId});
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/thuser", method = RequestMethod.DELETE)
	public void deleteThUserDetails(
			@RequestParam(value = "applicationName", defaultValue = "") String applicationName,
			@RequestParam(value = "appUserId", defaultValue = "") String appUserId,
			HttpServletRequest request,	HttpServletResponse response)
	{
		restProxyForUPM.stream("deleteThUserDetails", request, response, new String[] {applicationName, appUserId});
	}

}
