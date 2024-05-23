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
@RequestMapping(value="/hrms/payhub") 
public class PayHubController {
private static final Logger logger = LoggerFactory.getLogger(PayHubController.class);
	
	@Autowired
	private LeapUtil leapUtil;
	@Autowired
	private RestProxy restProxy;
	
	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/compratios", method = RequestMethod.GET)
	public void getPayCompRatios(@RequestParam(value = "userId") String userId,
			@RequestParam(value = "clientId", defaultValue = "") String clientId,
			@RequestParam(value = "locale", defaultValue = "") String locale,	
			@RequestParam(value = "module", defaultValue = "") String module,	
			@RequestParam(value = "includeCountries", defaultValue = "") String includeCountries,	
			HttpServletRequest request, HttpServletResponse response) {
		
           restProxy.stream("getPayCompRatios", request, response, new String[] { userId,clientId,locale,module,includeCountries});

	}
	
	
	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/access", method = RequestMethod.GET)
	public void getMarketAccess(@RequestParam(value = "countryId") String countryId,
			
			HttpServletRequest request, HttpServletResponse response) {
		
           restProxy.stream("getMarketAccess", request, response, new String[] { countryId});

           
	}	
	
	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/overviewaccess", method = RequestMethod.GET)
	public void getOverviewAccess(@RequestParam(value = "module") String module,
			@RequestParam(value = "includedCountryIds", defaultValue = "") String includedCountryIds,
			
			HttpServletRequest request, HttpServletResponse response) {
		
           restProxy.stream("getOverviewAccess", request, response, new String[] { module,includedCountryIds});

	}	
	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/compensationreport", method = RequestMethod.GET)
	public void compensationreport(
			
			HttpServletRequest request, HttpServletResponse response) {
		
           restProxy.stream("getCompensationAccess", request, response, new String[] { });

	}
	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/compandbenefit", method = RequestMethod.GET)
	public void getcompandbenefit(
			@RequestParam(value = "module", defaultValue = "") String module,
			@RequestParam(value = "countryId", defaultValue = "") String countryId,
			HttpServletRequest request, HttpServletResponse response) {
		
           restProxy.stream("getcompandbenefit", request, response, new String[] { module, countryId});

	}	
	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/compandbenefit", method = RequestMethod.POST)
	public void addcompandbenefit(
			
			HttpServletRequest request, HttpServletResponse response) {
		
           restProxy.stream("addcompandbenefit", request, response, new String[] { });

	}	
	
	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/compandbenefit", method = RequestMethod.PUT)
	public void updatecompandbenefit(
			
			HttpServletRequest request, HttpServletResponse response) {
		
           restProxy.stream("updatecompandbenefit", request, response, new String[] { });

	}	
	
	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_INTELLIGENCE_CLOUD')")
	@RequestMapping(value = "/businessunits", method = RequestMethod.GET)
	public void getBusinessUnits(@RequestParam(value = "countryId") String countryId,
			@RequestParam(value = "module",defaultValue = "") String module,
			HttpServletRequest request, HttpServletResponse response) {
		    restProxy.stream("getBusinessUnits", request, response, new String[] { countryId, module});

	}
	
	/**
	 * @param request
	 * @param response
	 * @author Harsha
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/allcompelements", method = RequestMethod.GET)
	public void getCompElements(@RequestParam(value = "countryCode",defaultValue = "") String countryCode,
			@RequestParam(value = "datasourceId",defaultValue = "") String datasourceId,
			HttpServletRequest request, HttpServletResponse response) {
		    restProxy.stream("getCompElements", request, response, new String[] { countryCode, datasourceId});
	}
	
	/**
	 * @param request
	 * @param response
	 * @author Harsha
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/allcompelements", method = RequestMethod.POST)
	public void postcompclements(	@RequestParam(value = "reportType", defaultValue = "") String reportType,
			HttpServletRequest request, HttpServletResponse response) {
		    restProxy.stream("postcompclements", request, response, new String[] { reportType});
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/payclientreport/marketoverviewreportdetails", method = RequestMethod.GET)
	public void marketOverviewReportDetails(
			HttpServletRequest request, HttpServletResponse response) {
		    restProxy.stream("marketOverviewReportDetails", request, response, new String[] { });
	}
	

	
	
	
	
}
