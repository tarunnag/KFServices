/**
 * 
 */
package com.haygroup.leap.common;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

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
public class AuthPropertiesUtil extends PropertyPlaceholderConfigurer {

	private static final Logger logger = LoggerFactory.getLogger(AuthPropertiesUtil.class);

	private static Map<String, String> ignoreUrlMap;

	private static Map<String, String> staticKeyMap;

	@Override
	protected void processProperties(ConfigurableListableBeanFactory beanFactory, Properties props)
			throws BeansException {

		super.processProperties(beanFactory, props);
		logger.debug("Loading properties file and loading to ignoreURLs and static Keys");

		ignoreUrlMap = new HashMap<String, String>();
		staticKeyMap = new HashMap<String, String>();

		for (Object key : props.keySet()) {
			String keyStr = key.toString();
			String valueStr = resolvePlaceholder(keyStr, props);

			if (keyStr.contains(HGLeapConstants.IGNORE_URL_KEY)) {	
				ignoreUrlMap.put(valueStr, valueStr);
			}

			if (keyStr.contains(HGLeapConstants.STATIC_AUTH_KEY)) {
				staticKeyMap.put(valueStr, valueStr);
			}

		}
		logger.debug("Finished loading properties file");
		logger.debug("# of ignoreURLs = {} and # of static Keys = {}", ignoreUrlMap.size(), staticKeyMap.size());

	}

	public static boolean isIgnoreURL(String url) {

		Iterator<String> tempIterator = ignoreUrlMap.keySet().iterator();
		while (tempIterator.hasNext()) {
			String string = (String) tempIterator.next();
			if (url.contains(string)) {
				return true;
			}
		}
		return false;

	}

	public static boolean isStaticAuthToken(String authToken) {
		return staticKeyMap.containsKey(authToken);
	}
	
	public static Map<String, String> getIgnoreUrls(){
		return ignoreUrlMap;
	}

	public static Map<String, String> getStaticKeys(){
		return staticKeyMap;
	}

}
