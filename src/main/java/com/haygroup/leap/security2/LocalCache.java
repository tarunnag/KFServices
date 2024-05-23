package com.haygroup.leap.security2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class LocalCache<T> {
		 
	    private final Long timeToLive = 2000l;
	    private Map<String, CacheObject> cacheMap;
	 
	    protected class CacheObject {
	        public long lastAccessed = System.currentTimeMillis();
	        public T value;
	 
	        protected CacheObject(T value) {
	            this.value = value;
	        }
	    }
	 
	    public LocalCache() {
	        cacheMap = new HashMap<String, CacheObject>();
	 
	        if (timeToLive > 0) {
	 
	            Thread t = new Thread(new Runnable() {
	                public void run() {
	                    while (true) {
	                        try {
	                            Thread.sleep(1000);
	                        } catch (InterruptedException ex) {
	                        }
	                        cleanup();
	                    }
	                }
	            });
	 
	            t.setDaemon(true);
	            t.start();
	        }
	    }
	 
	    public void put(String key, T value) {
	        synchronized (cacheMap) {
	        	cacheMap.put(key, new CacheObject(value));
	        }
	    }
	 
	    public T get(String key) {
	        synchronized (cacheMap) {
	        	CacheObject cacheObject = (CacheObject) cacheMap.get(key);
	 
	            if (cacheObject == null)
	                return null;
	            else {
	            	cacheObject.lastAccessed = System.currentTimeMillis();
	                return cacheObject.value;
	            }
	        }
	    }
	 
	    public void remove(String key) {
	        synchronized (cacheMap) {
	        	cacheMap.remove(key);
	        }
	    }
	 
	    public int size() {
	        synchronized (cacheMap) {
	            return cacheMap.size();
	        }
	    }
	 
	    public void cleanup() {
	 
	        long now = System.currentTimeMillis();
	        ArrayList<String> deleteKey = null;
	 
	        synchronized (cacheMap) {
	        	Set<String> keys = cacheMap.keySet();
	 
	            deleteKey = new ArrayList<String>((cacheMap.size() / 2) + 1);
	            CacheObject cacheObject = null;
	 
	            for(String key: keys) {
	            	
	            	cacheObject = (CacheObject) cacheMap.get(key);
	            	 if (cacheObject != null && (now > (timeToLive + cacheObject.lastAccessed))) {
		                    deleteKey.add(key);
		                }	            	
	            }
	            
	        }
	 
	        for (String key : deleteKey) {
	            synchronized (cacheMap) {
	                cacheMap.remove(key);
	            }
	 
	            Thread.yield();
	        }
	    }
	}