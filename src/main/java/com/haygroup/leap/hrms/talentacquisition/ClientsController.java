/**
 * 
 */
package com.haygroup.leap.hrms.talentacquisition;

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
@RequestMapping(value="/hrms/assessments")
public class ClientsController {
	
private static final Logger logger = LoggerFactory.getLogger(ProjectsController.class);
	
	@Autowired
	private LeapUtil leapUtil;
	@Autowired
	private RestProxy talentArchitectRestProxy;
	@Autowired
	private RestProxy restProxy;
	
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/clients/preferences", method = RequestMethod.GET)
	public void getClientMetadata(
			@RequestParam(value = "clientId", defaultValue = "") String clientId,	
			@RequestParam(value = "locale", defaultValue = "") String locale,
			@RequestParam(value = "projectId", defaultValue = "") String projectId,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		talentArchitectRestProxy.stream("getClientMetadata", request, response, new String[] { clientId, locale, projectId });

	}

}
