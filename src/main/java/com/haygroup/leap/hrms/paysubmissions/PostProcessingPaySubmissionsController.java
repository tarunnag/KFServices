package com.haygroup.leap.hrms.paysubmissions;

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
@RequestMapping(value="/hrms/paysubmissions") 
public class PostProcessingPaySubmissionsController {
	
	private static final Logger logger = LoggerFactory.getLogger(PostProcessingPaySubmissionsController.class);

	
	@Autowired
	private LeapUtil leapUtil;
	@Autowired
	private RestProxy restProxy;
	@Autowired
	private RestTemplateProxy restTemplate;


	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "", method = RequestMethod.POST)
	public void addPaySubmission(
			HttpServletRequest request, HttpServletResponse response) {
		restProxy.stream("addPaySubmission", request, response, new String[] {});

	}	
	
	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public void updatePaySubmission(
			@PathVariable String id,
			HttpServletRequest request, HttpServletResponse response) {

		restProxy.stream("updatePaySubmission", request, response, new String[] {id});

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public void getPaySubmissionById(
			@PathVariable String id,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("getPaySubmissionById", request, response, new String[] { id });

	}
		
	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "", method = RequestMethod.GET)
	public void getAllPaySubmisssions(
			HttpServletRequest request, HttpServletResponse response) {

		restProxy.stream("getAllPaySubmisssions", request, response, new String[] {});

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deletePaySubmission(
			@PathVariable String id,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("deletePaySubmission", request, response, new String[] { id });

	}
	
	
}
