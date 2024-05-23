/**
 * New controller for Job Attachments
 */


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

/**
 * @author KOLISHES
 *
 */


@Controller
@RequestMapping(value="/hrms/architect/jobattachments") 
public class ArchitectJobAttachmentsController {

	
	@Autowired
	@Qualifier("restProxyForNodeAPI")
	private RestProxy restProxyForNodeAPI;
	
	private static final Logger logger = LoggerFactory.getLogger(ArchitectJobAttachmentsController.class);
	
	/**
	 * @param jobId
	 * @param jobAttachmentId
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "getdownloadpresignedurl", method = RequestMethod.GET)
	public void getDownloadPreSignedURL(
			@RequestParam(value = "jobId", defaultValue = "") String jobId,
			@RequestParam(value = "jobAttachmentId", defaultValue = "") String jobAttachmentId,
			HttpServletRequest request,	HttpServletResponse response)
	{
	
		restProxyForNodeAPI.secureStream("getDownloadPreSignedURL", request, response, new String[] {jobId, jobAttachmentId});
	}
	
	/**
	 * @param jobId
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "getuploadpresignedurl", method = RequestMethod.POST)
	public void getUploadPreSignedURL(
			@RequestParam(value = "jobId", defaultValue = "") String jobId,
			HttpServletRequest request,	HttpServletResponse response)
	{
	
		restProxyForNodeAPI.secureStream("getUploadPreSignedURL", request, response, new String[] {jobId});
	}
	
	/**
	 * @param jobId
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "deletefile", method = RequestMethod.DELETE)
	public void deleteJobAttachment(
			@RequestParam(value = "jobId", defaultValue = "") String jobId,
			@RequestParam(value = "jobAttachmentId", defaultValue = "") String jobAttachmentId,
			HttpServletRequest request,	HttpServletResponse response)
	{
	
		restProxyForNodeAPI.secureStream("deleteJobAttachment", request, response, new String[] {jobId, jobAttachmentId});
	}
	
	/**
	 * @param jobId
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "metadata", method = RequestMethod.POST)
	public void saveAttachmentMetadata(
			@RequestParam(value = "jobId", defaultValue = "") String jobId,
			HttpServletRequest request,	HttpServletResponse response)
	{
	
		restProxyForNodeAPI.secureStream("saveAttachmentMetadata", request, response, new String[] {jobId});
	}
}