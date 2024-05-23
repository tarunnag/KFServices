package com.haygroup.leap.security2;

import org.apache.commons.lang.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.haygroup.leap.EncryptionUtil;
import com.haygroup.leap.common.AuthPropertiesUtil;
import com.haygroup.leap.security.AuthenticationManager;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Redis implementation of AuthenticationManager
 */
public class RedisAuthenticationManagerImpl implements AuthenticationManager {

	private ObjectMapper objectMapper;
	private JedisPool pool;
	private Jedis jedis = null;
	private int duration;
	EncryptionUtil encryptionUtil=new EncryptionUtil();

	private LocalCache<Object> localcache;
	
	private static final Logger logger = LoggerFactory.getLogger(RedisAuthenticationManagerImpl.class);

	public RedisAuthenticationManagerImpl(String host, String port, String password, String useSSL, Integer connTimeout, Integer duration, String serverType, LocalCache<Object> localcache) {



		logger.info("initializing redis - host " + StringEscapeUtils.escapeJava(host) + " port "+ StringEscapeUtils.escapeJava(port) + " password " + StringEscapeUtils.escapeJava(password) + " connTimeout " + connTimeout);
	
		if (serverType.equalsIgnoreCase("redis")) {

			logger.debug("initializing redis");
			this.localcache = localcache;
			this.objectMapper = new ObjectMapper();
			
			if (password == null || password.equalsIgnoreCase(""))
			{
				
				this.pool = new JedisPool(new JedisPoolConfig(), host, Integer.parseInt(port));
			}
			else
			{
			
				this.pool = new JedisPool(new JedisPoolConfig(), host, Integer.parseInt(port), connTimeout, encryptionUtil.decryptString(password),Boolean.parseBoolean(useSSL) );
			}
			this.duration = duration;

		}

	}

	@Override
	public boolean isAuthenticated(String url, String authToken) {

		return get(authToken) == null ? false : true;

	}

	@Override
	public void addAuthToken(String authToken, String legacyDuration, Object obj) {

		logger.debug("Adding AuthToken,duration - {} {}", StringEscapeUtils.escapeJava(authToken), duration);

		try {

			UserData userData = (UserData) obj;

			String str = objectMapper.writeValueAsString(userData);
			String key = userData.getAuthToken();

			try {
				jedis = pool.getResource();
				jedis.set(key, str);
				jedis.expire(key, duration);
				localcache.put(authToken, obj);
			} finally {
				if (jedis != null)
					jedis.close();
			}

		} catch (JsonProcessingException e) {
			logger.error("Json converation error", e);
		}

	}

	@Override
	public boolean removeAuthToken(String authToken) {

		logger.debug("Removing AuthToken - {}", StringEscapeUtils.escapeJava(authToken));
		try {
			jedis = pool.getResource();
			jedis.del(authToken);
			localcache.remove(authToken);
		} finally {
			if (jedis != null)
				jedis.close();
		}
		return true;

	}

	@Override
	public boolean needsAuthentication(String url) {
		return !AuthPropertiesUtil.isIgnoreURL(url);
	}

	@Override
	public Object get(String authToken) {

		try {
			
			logger.debug("Getting AuthToken from local cache- {}", StringEscapeUtils.escapeJava(authToken));
			UserData userData = (UserData) localcache.get(authToken);

			if(userData == null) {
			logger.debug("Getting AuthToken from server- {}", StringEscapeUtils.escapeJava(authToken));

			String content= null;
			try {
				jedis = pool.getResource();
				content = jedis.get(authToken);
			} finally {
				if (jedis != null)
					jedis.close();
			}

			if (content == null) {
				return null;
			}

			userData = objectMapper.readValue(content, UserData.class);
			localcache.put(authToken, userData);
			}
			
			try {
				jedis = pool.getResource();
				jedis.expire(authToken, duration);
			}finally {
				if (jedis != null)
					jedis.close();
			}			

			LeapAuthenticationToken leapAuthToken = new LeapAuthenticationToken(userData);
			return leapAuthToken;

		} catch (Exception e) {
			logger.error("Json conversion error", e);
			return null;
		}

	}
	
	public static void main(String args[])
	{
		RedisAuthenticationManagerImpl obj =  new RedisAuthenticationManagerImpl("kfproducts-prod-use.redis.cache.windows.net", 
				"6380", "DX3JqWT7SNR3EEaXDAGpkd4ZAcThXdLbyLiLYMcKLuU=", "true", new Integer(2000), new Integer(1200),"redis", null) ;
		System.out.println(obj.get(args[0]));

	}
	

}