package com.haygroup.leap.hrms.paydatacollection.postprocessing;

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
@RequestMapping(value="/hrms/paydatacollection/postprocessing/maintenance/") 
public class PayDataCollectionPostProcessingMaintenanceController 
{

	private static final Logger logger = LoggerFactory.getLogger(PayDataCollectionPostProcessingMaintenanceController.class);
	
	@Autowired
	private LeapUtil leapUtil;
	@Autowired
	private RestProxy restProxyForUPM;

	

	@PreAuthorize("hasRole('ROLE_USER') and hasRole('ROLE_INTERNAL_USER')")
	@RequestMapping(value = "upm", method = RequestMethod.POST)
	public void maintainUPM(
			HttpServletRequest request,	HttpServletResponse response)
	{

		restProxyForUPM.stream("maintainUPM", request, response, 
		new String[] {});

	}

	@PreAuthorize("hasRole('ROLE_USER') and hasRole('ROLE_INTERNAL_USER')")
	@RequestMapping(value = "metadata", method = RequestMethod.GET)
	public void getPostProcessingMaintenanceMetadata(
			
			HttpServletRequest request,	HttpServletResponse response)
	{

		restProxyForUPM.stream("getPostProcessingMaintenanceMetadata", request, response, 
		new String[] {});

	}
	
	
	
	@PreAuthorize("hasRole('ROLE_USER') and hasRole('ROLE_INTERNAL_USER')")
	@RequestMapping(value = "referencetables", method = RequestMethod.GET)
	public void getPostProcessingReferences(
			@RequestParam(value = "tableName", defaultValue = "") String tableName,
			HttpServletRequest request,	HttpServletResponse response)
	{

		restProxyForUPM.stream("getPostProcessingReferences", request, response, 
		new String[] {tableName});

	}

	
	@PreAuthorize("hasRole('ROLE_USER') and hasRole('ROLE_INTERNAL_USER')")
	@RequestMapping(value = "referencetables", method = RequestMethod.POST)
	public void addModifyReferences(
			
			HttpServletRequest request,	HttpServletResponse response)
	{

		restProxyForUPM.stream("addModifyReferences", request, response, 
		new String[] {});

	}
	
	
	@PreAuthorize("hasRole('ROLE_USER') and hasRole('ROLE_INTERNAL_USER')")
	@RequestMapping(value = "adminprocedurecategories", method = RequestMethod.GET)
	public void getAdminProcedureCategories(
			HttpServletRequest request,	HttpServletResponse response)
	{

		restProxyForUPM.stream("getAdminProcedureCategories", request, response, 
		new String[] {});

	}

	
	@PreAuthorize("hasRole('ROLE_USER') and hasRole('ROLE_INTERNAL_USER')")
	@RequestMapping(value = "adminprocedures", method = RequestMethod.GET)
	public void getAdminProcedures(
			@RequestParam(value = "categoryId", defaultValue = "") String categoryId,
			HttpServletRequest request,	HttpServletResponse response)
	{

		restProxyForUPM.stream("getAdminProcedures", request, response, 
		new String[] {categoryId});

	}
	
	
	@PreAuthorize("hasRole('ROLE_USER') and hasRole('ROLE_INTERNAL_USER')")
	@RequestMapping(value = "adminprocedures/parameters", method = RequestMethod.GET)
	public void getAdminProcedureParameters(
			@RequestParam(value = "adminProcedureId", defaultValue = "") String adminProcedureId,
			HttpServletRequest request,	HttpServletResponse response)
	{

		restProxyForUPM.stream("getAdminProcedureParameters", request, response, 
		new String[] {adminProcedureId});

	}

	@PreAuthorize("hasRole('ROLE_USER') and hasRole('ROLE_INTERNAL_USER')")
	@RequestMapping(value = "adminprocedures/parametervalues", method = RequestMethod.GET)
	public void getAdminProcedureParameterValues(
			@RequestParam(value = "adminProcedureId", defaultValue = "") String adminProcedureId,
			@RequestParam(value = "parameterName", defaultValue = "") String parameterName,
			@RequestParam(value = "inputValues", defaultValue = "") String inputValues,

			HttpServletRequest request,	HttpServletResponse response)
	{

		restProxyForUPM.stream("getAdminProcedureParameterValues", request, response, 
		new String[] {adminProcedureId,parameterName,inputValues});

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "companydemographics", method = RequestMethod.GET)
	public void getCompanyDemographics(
			@RequestParam(value = "countryCode", defaultValue = "") String countryCode,
			@RequestParam(value = "searchColumn", defaultValue = "") String searchColumn,
			@RequestParam(value = "searchString", defaultValue = "") String searchString,
			@RequestParam(value = "sortColumn", defaultValue = "") String sortColumn,
			@RequestParam(value = "sortBy", defaultValue = "") String sortBy,
			@RequestParam(value = "filterBy", defaultValue = "") String filterBy,
			@RequestParam(value = "filterValues", defaultValue = "") String filterValues,
			@RequestParam(value = "pageIndex", defaultValue = "") String pageIndex,
			@RequestParam(value = "pageSize", defaultValue = "") String pageSize,
			HttpServletRequest request, HttpServletResponse response) {
		restProxyForUPM.stream("getCompanyDemographics", request, response, new String[] {countryCode, searchColumn, searchString, sortColumn,sortBy, filterBy, filterValues, pageIndex, pageSize});

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "companydemographics/{id}", method = RequestMethod.GET)
	public void getCompanyDemographicsByOrgCode(
			@PathVariable String id, 
			@RequestParam(value = "countryCode", defaultValue = "") String countryCode,
			HttpServletRequest request, HttpServletResponse response) {

		restProxyForUPM.stream("getCompanyDemographicsByOrgCode", request, response, new String[] {id, countryCode});

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "companydemographics", method = RequestMethod.POST)
	public void addCompanyDemographics(
			HttpServletRequest request, HttpServletResponse response) {

		restProxyForUPM.stream("addCompanyDemographics", request, response, new String[] {});

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "companydemographics", method = RequestMethod.PUT)
	public void updateCompanyDemographics(
			HttpServletRequest request, HttpServletResponse response) {

		restProxyForUPM.stream("updateCompanyDemographics", request, response, new String[] {});

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "companydemographics/{id}", method = RequestMethod.DELETE)
	public void deleteCompanyDemographics(@PathVariable String id,
			@RequestParam(value = "countryCode", defaultValue = "") String countryCode,
			HttpServletRequest request, HttpServletResponse response) {

		restProxyForUPM.stream("deleteCompanyDemographics", request, response, new String[] {id, countryCode});

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "companydemographics/companymetadata", method = RequestMethod.GET)
	public void getCompanyMetaData(
			@RequestParam(value = "countryCode", defaultValue = "") String countryCode,
			@RequestParam(value = "module", defaultValue = "") String module,
			HttpServletRequest request, HttpServletResponse response) {

		restProxyForUPM.stream("getCompanyMetaData", request, response, new String[] {countryCode, module});

	}
}
