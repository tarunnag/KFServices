package com.haygroup.leap.activity;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.haygroup.leap.EncryptionUtil;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientOptions.Builder;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.util.JSON;

public class MongoDBActivityManagerImpl implements ActivityManager {

	MongoClient mongoClient;
	DB mongoDb;
	DBCollection activityCollection;
	String server;
	int port;
	String database;
	String collection;
	String serverId;
	boolean enabled;
	boolean tlsEnabled;
	String userName;
	String password;
	EncryptionUtil encryptionUtil=new EncryptionUtil();

	private static final Logger logger = LoggerFactory.getLogger(MongoDBActivityManagerImpl.class);

	public MongoDBActivityManagerImpl(String server, String port, String database, String collection, String serverId,
			String expiryTime, Boolean enabled, Boolean tlsEnabled, String userName,String password) {

		try {

			this.server = server;
			this.port = Integer.parseInt(port);
			this.database = database;
			this.collection = collection;
			this.serverId = serverId;
			if(!userName.isEmpty()) {
			this.userName=encryptionUtil.decryptString(userName);
			}
			if(!password.isEmpty()) {
			this.password=encryptionUtil.decryptString(password);
			}
			if (enabled != null) {
				this.enabled = enabled.booleanValue();
			} else {
				this.enabled = true;
			}
			
			logger.info("TLS Enabled "+tlsEnabled);
			Builder builder = MongoClientOptions.builder().socketTimeout(36000).sslEnabled(tlsEnabled).sslInvalidHostNameAllowed(tlsEnabled).sslContext(sslContext());
			
			MongoClientOptions clientOptions = builder.build();
			if(StringUtils.isNotEmpty(userName)) {
				logger.info("Connecting Mongo DB using username and Password");
				MongoCredential mongoCredential = MongoCredential.createCredential(this.userName, database,
	                    this.password.toCharArray());
	            ServerAddress serverAddress = new ServerAddress(this.server, this.port);
	            mongoClient = new MongoClient(serverAddress, mongoCredential, clientOptions);
	            
			} else {
				mongoClient = new MongoClient(new ServerAddress(this.server, this.port), clientOptions);
			}

			mongoDb = mongoClient.getDB(database);
			activityCollection = mongoDb.getCollection(collection);
			// activityCollection.dropIndex(new BasicDBObject("createdOn", 1));
			// activityCollection.createIndex(new BasicDBObject("createdOn", 1), new
			// BasicDBObject("expireAfterSeconds", expiryTime));
			
		} catch (NumberFormatException e) {
			logger.error("Unable to connect to MongoDb", e);
		} catch (Exception e) {
			logger.error("Exception while connecting to MongoDb", e);
		}
			
	}
	
	private static SSLContext sslContext() throws GeneralSecurityException, IOException {
		SSLContext context = SSLContext.getInstance("TLS");
		TrustManager trustManager = new X509TrustManager() {
			public X509Certificate[] getAcceptedIssuers() {
				List<X509Certificate> certs = new ArrayList<X509Certificate>();
				return certs.toArray(new X509Certificate[0]);
			}

			public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {

			}

			public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {

			}
		};
		context.init(null, new TrustManager[] { trustManager }, new SecureRandom());
		return context;
	}

	@Override
	public void log(Activity activity) {

		Thread t = new Thread(new InsertActivity(activity));
		t.start();

	}

	private class InsertActivity implements Runnable {
		Activity activity;

		public InsertActivity(Activity activity) {
			this.activity = activity;
		}

		public void run() {

			if (enabled == true) {
				try {

					activity.setServerId(serverId);

					Gson gson = new Gson();

					String jsonString = gson.toJson(activity);
					DBObject dbObject = (DBObject) JSON.parse(jsonString);

					if (activity.getUrl().indexOf("picture") == -1) {
						if (dbObject.get("requestData") != null) {
							BasicDBObject requestDataObj = (BasicDBObject) JSON
									.parse((String) dbObject.get("requestData"));

							if (activity.getUrl().indexOf("login") > 0) {
								if (requestDataObj.containsField("password")) {
									requestDataObj.put("password", "********");
								}
							} else if (activity.getUrl().indexOf("changepassword") > 0) {
								requestDataObj.put("oldPassword", "*****");
								requestDataObj.put("newPassword", "*****");
								requestDataObj.put("retypeNewPassword", "*****");

							}
							dbObject.put("requestData", requestDataObj);
						}
					} else {
						dbObject.put("requestData", null);
						logger.debug("Picture URL not loggin Request Data");
					}

					if (activity.getUrl().indexOf("picture") == -1) {
						if (dbObject.get("responseData") != null) {
							dbObject.put("responseData", JSON.parse((String) dbObject.get("responseData")));

						}
					} else {
						dbObject.put("responseData", null);
						logger.debug("Picture URL not loggin Response Data");
					}

					dbObject.put("createdOn", new Date());
					activityCollection.insert(dbObject);

					logger.debug("Completed logging activity");

				} catch (Exception ex) {
					logger.error("Unable to write to MongoDb", ex);
				}
			}
		}

	}

}
