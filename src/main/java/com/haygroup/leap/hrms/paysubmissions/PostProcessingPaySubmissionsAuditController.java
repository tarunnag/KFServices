package com.haygroup.leap.hrms.paysubmissions;

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
import com.haygroup.leap.client.RestTemplateProxy;
import com.haygroup.leap.common.LeapUtil; 

@Controller
@RequestMapping(value="/hrms/paysubmissions/audit") 
public class PostProcessingPaySubmissionsAuditController {
	
	private static final Logger logger = LoggerFactory.getLogger(PostProcessingPaySubmissionsAuditController.class);

	
	@Autowired
	private LeapUtil leapUtil;
	@Autowired
	private RestProxy restProxyForUPM;



	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "", method = RequestMethod.POST)
	public void addPaySubmissionAudit(
			@RequestParam(value = "paySubmissionId", defaultValue = "") String paySubmissionId,
			HttpServletRequest request, HttpServletResponse response) {
		restProxyForUPM.stream("addPaySubmissionAudit", request, response, new String[] {paySubmissionId});

	}	
	
	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "", method = RequestMethod.PUT)
	public void updatePaySubmissionAuditBySubmissionId(
			@RequestParam(value = "paySubmissionId", defaultValue = "") String paySubmissionId,
			HttpServletRequest request, HttpServletResponse response) {

		restProxyForUPM.stream("updatePaySubmissionAuditBySubmissionId", request, response, new String[] {paySubmissionId});

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "", method = RequestMethod.GET)
	public void getPaySubmissionAuditBySubmissionId(
			@RequestParam(value = "paySubmissionId", defaultValue = "") String paySubmissionId,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxyForUPM.stream("getPaySubmissionAuditBySubmissionId", request, response, new String[] { paySubmissionId });

	}
		
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "", method = RequestMethod.DELETE)
	public void deletePaySubmissionAuditBySubmissionId(
			@RequestParam(value = "paySubmissionId", defaultValue = "") String paySubmissionId,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxyForUPM.stream("deletePaySubmissionAuditBySubmissionId", request, response, new String[] { paySubmissionId });

	}
	
	
}
