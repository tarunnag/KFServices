package com.haygroup.leap;

import java.io.UnsupportedEncodingException;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.haygroup.leap.client.RestProxy;
import com.haygroup.leap.common.HGLeapConstants;
import com.haygroup.leap.common.LeapUtil;
import com.haygroup.leap.domain.SurveyDetails;


@Controller
@RequestMapping(value = "/surveys")
public class SurveysController 
{


	@Autowired
	private LeapUtil leapUtil;
	@Autowired
	private RestProxy restProxy;
	
	private static final Logger logger = LoggerFactory.getLogger(SurveysController.class);

	/**
	 * @param userId
	 * @param model
	 * @param response
	 */
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public void getAchievements(@RequestParam(value="tokenId",defaultValue="") String tokenId,
						HttpServletRequest request, HttpServletResponse response) 
	{
		String userId = leapUtil.getUserId(request.getHeader(HGLeapConstants.AUTH_TOKEN));
		restProxy.stream("getSurvey", request, response, new String[] { userId,tokenId });

	}
	
	//Removing Unused apis's
	/*
	@RequestMapping(value = "", method = RequestMethod.POST)
	public void addSurveys(	HttpServletRequest request, HttpServletResponse response)
	{

			restProxy.stream("addSurveys", request, response, new String[] {  });
	}
	 */
	
	
	/**
	 * 
	 * 

	 */
	@RequestMapping(value = "/achievements", method = RequestMethod.POST)
	public void createAchievementSurvey(		
			@RequestParam(value="taskGroupId",defaultValue="") String taskGroupId,
			HttpServletRequest request, HttpServletResponse response)
	{

		String userId = leapUtil.getUserId(request.getHeader(HGLeapConstants.AUTH_TOKEN));
		restProxy.stream("createAchievementSurvey", request, response, new String[] { userId,taskGroupId });

	}

	
	@RequestMapping(value = "/response", method = RequestMethod.POST)
	public void submitRosterSurveyResponse(		
			@RequestParam(value="tokenId",defaultValue="") String tokenId,
			HttpServletRequest request, HttpServletResponse response,ModelMap model)
	{

		//String userId = leapUtil.getUserId(request.getHeader(HGLeapConstants.AUTH_TOKEN));
		//tokenId="FE5E6D7D-AC5D-4644-98B1-BF397CC6A1D7";
		//userId="10";
		
		model.remove("one-on-one");
		model.remove("team-tip");
		restProxy.stream("submitSurveyResponse", request, response, new String[] { tokenId });

	}

		
		
//	@RequestMapping(value = "/{tokenId}", method = RequestMethod.GET)
//	public ModelAndView createSurvey(		
//			@RequestParam(value="tokenId",defaultValue="") String tokenId,
//			HttpServletRequest request, HttpServletResponse response,ModelMap model)
	
	@RequestMapping(value = "/{tokenId}", method = RequestMethod.GET)
	public ModelAndView createSurvey(		
			@PathVariable String tokenId,
			HttpServletRequest request, HttpServletResponse response,Model model) throws UnsupportedEncodingException
	{
		
		logger.info("create survey input is :"+StringEscapeUtils.escapeJava(tokenId));
		model.addAttribute("one-on-one","");
		model.addAttribute("team-tip","");
		byte[] stream = restProxy.stream("createAchievementSurvey", request, new String[] {tokenId}, request.getMethod());
		SurveyDetails surveyDetails = new SurveyDetails();
		Gson gson = new Gson();
		
		if(stream!=null)
		{
			String outputResponse = new String(stream,"UTF-8");

			logger.info("create survey output is :" + StringEscapeUtils.escapeJava(outputResponse.toString()));

			surveyDetails = gson.fromJson(outputResponse, SurveyDetails.class);
			 
			 logger.info("ResponseCode:-----" + StringEscapeUtils.escapeJava(surveyDetails.getResponseCode()));
				
			if(surveyDetails.getData() != null){
				
				String email = surveyDetails.getData().getRoster().getEmail();
				String application = "ACTIVATE";
				String locale = request.getParameter("language").replace('_', '-');
				String baseurl = request.getRequestURL().toString();
				
				logger.info("Survey URL: " + StringEscapeUtils.escapeJava(baseurl));
				
				request.setAttribute("email", email);
				request.setAttribute("application", application);
				request.setAttribute("locale", locale);
				request.setAttribute("surveytoken", tokenId);
				request.setAttribute("baseurl", baseurl);
				
				logger.info("Attributes: Email: " + StringEscapeUtils.escapeJava(email) + " App: " + StringEscapeUtils.escapeJava(application) + " Locale: " + StringEscapeUtils.escapeJava(locale) + "Base URL: " + StringEscapeUtils.escapeJava(baseurl));
				
				for(int i=0;i<surveyDetails.getData().getTasks().length;i++)
				{
					//logger.debug("Survey :"+surveyDetails.getData().getTasks()[i].getTaskTemplate().getDescription());
					if("1".equalsIgnoreCase(surveyDetails.getData().getTasks()[i].getTaskTemplate().getType()))
					{
						model.addAttribute("one-on-one","true");
						//logger.debug("Survey :"+surveyDetails.getData().getTasks()[i].getTaskTemplate().getType()+"       "+surveyDetails.getData().getTasks()[i].getTaskTemplate().getDescription());
						
					}
					if("3".equalsIgnoreCase(surveyDetails.getData().getTasks()[i].getTaskTemplate().getType()))
					{
					
						model.addAttribute("team-tip","true");
						//logger.debug("Survey :"+surveyDetails.getData().getTasks()[i].getTaskTemplate().getType()+"    "+surveyDetails.getData().getTasks()[i].getTaskTemplate().getDescription());
					}
				}
			}
			//logger.debug("model value:-----"+model.g("team-tip"));
			 
			logger.info("survey token:" + StringEscapeUtils.escapeJava(tokenId));
			 surveyDetails.setTokenId(tokenId);
			 request.setAttribute("surveyId",tokenId);
		}
		
		//return "survey";
		return new ModelAndView("survey", "SurveyDetails",surveyDetails);
		
	}
	
	@RequestMapping(value = "/message",method= {RequestMethod.GET})
	public String displayMessage(@RequestParam(value = "displayMessage", defaultValue = "") String displayMessage, Locale locale, 
			HttpServletRequest request, Model model, HttpServletResponse response){
		
	
		request.setAttribute("displayMessage", displayMessage);
		return  "message";
	}
	

	
	
}
