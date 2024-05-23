/**
 * 
 */
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

import com.haygroup.leap.client.RestProxy;
import com.haygroup.leap.common.LeapUtil;

/**
 * @author KOLISHES
 *
 */

@Controller
@RequestMapping(value="/hrms/actions")  
public class MainActionsController {
	
private static final Logger logger = LoggerFactory.getLogger(SuccessProfileActionsController.class);
	
	@Autowired
	private LeapUtil leapUtil;
	@Autowired
	private RestProxy restProxy;
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/calculatejobgraderesponsibility", method = RequestMethod.POST)
	public void calculateprofilegrade(
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("calculateJobGradeResponsibility", request, response, new String[] {  });

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/publishtoworkday", method = RequestMethod.POST)
	public void publishToWorkday(
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("publishToWorkday", request, response, new String[] {  });

	}
	/*
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/bulkupdatejobdescriptions", method = RequestMethod.PUT)
	public void bulkupdatejobdescriptions(
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("bulkupdatejobdescriptions", request, response, new String[] {  });

	}
	 */

}
