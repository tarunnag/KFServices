/**
 * 
 */
package com.haygroup.leap.hrms.insight;

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
import com.haygroup.leap.hrms.JobDescriptionController;

/**
 * @author KOLISHES
 *
 */

@Controller
@RequestMapping(value="/hrms/insights") 
public class JobModalController {
	
private static final Logger logger = LoggerFactory.getLogger(JobDescriptionController.class);
	
	@Autowired
	private LeapUtil leapUtil;
	@Autowired
	private RestProxy restProxy;
	
	/**
	 * @param countryCode
	 * @param companyOrgCode
	 * @param clientId
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/jobmodal", method = RequestMethod.GET)
	public void getClientJobModal(
			@RequestParam(value = "countryCode", defaultValue = "") String countryCode,
			@RequestParam(value = "companyOrgCode", defaultValue = "") String companyOrgCode,
			@RequestParam(value = "clientId", defaultValue = "") String clientId,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("getClientJobModal", request, response, new String[] { countryCode, companyOrgCode, clientId});

	}
	
	
	/**
	 * @param countryCode
	 * @param companyOrgCode
	 * @param clientId
	 * @param insightType
	 * @param jobModalEntityCode
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/jobmodalinsights", method = RequestMethod.GET)
	public void getClientJobModalInsights(
			@RequestParam(value = "countryCode", defaultValue = "") String countryCode,
			@RequestParam(value = "companyOrgCode", defaultValue = "") String companyOrgCode,
			@RequestParam(value = "clientId", defaultValue = "") String clientId,
			@RequestParam(value = "insightType", defaultValue = "") String insightType,
			@RequestParam(value = "jobModalEntityCode", defaultValue = "") String jobModalEntityCode,
			@RequestParam(value = "level", defaultValue = "") String level,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("getClientJobModalInsights", request, response, new String[] { countryCode, companyOrgCode, clientId, insightType, jobModalEntityCode, level});

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/jobexports", method = RequestMethod.GET)
	public void getJobExports(
			@RequestParam(value = "countryCode", defaultValue = "") String countryCode,
			@RequestParam(value = "companyOrgCode", defaultValue = "") String companyOrgCode,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("getJobExports", request, response, new String[] { countryCode, companyOrgCode});

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/orgsummary", method = RequestMethod.GET)
	public void getOrgSummary(
			@RequestParam(value = "countryCode", defaultValue = "") String countryCode,
			@RequestParam(value = "companyOrgCode", defaultValue = "") String companyOrgCode,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("getOrgSummary", request, response, new String[] { countryCode, companyOrgCode});

	}
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/rewardpractice", method = RequestMethod.GET)
	public void getRewardPractice(
			@RequestParam(value = "countryCode", defaultValue = "") String countryCode,
			@RequestParam(value = "companyOrgCode", defaultValue = "") String companyOrgCode,
			@RequestParam(value = "viewType", defaultValue = "") String viewType,
			@RequestParam(value = "filterBy", defaultValue = "") String filterBy,
			@RequestParam(value = "filterValues", defaultValue = "") String filterValues,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("getRewardPractice", request, response, new String[] { countryCode, companyOrgCode, viewType, filterBy, filterValues });

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/internalequity", method = RequestMethod.GET)
	public void getInternalEquity(
			@RequestParam(value = "countryCode", defaultValue = "") String countryCode,
			@RequestParam(value = "companyOrgCode", defaultValue = "") String companyOrgCode,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("getInternalEquity", request, response, new String[] { countryCode, companyOrgCode});

	}
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/levelgapanalysis", method = RequestMethod.GET)
	public void getLevelGapAnalysis(
			@RequestParam(value = "countryCode", defaultValue = "") String countryCode,
			@RequestParam(value = "companyOrgCode", defaultValue = "") String companyOrgCode,
			@RequestParam(value = "viewType", defaultValue = "") String viewType,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("getLevelGapAnalysis", request, response, new String[] { countryCode, companyOrgCode, viewType});

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/orgdemographic", method = RequestMethod.GET)
	public void getOrgDemographic(
			@RequestParam(value = "countryCode", defaultValue = "") String countryCode,
			@RequestParam(value = "companyOrgCode", defaultValue = "") String companyOrgCode,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("getOrgDemographic", request, response, new String[] { countryCode, companyOrgCode});

	}
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/spansandlayers", method = RequestMethod.GET)
	public void getSpansAndLayers(
			@RequestParam(value = "countryCode", defaultValue = "") String countryCode,
			@RequestParam(value = "companyOrgCode", defaultValue = "") String companyOrgCode,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("getSpansAndLayers", request, response, new String[] { countryCode, companyOrgCode});

	}
	
//compareroles	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/compareroles", method = RequestMethod.GET)
	public void getCompareRoles(
			@RequestParam(value = "countryCode", defaultValue = "") String countryCode,
			@RequestParam(value = "companyOrgCode", defaultValue = "") String companyOrgCode,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("getCompareRoles", request, response, new String[] { countryCode, companyOrgCode});

	}
	@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_INTELLIGENCE_CLOUD')")
	@RequestMapping(value = "/workforcealignment", method = RequestMethod.GET)
	public void getWorkForceAlignment(
			@RequestParam(value = "countryCode", defaultValue = "") String countryCode,
			@RequestParam(value = "companyOrgCode", defaultValue = "") String companyOrgCode,
			@RequestParam(value = "viewType", defaultValue = "") String viewType,
			@RequestParam(value = "filterBy", defaultValue = "") String filterBy,
			@RequestParam(value = "filterValues", defaultValue = "") String filterValues,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("getWorkForceAlignment", request, response, new String[] { countryCode, companyOrgCode, viewType, filterBy, filterValues});

	}
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/workforcepotential", method = RequestMethod.GET)
	public void getWorkForcePotential(
			@RequestParam(value = "countryCode", defaultValue = "") String countryCode,
			@RequestParam(value = "companyOrgCode", defaultValue = "") String companyOrgCode,
			@RequestParam(value = "viewType", defaultValue = "") String viewType,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("getWorkForcePotential", request, response, new String[] { countryCode, companyOrgCode,viewType});

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/performance", method = RequestMethod.GET)
	public void getInsightPerformance(
			@RequestParam(value = "countryCode", defaultValue = "") String countryCode,
			@RequestParam(value = "companyOrgCode", defaultValue = "") String companyOrgCode,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("getInsightPerformance", request, response, new String[] { countryCode, companyOrgCode});

	}
	
	@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_INTELLIGENCE_CLOUD')")
	@RequestMapping(value = "/rolematrix", method = RequestMethod.GET)
	public void getInsightRoleMatrix(
						HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("getInsightRoleMatrix", request, response, new String[] { });
	}
	
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/effectiveness", method = RequestMethod.GET)
	public void getInsightsEffectiveness(
			@RequestParam(value = "familyId", defaultValue = "") String familyId,
			@RequestParam(value = "countryCode", defaultValue = "") String countryCode,
			@RequestParam(value = "companyOrgCode", defaultValue = "") String companyOrgCode,
			@RequestParam(value = "viewType", defaultValue = "") String viewType,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("getInsightsEffectiveness", request, response, new String[] { familyId, countryCode, companyOrgCode, viewType});
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/perceptionofpay", method = RequestMethod.GET)
	public void getInsightsPerceptionOfPay(
			@RequestParam(value = "countryCode", defaultValue = "") String countryCode,
			@RequestParam(value = "companyOrgCode", defaultValue = "") String companyOrgCode,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("getInsightsPerceptionOfPay", request, response, new String[] {countryCode, companyOrgCode});
	}
	
	 /**
		 * @param reportType
		 * @param request
		 * @param response
		 * @author Harsha Moback
		 *
		 */
		
		@PreAuthorize("hasRole('ROLE_USER')")
		@RequestMapping(value = "/reports/config", method = RequestMethod.POST)
		public void getInsightReportsConfig(
				@RequestParam(value = "reportType", defaultValue = "") String reportType,
				HttpServletRequest request,	HttpServletResponse response)
		{
			
			restProxy.stream("getInsightReportsConfig", request, response, new String[] { reportType});

		}

}
