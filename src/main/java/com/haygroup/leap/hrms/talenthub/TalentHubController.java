package com.haygroup.leap.hrms.talenthub;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.haygroup.leap.client.RestProxy;

@Controller
@RequestMapping(value="/hrms/talenthub") 
public class TalentHubController {

	@Autowired
	@Qualifier("restProxyForKFGrade")
	private RestProxy restProxyForKFGrade;

	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/orgdatatemplate", method = RequestMethod.GET)
	public void getDataTemplate(
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxyForKFGrade.stream("getDataTemplate", request, response, new String[] { });

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/orgdata", method = RequestMethod.POST)
	public void createOrgData(
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxyForKFGrade.stream("createOrgData", request, response, new String[] { });

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/orgdata", method = RequestMethod.GET)
	public void getOrgData(
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxyForKFGrade.stream("getOrgData", request, response, new String[] { });

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/orgdata/{companyId}", method = RequestMethod.GET)
	public void getOrgDataForId(
			@PathVariable String companyId,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxyForKFGrade.stream("getOrgDataForId", request, response, new String[] {companyId });

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/orgdata/{companyId}", method = RequestMethod.PUT)
	public void updateOrgData(
			@PathVariable String companyId,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxyForKFGrade.stream("updateOrgData", request, response, new String[] { companyId});

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/orgdata/questions", method = RequestMethod.POST)
	public void postDynamicQuestions(
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxyForKFGrade.stream("postDynamicQuestions", request, response, new String[] { });

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/orgdata/grade", method = RequestMethod.POST)
	public void postFinalGradeData(
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxyForKFGrade.stream("postFinalGradeData", request, response, new String[] { });

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/orgdata/grade/raw", method = RequestMethod.POST)
	public void postGradeTest(
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxyForKFGrade.stream("postGradeTest", request, response, new String[] { });

	}
	
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/orgdata/{companyId}", method = RequestMethod.DELETE)
	public void deleteOrgData(
			@PathVariable String companyId,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxyForKFGrade.stream("deleteOrgData", request, response, new String[] {companyId });

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/orgdata/gradetemplate", method = RequestMethod.POST)
	public void postGradeTemplateData(
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxyForKFGrade.stream("postGradeTemplateData", request, response, new String[] { });

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/orgdata/importorgdata", method = RequestMethod.POST)
	public void importOrgData(
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxyForKFGrade.stream("importOrgData", request, response, new String[] { });

	}
	
	
	
	/**
	 * @author Harsha vardhan moback
	 * @apiNote Adding Organization Data Upload API's
	 * 
	 */
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/orgdataupload/status", method = RequestMethod.GET)
	public void getOrgDataUploadStatus(
			@RequestParam(value = "fileId", defaultValue = "") String fileId,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxyForKFGrade.stream("getOrgDataUploadStatus", request, response, new String[] {fileId });

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/orgdataupload/status", method = RequestMethod.PUT)
	public void putOrgDataUploadStatus(
			@RequestParam(value = "fileId", defaultValue = "") String fileId,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxyForKFGrade.stream("putOrgDataUploadStatus", request, response, new String[] {fileId });

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/orgdataupload/file", method = RequestMethod.POST)
	public void postOrgDataUploadStatus(
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxyForKFGrade.stream("postOrgDataUploadStatus", request, response, new String[] { });

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/orgdataupload/countrydata", method = RequestMethod.GET)
	public void getOrgDataUploadCountryData(
			@RequestParam(value = "fileId", defaultValue = "") String fileId,
			@RequestParam(value = "companyId", defaultValue = "") String companyId,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxyForKFGrade.stream("getOrgDataUploadCountryData", request, response, new String[] {fileId, companyId });

	}
	
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/orgdataupload/file", method = RequestMethod.GET)
	public void downloadExcelReport(
			@RequestParam(value = "fileId", defaultValue = "") String fileId,
			@RequestParam(value = "companyId", defaultValue = "") String companyId,
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxyForKFGrade.stream("downloadExcelReport", request, response, new String[] {fileId, companyId });

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/orgdata/reset", method = RequestMethod.POST)
	public void resetOrgData(
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxyForKFGrade.stream("resetOrgData", request, response, new String[] { });

	}
	
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/orgdata/validate", method = RequestMethod.POST)
	public void postValidateOrgData(
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxyForKFGrade.stream("postValidateOrgData", request, response, new String[] { });
		
	}
	
}
