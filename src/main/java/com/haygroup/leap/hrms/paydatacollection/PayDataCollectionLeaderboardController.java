package com.haygroup.leap.hrms.paydatacollection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.haygroup.leap.client.RestProxy;
import com.haygroup.leap.common.LeapUtil;

@Controller
@RequestMapping(value="/hrms/paydatacollection/companies") 
public class PayDataCollectionLeaderboardController 
{

	private static final Logger logger = LoggerFactory.getLogger(PayDataCollectionActionsController.class);
	
	@Autowired
	private LeapUtil leapUtil;
	@Autowired
	private RestProxy restProxy;

	/**
	 * @param year
	 * @param clientId
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/leaderboard", method = RequestMethod.GET)
	public void getCompanyLeaderboard(
			@RequestParam(value = "year", defaultValue = "") String year,
			@RequestParam(value = "pdcClientId", defaultValue = "") String pdcClientId,
			@RequestParam(value = "module", defaultValue = "") String module,
			@RequestParam(value = "mock", defaultValue = "") String mock,
			HttpServletRequest request, HttpServletResponse response) {

		restProxy.stream("getCompanyLeaderboard", request, response, new String[] {year,pdcClientId,module,mock});

	}
	
	
	/**
	 * @param year
	 * @param clientId
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/leaderboard", method = RequestMethod.POST)
	public void addCompanyLeaderboard(
			HttpServletRequest request, HttpServletResponse response) {

		restProxy.stream("addCompanyLeaderboard", request, response, new String[] {});

	}	
	
	/**
	 * @param year
	 * @param clientId
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/leaderboard", method = RequestMethod.PUT)
	public void modifyCompanyLeaderboard(
			HttpServletRequest request, HttpServletResponse response) {

		restProxy.stream("modifyCompanyLeaderboard", request, response, new String[] {});

	}	
	
	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/reset", method = RequestMethod.POST)
	public void resetPaydataCollectionCompany(
			HttpServletRequest request, HttpServletResponse response) {

		restProxy.stream("resetPaydataCollectionCompany", request, response, new String[] {});

	}	

	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_INTELLIGENCE_CLOUD')")
	@RequestMapping(value = "/organizationcodes", method = RequestMethod.GET) 
	public void getCompanyOrgCodes(
			@RequestParam(value = "countries", defaultValue = "") String countries,
			@RequestParam(value = "preferredClientId", defaultValue = "") String preferredClientId,
			@RequestParam(value = "orgCodesFilter", defaultValue = "") String orgCodesFilter,
			HttpServletRequest request, HttpServletResponse response) {

		restProxy.stream("getCompanyOrgCodes", request, response, new String[] {countries,preferredClientId,orgCodesFilter});

	}

	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_INTELLIGENCE_CLOUD')")
	@RequestMapping(value = "/organizationcodes", method = RequestMethod.POST)
	public void postCompanyOrgCodes(
			
			HttpServletRequest request, HttpServletResponse response) {

		restProxy.stream("postCompanyOrgCodes", request, response, new String[] {});

	}
	


	
	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/resetleaderboard", method = RequestMethod.POST)
	public void resetLeaderboard(HttpServletRequest request, HttpServletResponse response) {
		restProxy.stream("resetleaderboard", request, response, new String[] {});

	}
	
	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/getcountriesclients", method = RequestMethod.GET)
	public void getPayCountriesClients(HttpServletRequest request, HttpServletResponse response) {
		restProxy.stream("getPayCountriesClients", request, response, new String[] {});
	}
	
	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/getclientsinfo", method = RequestMethod.POST)
	public void getPayClientsInfo(HttpServletRequest request, HttpServletResponse response) {
		restProxy.stream("getPayClientsInfo", request, response, new String[] {});
	}
	
	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/cleanup", method = RequestMethod.POST)
	public void cleanUpLeaderBoard(
			HttpServletRequest request, HttpServletResponse response) {

		restProxy.stream("cleanUpLeaderboard", request, response, new String[] {});

	}
	/**
	 * @param request/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public void cleanUpLeaderBoard(
			@RequestParam(value = "pdcClientId", defaultValue = "") String pdcClientId,
			HttpServletRequest request, HttpServletResponse response) {

		restProxy.stream("getAllLeaderboard", request, response, new String[] {pdcClientId});

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/invalidsubmissions", method = RequestMethod.POST)
	public void postInvalidSubmissions(
			HttpServletRequest request, HttpServletResponse response) {

		restProxy.stream("postInvalidSubmissions", request, response, new String[] {});

	}
	
}
