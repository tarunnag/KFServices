/**
 * 
 */
package com.haygroup.leap.common;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import com.haygroup.leap.UsersController;

/**
 * @author suchetha_ponakampall
 *
 */ 
public class PropertyFileUtil extends PropertyPlaceholderConfigurer {
	   private static final Logger logger = LoggerFactory.getLogger(PropertyFileUtil.class);
	   private static Map<String,String> propertiesMap;
	   
	   
	   
	   @Override
	   protected void processProperties(ConfigurableListableBeanFactory beanFactory,
	             Properties props) throws BeansException {
	        super.processProperties(beanFactory, props);
	 
	        propertiesMap = new ConcurrentHashMap<String, String>();  
	        for (Object key : props.keySet()) {
	            String keyStr = key.toString();
	            String valueStr = resolvePlaceholder(keyStr, props);
	            propertiesMap.put(keyStr, valueStr);
	        }
	    }
	 
	    public static Boolean identifyURL(String url) {
	    	
	        return propertiesMap.containsValue(url);
	    }
	    
	    public static Map<String,String> getPropertiesMap()
	    {
	    	return propertiesMap;
	    }
	}
   
    
    
    

