package com.haygroup.leap.hrms;

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
@RequestMapping(value="/hrms/thportal/workday") 
public class SuccessProfileWorkdayController {
	
	@Autowired
	@Qualifier("restProxyForTHPortal")
	private RestProxy restProxyForTHPortal;
	
/*	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/uploaddata", method = RequestMethod.POST)
	public void workdayUploadData(
			@RequestParam(value = "fileKey", defaultValue = "") String fileKey,
			HttpServletRequest request,	HttpServletResponse response)
	{
		restProxyForCriticalExp.secureStream("workDayUploadData", request, response, new String[] {fileKey});
	}*/
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/status", method = RequestMethod.GET)
	public void getWorkdayUploadStatus(
			@RequestParam(value = "fileUUID", defaultValue = "") String fileUUID,
			HttpServletRequest request,	HttpServletResponse response)
	{
		restProxyForTHPortal.secureStream("getWorkdayUploadStatus", request, response, new String[] {fileUUID});
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/status", method = RequestMethod.PUT)
	public void putWorkdayUploadStatus(
			@RequestParam(value = "processStatus", defaultValue = "") String processStatus,
			@RequestParam(value = "fileUUID", defaultValue = "") String fileUUID,
			HttpServletRequest request,	HttpServletResponse response)
	{
		restProxyForTHPortal.secureStream("putWorkdayUploadStatus", request, response, new String[] {processStatus,fileUUID});
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/uploadurl", method = RequestMethod.GET)
	public void workdayUploadUrl (
			@RequestParam(value = "clientId", defaultValue = "") String clientId,
			HttpServletRequest request,	HttpServletResponse response)
	{
		restProxyForTHPortal.secureStream("workdayUploadUrl", request, response, new String[] {clientId});
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/downloadurl", method = RequestMethod.GET)
	public void workdayDownloadUrl  (
			@RequestParam(value = "clientId", defaultValue = "") String clientId,
			HttpServletRequest request,	HttpServletResponse response)
	{
		restProxyForTHPortal.secureStream("workdayDownloadUrl", request, response, new String[] {clientId});
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/pushdata", method = RequestMethod.POST)
	public void workdayPushData  (
			@RequestParam(value = "fileUUID", defaultValue = "") String fileUUID,
			@RequestParam(value = "ts", defaultValue = "") String ts,
			@RequestParam(value = "clientId", defaultValue = "") String clientId,
			HttpServletRequest request,	HttpServletResponse response)
	{
		restProxyForTHPortal.secureStream("workdayPushData", request, response, new String[] {fileUUID, ts, clientId});
	}
	

}
