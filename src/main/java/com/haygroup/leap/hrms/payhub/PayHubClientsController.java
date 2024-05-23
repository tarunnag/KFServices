package com.haygroup.leap.hrms.payhub;

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

@Controller
@RequestMapping(value="/hrms/payhub/clients") 

public class PayHubClientsController {
	private static final Logger logger = LoggerFactory.getLogger(PayHubClientsController.class);
	
	@Autowired
	private LeapUtil leapUtil;
	
	@Autowired
	private RestProxy restProxy;
	
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/clientinfo", method = RequestMethod.GET)
	public void getClientMetadata(
			@RequestParam(value = "userId", defaultValue = "") String userId,	
			@RequestParam(value = "clientId", defaultValue = "") String clientId,	
			@RequestParam(value = "locale", defaultValue = "") String locale,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("getClientInfo", request, response, new String[] { userId, clientId, locale });

	}
}
