/**
 * 
 */
package com.haygroup.leap.security;

/**
 * AuthenticationManager
 */
public interface AuthenticationManager {
		
		public boolean needsAuthentication(String url);
		public boolean isAuthenticated(String url, String authToken);
		public void addAuthToken(String authToken, String duration,Object obj);
		public boolean removeAuthToken(String authToken);
		public Object get(String authToken);
}
