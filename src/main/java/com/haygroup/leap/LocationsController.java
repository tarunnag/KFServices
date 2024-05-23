package com.haygroup.leap;

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

import com.haygroup.leap.client.RestProxy;
import com.haygroup.leap.common.LeapUtil;



@Controller
@RequestMapping(value="/locations") 
public class LocationsController 
{

private static final Logger logger = LoggerFactory.getLogger(LocationsController.class);
	
	@Autowired
	private LeapUtil leapUtil;
	@Autowired
	private RestProxy restProxy;

	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "", method = RequestMethod.POST)
	public void postLocations(HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxy.stream("postLocations", request, response, new String[] { });

	}

	
}
