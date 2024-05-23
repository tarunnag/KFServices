package com.haygroup.leap.hrms.payequity;

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


/**
 * @author Harsha
 *
 */ 

@Controller
@RequestMapping(value="/hrms/payequity/actions") 
public class PayEquityController {
	
	private static final Logger logger = LoggerFactory.getLogger(PayEquityController.class);
	
	@Autowired
	private RestProxy restProxy;

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/useraccesscountry", method = RequestMethod.GET)
	public void useraccesscountry(
			 @RequestParam(value = "module", defaultValue = "") String module,
			 HttpServletRequest request,HttpServletResponse response)  {
		
		restProxy.stream("useraccesscountry", request, response, new String[] {module});
		
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/useraccessreports", method = RequestMethod.GET)
	public void useraccessreports(
			 @RequestParam(value = "module", defaultValue = "") String module,
			 HttpServletRequest request,HttpServletResponse response)  {
		
		restProxy.stream("useraccessreports", request, response, new String[] {module});
		
	}

}
