package com.haygroup.leap.hrms;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.haygroup.leap.client.RestProxy;

@Controller
@RequestMapping(value="/hrms/bulkrunner")
public class BulkRunnerController {
	
	@Autowired
	private RestProxy restProxyForBulkRunnerNodeAPI;
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value="/", method=RequestMethod.GET)
	public void getBulkRunner(
			HttpServletRequest request,	HttpServletResponse response) {
		
		restProxyForBulkRunnerNodeAPI.stream("getBulkRunner", request, response,new String[] {});
	}
	
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value="/taskgroups", method=RequestMethod.POST)
	public void addBulkRunnerTaskGroups(
			HttpServletRequest request,	HttpServletResponse response) {
		
		restProxyForBulkRunnerNodeAPI.stream("addBulkRunnerTaskGroups", request, response,new String[] {});
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value="/taskgroups", method=RequestMethod.PUT)
	public void insertBulkRunnerTaskGroups(
			HttpServletRequest request,	HttpServletResponse response) {
		
		restProxyForBulkRunnerNodeAPI.stream("insertBulkRunnerTaskGroups", request, response,new String[] {});
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value="/taskgroups/{id}", method=RequestMethod.GET)
	public void getBulkRunnerTaskGroupsById(
			@PathVariable String id,
			HttpServletRequest request,	HttpServletResponse response) {
		
		restProxyForBulkRunnerNodeAPI.stream("getBulkRunnerTaskGroupsById", request, response,new String[] {id});
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value="/taskgroups", method=RequestMethod.GET)
	public void getBulkRunnerTaskGroups(
			@RequestParam(value="mode",defaultValue="") String mode,
			@RequestParam(value="filters",defaultValue="") String filters,
			HttpServletRequest request,	HttpServletResponse response) {
		
		restProxyForBulkRunnerNodeAPI.stream("getBulkRunnerTaskGroups", request, response,new String[] {mode,filters});
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value="/taskgroups/{id}", method=RequestMethod.DELETE)
	public void deleteBulkRunnerTaskGroupsById(
			@PathVariable String id,
			HttpServletRequest request,	HttpServletResponse response) {
		
		restProxyForBulkRunnerNodeAPI.stream("deleteBulkRunnerTaskGroupsById", request, response,new String[] {id});
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value="/taskgroups/{id}/{action}", method=RequestMethod.PUT)
	public void insertTaskGroupAction(
			@PathVariable String id,
			@PathVariable String action,
			HttpServletRequest request,	HttpServletResponse response) {
		
		restProxyForBulkRunnerNodeAPI.stream("insertTaskGroupAction", request, response,new String[] {id, action});
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value="/taskgroups/{id}/{action}", method=RequestMethod.POST)
	public void addTaskGroupAction(
			@PathVariable String id,
			@PathVariable String action,
			HttpServletRequest request,	HttpServletResponse response) {
		
		restProxyForBulkRunnerNodeAPI.stream("addTaskGroupAction", request, response,new String[] {id, action});
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value="/activities/tasks/run", method=RequestMethod.PUT)
	public void insertBulkRunnerActivityTasks(
			HttpServletRequest request,	HttpServletResponse response) {
		
		restProxyForBulkRunnerNodeAPI.stream("insertBulkRunnerActivityTasks", request, response,new String[] {});
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value="/tasks/{id}", method=RequestMethod.POST)
	public void addBulkRunnerTasks(
			@PathVariable String id,
			HttpServletRequest request,	HttpServletResponse response) {
		
		
		restProxyForBulkRunnerNodeAPI.stream("addBulkRunnerTasks", request, response,new String[] {id});
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value="/activities/rerun", method=RequestMethod.PUT)
	public void rerunBulkRunnerActivities(
			@PathVariable String id,
			HttpServletRequest request,	HttpServletResponse response) {
		
		restProxyForBulkRunnerNodeAPI.stream("rerunBulkRunnerActivities", request, response,new String[] {id});
	}
	
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value="/tasks/{id}", method=RequestMethod.DELETE)
	public void deleteBulkRunnerTasksById(
			@PathVariable String id,
			HttpServletRequest request,	HttpServletResponse response) {
		
		restProxyForBulkRunnerNodeAPI.stream("deleteBulkRunnerTasksById", request, response,new String[] {id});
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value="/tasks/{id}/{action}", method=RequestMethod.PUT)
	public void insertTasksAction(
			@PathVariable String id,
			@PathVariable String action,
			HttpServletRequest request,	HttpServletResponse response) {
		
		restProxyForBulkRunnerNodeAPI.stream("insertTasksAction", request, response,new String[] {id, action});
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value="/tasks/{id}", method=RequestMethod.GET)
	public void getBulkRunnerTasksById(
			@PathVariable String id,
			HttpServletRequest request,	HttpServletResponse response) {
		
		restProxyForBulkRunnerNodeAPI.stream("getBulkRunnerTasksById", request, response,new String[] {id});
	}

}