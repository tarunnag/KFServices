package com.haygroup.leap.hrms.paydatacollection;

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

@Controller
@RequestMapping(value="/hrms/paydatacollection/jobmatching")
public class PayDataCollectionJobMatchingController 
{

	private static final Logger logger = LoggerFactory.getLogger(PayDataCollectionJobMatchingController.class);
	
	@Autowired
	private LeapUtil leapUtil;
	@Autowired
	private RestProxy jobMatchingRestProxy; 
	
	
	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/{uploadId}",	method = RequestMethod.GET)
	public void getJobMatchingUUIDDetail(
			@PathVariable String uploadId,HttpServletRequest request,HttpServletResponse response) 
	{

		jobMatchingRestProxy.stream("getJobMatchingUUIDDetail", request, response, new String[] { uploadId});

	}
	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/{uploadId}/jobs",	method = RequestMethod.GET)
	public void getUploadJobs(
			@PathVariable String uploadId,
			@RequestParam(value = "group", defaultValue = "") String group,
			@RequestParam(value = "upload_id", defaultValue = "") String uploadid,
			@RequestParam(value = "sortColumn", defaultValue = "") String sortColumn,
			@RequestParam(value = "sortBy", defaultValue = "") String sortBy,
			@RequestParam(value = "searchColumn", defaultValue = "") String searchColumn,
			@RequestParam(value = "filterBy", defaultValue = "") String filterBy,
			@RequestParam(value = "filterValues", defaultValue = "") String filterValues,
			@RequestParam(value = "pageIndex", defaultValue = "1") String pageIndex,
			@RequestParam(value = "pageSize", defaultValue = "20") String pageSize,
			@RequestParam(value = "searchString", defaultValue = "") String searchString,
			HttpServletRequest request,HttpServletResponse response) 
	{

		jobMatchingRestProxy.stream("getUploadJobs", request, response, new String[] { uploadId, group, uploadid, sortColumn,
				sortBy, searchColumn, filterBy, filterValues, pageIndex, pageSize, searchString});

	}
	
	
	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/{uploadId}/jobnames",	method = RequestMethod.GET)
	public void getJobMatchingJobNames(
			@PathVariable String uploadId,HttpServletRequest request,HttpServletResponse response) 
	{

		jobMatchingRestProxy.stream("getJobMatchingJobNames", request, response, new String[] { uploadId});

	}
	
	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/{uploadId}/jobcategories",method = RequestMethod.GET)
	public void getJobMatchingJobCategories(
			@RequestParam(value = "upload_id", defaultValue = "") String upload_id,
			@RequestParam(value = "sortColumn", defaultValue = "") String sortColumn,
			@RequestParam(value = "sortBy", defaultValue = "") String sortBy,
			@RequestParam(value = "searchColumn", defaultValue = "") String searchColumn,
			@RequestParam(value = "filterBy", defaultValue = "") String filterBy,
			@RequestParam(value = "filterValues", defaultValue = "") String filterValues,
			@RequestParam(value = "pageIndex", defaultValue = "") String pageIndex,
			@RequestParam(value = "pageSize", defaultValue = "") String pageSize,
			@RequestParam(value = "searchString", defaultValue = "") String searchString,
			@PathVariable String uploadId,HttpServletRequest request,HttpServletResponse response) 
	{

		jobMatchingRestProxy.stream("getJobMatchingJobCategories", request, response,
				new String[] { uploadId, upload_id, sortColumn, sortBy, searchColumn, filterBy, filterValues, pageIndex,
						pageSize, searchString });

	}
	
	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	//searchString={1}&searchColumn={2}&filterBy={3}&filterValues={4}
	@RequestMapping(value = "/{uploadId}/top5Matches",	method = RequestMethod.GET)
	public void getJobMatchingTop5Matches(
			@PathVariable String uploadId,HttpServletRequest request,HttpServletResponse response) 
	{

		jobMatchingRestProxy.stream("getJobMatchingTop5Matches", request, response, new String[] { uploadId});

	}
	
	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/{uploadId}/updatestatus",	method = RequestMethod.PUT)
	public void jobMatchingUpdateStatus(
			@PathVariable String uploadId,HttpServletRequest request,HttpServletResponse response) 
	{

		jobMatchingRestProxy.stream("jobMatchingUpdateStatus", request, response, new String[] { uploadId});

	}
	
	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/{uploadId}/searchfilters",	method = RequestMethod.GET)
	public void getJobMatchingSearchFilters(
			@RequestParam(value = "kfJobName", defaultValue = "") String kfJobName,
			@PathVariable String uploadId,HttpServletRequest request,HttpServletResponse response) 
	{

		jobMatchingRestProxy.stream("getJobMatchingSearchFilters", request, response, new String[] { uploadId,kfJobName});

	}
	
	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/{uploadId}/categoryfilters",	method = RequestMethod.GET)
	public void getJobMatchingCategoryFilters(
			@RequestParam(value = "searchString", defaultValue = "") String searchString,
			@RequestParam(value = "searchColumn", defaultValue = "") String searchColumn,
			@RequestParam(value = "filterBy", defaultValue = "") String filterBy,
			@RequestParam(value = "filterValues", defaultValue = "") String filterValues,
			@PathVariable String uploadId,HttpServletRequest request,HttpServletResponse response) 
	{

		jobMatchingRestProxy.stream("getJobMatchingCategoryFilters", request, response,
				new String[] { uploadId, searchString, searchColumn, filterBy, filterValues });

	}
	
	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/{uploadId}/splitjobs",	method = RequestMethod.PUT)
	public void putJobMatchingSplitJobs(
			@PathVariable String uploadId,HttpServletRequest request,HttpServletResponse response) 
	{

		jobMatchingRestProxy.stream("putJobMatchingSplitJobs", request, response, new String[] { uploadId});

	}
	
	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/{uploadId}/mergejobs",	method = RequestMethod.PUT)
	public void putJobMatchingMergeJobs(
			@PathVariable String uploadId,HttpServletRequest request,HttpServletResponse response) 
	{

		jobMatchingRestProxy.stream("putJobMatchingMergeJobs", request, response, new String[] { uploadId});

	}
	
	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/{uploadId}/matchjobs",	method = RequestMethod.PUT)
	public void putJobMatchingMatchJobs(
			@PathVariable String uploadId,HttpServletRequest request,HttpServletResponse response) 
	{

		jobMatchingRestProxy.stream("putJobMatchingMatchJobs", request, response, new String[] { uploadId});

	}
	
	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/{uploadId}/topMatches",	method = RequestMethod.GET)
	public void getJobMatchingTopMatches(
			@PathVariable String uploadId,HttpServletRequest request,HttpServletResponse response) 
	{

		jobMatchingRestProxy.stream("getJobMatchingTopMatches", request, response, new String[] { uploadId});

	}
	
	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/{uploadId}/info",	method = RequestMethod.GET)
	public void getJobMatchingInfo(
			@PathVariable String uploadId,HttpServletRequest request,HttpServletResponse response) 
	{

		jobMatchingRestProxy.stream("getJobMatchingInfo", request, response, new String[] { uploadId});

	}
	
	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/{uploadId}/markascomplete",	method = RequestMethod.GET)
	public void getJobMatchingMarkAsComplete(@PathVariable String uploadId,HttpServletRequest request,HttpServletResponse response) 
	{

		jobMatchingRestProxy.stream("getJobMatchingMarkAsComplete", request, response, new String[] { uploadId});
  
	}
	
	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/{uploadId}/export",	method = RequestMethod.GET)
	public void getJobMatchingExport(
			@PathVariable String uploadId,HttpServletRequest request,HttpServletResponse response) 
	{

		jobMatchingRestProxy.stream("getJobMatchingExport", request, response, new String[] { uploadId});
		
	}
	
	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/{uploadId}/clientexport",	method = RequestMethod.GET)
	public void getJobMatchingClientExport(@PathVariable String uploadId,HttpServletRequest request,HttpServletResponse response) 
	{

		jobMatchingRestProxy.stream("getJobMatchingClientExport", request, response, new String[] { uploadId});

	}
	
	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/{uploadId}/clientexportlink",	method = RequestMethod.GET)
	public void getJobMatchingClientExportLink(@PathVariable String uploadId,HttpServletRequest request,HttpServletResponse response) 
	{

		jobMatchingRestProxy.stream("getJobMatchingClientExportLink", request, response, new String[] { uploadId});

	}
	
	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/companyData/{companyOrgCode}/export",	method = RequestMethod.GET)
	public void getCompanyOrgCodeExport(@PathVariable String companyOrgCode,HttpServletRequest request,HttpServletResponse response) 
	{

		jobMatchingRestProxy.stream("getCompanyOrgCodeExport", request, response, new String[] { companyOrgCode});
  
	}
	

	/**
	 * @param uploadId
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/{uploadId}/exportpipe",	method = RequestMethod.GET)
	public void getJobMatchingExportPipe(@PathVariable String uploadId,HttpServletRequest request,HttpServletResponse response) 
	{

		jobMatchingRestProxy.stream("getJobMatchingExportPipe", request, response, new String[] { uploadId});

	}
	
	

	/**
	 * @param uploadId
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/{uploadId}/jmtexport",	method = RequestMethod.GET)
	public void getJobMatchingJMTExport(@PathVariable String uploadId,HttpServletRequest request,HttpServletResponse response) 
	{

		jobMatchingRestProxy.stream("getJobMatchingJMTExport", request, response, new String[] { uploadId});

	}
	

	/**
	 * @param uploadId
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/{uploadId}/jmtdownload",	method = RequestMethod.GET)
	public void getJobMatchingJMTDownload(@PathVariable String uploadId,HttpServletRequest request,HttpServletResponse response) 
	{

		jobMatchingRestProxy.stream("getJobMatchingJMTDownload", request, response, new String[] { uploadId});

	}
	

	/**
	 * @param uploadId
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/{uploadId}/exportasync",	method = RequestMethod.GET)
	public void getJobMatchingExportAsync(@PathVariable String uploadId,HttpServletRequest request,HttpServletResponse response) 
	{

		jobMatchingRestProxy.stream("getJobMatchingExportAsync", request, response, new String[] { uploadId});

	}
	

	/**
	 * @param uploadId
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/postprocessing/{uploadId}/status",	method = RequestMethod.POST)
	public void postJobMatchingStatus(@PathVariable String uploadId,HttpServletRequest request,HttpServletResponse response) 
	{

		jobMatchingRestProxy.stream("postJobMatchingStatus", request, response, new String[] { uploadId});

	}
	

	/**
	 * @param uploadId
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/postprocessing/{uploadId}/status",	method = RequestMethod.PUT)
	public void putJobMatchingStatus(@PathVariable String uploadId,HttpServletRequest request,HttpServletResponse response) 
	{

		jobMatchingRestProxy.stream("putJobMatchingStatus", request, response, new String[] { uploadId});

	}	

	/**
	 * @param uploadId
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/postprocessing/{uploadId}/status",	method = RequestMethod.GET)
	public void getJobMatchingStatus(@PathVariable String uploadId,HttpServletRequest request,HttpServletResponse response) 
	{

		jobMatchingRestProxy.stream("getJobMatchingStatus", request, response, new String[] { uploadId});

	}
	
	/**
	 * @param uploadId
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/{uploadId}/playgroundcollection",	method = RequestMethod.GET)
	public void getJobMatchingplaygroundcollection(@PathVariable String uploadId,HttpServletRequest request,HttpServletResponse response) 
	{

		jobMatchingRestProxy.stream("getJobMatchingplaygroundcollection", request, response, new String[] { uploadId});

	}
	
	
	/**
	 * @param uploadId
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/{uploadId}/updatelbstatus",	method = RequestMethod.PUT)
	public void putUpdatelbStatus(@PathVariable String uploadId,HttpServletRequest request,HttpServletResponse response) 
	{

		jobMatchingRestProxy.stream("putUpdatelbStatus", request, response, new String[] { uploadId});

	}
	
	/**
	 * @param uploadId
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/{uploadId}/jmtfileupload",	method = RequestMethod.POST)
	public void getJobMatchingJMTUpload(@PathVariable String uploadId,HttpServletRequest request,HttpServletResponse response) 
	{

		jobMatchingRestProxy.stream("getJobMatchingJMTUpload", request, response, new String[] { uploadId});

	}
	
	/**
	 * @param uploadId
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/{uploadId}/updaterecordcount",	method = RequestMethod.POST)
	public void getJobMatchingJMTUploadRecordCount(@PathVariable String uploadId,HttpServletRequest request,HttpServletResponse response) 
	{

		jobMatchingRestProxy.stream("getJobMatchingJMTUploadRecordCount", request, response, new String[] { uploadId});

	}
	
	/**
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/{uploadId}/errorinfo",	method = RequestMethod.GET)
	public void getErrorInfo(@PathVariable String uploadId,HttpServletRequest request,HttpServletResponse response) 
	{

		jobMatchingRestProxy.stream("getErrorInfo", request, response, new String[] { uploadId});
  
	}
	
	/**
	 * @param request
	 * @param response  /<component>/queuecount
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/{uploadId}/{componentName}/queuecount",	method = RequestMethod.GET)
	public void getQueueCount(@PathVariable String uploadId,@PathVariable String componentName,HttpServletRequest request,HttpServletResponse response) 
	{

		jobMatchingRestProxy.stream("getQueueCount", request, response, new String[] { uploadId,componentName});
  
	}

}
