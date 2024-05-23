package com.haygroup.leap.common;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.HtmlUtils;

public class SessionManager 
{
	public static HashMap<String,Long> sessionTable = new HashMap<String,Long>(); 
	private static final Logger logger = LoggerFactory.getLogger(SessionManager.class);
	
	/**
	 * @param string
	 */
	public static void addToSessionTable(String key) 
	{
		sessionTable.put(key, System.currentTimeMillis());
	}
	
	/**
	 * @param key
	 * @return
	 */
	public static boolean isSessionValid(String key)
	{

		Long value = sessionTable.get(key);
		boolean retValue = false;
		
		if(value!=null)
		{
			retValue = true;
		}
		
		logger.info("Returning value of :"+retValue);
		return retValue;
	}

	/**
	 * 
	 */
	public static void printSessionTokens()
	{
		
		@SuppressWarnings("rawtypes")
		Set set = sessionTable.entrySet();
		@SuppressWarnings("unchecked")
		Iterator<String> iter = set.iterator();
		
		while(iter.hasNext())
		{
			logger.info("Value is "+iter.next());
		}
		
	}
	
	/**
	 * @param string
	 */
	public  static void addToSessionTable(String key,long userId)
	{
		try
		{
			synchronized(sessionTable){
			 
			 sessionTable.put(key, userId);
			}
		}
		catch(java.lang.Exception ex)
		{
			logger.error("error putting into session table key,userId "+HtmlUtils.htmlEscape(key)+","+userId);
		}
	}	
	
	
	public static long getUserId(String authToken)
	{
		return sessionTable.get(authToken);
	}
	
	
	/**
	 * @param string
	 */
	public static void removeFromSessionTable(String key)
	{
		try
		{
			synchronized(sessionTable){
				 
				sessionTable.remove(key);
				}
			
		}
		catch(java.lang.Exception ex)
		{
			logger.error("error removing from session table "+HtmlUtils.htmlEscape(key));
		}
	}	
	
}
