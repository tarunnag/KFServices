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
@RequestMapping(value="/hrms/payhub/payprevalance") 
public class PayHubPrevalanceReportsController {

	
	private static final Logger logger = LoggerFactory.getLogger(PayHubReferencesController.class);
	
	@Autowired
	private LeapUtil leapUtil;
	@Autowired
	private RestProxy restProxy;
	/**
	 * 
	 * Service is used to get All families 
	 * 
	 * @param model
	 * @param response
	 */
	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public void getPayPrevalanceReport(
		    HttpServletRequest request,
			HttpServletResponse response) {

		//TODO: REMOVE userId from the above RequestParam after all the UI changes have been made
		restProxy.stream("getPayPrevalanceReport", request, response, new String[] { });

	}
	
	
	
}
