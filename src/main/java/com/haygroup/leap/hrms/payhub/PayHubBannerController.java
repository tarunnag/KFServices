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
@RequestMapping(value="/hrms/payhub/banner") 
public class PayHubBannerController {
	
	
private static final Logger logger = LoggerFactory.getLogger(PayHubBannerController.class);
	
	@Autowired
	private LeapUtil leapUtil;
	@Autowired
	private RestProxy restProxy;

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "", method = RequestMethod.GET)
	public void getBanner(
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("getBanner", request, response, new String[] { });

	}
	

	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "", method = RequestMethod.POST)
	public void postBanner(
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("postBanner", request, response, new String[] { });

	}
		

}
