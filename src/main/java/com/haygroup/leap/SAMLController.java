/**
 * 
 */
package com.haygroup.leap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.HtmlUtils;
import org.springframework.web.bind.annotation.RequestBody;

import com.haygroup.leap.client.RestProxy;
import com.haygroup.leap.common.LeapUtil;
import com.haygroup.leap.common.PropertiesUtil;

/**
 * @author KOLISHES
 *
 */
@Controller
@RequestMapping(value="/sso")  
public class SAMLController {
	private static final Logger logger = LoggerFactory.getLogger(SAMLController.class);
	
	@Autowired
	private LeapUtil leapUtil;
	@Autowired
	private RestProxy restProxy;
	
	@RequestMapping(value = "/samlssocheck", method = RequestMethod.GET)
	public void validateUserSAMLAccess(
		HttpServletRequest request,	HttpServletResponse response, @RequestParam(value = "username", defaultValue = "") String username
	) {
		restProxy.stream("validateUserSAMLAccess", request, response, new String[] { username });
	}

	@RequestMapping(value = "/acs", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public void assertionConsumerService(
		HttpServletRequest request, HttpServletResponse response, @RequestBody MultiValueMap<String, String> formData
	) {
		String samlResponse = formData.getFirst("SAMLResponse");
		String samlUIRedirectBase = PropertiesUtil.getProperty("samlUIRedirectBase");
		String redirectUrl = samlUIRedirectBase + "/#samlresponse=" + samlResponse;
		logger.debug("RKEM redirectUrl - " + StringEscapeUtils.escapeJava(redirectUrl));
		response.setHeader("Location", redirectUrl==null?null:StringEscapeUtils.unescapeJava(redirectUrl.trim()));
		response.setStatus(302);
	}
}
