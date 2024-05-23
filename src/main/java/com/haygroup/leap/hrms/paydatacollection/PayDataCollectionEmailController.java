package com.haygroup.leap.hrms.paydatacollection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.haygroup.leap.client.RestProxy;
import com.haygroup.leap.common.LeapUtil;

@Controller
@RequestMapping(value="/hrms/paydatacollection/email") 
public class PayDataCollectionEmailController {
	
	private static final Logger logger = LoggerFactory.getLogger(PayDataCollectionEmailController.class);

	@Autowired
	private LeapUtil leapUtil;
	@Autowired
	private RestProxy restProxy;

	/**
	* @param type
	* @param request
	* @param response
	*/
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/searchemailtemplates", method = RequestMethod.GET)
	public void searchEmailTemplates(
									@RequestParam(value = "searchString", defaultValue = "") String searchString,
									@RequestParam(value = "searchColumn", defaultValue = "") String searchColumn,
									@RequestParam(value = "sortColumn", defaultValue = "") String sortColumn,
									@RequestParam(value = "sortBy", defaultValue = "") String sortBy,
									@RequestParam(value = "filterBy", defaultValue = "") String filterBy,
									@RequestParam(value = "filterValues", defaultValue = "") String filterValues,                                                                                                       
									@RequestParam(value = "pageIndex", defaultValue = "") String pageIndex,
									@RequestParam(value = "pageSize", defaultValue = "") String pageSize,
					   HttpServletRequest request,   HttpServletResponse response)
	{

					restProxy.stream("searchEmailTemplates", request, response, new String[] { searchString, searchColumn,sortColumn,sortBy,filterBy,filterValues, pageIndex, pageSize });

	}



	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "/{templateId}", method = RequestMethod.GET)
    public void getEmailTemplateById (@PathVariable String templateId,HttpServletRequest request,HttpServletResponse response) {

                restProxy.stream("getEmailTemplateById", request, response, new String[] { templateId });

	}
	
	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public void addEmailTemplate(
			HttpServletRequest request, HttpServletResponse response) {

		restProxy.stream("addEmailTemplate", request, response, new String[] {});

	}	
	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/{templateId}", method = RequestMethod.PUT)
	public void updateEmailTemplate(@PathVariable String templateId,
			HttpServletRequest request, HttpServletResponse response) {

		restProxy.stream("updateEmailTemplate", request, response, new String[] {templateId});

	}
	

	/**
	* @param type
	* @param request
	* @param response
	*/
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/searchClientByAccess", method = RequestMethod.GET)
	public void searchClientByAccess(
									@RequestParam(value = "searchString", defaultValue = "") String searchString,
									@RequestParam(value = "searchColumn", defaultValue = "") String searchColumn,
									@RequestParam(value = "sortColumn", defaultValue = "") String sortColumn,
									@RequestParam(value = "sortBy", defaultValue = "") String sortBy,
									@RequestParam(value = "filterBy", defaultValue = "") String filterBy,
									@RequestParam(value = "filterValues", defaultValue = "") String filterValues,                                                                                                       
									@RequestParam(value = "pageIndex", defaultValue = "") String pageIndex,
									@RequestParam(value = "pageSize", defaultValue = "") String pageSize,
					   HttpServletRequest request,   HttpServletResponse response)
	{

					restProxy.stream("searchClientByAccess", request, response, new String[] { searchString, searchColumn,sortColumn,sortBy,filterBy,filterValues, pageIndex, pageSize });

	}
	
	/**
	* @param type
	* @param request
	* @param response
	*/
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/defaulttemplate/{templateId} ", method = RequestMethod.GET)
	public void getDefaulttEmailTemplate(@PathVariable String templateId, HttpServletRequest request, HttpServletResponse response) {
		restProxy.stream("getDefaulttEmailTemplate", request, response, new String[] { templateId });
	}

	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/sendemail", method = RequestMethod.POST)
	public void sendEmail(HttpServletRequest request, HttpServletResponse response) {
                restProxy.stream("sendEmail", request, response, new String[] {});
	}
	
}
