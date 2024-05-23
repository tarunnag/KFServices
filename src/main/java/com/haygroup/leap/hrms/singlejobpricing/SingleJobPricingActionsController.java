package com.haygroup.leap.hrms.singlejobpricing;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.haygroup.leap.client.RestProxy;

@Controller
@RequestMapping(value="/hrms/sjp/actions") 
public class SingleJobPricingActionsController {
		
	@Autowired
	@Qualifier("restProxy")
	private RestProxy restProxy;
	

	/**
	 * @Param module
	 * @param countryCode
	 * @param peerGroupId
	 * @param familyId
	 * @param subFamilyId
	 * @param gradeId
	 * @param gradeSetId
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/criteria", method = RequestMethod.GET)
	public void getCriteria(
			@RequestParam(value = "module", defaultValue = "") String module,
			@RequestParam(value = "countryCode", defaultValue = "") String countryCode,
			@RequestParam(value = "peerGroupId", defaultValue = "") String peerGroupId,
			@RequestParam(value = "familyId", defaultValue = "") String familyId,
			@RequestParam(value = "subFamilyId", defaultValue = "") String subFamilyId,
			@RequestParam(value = "gradeId", defaultValue = "") String gradeId,
			@RequestParam(value = "gradeSetId", defaultValue = "") String gradeSetId,
			@RequestParam(value = "marketJobId", defaultValue = "") String marketJobId,
		
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("getMarketCriteria", request, response, new String[] { module, countryCode,peerGroupId,familyId,subFamilyId, gradeId, gradeSetId,marketJobId});


    }
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/companycount", method = RequestMethod.POST)
	public void postCompanyCount(
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("postCompanyCount", request, response, new String[] {});


    }
	
	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/participant", method = RequestMethod.POST)
	public void postSJPResult(
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("postSJPResult", request, response, new String[] {});


    }
	
	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/report", method = RequestMethod.POST)
	public void postParticipant(
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("postParticipant", request, response, new String[] {});


	}
	

	/**
	 * @Param module
	 * @param familyId
	 * @param subFamilyId
	 * @param gradeId
	 * @param gradeSetId
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/usercountries", method = RequestMethod.GET)
	public void getUserCountriesForSJPR(
			@RequestParam(value = "module", defaultValue = "") String module,
			@RequestParam(value = "familyId", defaultValue = "") String familyId,
			@RequestParam(value = "subFamilyId", defaultValue = "") String subFamilyId,
			@RequestParam(value = "gradeId", defaultValue = "") String gradeId,
			@RequestParam(value = "gradeSetId", defaultValue = "") String gradeSetId,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("getUserCountriesForSJPR", request, response, new String[] { module,familyId,subFamilyId, gradeId, gradeSetId});


    }

}
