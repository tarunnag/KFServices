package com.haygroup.leap.hrms.usermanagement;

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
@RequestMapping(value="/hrms/usermanagement") 
public class ThUserManagementController {
		

	@Autowired
	private RestProxy restProxyForUPM;
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/thuser", method = RequestMethod.GET)
	public void getThUser(
			@RequestParam(value = "applicationName", defaultValue = "") String applicationName,
			@RequestParam(value = "searchString", defaultValue = "") String searchString,
			@RequestParam(value = "searchColumn", defaultValue = "") String searchColumn,
			@RequestParam(value = "sortBy", defaultValue = "") String sortBy,
			@RequestParam(value = "sortColumn", defaultValue = "") String sortColumn,
			@RequestParam(value = "filterBy", defaultValue = "") String filterBy,
			@RequestParam(value = "filterValues", defaultValue = "") String filterValues,
			@RequestParam(value = "pageIndex", defaultValue = "") String pageIndex,
			@RequestParam(value = "pageSize", defaultValue = "") String pageSize,
			HttpServletRequest request,	HttpServletResponse response)
	{
		restProxyForUPM.stream("getThUMUser", request, response, new String[] {applicationName, searchString, searchColumn, sortBy, sortColumn, filterBy, filterValues, pageIndex, pageSize});
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/thuser/{id}", method = RequestMethod.GET)
	public void getThUserDetails(
			@PathVariable String id,
			@RequestParam(value = "applicationName", defaultValue = "") String applicationName,
			HttpServletRequest request,	HttpServletResponse response)
	{
		restProxyForUPM.stream("getThUMUserDetails", request, response, new String[] {id, applicationName});
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/thuser", method = RequestMethod.POST)
	public void addThUserDetails(
			@RequestParam(value = "applicationName", defaultValue = "") String applicationName,
			HttpServletRequest request,	HttpServletResponse response)
	{
		restProxyForUPM.stream("addThUMUserDetails", request, response, new String[] {applicationName});
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/thuser", method = RequestMethod.PUT)
	public void updateThUserDetails(
			@RequestParam(value = "applicationName", defaultValue = "") String applicationName,
			@RequestParam(value = "appUserId", defaultValue = "") String appUserId,
			HttpServletRequest request,	HttpServletResponse response)
	{
		restProxyForUPM.stream("updateThUMUserDetails", request, response, new String[] {applicationName, appUserId});
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/thuser", method = RequestMethod.DELETE)
	public void deleteThUserDetails(
			@RequestParam(value = "applicationName", defaultValue = "") String applicationName,
			@RequestParam(value = "appUserId", defaultValue = "") String appUserId,
			HttpServletRequest request,	HttpServletResponse response)
	{
		restProxyForUPM.stream("deleteThUMUserDetails", request, response, new String[] {applicationName, appUserId});
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/groups", method = RequestMethod.GET)
	public void getUserManagementGroup(
			@RequestParam(value = "applicationName", defaultValue = "") String applicationName,
			HttpServletRequest request,	HttpServletResponse response)
	{
		restProxyForUPM.stream("getUserManagementGroup", request, response, new String[] {applicationName});
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/groups/{id}", method = RequestMethod.GET)
	public void getUserMgmtGroupsById(
			@PathVariable String id,
			@RequestParam(value = "applicationName", defaultValue = "") String applicationName,
			HttpServletRequest request,	HttpServletResponse response)
	{
		restProxyForUPM.stream("getUserMgmtGroupsById", request, response, new String[] {id, applicationName});
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/groups", method = RequestMethod.POST)
	public void addUserMgmtGroups(
			@RequestParam(value = "applicationName", defaultValue = "") String applicationName,
			HttpServletRequest request,	HttpServletResponse response)
	{
		restProxyForUPM.stream("addUserMgmtGroups", request, response, new String[] {applicationName});
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/groups/{id}", method = RequestMethod.PUT)
	public void updateUserMgmtGroups(
			@PathVariable String id,
			@RequestParam(value = "applicationName", defaultValue = "") String applicationName,
			HttpServletRequest request,	HttpServletResponse response)
	{
		restProxyForUPM.stream("updateUserMgmtGroups", request, response, new String[] {id, applicationName});
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/groups/{id}", method = RequestMethod.DELETE)
	public void deleteUserMgmtGroups(
			@PathVariable String id,
			@RequestParam(value = "applicationName", defaultValue = "") String applicationName,
			HttpServletRequest request,	HttpServletResponse response)
	{
		restProxyForUPM.stream("deleteUserMgmtGroups", request, response, new String[] {id, applicationName});
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/thuser/countrygroups", method = RequestMethod.GET)
	public void getUserCountriesAndGroups(
			@RequestParam(value = "appUserId", defaultValue = "") String appUserId,
			@RequestParam(value = "applicationName", defaultValue = "") String applicationName,
			@RequestParam(value = "module", defaultValue = "") String module,
			HttpServletRequest request,	HttpServletResponse response)
	{
		restProxyForUPM.stream("getUserCountriesAndGroups", request, response, new String[] {appUserId, applicationName, module});
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/applications", method = RequestMethod.GET)
	public void getApplicationsList(
			HttpServletRequest request,	HttpServletResponse response)
	{
		restProxyForUPM.stream("getApplicationsList", request, response, new String[] {});
	}
}
