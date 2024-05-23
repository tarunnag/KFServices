package com.haygroup.leap.hrms.architect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.haygroup.leap.client.RestProxy;
import com.haygroup.leap.client.RestProxyImpl;

import org.springframework.web.client.RestTemplate;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.SSLContext;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;



@Controller
@RequestMapping(value="/hrms/architect") 
public class ArchitectController {
	

//	@Autowired
//	@Qualifier("architectRestProxy")
//	private RestProxy architectRestProxy;

	
	@Autowired
	@Qualifier("restProxyForNodeAPI")
	private RestProxy restProxyForNodeAPI;
	
	private static final Logger logger = LoggerFactory.getLogger(ArchitectController.class);	
	

	/**
	 * @Param outputType
	 * @param type
	 * @param sortColumn
	 * @param sortBy
	 * @param searchString
	 * @param searchColumn
	 * @param filterBy
	 * @param filterValues
	 * @param pageIndex
	 * @param pageSize
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/jobs", method = RequestMethod.GET)
	public void getArchitectJobs(
			@RequestParam(value = "outputType", defaultValue = "") String outputType,
			@RequestParam(value = "type", defaultValue = "") String type,
			@RequestParam(value = "sortColumn", defaultValue = "") String sortColumn,
			@RequestParam(value = "sortBy", defaultValue = "") String sortBy,
			@RequestParam(value = "searchString", defaultValue = "") String searchString,
			@RequestParam(value = "searchColumn", defaultValue = "") String searchColumn,
			@RequestParam(value = "filterBy", defaultValue = "") String filterBy,
			@RequestParam(value = "filterValues", defaultValue = "") String filterValues,
			@RequestParam(value = "pageIndex", defaultValue = "") String pageIndex,
			@RequestParam(value = "pageSize", defaultValue = "") String pageSize,
			@RequestParam(value = "clientId", defaultValue = "") String clientId,
			@RequestParam(value = "wcToggle", defaultValue = "") String wcToggle,
			@RequestParam(value = "lcid", defaultValue = "") String lcid,

			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxyForNodeAPI.secureStream("getArchitectJobs", request, response, new String[] { outputType, type,sortColumn,sortBy,searchString, searchColumn,filterBy, filterValues, pageIndex,pageSize,clientId, wcToggle,lcid});

	}
	
	

	

	/**
	 * @param jobId
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/jobs/{jobId}", method = RequestMethod.GET)
	public void getJobs(
			@PathVariable String jobId,
			@RequestParam(value = "outputType", defaultValue = "") String outputType,
			@RequestParam(value = "reportType", defaultValue = "") String reportType,
			@RequestParam(value = "clientId", defaultValue = "") String clientId,
			@RequestParam(value = "wcToggle", defaultValue = "") String wcToggle,
			HttpServletRequest request,	HttpServletResponse response)
	{
	
		restProxyForNodeAPI.secureStream("getArchitectJobsForId", request, response, new String[] { jobId,outputType, reportType, clientId, wcToggle});

	}
	
	/**
	 * @param jobId
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/jobs/{jobId}", method = RequestMethod.DELETE)
	public void deleteJob(
			@PathVariable String jobId,
			HttpServletRequest request,	HttpServletResponse response)
	{
	
		restProxyForNodeAPI.secureStream("deleteArchitectJobsForId", request, response, new String[] { jobId });

	}
	
	/**
	 * @param jobId
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/jobs/{jobId}", method = RequestMethod.PUT)
	public void updateJob(
			@PathVariable String jobId,
			HttpServletRequest request,	HttpServletResponse response)
	{
	
		restProxyForNodeAPI.secureStream("UpdateArchitectJobsForId", request, response, new String[] {jobId});

	}
	
	
	/**
	 * @param jobId
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/jobs", method = RequestMethod.POST)
	public void addJob(HttpServletRequest request,	HttpServletResponse response)
	{
	
		restProxyForNodeAPI.secureStream("postArchitectJobs", request, response, new String[] {});
	}
	
	/**
	 * @param type
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/jobs/summary", method = RequestMethod.GET)
	public void getJobSummary(
			@RequestParam(value = "type", defaultValue = "") String type,
			HttpServletRequest request,	HttpServletResponse response)
	{
	
		restProxyForNodeAPI.secureStream("getJobSummary", request, response, new String[] {type});
	}
	
	/**
	 * @param type
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/jobs/jobevaluation/{jobId}", method = RequestMethod.GET)
	public void getJobEvaluation(
			@PathVariable String jobId,
			HttpServletRequest request,	HttpServletResponse response)
	{
	
		restProxyForNodeAPI.secureStream("getJobEvaluation", request, response, new String[] {jobId});
	}
	
	/**
	 * @param type
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/jobs/properties", method = RequestMethod.GET)
	public void getJobProperties(
			@RequestParam(value = "clientId", defaultValue = "") String clientId,
			HttpServletRequest request,	HttpServletResponse response)
	{
	
		restProxyForNodeAPI.secureStream("getJobProperties", request, response, new String[] {clientId});
	}
	
	/**
	 * @param type
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/customgrades", method = RequestMethod.GET)
	public void getKFACustomGrades(
			@RequestParam(value = "type", defaultValue = "") String type,
			@RequestParam(value = "clientId", defaultValue = "") String clientId,
			HttpServletRequest request,	HttpServletResponse response
			)
	{
	
		restProxyForNodeAPI.secureStream("getKFACustomGrades", request, response, new String[] {type, clientId});
	}
	
	/**
	 * @param type
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/jobsforcompare", method = RequestMethod.GET)
	public void getJobsForCompare(
			@RequestParam(value = "jobIds", defaultValue = "") String jobIds,
			@RequestParam(value = "wcToggle", defaultValue = "") String wcToggle,
			HttpServletRequest request,	HttpServletResponse response)
	{
		restProxyForNodeAPI.secureStream("getJobsForCompare", request, response, new String[] {jobIds,wcToggle});
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/currencymagnitude", method = RequestMethod.GET)
	public void getCurrencyMagnitude(
			HttpServletRequest request,	HttpServletResponse response)
	{
	
		restProxyForNodeAPI.secureStream("getCurrencyMagnitude", request, response, new String[] {});
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/pdf2doc", method = RequestMethod.POST)
	public void postPDFtoDOC(
			HttpServletRequest request,	HttpServletResponse response)
	{
	
		restProxyForNodeAPI.secureStream("postPDFtoDOC", request, response, new String[] {});
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/jobs/statusoptions", method = RequestMethod.GET)
	public void getStatusOptions(HttpServletRequest request,	HttpServletResponse response)
	{
	
		restProxyForNodeAPI.secureStream("getStatusOptions", request, response, new String[] {});
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/jobs/preferences", method = RequestMethod.GET)
	public void getMatrixExportPreferences(HttpServletRequest request,	HttpServletResponse response)
	{
	
		restProxyForNodeAPI.secureStream("getMatrixExportPreferences", request, response, new String[] {});
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/jobs/preferences", method = RequestMethod.PUT)
	public void saveMatrixExportPreferences(HttpServletRequest request,	HttpServletResponse response)
	{
	
		restProxyForNodeAPI.secureStream("saveMatrixExportPreferences", request, response, new String[] {});
	}
/*	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/jobs/matrixproperties", method = RequestMethod.GET)
	public void getMatrixProperties(HttpServletRequest request,	HttpServletResponse response)
	{
	
		restProxyForNodeAPI.secureStream("getMatrixProperties", request, response, new String[] {});
	}*/
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/pdfmodelstate", method = RequestMethod.GET)
	public void getPdfModelState(HttpServletRequest request,	HttpServletResponse response)
	{
		restProxyForNodeAPI.secureStream("getPdfModelState", request, response, new String[] {});
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/pdfmodelstate", method = RequestMethod.PUT)
	public void addPdfModelState(HttpServletRequest request,	HttpServletResponse response)
	{
		restProxyForNodeAPI.secureStream("addPdfModelState", request, response, new String[] {});
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/jobs/summary/preferences", method = RequestMethod.PUT)
	public void saveJobSummaryPreferences(HttpServletRequest request,	HttpServletResponse response)
	{
		restProxyForNodeAPI.secureStream("saveJobSummaryPreferences", request, response, new String[] {});
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/jobs/summary/preferences", method = RequestMethod.GET)
	public void getJobSummaryPreferences(HttpServletRequest request,	HttpServletResponse response)
	{
		restProxyForNodeAPI.secureStream("getJobSummaryPreferences", request, response, new String[] {});
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/permissions", method = RequestMethod.GET)
	public void getArchitectPermissions(HttpServletRequest request,	HttpServletResponse response)
	{
		restProxyForNodeAPI.secureStream("getArchitectPermissions", request, response, new String[] {});
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/jobs/userproperties", method = RequestMethod.GET)
	public void getUserJobProperties(
			@RequestParam(value = "clientId", defaultValue = "") String clientId,
			HttpServletRequest request,	HttpServletResponse response)
	{
	
		restProxyForNodeAPI.secureStream("getUserJobProperties", request, response, new String[] {clientId});
	}
	
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/families", method = RequestMethod.GET)
	public void getArchitectFamilies(
			HttpServletRequest request,	HttpServletResponse response)
	{
	
		restProxyForNodeAPI.secureStream("getArchitectFamilies", request, response, new String[] {});
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/jobs/profilelevelmappings", method = RequestMethod.GET)
	public void getArchitectProfileLevelMappings(
			HttpServletRequest request,	HttpServletResponse response)
	{
	
		restProxyForNodeAPI.secureStream("getArchitectProfileLevelMappings", request, response, new String[] {});
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/export/ondemand/{reportType}/{action}", method = RequestMethod.POST)
	public void postArchitectExportOndemand(
			@PathVariable String reportType,
			@PathVariable String action,
			HttpServletRequest request,	HttpServletResponse response)
	{
	
		restProxyForNodeAPI.secureStream("postArchitectExportOndemand", request, response, new String[] {reportType, action});
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/auditlog", method = RequestMethod.POST)
	public void saveAuditLogs(
			HttpServletRequest request,	HttpServletResponse response)
	{
	
		restProxyForNodeAPI.secureStream("saveAuditLogs", request, response, new String[] {});
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/jobs/deletebyclientjobids", method = RequestMethod.POST)
	public void deleteByClientJobIds(HttpServletRequest request,	HttpServletResponse response,
			@RequestParam(value = "clientId", defaultValue = "") String clientId,
			@RequestParam(value = "userId", defaultValue = "") String userId
			)
	{
	
		restProxyForNodeAPI.secureStream("deleteByClientJobIds", request, response, new String[] {clientId,userId});
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/jobs/createoperationidbyjobsids", method = RequestMethod.POST)
	public void createOperationIdByJobsIds(HttpServletRequest request,	HttpServletResponse response)
	{
	
		restProxyForNodeAPI.secureStream("createOperationIdByJobsIds", request, response, new String[] {});
	}
	
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/jobs/byoperationid", method = RequestMethod.GET)
	public void getJobsByOperationId(HttpServletRequest request,	HttpServletResponse response,
			@RequestParam(value = "outputType", defaultValue = "") String outputType,
			@RequestParam(value = "type", defaultValue = "") String type,
			@RequestParam(value = "sortColumn", defaultValue = "") String sortColumn,
			@RequestParam(value = "sortBy", defaultValue = "") String sortBy,
			@RequestParam(value = "searchString", defaultValue = "") String searchString,
			@RequestParam(value = "searchColumn", defaultValue = "") String searchColumn,
			@RequestParam(value = "filterBy", defaultValue = "") String filterBy,
			@RequestParam(value = "filterValues", defaultValue = "") String filterValues,
			@RequestParam(value = "pageIndex", defaultValue = "") String pageIndex,
			@RequestParam(value = "pageSize", defaultValue = "") String pageSize,
			@RequestParam(value = "clientId", defaultValue = "") String clientId,
			@RequestParam(value = "wcToggle", defaultValue = "") String wcToggle,
			@RequestParam(value = "operationId", defaultValue = "") String operationId)
	{
	
		restProxyForNodeAPI.secureStream("getJobsByOperationId", request, response, new String[] {outputType,type,sortColumn,sortBy,searchString,searchColumn,filterBy,filterValues,pageIndex,pageSize,clientId,wcToggle,operationId});
	}
	
	/*
	 * GET method changed to POST
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/jobs/jescoresbyjobsids", method = RequestMethod.POST)
	public void insertJEScoresByJobIds(HttpServletRequest request,	HttpServletResponse response,
			@RequestParam(value = "outputType", defaultValue = "") String outputType)
	{
	
		restProxyForNodeAPI.secureStream("insertJEScoresByJobIds", request, response, new String[] {outputType});
	}
	
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/jobs/getjobidsbyoperationid", method = RequestMethod.GET)
	public void getJobIdsByOperationId(
			@RequestParam(value = "outputType", defaultValue = "") String outputType,
			@RequestParam(value = "type", defaultValue = "") String type,
			@RequestParam(value = "sortColumn", defaultValue = "") String sortColumn,
			@RequestParam(value = "sortBy", defaultValue = "") String sortBy,
			@RequestParam(value = "searchString", defaultValue = "") String searchString,
			@RequestParam(value = "searchColumn", defaultValue = "") String searchColumn,
			@RequestParam(value = "filterBy", defaultValue = "") String filterBy,
			@RequestParam(value = "filterValues", defaultValue = "") String filterValues,
			@RequestParam(value = "pageIndex", defaultValue = "") String pageIndex,
			@RequestParam(value = "pageSize", defaultValue = "") String pageSize,
			@RequestParam(value = "clientId", defaultValue = "") String clientId,
			@RequestParam(value = "wcToggle", defaultValue = "") String wcToggle,
			@RequestParam(value = "operationId", defaultValue = "") String operationId,
			HttpServletRequest request,	HttpServletResponse response)
	{
	
		restProxyForNodeAPI.secureStream("getJobIdsByOperationId", request, response, new String[] {outputType,type,sortColumn,sortBy,searchString,searchColumn,filterBy,filterValues,pageIndex,pageSize,clientId,wcToggle,operationId});
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/jobs/jebulkupdategrades", method = RequestMethod.POST)
	public void JEBulkUpdateGrades(HttpServletRequest request,	HttpServletResponse response,
			@RequestParam(value = "clientId", defaultValue = "") String clientId,
			@RequestParam(value = "userId", defaultValue = "") String userId
			)
	{
	
		restProxyForNodeAPI.secureStream("JEBulkUpdateGrades", request, response, new String[] {clientId,userId});
	}
	
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/jobs/savejescores", method = RequestMethod.POST)
	public void saveJEScores(HttpServletRequest request,	HttpServletResponse response)
	{
	
		restProxyForNodeAPI.secureStream("saveJEScores", request, response, new String[] {});
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/jobs/deletebyoperationclientjobid", method = RequestMethod.POST)
	public void deleteByOperationClientJobId(HttpServletRequest request,	HttpServletResponse response)
	{
	
		restProxyForNodeAPI.secureStream("deleteByOperationClientJobId", request, response, new String[] {});
	}
	
}
