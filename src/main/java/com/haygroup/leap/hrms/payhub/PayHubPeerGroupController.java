package com.haygroup.leap.hrms.payhub;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.net.util.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.haygroup.leap.client.RestProxy;
import com.haygroup.leap.client.RestTemplateProxy;
import com.haygroup.leap.common.LeapUtil;
import com.haygroup.leap.common.PropertiesUtil;

@Controller
@RequestMapping(value="/hrms/payhub/peergroups") 
public class PayHubPeerGroupController {
	
	private static final Logger logger = LoggerFactory.getLogger(PayHubPeerGroupController.class);

	
	@Autowired
	private LeapUtil leapUtil;
	@Autowired
	private RestProxy restProxy;
	@Autowired
	private RestTemplateProxy restTemplate;


	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public void getPeerGroups(
			@RequestParam(value = "countryCode", defaultValue = "") String countryCode,
			@RequestParam(value = "module", defaultValue = "") String module,
			@RequestParam(value = "payMarketId", defaultValue = "") String payMarketId,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("getPeerGroups", request, response, new String[] {countryCode, module, payMarketId });

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public void getPeerGroupDetails(
			@PathVariable String id,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("getPeerGroupDetails", request, response, new String[] { id });

	}
	
	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public void addPeerGroupDetails(
			HttpServletRequest request, HttpServletResponse response) {

		restProxy.stream("postPeerGroupDetails", request, response, new String[] {});

	}	
	
	
	
	/**
	 * @param request
	 * @param response
	 */
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/config", method = RequestMethod.GET)
	public void getDataDensityTypes(
			HttpServletRequest request, HttpServletResponse response) {

		restProxy.stream("getDataDensityTypes", request, response, new String[] {});

	}	
	
	
	

}
