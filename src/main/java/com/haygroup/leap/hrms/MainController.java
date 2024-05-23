package com.haygroup.leap.hrms;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;

import com.haygroup.leap.client.RestProxy;
import com.haygroup.leap.common.HGLeapConstants;
import com.haygroup.leap.common.LeapUtil;




@Controller
@RequestMapping(value="/hrms")  
public class MainController {
	
private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	@Autowired
	private LeapUtil leapUtil;
	@Autowired
	private RestProxy restProxy;
	
	
	/**
	 * @param applicationId
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/dashboards", method = RequestMethod.GET)
	public void getDashboards(@RequestParam String type,
			@RequestParam String preferredLocale,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("getDashboards", request, response, new String[] { type,preferredLocale });
		
	}	
}
