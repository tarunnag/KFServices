/**
 * 
 */
package com.haygroup.leap.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Sanjay_Manchiganti
 *
 */
public class HGLeapConstants
{

	public final static String AUTH_TOKEN 					= "authToken";
	public final static String INVALID_TOKEN 				= "{\"responseCode\": \"SYS.10000\", \"responseMessage\": \"Invalid Authtoken\"}";
	public final static String JSON_CONTENT_TYPE 			= "application/json"; 
	public final static String HEADER_CONTENT_TYPE 			= "Content-Type";
	public final static String IGNORE_URL_KEY 				= "ignore.url";
	public final static String STATIC_AUTH_KEY 				= "static.authtoken";
	public final static String PICTURE_URL 					= "picture";
	public final static String USER_URL						= "/users/";
	public final static int    USER_URL_LENGTH 				= 7;
	public final static String CHARSET						= "UTF-8";
	
	//All roles
	public final static String ROLE_STYLES_AND_CLIMATE 		= "ROLE_STYLES_AND_CLIMATE";
	public final static String ROLE_JOB_GRADING 			= "ROLE_JOB_GRADING";
	public final static String ROLE_JOB_PRICING 			= "ROLE_JOB_PRICING";
	public final static String ROLE_JOURNEY					= "ROLE_JOURNEY";
	public final static String ROLE_SCRIBE					= "ROLE_SCRIBE";
	public final static String ROLE_EMBARK					= "ROLE_EMBARK";
	public final static String ROLE_USER 					= "ROLE_USER";
	public final static String INTELLIGENCE_CLOUD			= "INTELLIGENCE_CLOUD";
	public final static String ROLE_INTELLIGENCE_CLOUD		= "ROLE_INTELLIGENCE_CLOUD";
	public final static String ROLE_INTERNAL_USER			= "ROLE_INTERNAL_USER";
	
	//All products
	public final static String PRODUCT_STYLES_AND_CLIMATE	 	= "Styles And Climate";
	public final static String PRODUCT_JOB_GRADING			 	= "Job Grading";
	public final static String PRODUCT_JOB_PRICING	  		 	= "Job Pricing";
	public final static String PRODUCT_ARCHER		  		 	= "Archer";
	public final static String PRODUCT_JOURNEY					= "Journey";
	public final static String PRODUCT_SCRIBE                   = "Scribe";
	public final static String PRODUCT_EMBARK					= "Embark";
	public static final String CLIENT_ID 						= "clientId";
	public static final String USER_ID							= "userId";
	public static final String LOCALE	 						= "locale";
	
	public static final String APPLICATION_JOURNEY				= "Journey";
	public static final String APPLICATION_SCRIBE				= "Scribe";
	public static final String APPLICATION_ACTIVATE				= "Activate";
	public final static String APPLICATION_JOB_MAPPING			= "JobMapping";
	public static final String APPLICATION						= "APPLICATION";
	public static final String SESSION_LONG_DURATION 			= "LONG";
	public static final String SESSION_MEDIUM_DURATION 			= "MEDIUM";	
	public static final String SESSION_SHORT_DURATION 			= "SHORT";	
	
	public static final String CONTENT_DISPOSITION				= "Content-Disposition";
	public static final String PS_SESSION_ID					= "ps-session-id";
	public static final String LOCATION							= "Location";
	public static final List<String> CLIENT_IDS = new ArrayList<>( Arrays.asList("14193"));
	public static final String AUTHORIZATION					= "Authorization";
	public static final String SPECIALCHARS						="[`^]";
} 
