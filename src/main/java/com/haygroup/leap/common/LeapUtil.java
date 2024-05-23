package com.haygroup.leap.common;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.util.HtmlUtils;

import com.google.gson.Gson;
import com.haygroup.leap.EncryptionUtil;
import com.haygroup.leap.activity.Activity;
import com.haygroup.leap.activity.ActivityImpl;
import com.haygroup.leap.activity.ActivityManager;
import com.haygroup.leap.domain.LoginResponse;
import com.haygroup.leap.security2.CacheFactory;
import com.haygroup.leap.security2.LeapAuthenticationToken;

//import org.apache.commons.codec.binary.Base64;

public class LeapUtil
{
	
	 EncryptionUtil encryptionUtil=new EncryptionUtil();
	private ActivityManager activityManager;
	
	private CacheFactory cacheFactory;
	
	
	private String password;

	private String endpoint;
	private String userName;
	private Properties prop;
	private String basicAuth;
	private int TIMEOUT = 64000;
	// private PropertiesUtil propUtil;

	public static HashMap<String,Long> sessionTable = new HashMap<String,Long>(); 
	
	private static final Logger logger = LoggerFactory.getLogger(LeapUtil.class);

	public LeapUtil(String userName, String password, String endpoint,ActivityManager activityManager, CacheFactory cacheFactory)
	{
		logger.info("LeapUtil constructor:==="); 
		this.userName = encryptionUtil.decryptString(userName);
		this.password = encryptionUtil.decryptString(password);
		logger.info("username-----" + HtmlUtils.htmlEscape(this.userName) );  
		this.endpoint = endpoint;
		// propUtil= new PropertiesUtil();
		// this.prop= new Properties;
		// restProperties();
		serviceProperties();
		
		this.activityManager = activityManager;
		this.cacheFactory = cacheFactory;
	}

	private void restProperties()
	{

		this.prop = new Properties();
		try
		{
			prop.load(new FileInputStream(
					"\\src\\main\\resources\\RestResources.properties"));

			logger.info("Users:====" + prop.getProperty("users"));
		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void serviceProperties()
	{
		String authStr = getUserName() + ":" + getPassword();
		// String authStr = getUserName() + ";" + "heckler416";
		String basicAuth = "Basic "
				+ Base64.getEncoder().encodeToString(authStr
						.getBytes());

		setBasicAuth(basicAuth);
		System.out.println(basicAuth);
		logger.info("basicAuth------------" + HtmlUtils.htmlEscape(basicAuth));
	}

	private String getBasicAuth()
	{
		return basicAuth;
	}

	private void setBasicAuth(String basicAuth)
	{
		this.basicAuth = basicAuth;
	}

	// public PropertiesUtil getPropUtil() {
	// return propUtil;
	// }
	// public void setPropUtil(PropertiesUtil propUtil) {
	// this.propUtil = propUtil;
	// }

	private String getPassword()
	{
		return password;
	}

	private void setPassword(String password)
	{
		this.password = password;
	}

	private String getEndpoint()
	{
		return endpoint;
	}

	private void setEndpoint(String endpoint)
	{
		this.endpoint = endpoint;
	}

	private String getUserName()
	{
		return userName;
	}

	private void setUserName(String userName)
	{
		this.userName = userName;
	}


	/**
	 * @param url
	 * @param response
	 */
	private void streamOutput(URL url, HttpServletResponse response)
	{
		String charset = "UTF-8";
	}
	
	
	/**
	 * @param url
	 * @param response
	 */
	private void streamOutput(URL url, HttpServletRequest request, HttpServletResponse response)
	{
		String charset = "UTF-8";

		try
		{

			HttpURLConnection uc = (HttpURLConnection) url.openConnection();

			uc.setConnectTimeout(TIMEOUT);
			uc.setReadTimeout(TIMEOUT);
			
			logger.info("Setting timeout");
			logger.info("Setting authToken "+ StringEscapeUtils.escapeJava((String)request.getAttribute("authToken")));
			
			uc.setRequestProperty(HGLeapConstants.AUTH_TOKEN, (String)request.getAttribute("authToken"));
			String authStr = getUserName() + ";" + getPassword();
			String basicAuth = Base64.getEncoder().encodeToString(authStr.getBytes(charset));
			//logger.info("basicAuth:===" + HtmlUtils.htmlEscape(basicAuth));
			uc.setRequestProperty("Authorization", getBasicAuth());

			logger.info("streamOutput URL is :===" + url);



			logger.info("after streaming URL");

			InputStream is;

			if (response != null)
			{
				response.setContentType(StringEscapeUtils.escapeJava(uc.getContentType()));
				response.setStatus(uc.getResponseCode());
				
				if(uc.getResponseCode() == 200)
				{
					is =  uc.getInputStream();
				}
				else
				{
					is =  uc.getErrorStream();
				}
				
				if(is!=null)
				{
					int data;
					while ((data = is.read()) != -1)
					{
						response.getOutputStream().write(data);
					}
				}
			}
			else
			{
				is = (InputStream) uc.getInputStream();
				int data;
				while ((data = is.read()) != -1)
				{
					System.out.println(data);
				}
			}
			if(is!=null)
			{
				is.close();
			}

		}
		catch (MalformedURLException e1)
		{
			e1.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	
	/**
	 * @param url
	 * @param body
	 * @param response
	 */
	private void streamDELETEGeneric(URL url, HttpServletRequest request,
			HttpServletResponse response)
	{
		String charset = "UTF-8";

		try
		{

			HttpURLConnection uc = (HttpURLConnection) url.openConnection();
			uc.setDoOutput(true);
			uc.setRequestProperty("Accept-Charset", charset);
			uc.setRequestProperty("Content-Type","application/json;charset=" + charset);
			uc.setRequestMethod("DELETE");
			
			logger.info("Setting authToken to "+StringEscapeUtils.escapeJava((String)request.getAttribute("authToken")));
			if(request.getAttribute("authToken")!=null)
			{
				uc.setRequestProperty(HGLeapConstants.AUTH_TOKEN, (String)request.getAttribute("authToken"));
			}
			else
			{
				logger.info("Not setting authtoken in header");
			}
			
			//logger.info("using basicAuth:===" + HtmlUtils.htmlEscape(getBasicAuth()));
			uc.setRequestProperty("Authorization", getBasicAuth());
			StringBuilder sb = new StringBuilder();

			logger.info("Reading output from the server");
			InputStream is;

			if (response != null)
			{
				//response.setContentType("application/json");
				response.setContentType(StringEscapeUtils.escapeJava(uc.getContentType()));
				response.setStatus(uc.getResponseCode());
				
				String responseCode =  "";
				if(uc.getResponseCode() == 200)
				{
					is = (InputStream) uc.getInputStream();
					responseCode = "{\"responseCode\": \"RES.20000\", \"responseMessage\": \"Resource Deleted successfully\"}";
				}
				else
				{
					is = (InputStream) uc.getErrorStream();
					responseCode = "{\"responseCode\": \"SYS.10000\", \"responseMessage\": \"System error occured\"}";
				}				
//				is = (InputStream) uc.getInputStream();
				//TODOS: FIX THIS
				
				responseCode = StringEscapeUtils.unescapeJava(responseCode);
				response.getOutputStream().write(responseCode.toString().getBytes(charset));
				is.close();
			}
			
			

		}
		catch (MalformedURLException e1)
		{
			e1.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	
	

	/**
	 * @param url
	 * @param body
	 * @param response
	 */
	private void streamPUTGeneric(URL url, HttpServletRequest request,
			HttpServletResponse response)
	{
		String charset = "UTF-8";

		try
		{

			HttpURLConnection uc = (HttpURLConnection) url.openConnection();
			uc.setConnectTimeout(TIMEOUT);
			uc.setReadTimeout(TIMEOUT);			
			uc.setDoOutput(true);
			uc.setRequestProperty("Accept-Charset", charset);
			uc.setRequestProperty("Content-Type","application/json;charset=" + charset);
			uc.setRequestMethod("PUT");
			
			logger.info("Setting authToken to "+StringEscapeUtils.escapeJava((String)request.getAttribute("authToken")));
			if(request.getAttribute("authToken")!=null)
			{
				uc.setRequestProperty(HGLeapConstants.AUTH_TOKEN, (String)request.getAttribute("authToken"));
			}
			else
			{
				logger.info("Not setting authtoken in header");
			}
			
			//logger.info("using basicAuth:===" + HtmlUtils.htmlEscape(getBasicAuth()));
			uc.setRequestProperty("Authorization", getBasicAuth());
			StringBuilder sb = new StringBuilder();

//			if(request!=null)
//			{
//				// Reading the body of the HTTP POST
//				BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
//				String line = "";
//				while ((line = reader.readLine()) != null)
//				{
//					sb.append(line);
//				}
//				reader.close();
//				logger.info("Body of the post is " + sb.toString());
//				uc.getOutputStream().write(sb.toString().getBytes(charset));
//			}
		
			
			if(request!=null)
			{
				InputStream is = request.getInputStream();
				int data;
				while ((data = is.read()) != -1)
				{
					//logger.info("writing data");
					uc.getOutputStream().write(data);
				}				
			}
			
			
			logger.info("Reading output from the server");
			InputStream is;

			if (response != null)
			{
//				response.setContentType("application/json");
				response.setContentType(StringEscapeUtils.escapeJava(uc.getContentType()));
				response.setStatus(uc.getResponseCode());
				
				if(uc.getResponseCode() == 200)
				{
					is = (InputStream) uc.getInputStream();
				}
				else
				{
					is = (InputStream) uc.getErrorStream();
				}
//				is = (InputStream) uc.getInputStream();
				int data;
				while ((data = is.read()) != -1)
				{
					response.getOutputStream().write(data);
				}
				is.close();
			}
			else
			{
				is = (InputStream) uc.getInputStream();
				int data;
				while ((data = is.read()) != -1)
				{
					System.out.println(data);
				}
			}
			is.close();

		}
		catch (MalformedURLException e1)
		{
			e1.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	
	
	
	
	/**
	 * @param url
	 * @param body
	 * @param response
	 */
	private void streamPostGeneric(URL url, HttpServletRequest request,
			HttpServletResponse response)
	{
		Activity activity = new ActivityImpl();
		activity.setUrl(url.toString());
		
		String charset = "UTF-8";

		try
		{

			HttpURLConnection uc = (HttpURLConnection) url.openConnection();
			uc.setConnectTimeout(TIMEOUT);
			uc.setReadTimeout(TIMEOUT); 
			uc.setDoOutput(true);
			uc.setRequestProperty("Accept-Charset", charset);
			uc.setRequestProperty(HGLeapConstants.HEADER_CONTENT_TYPE,request.getHeader(HGLeapConstants.HEADER_CONTENT_TYPE));

			logger.info("Header content type to "+StringEscapeUtils.escapeJava(request.getHeader(""+HGLeapConstants.HEADER_CONTENT_TYPE)));
			
			if(request.getAttribute("authToken")!=null)
			{
				uc.setRequestProperty(HGLeapConstants.AUTH_TOKEN, (String)request.getAttribute("authToken"));
				activity.setAuthToken((String)request.getAttribute("authToken"));
			}
			else
			{
				logger.info("Not setting authtoken in header");
			}
			
			//logger.info("using basicAuth:===" + getBasicAuth());
			uc.setRequestProperty("Authorization", getBasicAuth());

			if(request!=null)
			{
				InputStream is = request.getInputStream();
				ByteArrayOutputStream tempOS = new ByteArrayOutputStream();
				int data;
				while ((data = is.read()) != -1)
				{
					//logger.info("writing data");
					uc.getOutputStream().write(data);
					tempOS.write(data);
				}
				activity.setRequestData(new String(tempOS.toByteArray()));
			}
		
			
			logger.info("Reading output from the server");
			InputStream is;

			if (response != null)
			{
				response.setContentType(StringEscapeUtils.escapeJava(uc.getContentType()));
				response.setStatus(uc.getResponseCode());
				
				logger.info("ResponseCode is :"+uc.getResponseCode());
				if(uc.getResponseCode() == 200)
				{
					is = (InputStream) uc.getInputStream();
				}
				else
				{
					is = (InputStream) uc.getErrorStream();
				}
			
				if(is!=null)
				{
					logger.info("InputStream is :"+StringEscapeUtils.escapeJava(is.toString()));
					int data;

					ByteArrayOutputStream tempOS = new ByteArrayOutputStream();
					while ((data = is.read()) != -1)
					{
						response.getOutputStream().write(data);
						tempOS.write(data);
					}
					is.close();
					activity.setResponseData(new String(tempOS.toByteArray()));
				}
			}
			else
			{
				is = (InputStream) uc.getInputStream();
				int data;
				while ((data = is.read()) != -1)
				{
					System.out.println(data);
				}
			}
			if(is!=null)
			{
				is.close();
			}

			activityManager.log(activity);
			
		}
		catch (MalformedURLException e1)
		{
			e1.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}


	/**
	 * @param url
	 * @param body
	 * @param response
	 */
	private void streamPostLogin(URL url, HttpServletRequest request,HttpServletResponse response)
	{
		String charset = "UTF-8";
		HttpURLConnection uc = null;
		try
		{

			uc = (HttpURLConnection) url.openConnection();
			uc.setDoOutput(true);
			uc.setRequestProperty("Accept-Charset", charset);
			uc.setRequestProperty("Content-Type","application/json;charset=" + charset);
			
			//logger.info("using basicAuth:===" + HtmlUtils.htmlEscape(getBasicAuth()));
			uc.setRequestProperty("Authorization", getBasicAuth()); 
			StringBuilder sb = new StringBuilder();

			if(request!=null)
			{
				// Reading the body of the HTTP POST
				try(InputStream is=request.getInputStream()){
				BufferedReader reader = new BufferedReader(new InputStreamReader(is));
				String line = "";
				while ((line = reader.readLine()) != null)
				{
					sb.append(line);
				}
				reader.close();
				//logger.info("Body of the post is " + HtmlUtils.htmlEscape(sb.toString()));
				uc.getOutputStream().write(HtmlUtils.htmlEscape(sb.toString()).getBytes(charset));
				is.close();
				}
			}
			else
			{
		
			}
			
			
			logger.info("Reading output from the server");
			InputStream is=null;

			if (response != null)
			{
				try {
				response.setContentType(StringEscapeUtils.escapeJava(uc.getContentType()));
				response.setStatus(uc.getResponseCode());
				
				if(uc.getResponseCode() == 200)
				{
					is = (InputStream) uc.getInputStream();
				}
				else
				{
					is = (InputStream) uc.getErrorStream(); 
				}

				BufferedReader reader = new BufferedReader(new InputStreamReader(is));
				
				String line = "";
				StringBuilder sb1 = new StringBuilder();
				while ((line = reader.readLine()) != null)
				{
					sb1.append(line);
				}
				reader.close();	
				//logger.info("Response is " + HtmlUtils.htmlEscape(sb1.toString()));	
				String responseString = sb1.toString();
				
				Gson gson = new Gson();
				if(uc.getResponseCode() == 200)
				{
					LoginResponse loginResponse = gson.fromJson(responseString, LoginResponse.class);
					
					//logger.info("User object from login response :"+loginResponse.getData().getUserId()+"authToken ==>"+HtmlUtils.htmlEscape(loginResponse.getData().getAuthToken()));
					String authToken 	= loginResponse.getData().getAuthToken();
					long userId			= loginResponse.getData().getUserId();
					addToSessionTable(authToken,userId);
				}
			
				
				
//				int authTokenIndex = responseString.indexOf("\"authToken\"");
//				int colonIndex = responseString.indexOf(":",authTokenIndex);
//				int commaIndex = responseString.indexOf(",",authTokenIndex);
//				String authToken = responseString.substring(colonIndex+2,commaIndex-1);
//				String authToken = loginResponse.getData().getAuthToken();
//				logger.info("Adding "+authToken+" to session table");
//				addToSessionTable(authToken);

				
				
				
				responseString = StringEscapeUtils.unescapeJava(sb1.toString());
				response.getOutputStream().write(responseString.getBytes(charset));
				}catch (Exception e) {
					logger.error("Exception :",e);
				}
				finally {
					try {
						if (is != null) {
							is.close();
						}
					} catch (Exception e) {
						logger.error("Exception :",e);
					}
				}
				
			}
			else
			{
				if(uc.getResponseCode() == 200)
				{
					is = (InputStream) uc.getInputStream();
				}
				else
				{
					is = (InputStream) uc.getErrorStream();
				}
				is = (InputStream) uc.getInputStream();
				int data;
				while ((data = is.read()) != -1)
				{
					System.out.println(data);
				}
				is.close();
			}
			
		}
		catch (MalformedURLException e1)
		{
			e1.printStackTrace();
		}
		catch (IOException e)
		{
			if(uc!=null)
			{
				
			}
			e.printStackTrace();
		}
	}

	
	
	
	
	/**
	 * @param url
	 * @param body
	 * @param response
	 */
	private void streamPostOutput(URL url, String body,
			HttpServletResponse response)
	{
		String charset = "UTF-8";

		try
		{

			HttpURLConnection uc = (HttpURLConnection) url.openConnection();
			uc.setDoOutput(true);
			uc.setRequestProperty("Accept-Charset", charset);
			uc.setRequestProperty("Content-Type","application/x-www-form-urlencoded;charset=" + charset);
			uc.setRequestProperty(HGLeapConstants.AUTH_TOKEN, "sseess");
			//logger.info("using basicAuth:===" + HtmlUtils.htmlEscape(getBasicAuth()));
			uc.setRequestProperty("Authorization", getBasicAuth());

			OutputStream output = null;
			try
			{
				output = uc.getOutputStream();
				output.write(body.getBytes(charset));

			}
			finally
			{
				if (output != null)
					try
					{
						output.close();
					}
					catch (IOException logOrIgnore)
					{
					}
			}

			//logger.info("URL:===" + url);

			InputStream is;

			if (response != null)
			{
				response.setContentType("application/json");
				is = (InputStream) uc.getInputStream();
				int data;
				while ((data = is.read()) != -1)
				{
					response.getOutputStream().write(data);
				}
			}
			else
			{
				is = (InputStream) uc.getInputStream();
				int data;
				while ((data = is.read()) != -1)
				{
					System.out.println(data);
				}
			}
			is.close();

		}
		catch (MalformedURLException e1)
		{
			e1.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	/*public static void main(String args[])
	{
		LeapUtil leapUtil = new LeapUtil("leapuser", "leapuser",
				"http://10.10.205.24:5555",null, null);
		// LeapUtil leapUtil = new LeapUtil("leapadmin",
		// "leapadmin","http://127.0.0.1:9000");

		URL url;
		try
		{
			url = new URL("http://10.10.205.24:5555/rest/HGLeapMain/model/action/login");
//
			String body = "{ \"username\" :\"om_baghel@haygroup.com\", \"password\" :\"om\" }";
			//leapUtil.streamPostOutput(url, body, null);
			//leapUtil.streamPostGeneric1(url, null, null);
			leapUtil.streamPostLogin(url, null, null);

			//url = new URL("http://10.10.205.24:5555/rest/HGLeapMain/model/references/families");

			//String body = "jsonString={ \"username\" :\"om_baghel@haygroup.com\", \"password\" :\"om\" }";

		
		}
		catch (MalformedURLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}*/
	
	/*public static void main5(String[] args)
	{
		String jsonInput =  "{\"name1\":\"Spike1\",\"authtoken\":\"8029eac8-064c-47ff-b9c8-65cf2f4d26d6\",\"name\":\"Spike\"}"; 
		String in = "\"c\"";
		System.out.println(in);
		System.out.println(jsonInput.indexOf("\"authtoken\":"));
		System.out.println(jsonInput.indexOf(":",18));
		System.out.println(jsonInput.indexOf(",",18));
		System.out.println(jsonInput.substring(31,67));
		System.out.println(StringEscapeUtils.unescapeJava(in));
		
	}*/

	/**
	 * @param string
	 */
	private static void addToSessionTable(String key)
	{
		sessionTable.put(key, System.currentTimeMillis());
	}
	
	/**
	 * @param key
	 * @return
	 */
	private static boolean isSessionValid(String key)
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
	private static void printSessionTokens()
	{
		
		Set set = sessionTable.entrySet();
		Iterator iter = set.iterator();
		
		while(iter.hasNext())
		{
			logger.info("Value is "+iter.next());
		}
		
	}
	
	/**
	 * @param string
	 */
	private static void addToSessionTable(String key,long userId)
	{
		sessionTable.put(key, userId);
	}	
	
	
	public String getUserId(String authToken)
	{
		//return authenticationManager.get(authToken).toString();
		
		if(authToken!=null)
		{
		LeapAuthenticationToken data = (LeapAuthenticationToken) cacheFactory.getInstance().get(authToken);
		if(data != null)
			return data.getUserData().getUserid();
		else
			return null;
		}
		else
		{
			return null;
		}
	}
	
	public String getLocale(String authToken)
	{
		//return authenticationManager.get(authToken).toString();

		//System.out.println("Authentication manager "+authenticationManager);
		if(authToken!=null)
		{
			LeapAuthenticationToken data = (LeapAuthenticationToken) cacheFactory.getInstance().get(authToken);
			if(data != null)
				return data.getUserData().getLocale();
			else
				return null;
		}
		else
			return null;
		
	}
	
	public String getUserAttribute(String authToken,String attributeName)
	{
		//return authenticationManager.get(authToken).toString();

		//System.out.println("Authentication manager "+authenticationManager);
		String retVal = "";
		if(authToken!=null)
		{
			LeapAuthenticationToken data = (LeapAuthenticationToken) cacheFactory.getInstance().get(authToken);
			if(data != null)
			{
				if(attributeName.equalsIgnoreCase(HGLeapConstants.USER_ID))
				{
					retVal = data.getUserData().getUserid();
				}
				else if(attributeName.equalsIgnoreCase(HGLeapConstants.CLIENT_ID))
				{
					retVal = data.getUserData().getClientId();
				}
				else if(attributeName.equalsIgnoreCase(HGLeapConstants.LOCALE))
				{
					retVal 	=	data.getUserData().getLocale();
				}
			}
			else
			{
				retVal = "";
			}
		}
		else
		{
			retVal = "";
		}
			
		
		return retVal;
		
	}
	
}


