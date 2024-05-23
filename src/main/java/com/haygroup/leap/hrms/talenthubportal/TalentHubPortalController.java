package com.haygroup.leap.hrms.talenthubportal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.ResponseEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
@RequestMapping(value="/hrms/thportal") 
public class TalentHubPortalController {

	@Autowired
	@Qualifier("restProxyForTHPortal")
	private RestProxy restProxyForTHPortal;
	
private static final Logger logger = LoggerFactory.getLogger(TalentHubPortalController.class);

	/**
	 * @param pageIndex
	 * @param pageSize
	 * @param searchString
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER') and hasRole('ROLE_INTERNAL_USER')")
	@RequestMapping(value = "admin/clients", method = RequestMethod.GET)
	public void searchClients(
			@RequestParam(value = "pageIndex", defaultValue = "") String pageIndex,
			@RequestParam(value = "pageSize", defaultValue = "") String pageSize,
			@RequestParam(value = "searchString", defaultValue = "") String searchString,
			HttpServletRequest request,	HttpServletResponse response)
	{
	
		restProxyForTHPortal.secureStream("searchTHPortalClients", request, response, new String[] {pageIndex, pageSize, searchString});
	}
	
	/**
	 * @param fileUUID
	 * @param status
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER') and hasRole('ROLE_INTERNAL_USER')")
	@RequestMapping(value = "admin/downloadurl", method = RequestMethod.GET)
	public void getDownloadPreSignedURL(
			@RequestParam(value = "fileUUID", defaultValue = "") String fileUUID,
			@RequestParam(value = "status", defaultValue = "") String status,
			HttpServletRequest request,	HttpServletResponse response)
	{
	
		restProxyForTHPortal.secureStream("getTHPortalDownloadPreSignedURL", request, response, new String[] {fileUUID, status});
	}
	
	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER') and hasRole('ROLE_INTERNAL_USER')")
	@RequestMapping(value = "admin/uploadurl", method = RequestMethod.GET)
	public void getUploadPreSignedURL(
			@RequestParam(value = "uploadClientId", defaultValue = "") String uploadClientId,
			HttpServletRequest request,	HttpServletResponse response)
	{
	
		restProxyForTHPortal.secureStream("getTHPortalUploadPreSignedURL", request, response, new String[] {uploadClientId});
	}
	
	/**
	 * @param fileUUID
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER') and hasRole('ROLE_INTERNAL_USER')")
	@RequestMapping(value = "admin/uploadstatus", method = RequestMethod.GET)
	public void getFileUploadStatus(
			@RequestParam(value = "fileUUID", defaultValue = "") String fileUUID,
			HttpServletRequest request,	HttpServletResponse response)
	{
	
		restProxyForTHPortal.secureStream("getTHPortalFileUploadStatus", request, response, new String[] {fileUUID});
	}
	
	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER') and hasRole('ROLE_INTERNAL_USER')")
	@RequestMapping(value = "admin/uploadstatus", method = RequestMethod.PUT)
	public void getUploadStatus(
			HttpServletRequest request,	HttpServletResponse response)
	{
	
		restProxyForTHPortal.secureStream("getTHPortalUploadStatus", request, response, new String[] {});
	}
	
	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER') and hasRole('ROLE_INTERNAL_USER')")
	@RequestMapping(value = "admin/uploaddata", method = RequestMethod.POST)
	public void uploadData(
			HttpServletRequest request,	HttpServletResponse response)
	{
	
		restProxyForTHPortal.secureStream("uploadData", request, response, new String[] {});
	}
	
	/**
	 * @param fileUUID
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER') and hasRole('ROLE_INTERNAL_USER')")
	@RequestMapping(value = "admin/pushdata", method = RequestMethod.POST)
	public void pushData(
			@RequestParam(value = "fileUUID", defaultValue = "") String fileUUID,
			HttpServletRequest request,	HttpServletResponse response)
	{
	
		restProxyForTHPortal.secureStream("pushData", request, response, new String[] {fileUUID});
	}
	
	/**
	 * @param fileUUID
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER') and hasRole('ROLE_INTERNAL_USER')")
	@RequestMapping(value = "admin/data", method = RequestMethod.GET)
	public void fetchData(
			@RequestParam(value = "fileUUID", defaultValue = "") String fileUUID,
			HttpServletRequest request,	HttpServletResponse response)
	{
	
		restProxyForTHPortal.secureStream("fetchData", request, response, new String[] {fileUUID});
	}
	
	/**
	 * @param fileUUID
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER') and hasRole('ROLE_INTERNAL_USER')")
	@RequestMapping(value = "admin/successprofiles", method = RequestMethod.GET)
	public void searchProfiles(
			@RequestParam(value = "type", defaultValue = "") String type,
			@RequestParam(value = "searchColumn", defaultValue = "") String searchColumn,
			@RequestParam(value = "searchString", defaultValue = "") String searchString,
			@RequestParam(value = "sortColumn", defaultValue = "") String sortColumn,
			@RequestParam(value = "sortBy", defaultValue = "") String sortBy,
			@RequestParam(value = "filterBy", defaultValue = "") String filterBy,
			@RequestParam(value = "filterValues", defaultValue = "") String filterValues,
			@RequestParam(value = "pageIndex", defaultValue = "") String pageIndex,
			@RequestParam(value = "pageSize", defaultValue = "") String pageSize,
			@RequestParam(value = "requestClient", defaultValue = "") String requestClient,
			HttpServletRequest request,	HttpServletResponse response)
	{
	
		restProxyForTHPortal.secureStream("searchProfiles", request, response, new String[] {type, searchColumn, searchString, sortColumn, sortBy, filterBy, filterValues, pageIndex, pageSize, requestClient });
	}
	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER') and hasRole('ROLE_INTERNAL_USER')")
	@RequestMapping(value = "admin/exportprofiles", method = RequestMethod.POST)
	public void postAdminExportprofiles(
			HttpServletRequest request,	HttpServletResponse response)
	{
		restProxyForTHPortal.secureStream("postAdminExportprofiles", request, response, new String[] {});
	}	
	
	/**
	 * @author Harsha
	 */
	
	//Insert data to staging table
	@PreAuthorize("hasRole('ROLE_USER') and hasRole('ROLE_INTERNAL_USER')")
	@RequestMapping(value = "admin/jobs/updatedata", method = RequestMethod.POST)
	public void adminUpdateJobsData(
			@RequestParam(value = "fileKey", defaultValue = "") String fileKey,
			HttpServletRequest request,	HttpServletResponse response)
	{
		restProxyForTHPortal.secureStream("adminUpdateJobsData", request, response, new String[] {fileKey});
	}
	
	//Get status by UUID
	@PreAuthorize("hasRole('ROLE_USER') and hasRole('ROLE_INTERNAL_USER')")
	@RequestMapping(value = "admin/jobs/updatestatus", method = RequestMethod.GET)
	public void getAdminUpdateJobsStatus(
			@RequestParam(value = "processStatus", defaultValue = "") String processStatus,
			@RequestParam(value = "fileUUID", defaultValue = "") String fileUUID,
			HttpServletRequest request,	HttpServletResponse response)
	{
		restProxyForTHPortal.secureStream("getAdminUpdateJobsStatus", request, response, new String[] {processStatus,fileUUID});
	}
	
	//Insert status to ProfileUploadFromEmpPayDataStatus
	@PreAuthorize("hasRole('ROLE_USER') and hasRole('ROLE_INTERNAL_USER')")
	@RequestMapping(value = "admin/jobs/updatestatus", method = RequestMethod.PUT)
	public void putAdminUpdateJobsStatus(
			@RequestParam(value = "processStatus", defaultValue = "") String processStatus,
			@RequestParam(value = "fileUUID", defaultValue = "") String fileUUID,
			HttpServletRequest request,	HttpServletResponse response)
	{
		restProxyForTHPortal.secureStream("putAdminUpdateJobsStatus", request, response, new String[] {processStatus,fileUUID});
	}
	
	//Get Signed Url For Upload
	@PreAuthorize("hasRole('ROLE_USER') and hasRole('ROLE_INTERNAL_USER')")
	@RequestMapping(value = "admin/jobs/uploadurl", method = RequestMethod.GET)
	public void getAdminUploadJobsUrl (
			@RequestParam(value = "clientId", defaultValue = "") String clientId,
			HttpServletRequest request,	HttpServletResponse response)
	{
		restProxyForTHPortal.secureStream("getAdminUploadJobsUrl", request, response, new String[] {clientId});
	}
	
	//Get Signed Url For Download 
	@PreAuthorize("hasRole('ROLE_USER') and hasRole('ROLE_INTERNAL_USER')")
	@RequestMapping(value = "admin/jobs/downloadurl", method = RequestMethod.GET)
	public void getAdminDownloadJobsUrl  (
			@RequestParam(value = "fileUUID", defaultValue = "") String fileUUID,
			@RequestParam(value = "status", defaultValue = "") String status,
			@RequestParam(value = "clientId", defaultValue = "") String clientId,
			HttpServletRequest request,	HttpServletResponse response)
	{
		restProxyForTHPortal.secureStream("getAdminDownloadJobsUrl", request, response, new String[] {fileUUID, status, clientId});
	}
	
	//Move data from staging table to ClientJob table
	@PreAuthorize("hasRole('ROLE_USER') and hasRole('ROLE_INTERNAL_USER')")
	@RequestMapping(value = "admin/jobs/pushData", method = RequestMethod.POST)
	public void adminPushJobsData  (
			@RequestParam(value = "fileUUID", defaultValue = "") String fileUUID,
			@RequestParam(value = "uploadClientId", defaultValue = "") String uploadClientId,
			HttpServletRequest request,	HttpServletResponse response)
	{
		restProxyForTHPortal.secureStream("adminPushJobsData", request, response, new String[] {fileUUID, uploadClientId});
	}
	
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "admin/languages", method = RequestMethod.GET)
	public void getLanguages  (
			HttpServletRequest request,	HttpServletResponse response)
	{
		restProxyForTHPortal.secureStream("getLanguages", request, response, new String[] {});
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "admin/translations", method = RequestMethod.GET)
	public void getTranslations  (
			@RequestParam(value = "categoryId", defaultValue = "") String categoryId,
			@RequestParam(value = "languageId", defaultValue = "") String languageId,
			@RequestParam(value = "cmsLanguageId", defaultValue = "") String cmsLanguageId,
			HttpServletRequest request,	HttpServletResponse response)
	{
		restProxyForTHPortal.secureStream("getTranslations", request, response, new String[] {categoryId, languageId, cmsLanguageId});
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "admin/translations", method = RequestMethod.POST)
	public void setTranslations  (
			HttpServletRequest request,	HttpServletResponse response)
	{
		restProxyForTHPortal.secureStream("setTranslations", request, response, new String[] {});
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "admin/jobs/validateJobs", method = RequestMethod.POST)
	public void validateJobTitles  (
			HttpServletRequest request,	HttpServletResponse response)
	{
		restProxyForTHPortal.secureStream("validateJobTitles", request, response, new String[] {});
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "admin/jobs/kfajobsexport", method = RequestMethod.POST)
	public void startKfaJobsExport  (
			@RequestParam(value = "clientId", defaultValue = "") String clientId,
			HttpServletRequest request,	HttpServletResponse response)
	{
		restProxyForTHPortal.secureStream("startKfaJobsExport", request, response, new String[] { clientId});
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "admin/jobs/kfajobsexport", method = RequestMethod.PUT)
	public void kfajobsexport  (
			HttpServletRequest request,	HttpServletResponse response)
	{
		restProxyForTHPortal.secureStream("updateKfaJobTaskExportStatus", request, response, new String[] {});
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "admin/jobs/kfajobsexport", method = RequestMethod.GET)
	public void getKfaJobsExport  (
			@RequestParam(value = "fileUUID", defaultValue = "") String fileUUID,
			@RequestParam(value = "clientId", defaultValue = "") String clientId,
			HttpServletRequest request,	HttpServletResponse response)
	{
		restProxyForTHPortal.secureStream("getKfaJobsExport", request, response, new String[] {fileUUID, clientId});
	}
	
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "admin/jobs/validateJobCodes", method = RequestMethod.POST)
	public void validateJobCodes  (
			HttpServletRequest request,	HttpServletResponse response)
	{
		restProxyForTHPortal.secureStream("validateJobCodes", request, response, new String[] {});
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "admin/jobs/clientJobsInfo", method = RequestMethod.GET)
	public void clientJobsInfo  (
			HttpServletRequest request,	HttpServletResponse response,
			@RequestParam(value = "searchClientId", defaultValue = "") String searchClientId
			)
	{
		restProxyForTHPortal.secureStream("clientJobsInfo", request, response, new String[] { searchClientId});
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/admin/banners", method = RequestMethod.GET)
	public void getTHPortalAdminBanners  (
			HttpServletRequest request,	HttpServletResponse response,
			@RequestParam(value = "query", defaultValue = "") String query)
	{
		restProxyForTHPortal.secureStream("getTHPortalAdminBanners", request, response, new String[] { query});
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/admin/banners", method = RequestMethod.PUT)
	public void updateTHPortalAdminBanners  (
			HttpServletRequest request,	HttpServletResponse response)
	{
		restProxyForTHPortal.secureStream("updateTHPortalAdminBanners", request, response, new String[] { });
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/admin/talenthub/statistics/serverstatistics", method = RequestMethod.GET)
	public void talentHubServerStatistics  (
			HttpServletRequest request,	HttpServletResponse response)
	{

		restProxyForTHPortal.stream("talentHubServerStatistics", request, response, new String[] { });

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/admin/talenthub/statistics/clientstatistics", method = RequestMethod.GET)
	public void talentHubClientStatistics  (
			HttpServletRequest request,	HttpServletResponse response,
			@RequestParam(value = "requestClient", defaultValue = "") String requestClient)
	{
		restProxyForTHPortal.stream("talentHubClientStatistics", request, response, new String[] {requestClient});

	}
}
