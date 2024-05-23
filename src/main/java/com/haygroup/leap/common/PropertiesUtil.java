/**
 * 
 */
package com.haygroup.leap.common;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.web.util.HtmlUtils;

import com.haygroup.leap.UsersController;

/**
 * @author suchetha_ponakampall
 *
 */ 
public class PropertiesUtil extends PropertyPlaceholderConfigurer {
	   private static final Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);
	   private static Map<String,String> propertiesMap;
	   
	     
	   @Override
	   protected void processProperties(ConfigurableListableBeanFactory beanFactory,
	             Properties props) throws BeansException {
	        super.processProperties(beanFactory, props);
	 
	        propertiesMap = new HashMap<String, String>();  
	        for (Object key : props.keySet()) {
	            String keyStr = key.toString();
	            String valueStr = resolvePlaceholder(keyStr, props);
	            propertiesMap.put(keyStr, valueStr);
	           // propertiesMap.put(keyStr, parseStringValue(props.getProperty(keyStr),props, new HashSet()));
	        }
	        
	        
	        logger.info("Adding the authToken to the properties table "+propertiesMap.get("authToken"));
	        //LeapUtil.addToSessionTable(propertiesMap.get(HGLeapConstants.AUTH_TOKEN));
	        //logger.info("Is the key present?"+LeapUtil.isSessionValid(HGLeapConstants.AUTH_TOKEN));
	    }
	 
	    public static String getProperty(String name) {
	    	logger.info("getProperty--------------"+HtmlUtils.htmlEscape(name)+ HtmlUtils.htmlEscape(propertiesMap.get(name)));
	        return (String) propertiesMap.get(name); 
	    }
	}
   
    
    
    

