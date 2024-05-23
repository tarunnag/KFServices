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
@RequestMapping(value="/hrms/paydatacollection/postprocessing/upm") 
public class PayDataCollectionUPMDatabaseController 
{

	private static final Logger logger = LoggerFactory.getLogger(PayDataCollectionUPMDatabaseController.class);
	
	@Autowired
	private LeapUtil leapUtil;
	@Autowired
	private RestProxy restProxyForUPM;
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/updatedata", method = RequestMethod.POST)
	public void updateUPMData(
			HttpServletRequest request,	HttpServletResponse response)
	{
		
		restProxyForUPM.stream("updateUPMData", request, response, 
		new String[] {});

	}

}
