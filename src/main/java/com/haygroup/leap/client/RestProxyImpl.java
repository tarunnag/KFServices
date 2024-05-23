package com.haygroup.leap.client;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.text.MessageFormat;
import java.util.Base64;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.util.HtmlUtils;

import com.haygroup.leap.EncryptionUtil;
import com.haygroup.leap.activity.Activity;
import com.haygroup.leap.activity.ActivityImpl;
import com.haygroup.leap.activity.ActivityManager;
import com.haygroup.leap.common.HGLeapConstants;
import com.haygroup.leap.common.LeapUtil;
import com.haygroup.leap.common.PropertiesUtil;
//import com.haygroup.leap.common.PropertyFileUtil; 
import com.haygroup.leap.common.PropertyFileUtil;
import org.apache.commons.lang.StringUtils;


public class RestProxyImpl implements RestProxy {

	//@Autowired
	private LeapUtil leapUtil;	
	private ActivityManager activityManager;
	private String password;
	private String baseUrl;
	private String userName;
	private String basicAuth;	
	private int timeout;
	private long totalTimeTaken;
	EncryptionUtil encryptionUtil=new EncryptionUtil();
	static String chars="[`^|{}<>]";
	private static final Logger logger = LoggerFactory.getLogger(RestProxyImpl.class);

	public RestProxyImpl(String userName, String password, String baseUrl, String timeout, ActivityManager activityManager,LeapUtil leapUtil) 
	{
	
		if(!userName.isEmpty()) {	
		this.userName = encryptionUtil.decryptString(userName);
		}
		if(!password.isEmpty()) {
		this.password = encryptionUtil.decryptString(password);
		}
		
		this.baseUrl = baseUrl;
		this.timeout = Integer.parseInt(timeout);
		this.leapUtil = leapUtil;
		logger.info("Initializing RestProxyImpl Constructor "+this.leapUtil);
		String authStr = this.userName + ":" + this.password;
		this.basicAuth = "Basic " + Base64.getEncoder().encodeToString(authStr.getBytes());
		logger.debug("RestProxyImpl-->basicAuth="+StringEscapeUtils.escapeJava(this.basicAuth));
		this.activityManager = activityManager;
		patchHttpsUrlConnectionToTrustSelfSignedSSLCerts();
	}

	public boolean stream(String urlId, HttpServletRequest request, HttpServletResponse response, String[] urlParameters) {
		return stream(urlId, request, response, urlParameters, request.getMethod());
	}
	
	public boolean secureStream(String urlId, HttpServletRequest request, HttpServletResponse response, String[] urlParameters) {
		return secureStream(urlId, request, response, urlParameters, request.getMethod());
	}
	
	@Override
	/*
	 * Only used by Login
	 * @see com.haygroup.leap.client.RestProxy#stream(java.lang.String, javax.servlet.http.HttpServletRequest, java.lang.String[], java.lang.String)
	 */
	public byte[] stream(String urlId, HttpServletRequest request, String[] urlParameters, String method) {
		MockHttpResponse mockResponse = new MockHttpResponse();
		stream(urlId, request, mockResponse, urlParameters, method);
		return mockResponse.getData();
	}
	

	
	//@Override
	//Not used currently
	@Deprecated
	public boolean httpStream(String urlId, HttpServletRequest request, HttpServletResponse response, String[] urlParameters, String httpMethod) {
		
		// Record activity
		Activity activity = new ActivityImpl(request);
		try {

			// Get url corresponding to urlId
			String urlString = PropertiesUtil.getProperty(urlId);
			logger.debug("urlId {} maps to url {}", StringEscapeUtils.escapeJava(urlId), StringEscapeUtils.escapeJava(urlString));

			for(int index=0;index<urlParameters.length;index++)
			{
				urlParameters[index] = URLEncoder.encode(urlParameters[index],"UTF-8");
			}
			
			// Url substitution
			urlString = MessageFormat.format(urlString, (Object[]) urlParameters);
			logger.debug("final url after substitution " + StringEscapeUtils.escapeJava(getBaseUrl()) +StringEscapeUtils.escapeJava(urlString));

			urlString = appendParams(urlString,request);
			logger.debug("Opening Connection to Target Server -{}-", StringEscapeUtils.escapeJava(getBaseUrl()) + StringEscapeUtils.escapeJava(urlString));
			logger.debug("HTTP Method {}", StringEscapeUtils.escapeJava(httpMethod));
			
			URL url = new URL(getBaseUrl() + urlString);
			HttpURLConnection uc = (HttpURLConnection) url.openConnection();
			uc.setConnectTimeout(timeout);
			uc.setReadTimeout(timeout);
			uc.setDoOutput(true);
			uc.setRequestProperty("Authorization", getBasicAuth());
			uc.setRequestMethod(httpMethod);

			logger.debug("Copying Headers");
			Enumeration enumeration = request.getHeaderNames();
			while (enumeration.hasMoreElements()) {
				String header = (String) enumeration.nextElement();
				logger.info("inside while loop headers part");
				if(header.compareToIgnoreCase(HGLeapConstants.AUTH_TOKEN) == 0) {
					header = HGLeapConstants.AUTH_TOKEN;
				}
				uc.setRequestProperty(header, request.getHeader(header));
			}
			
			if(request.getAttribute(HGLeapConstants.AUTH_TOKEN) != null) {
				uc.setRequestProperty(HGLeapConstants.AUTH_TOKEN, (String) request.getAttribute("authToken"));
			}else if(request.getAttribute(HGLeapConstants.AUTHORIZATION) != null) {
				uc.setRequestProperty(HGLeapConstants.AUTH_TOKEN, (String) request.getAttribute("Authorization"));
			}
			
			uc.connect();
			
			//Reading from Source-Input and Writing to Target-Output and temporary output			
			if("POST".equals(httpMethod) || "PUT".equals(httpMethod)){

				logger.debug("Reading from Source-Input and Writing to Target-Output");
				ByteArrayOutputStream tempOS = new ByteArrayOutputStream();
				int data;				
				InputStream is = request.getInputStream();
				if (is != null) {
					while ((data = is.read()) != -1) {
						uc.getOutputStream().write(data);
						tempOS.write(data);
					}
					activity.setRequestData(new String(tempOS.toByteArray()));
				} else {
					logger.debug("Nothing to read from Source-Input");
				}
				uc.getOutputStream().flush();
				uc.getOutputStream().close();
			}
			
			logger.debug("Completed sending request to Target Server. Begining processing output from Target Server");
			response.setContentType(uc.getContentType());
			response.setStatus(uc.getResponseCode());
			response.setHeader(HGLeapConstants.CONTENT_DISPOSITION,uc.getHeaderField(HGLeapConstants.CONTENT_DISPOSITION));
			
			InputStream is = null;
			ByteArrayOutputStream tempOS = new ByteArrayOutputStream();
			int data;
			
			logger.debug("ResponseCode " + uc.getResponseCode());
			if (uc.getResponseCode() == 200 || uc.getResponseCode() == 201) {
				is = (InputStream) uc.getInputStream();
			} else {
				is = (InputStream) uc.getErrorStream();
			}

			//Reading from Target-Input and Writing to Source-Output and temporary output
			if (is != null) {
				logger.debug("Reading from Target-Input and Writing to Source-Output");
				tempOS = new ByteArrayOutputStream();
				while ((data = is.read()) != -1) {
					response.getOutputStream().write(data);
					tempOS.write(data);
				}
				is.close();
				activity.setResponseData(new String(tempOS.toByteArray()));
			} else {
				logger.warn("Nothing to read from Target-Input");
			}

			return true;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			try{
				response.setStatus(200);
				response.setContentType("application/json");
				response.getOutputStream().print("{\"responseCode\" : \"RES.500\", \"responseMessage\" : \"System Error\"}");
			}catch(Exception ex){
				ex.printStackTrace();
			}
		} finally{
			//Log Activity
			activity.setResponseTimeStamp(0);
			if(!PropertyFileUtil.identifyURL(activity.getUrl())) {
				activityManager.log(activity);
			}
		}

		return false;
	}

	private String appendParams(String urlString, HttpServletRequest request) 
	{
		String locale 			= leapUtil.getUserAttribute(request.getHeader(HGLeapConstants.AUTH_TOKEN),HGLeapConstants.LOCALE);
		String loggedInUserId 	= leapUtil.getUserAttribute(request.getHeader(HGLeapConstants.AUTH_TOKEN),HGLeapConstants.USER_ID);
		String loggedInUserClientId	= leapUtil.getUserAttribute(request.getHeader(HGLeapConstants.AUTH_TOKEN),HGLeapConstants.CLIENT_ID);
		
		if(locale!=null && !locale.equalsIgnoreCase("")) {
			if(urlString.indexOf("?")>0) {
				urlString = urlString +"&locale="+locale;
			} else {
				urlString = urlString +"?locale="+locale;
			}
		}
		
		if(loggedInUserId!=null && !loggedInUserId.equalsIgnoreCase("")) {
			if(urlString.indexOf("?")>0) {
				urlString = urlString+"&userId="+loggedInUserId;
			} else {
				urlString = urlString+"?userId="+loggedInUserId;
			}
		}
		
		if(loggedInUserClientId!=null && !loggedInUserClientId.equalsIgnoreCase("")) {
			//String clientIdKey = null;
			String clientIdKey = "loggedInUserClientId"; //default to loggedInUserClientId

			
			/*
			 * old logic for KFAS requests
			// KFAS default to client id, rather than loggedInUserClientId, unless there is an existing client id which could 
			// be different, in which case use the default loggedInUserClientId. (Although this isn't used in KFAS).
			if(isKFASRequest(urlString) && !urlString.contains("clientId")) {
				clientIdKey = "clientId";	
			} else {
				clientIdKey = "loggedInUserClientId";
			}*/
			
			//New logic for KFAS. Replace ClientId param if already exists. 
			//If not exists, append clientId param with value driven from auth token
			if(isKFASRequest(urlString)){
				clientIdKey = "clientId";
				if(urlString.contains("?clientId=") || urlString.contains("&clientId=")){
					urlString = removeParam(urlString, "clientId");
					if(urlString.endsWith("&")) {
						urlString = StringUtils.chop(urlString);
					}
				}
			}
			
			if(urlString.indexOf("?")>0) {
				urlString = urlString+"&"+clientIdKey+"="+loggedInUserClientId;
			} else {
				urlString = urlString+"?"+clientIdKey+"="+loggedInUserClientId;
			}
		}
		
		return urlString;
	}
	
	private String removeParam(String urlString, String param) {
		String regex = "(?<=[?&;])"+param+"=.*?($|[&;])";	
		String newUrl = urlString.replaceFirst(param,"");
		return newUrl;
		
	}

	/**
	 * Determine if the url is for the KFAS platform, this drives how the automatic query string parameters are appended.
	 * @param urlString the base url e.g. integrationrest/ta/v1.0/projects?parameter=1
	 * @return true if the url for meant for the KFAS system, otherwise false.
	 */
	private boolean isKFASRequest(String urlString) {
		urlString = urlString.toLowerCase();
		return 	urlString.indexOf("integrationrest/ta/") > 0 ||  // Talent Assessment / Select
				urlString.indexOf("integrationrest/tm/") > 0 ||  // Talent Management / Assess
				urlString.indexOf("integrationrest/kfas/") > 0;  // General / Legacy API method
	}

	public ActivityManager getActivityManager() {
		return activityManager;
	}

	public void setActivityManager(ActivityManager activityManager) {
		this.activityManager = activityManager;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getBasicAuth() {
		return basicAuth;
	}

	public void setBasicAuth(String basicAuth) {
		this.basicAuth = basicAuth;
	}

	
	public boolean secureStream(String urlId, HttpServletRequest request, HttpServletResponse response, String[] urlParameters,String httpMethod) {
		return stream( urlId,  request,  response, urlParameters, httpMethod);

	}
	
	private HttpURLConnection getHttpURLConnection(String baseUrl, URL url) {
		try {
			if(getBaseUrl().startsWith("https"))
			{
				return (HttpsURLConnection) url.openConnection();
			}
			else 
			{
				return (HttpURLConnection) url.openConnection();
			}
		} catch(Exception ex) {
			logger.error("Error opening URL Connection", ex);
		}
		return null;
	}
	
	
	@Override
	public boolean stream(String urlId, HttpServletRequest request, HttpServletResponse response, String[] urlParameters,String httpMethod) {
	
		//record activity
		Activity activity = new ActivityImpl(request);
		try {
		 if(containsSpecialChars(request.getQueryString())) {
			 	response.setStatus(500);
	        	response.setContentType("application/json");
				response.getOutputStream().print("{\"responseCode\" : \"RES.500\", \"responseMessage\" : \"Invalid Characters found in the request param\"}");	
	        }
        else {
			//get url corresponding to urlId
			String urlString = PropertiesUtil.getProperty(urlId);
			logger.debug("urlId {} maps to url {}", StringEscapeUtils.escapeJava(urlId), StringEscapeUtils.escapeJava(urlString));
	
			for(int index=0;index<urlParameters.length;index++)
			{
				urlParameters[index] = URLEncoder.encode(urlParameters[index],"UTF-8");
			}
			
			//url substituation
			urlString = MessageFormat.format(urlString, (Object[]) urlParameters);
			logger.debug("final url after substitution " + StringEscapeUtils.escapeJava(urlString));
	
			urlString = appendParams(urlString,request);
			logger.debug("Opening Connection to Target Server -{}-", StringEscapeUtils.escapeJava(getBaseUrl()) + StringEscapeUtils.escapeJava(urlString));
			logger.debug("HTTP Method {}", StringEscapeUtils.escapeJava(httpMethod));
			
			//urlString = appendParams(urlString,request);
			
			URL url = new URL(getBaseUrl() + urlString);
			HttpURLConnection uc = getHttpURLConnection(getBaseUrl(),url);
			uc.setConnectTimeout(timeout);
			uc.setReadTimeout(timeout);
			uc.setDoOutput(true);
			uc.setRequestProperty("Authorization", getBasicAuth());
			uc.setRequestMethod(httpMethod);
	
			logger.debug("Copying Headers");
			Enumeration enumeration = request.getHeaderNames();
			while (enumeration.hasMoreElements()) {
				String header = (String) enumeration.nextElement();
				
				if(header.compareToIgnoreCase(HGLeapConstants.AUTH_TOKEN) == 0) {
					//logger.info("inside while loop normal headers part");
					header = HGLeapConstants.AUTH_TOKEN;
				}else if(header.compareToIgnoreCase(HGLeapConstants.AUTHORIZATION)== 0) {
					//logger.info("inside while loop ucp headers part");
					header = HGLeapConstants.AUTHORIZATION;
				}
				
				uc.setRequestProperty(header, request.getHeader(header));
			}
			
			if(request.getAttribute(HGLeapConstants.AUTH_TOKEN) != null){
				logger.info("inside if condition  normal headers part");
				uc.setRequestProperty(HGLeapConstants.AUTH_TOKEN, (String) request.getAttribute("authToken"));
			}else if(request.getAttribute(HGLeapConstants.AUTHORIZATION) != null) {
				logger.info("inside if condition ucp headers part");
				uc.setRequestProperty(HGLeapConstants.AUTH_TOKEN, (String) request.getAttribute("Authorization"));
			}
			
			uc.connect();
			
			//Reading from Source-Input and Writing to Target-Output and temporary output			
			if("POST".equals(httpMethod) || "PUT".equals(httpMethod)){
	
				logger.debug("Reading from Source-Input and Writing to Target-Output");
				ByteArrayOutputStream tempOS = new ByteArrayOutputStream();
				int data;				
				InputStream is = request.getInputStream();
				if (is != null) {
					while ((data = is.read()) != -1) {
						uc.getOutputStream().write(data);
						tempOS.write(data);
					}
					activity.setRequestData(new String(tempOS.toByteArray()));
				}else{
					logger.debug("Nothing to read from Source-Input");
				}
				uc.getOutputStream().flush();
				uc.getOutputStream().close();
			
			}
			
			
			logger.debug("Completed sending request to Target Server. Begining processing output from Target Server");
			response.setContentType(uc.getContentType()==null?null:StringEscapeUtils.escapeJava(uc.getContentType().trim()));
			response.setStatus(uc.getResponseCode());
			response.setHeader(HGLeapConstants.CONTENT_DISPOSITION,
					uc.getHeaderField(HGLeapConstants.CONTENT_DISPOSITION)==null?null:
						StringEscapeUtils.unescapeJava(uc.getHeaderField(HGLeapConstants.CONTENT_DISPOSITION).trim()));
			response.setHeader(HGLeapConstants.LOCATION,
					uc.getHeaderField(HGLeapConstants.LOCATION)==null?null:
						StringEscapeUtils.unescapeJava(uc.getHeaderField(HGLeapConstants.LOCATION).trim()));

			
//			Not working because of CORS issue in Access-Control-Allow Origin has multiple values(In architect and DCT app)
//			Map<String,List<String>> responseHeaders = uc.getHeaderFields();
//			
//			for(Map.Entry<String,List<String>> entry :responseHeaders.entrySet())
//			{
//			response.addHeader(entry.getKey(), entry.getValue().get(0));
//			} 
//			
			InputStream is = null;
			ByteArrayOutputStream tempOS = new ByteArrayOutputStream();
			int data;
			
			logger.debug("ResponseCode " + uc.getResponseCode());
			if (uc.getResponseCode() == 200 || uc.getResponseCode() == 201) {
				is = (InputStream) uc.getInputStream();
			} else {
				is = (InputStream) uc.getErrorStream();
			}
	
			//Reading from Target-Input and Writing to Source-Output and temporary output
			if (is != null) {
				logger.debug("Reading from Target-Input and Writing to Source-Output");
				tempOS = new ByteArrayOutputStream();
				while ((data = is.read()) != -1) {
					response.getOutputStream().write(data);
					tempOS.write(data);
				}
				is.close();
				activity.setResponseData(new String(tempOS.toByteArray()));
			}else{
				logger.warn("Nothing to read from Target-Input");
			}
	
	
			return true;
		}
		}
		catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			try{
				response.setStatus(500);
				response.setContentType("application/json");
				response.getOutputStream().print("{\"responseCode\" : \"RES.500\", \"responseMessage\" : \"System Error\"}");
			}catch(Exception ex){
				ex.printStackTrace();
			}
		} finally{
			//Log Activity
			activity.setResponseTimeStamp(0);
			if(!PropertyFileUtil.identifyURL(activity.getUrl())) {
				activityManager.log(activity);
			}
		}
		
        return false;
	}
		
	private void patchHttpsUrlConnectionToTrustSelfSignedSSLCerts() {
		try {
			HostnameVerifier hostnameVerifier = new HostnameVerifier() {
				@Override
				public boolean verify(String hostname, SSLSession session) {
					return true;
				}
			};

			HttpsURLConnection.setDefaultHostnameVerifier(hostnameVerifier);

			TrustManager[] trustAllCerts = new TrustManager[] { 
				new X509TrustManager() {
					public X509Certificate[] getAcceptedIssuers() { return null; }
					public void checkClientTrusted(X509Certificate[] certs, String authType) {}
					public void checkServerTrusted(X509Certificate[] certs, String authType) {}
				}
			};

			SSLContext sc = SSLContext.getInstance("TLSv1.2");
			sc.init(null, trustAllCerts, new SecureRandom());

			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		} catch (Exception e) {
			logger.debug("Exception occurred while setting the default ssl socket factory", e);
		}
	}
	
		public static boolean containsSpecialChars(String param) {
			String decodedString = null;
			boolean containsSpecialChar=false;
			try {
	  			Matcher m = null;  			
	  			Pattern p = Pattern.compile(HGLeapConstants.SPECIALCHARS);
	  			if(Objects.isNull(param)) {
	  				logger.info("no params exists");
	  			}else { 
	  			decodedString = URLDecoder.decode(param, StandardCharsets.UTF_8.toString());
	  			logger.info("request param after decode"+decodedString);
	  	         m= p.matcher(decodedString);
	  	         return m.find();
	  			}
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				logger.info("Exception while encoding the request param");
			}
			return containsSpecialChar;
    
 		}
 		
 		public static boolean isStringContainsUrlEncoded(String param) throws UnsupportedEncodingException {
 			boolean isEncoded = false;
 			if(Objects.isNull(param)) {
 				logger.info("no params exists to Encod");
 			}else {
 			String decodedString = URLDecoder.decode(param, StandardCharsets.UTF_8.toString());
 	        return  !decodedString.equals(param);
 			}
			return isEncoded;
 		}
		
	}
	
	
//	@Override
//	public boolean secureSelfSignedStream(String urlId, HttpServletRequest request, HttpServletResponse response, String[] urlParameters,String httpMethod) {
//	
//		
//		try {
//			Activity activity = new ActivityImpl(request);
//			OutputStream out ;
//		
//				//get url corresponding to urlId
//				String urlString = PropertiesUtil.getProperty(urlId);
//				logger.debug("urlId {} maps to url {}", urlId, urlString);
//		
//				for(int index=0;index<urlParameters.length;index++)
//				{
//					urlParameters[index] = URLEncoder.encode(urlParameters[index],"UTF-8");
//				}
//				
//				//url substituation
//				urlString = MessageFormat.format(urlString, (Object[]) urlParameters);
//				logger.debug("final url after substitution " + urlString);
//		
//				urlString = appendParams(urlString,request);
//				urlString = getBaseUrl()+urlString;
//				logger.debug("Opening Connection to Target Server -{}-", getBaseUrl() + urlString);
//				logger.debug("HTTP Method {}", httpMethod);
//				
//				
//				 RestTemplate restTemplate = this.getRestTemplate();
//				 ResponseEntity<String> response1 = restTemplate.getForEntity(urlString, String.class);
//			
//				 logger.info("Response: " + response1);
//				 
//				 out = response.getOutputStream();
//				 out.write(response1.getBody().getBytes());//wrapper.getData());
//				 out.close();
//				 response.setContentType(response1.getHeaders().getContentType().getType());
//		} catch(Exception e) {
//			logger.info(e.getMessage());
//		}
//		finally {
//			
//		}
//		
//		return true;
//	}
//
//	
//	public RestTemplate getRestTemplate() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
//		TrustStrategy acceptingTrustStrategy = new TrustStrategy() {
//			@Override
//			public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
//				return true;
//			}
//		};
//		SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy).build();
//		SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext, new NoopHostnameVerifier());
//		CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(csf).build();
//		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
//		requestFactory.setHttpClient(httpClient);
//		RestTemplate restTemplate = new RestTemplate(requestFactory);
//		return restTemplate;
//	}
	

