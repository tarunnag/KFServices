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

import com.haygroup.leap.client.RestProxy;
import com.haygroup.leap.client.RestTemplateProxy;
import com.haygroup.leap.common.LeapUtil;


@Controller
@RequestMapping(value="/hrms/payhub/payclientReport") 
public class PayHubClientReportsController {

	private static final Logger logger = LoggerFactory.getLogger(PayHubClientReportsController.class);

	@Autowired
	private LeapUtil leapUtil;
	@Autowired
	private RestProxy restProxy;
	@Autowired
	private RestTemplateProxy restTemplate;
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/useraccess", method = RequestMethod.GET)
	public void getPayClientReports(
		    HttpServletRequest request,
			HttpServletResponse response) {

		//TODO: REMOVE userId from the above RequestParam after all the UI changes have been made
		restProxy.stream("getPayClientReports", request, response, new String[] { });

	}
	
	
}
