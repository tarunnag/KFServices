package com.haygroup.leap.hrms.usermanagement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.haygroup.leap.client.RestProxy;

@Controller
@RequestMapping(value="/hrms/usermanagement/actions") 
public class ThUserManagementActionsController {
	
	@Autowired
	private RestProxy restProxyForUPM;
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/permissions", method = RequestMethod.GET)
	public void getUserManagementPermissions(
			@RequestParam(value = "appUserId", defaultValue = "") String appUserId,
			@RequestParam(value = "applicationName", defaultValue = "") String applicationName,
			@RequestParam(value = "module", defaultValue = "") String module,
			HttpServletRequest request,	HttpServletResponse response)
	{
		restProxyForUPM.stream("getUserManagementPermissions", request, response, new String[] {appUserId,applicationName,module});
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/countrypermission", method = RequestMethod.GET)
	public void checkUserCountryPermission(
			@RequestParam(value = "applicationName", defaultValue = "") String applicationName,
			@RequestParam(value = "countryCode", defaultValue = "") String countryCode,
			@RequestParam(value = "module", defaultValue = "") String module,
			@RequestParam(value = "itemId", defaultValue = "") String itemId,
			HttpServletRequest request,	HttpServletResponse response)
	{
		restProxyForUPM.stream("checkUserCountryPermission", request, response, new String[] {applicationName,countryCode,module,itemId});
	}
	

}
