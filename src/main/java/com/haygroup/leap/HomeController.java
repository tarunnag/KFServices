package com.haygroup.leap;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.HtmlUtils;

import com.haygroup.leap.common.PropertiesUtil;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome to new home controller! The client locale is {}.", StringEscapeUtils.escapeJava(locale.toString()));
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		model.addAttribute("authToken",PropertiesUtil.getProperty("authToken"));
		
		return "home";
	}
	
	@RequestMapping(value = "/error",method= {RequestMethod.GET , RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
	public void errorPage(HttpServletResponse response){

		response.setContentType("application/json");
		response.setStatus(401);
		String errorMessage = "{\"responseCode\": \"RES.40100\"," + 
				"\"responseMessage\": \"Unauthorized Access\"}";
		try {
			response.getOutputStream().write(errorMessage.getBytes());
			response.getOutputStream().close();
		} catch (IOException e) {
			logger.error("IO Error", e);
		}
		//return  "error";
	}

	@RequestMapping(value = "/error/{errorCode}",method= {RequestMethod.GET , RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
	public String error404(@PathVariable int errorCode, Locale locale, Model model, HttpServletResponse response){
		
		HttpStatus status = HttpStatus.valueOf(errorCode);
		
		if(status == null || status.value() == 500){
			model.addAttribute("errorCode", "APP.500");
			model.addAttribute("errorMessage", "Sorry. Something went wrong");
			
		}else{
			model.addAttribute("errorCode", "APP." + status.value());
			model.addAttribute("errorMessage", status.getReasonPhrase());
		}
		
		return  "error";
	}
	
	@RequestMapping(value = "/jsondoc.json", method = RequestMethod.GET)
	public String getRESTDoc(HttpServletResponse response)
	{
		response.setContentType("application/json");
		return "jsonDoc";
	}
	
	
	
}
