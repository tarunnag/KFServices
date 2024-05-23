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
import org.springframework.web.bind.annotation.RequestParam;

import com.haygroup.leap.client.RestProxy;
import com.haygroup.leap.common.LeapUtil;

/**
 * @author KOLISHES
 *
 */

@Controller
@RequestMapping(value="/hrms/references")
public class MainReferencesController {
	
private static final Logger logger = LoggerFactory.getLogger(SuccessProfileActionsController.class);
	
	@Autowired
	private LeapUtil leapUtil;
	@Autowired
	private RestProxy restProxy;
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/details", method = RequestMethod.GET)
	public void getReferencesDetails(
			@RequestParam String preferredClientId,
			@RequestParam String systemType,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("getReferencesDetails", request, response, new String[] { preferredClientId, systemType });
		
	}	

}
