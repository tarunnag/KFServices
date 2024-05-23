package com.haygroup.leap.hrms.architect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.haygroup.leap.client.RestProxy;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/hrms/architect/actions")
public class ArchitectActionsController {

	private static final Logger logger = LoggerFactory.getLogger(ArchitectController.class);

	@Autowired
	@Qualifier("restProxyForNodeAPI")
	private RestProxy restProxyForNodeAPI;

	/**
	 * @param jobId
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/validatejob", method = RequestMethod.POST)
	public void validateJob(HttpServletRequest request, HttpServletResponse response) {

		restProxyForNodeAPI.secureStream("validateArchitectJobs", request, response, new String[] {});
	}
	
	
	/**
	 * @param jobId
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_INTELLIGENCE_CLOUD')")
	@RequestMapping(value = "/getrelatedjobs", method = RequestMethod.GET)
	public void getrelatedjobs(@RequestParam(value = "jobIds", defaultValue = "") String jobIds,
			HttpServletRequest request, HttpServletResponse response) {
		
		restProxyForNodeAPI.secureStream("getrelatedjobs", request, response, new String[] {jobIds});
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/saverelatedjobs", method = RequestMethod.POST)
	public void saveRelatedJobs(HttpServletRequest request, HttpServletResponse response) {

		restProxyForNodeAPI.secureStream("saveRelatedJobs", request, response, new String[] {});
	}
	
}
