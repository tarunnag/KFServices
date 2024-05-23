package com.haygroup.leap.hrms.payhub;

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
@RequestMapping(value="/hrms/payhub/customgrades") 
public class PayHubCustomGradesController {
	
private static final Logger logger = LoggerFactory.getLogger(PayHubCustomGradesController.class);
	
	@Autowired
	private LeapUtil leapUtil;
	@Autowired
	private RestProxy restProxy;

	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/customgradesets", method = RequestMethod.GET)
	public void getCustomGradesets(
			@RequestParam(value = "countryCode", defaultValue = "") String countryCode,
			@RequestParam(value = "module", defaultValue = "") String module,
			@RequestParam(value = "clientId", defaultValue = "") String clientId,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("getCustomGradesets", request, response, new String[] { countryCode,module,clientId});

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/customgradesetdetails/{id}", method = RequestMethod.GET)
	public void getCustomGradesetDetails(
			@PathVariable String id,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("getCustomGradesetDetails", request, response, new String[] { id });

	}
	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/customgradesetdetails", method = RequestMethod.POST)
	public void addCustomGRadesetDetails(
			HttpServletRequest request, HttpServletResponse response) {

		restProxy.stream("postCustomGradesetDetails", request, response, new String[] {});

	}	

	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/customgradesetdetails", method = RequestMethod.PUT)
	public void updateCustomGRadesetDetails(
			HttpServletRequest request, HttpServletResponse response) {

		restProxy.stream("updateCustomGradesetDetails", request, response, new String[] {});

	}	
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/customgradesetdetails/{id}", method = RequestMethod.DELETE)
	public void deleteCustomGRadesetDetails(
			@PathVariable String id,
			HttpServletRequest request,	HttpServletResponse response)
	{
		restProxy.stream("deleteCustomGradesetDetails", request, response, new String[] {id});

	}
	

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/checkstatus", method = RequestMethod.GET)
	public void getCustomGradeStatus(
			
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("getCustomGradeStatus", request, response, new String[] {  });

	}
	
	
	  @PreAuthorize("hasRole('ROLE_USER')")
      @RequestMapping(value = "/gradesbasedpoints", method = RequestMethod.GET)
      public void getGradesByPoints(
                    @RequestParam(value = "gradeSetId", defaultValue = "") String gradeSetId,
                    @RequestParam(value = "countryCode", defaultValue = "") String countryCode,
                    @RequestParam(value = "clientGuid", defaultValue = "") String clientGuid,
                    @RequestParam(value = "hayPoints", defaultValue = "") String hayPoints,
                    @RequestParam(value = "reportType", defaultValue = "") String reportType,
                    HttpServletRequest request, HttpServletResponse response)
      {
             restProxy.stream("getGradesByPoints", request, response, new String[] {gradeSetId,countryCode,clientGuid,hayPoints,reportType});
            

      }	

	  @PreAuthorize("hasRole('ROLE_USER')")
      @RequestMapping(value = "/gradesByHayPoints", method = RequestMethod.GET)
      public void getGradesByHayPoints(
    		            @RequestParam(value = "hayPoints") String hayPoints,
                    @RequestParam(value = "gradeSetId", defaultValue = "1") String gradeSetId,
                    @RequestParam(value = "application", defaultValue = "") String application,
                    HttpServletRequest request, HttpServletResponse response)
      {
             restProxy.stream("getGradesByHayPoints", request, response, new String[] {hayPoints,gradeSetId,application});
            

      }	
	
}
