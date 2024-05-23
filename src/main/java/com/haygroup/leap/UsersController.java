package com.haygroup.leap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.access.prepost.PreAuthorize;

import com.haygroup.leap.client.RestProxy;
import com.haygroup.leap.common.HGLeapConstants;
import com.haygroup.leap.common.LeapUtil;

@Controller
@RequestMapping(value = "/users")
public class UsersController {

	// private ObjectMapper mapper = new ObjectMapper(); 

	@Autowired
	private LeapUtil leapUtil;
	@Autowired
	private RestProxy restProxy;

	//private static final Logger logger = LoggerFactory.getLogger(UsersController.class);

	/**
	 * @param userId
	 * @param model
	 * @param response
	*@Secured (HGLeapConstants.ROLE_USER)
	 */
	@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_INTELLIGENCE_CLOUD')")
	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	public void getUser(@PathVariable String userId, 
			@RequestParam(value = "rawData", defaultValue = "") String rawData,
			Model model, HttpServletRequest request,
			HttpServletResponse response) {
		restProxy.stream("getUser", request, response, new String[] { userId,rawData });
	}

	/**
	 * @param userId
	 * @param model
	 * @param response
	 */
	@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_INTELLIGENCE_CLOUD')")
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public void postUser(HttpServletRequest request, HttpServletResponse response) {

		restProxy.stream("postUser", request, response, new String[] {});

	}

	/**
	 * @param userId
	 * @param model
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/{userId}", method = RequestMethod.PUT)
	public void putUser(@PathVariable String userId, HttpServletRequest request, HttpServletResponse response) {

		restProxy.stream("putUser", request, response, new String[] { userId });

	}
	
	/**
	 * @param userId
	 * @param regionId
	 * @param model
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/{userId}/picture", method = RequestMethod.GET)
	public void getUserProfilePicture(@PathVariable String userId, 
		//	@RequestParam(value = "authToken") String authToken,
			HttpServletRequest request,
			HttpServletResponse response) {

	//	restProxy.stream("getUserProfilePicture", request, response, new String[] {userId,authToken});
		restProxy.stream("getUserProfilePicture", request, response, new String[] {userId});

	}

	/**
	 * @param userId
	 * @param model
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/{userId}/picture", method = RequestMethod.POST)
	public void postUserProfilePicture(@PathVariable String userId, Model model, HttpServletRequest request,
			HttpServletResponse response) {

		restProxy.stream("putUserProfilePicture", request, response, new String[] {userId});

	}

	/**
	 * @param userId
	 * @param model
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/{userId}/picture", method = RequestMethod.PUT)
	public void putUserProfilePicture(@PathVariable String userId, Model model, HttpServletRequest request,
			HttpServletResponse response) {

		restProxy.stream("putUserProfilePicture", request, response, new String[] {userId}, "POST");

	}

	/**
	 * @param userId
	 * @param model
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/{userId}/picture", method = RequestMethod.DELETE)
	public void deleteUserProfilePicture(@PathVariable String userId, Model model, HttpServletRequest request,
			HttpServletResponse response) {

		restProxy.stream("deleteUserProfilePicture", request, response, new String[] {userId});

	}

	/**
	 * @param userId
	 * @param model
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/{userId}/locations", method = RequestMethod.GET)
	public void getUserLocations(@PathVariable String userId, Model model, HttpServletRequest request,
			HttpServletResponse response) {

		restProxy.stream("getUserLocations", request, response, new String[] {userId});

	}

	/**
	 * @param userId
	 * @param model
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/{userId}/locations", method = RequestMethod.POST)
	public void postUserLocations(@PathVariable String userId, Model model, HttpServletRequest request,
			HttpServletResponse response) {

		restProxy.stream("postUserLocations", request, response, new String[] {userId});

	}

	/**
	 * @param userId
	 * @param locationId
	 * @param model
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/{userId}/locations", method = RequestMethod.PUT)
	public void putUserLocation(@PathVariable String userId, Model model, HttpServletRequest request,
			HttpServletResponse response) {

		restProxy.stream("putUserLocation", request, response, new String[] {userId});

	}

	/**
	 * @param userId
	 * @param locationId
	 * @param model
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/{userId}/locations/{locationId}", method = RequestMethod.DELETE)
	public void deleteUserLocation(@PathVariable String userId, @PathVariable String locationId, Model model,
			HttpServletRequest request, HttpServletResponse response) {

		restProxy.stream("deleteUserLocation", request, response, new String[] {userId, locationId});

	}

	/**
	 * @param userId
	 * @param model
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/{userId}/notifications", method = RequestMethod.GET)
	public void getNotifications(@PathVariable String userId, Model model, HttpServletRequest request,
			HttpServletResponse response) {

		restProxy.stream("getNotifications", request, response, new String[] {userId});

	}

	/**
	 * @param userId
	 * @param notificationId
	 * @param model
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/{userId}/notifications/{notificationId}", method = RequestMethod.DELETE)
	public void deleteUserNotification(@PathVariable String userId, @PathVariable String notificationId, Model model,
			HttpServletRequest request, HttpServletResponse response) {

		restProxy.stream("deleteUserNotification", request, response, new String[] {notificationId});

	}

	/**
	 * @param userId
	 * @param notificationId
	 * @param model
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/{userId}/notifications/{notificationId}", method = RequestMethod.PUT)
	public void putUserNotification(@PathVariable String userId, @PathVariable String notificationId, Model model,
			HttpServletRequest request, HttpServletResponse response) {

		restProxy.stream("putUserNotification", request, response, new String[] {notificationId});

	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/subscriptions/{subscriptionId}", method = RequestMethod.GET)
	public void getSubscriptions(@PathVariable String subscriptionId, HttpServletRequest request,
			HttpServletResponse response) {

		restProxy.stream("getSubscriptions", request, response, new String[] {subscriptionId});

	}

	/**
	 * @param userId
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_INTELLIGENCE_CLOUD')")
	@RequestMapping(value = "/{userId}/preferences", method = RequestMethod.GET)
	public void getUserPreferences(@PathVariable String userId, 
			@RequestParam(value = "options", defaultValue = "true") String options,
			@RequestParam(value = "preferenceId", defaultValue = "") String preferenceId,
			@RequestParam(value = "application", defaultValue = "") String application,
			HttpServletRequest request, HttpServletResponse response) {

		restProxy.stream("getUserPreferences", request, response, new String[] {userId, options, preferenceId, application});

	}

	/**
	 * @param userId
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_INTELLIGENCE_CLOUD')")
	@RequestMapping(value = "/{userId}/preferences", method = RequestMethod.POST)
	public void postUserPreferences(@PathVariable String userId, HttpServletRequest request,
			HttpServletResponse response) {

		restProxy.stream("postUserPreferences", request, response, new String[] {userId});

	}

	/**
	 * @param userId
	 * @param request
	 * @param response
	 */
	@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_INTELLIGENCE_CLOUD')")
	@RequestMapping(value = "/{userId}/preferences", method = RequestMethod.PUT)
	public void putUserPreferences(@PathVariable String userId, HttpServletRequest request, HttpServletResponse response) {

		restProxy.stream("putUserPreferences", request, response, new String[] {userId});

	}

	/**
	 * @param userId
	 * @param taskTemplateId
	 * @param model
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/{userId}/reset", method = RequestMethod.POST)
	public void resetUser(@PathVariable String userId,  
				@RequestParam(value = "applicationName", defaultValue = "") String applicationName,
			HttpServletRequest request, HttpServletResponse response) {

		restProxy.stream("resetUser", request, response, new String[] {userId, applicationName});

	}

	// getUserGroups=/rest/HGLeapMain/model/users/groups/?type={1}
	/**
	 * @param userId
	 * @param model
	 * @param response
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/{userId}/groups", method = RequestMethod.GET)
	public void getUserGroups(@PathVariable String userId,@RequestParam(value = "type") String type, Model model, HttpServletRequest request,
			HttpServletResponse response) {
		
		restProxy.stream("getUserGroups", request, response, new String[] {userId, type});

	}
	
	/**
     * @param userId
     * @param model
     * @param response
     */
     @PreAuthorize("hasRole('ROLE_USER')")
     @RequestMapping(value = "/{userId}/clients", method = RequestMethod.GET)
     public void getUserClients(@PathVariable String userId, Model model, HttpServletRequest request,
                                     HttpServletResponse response) 
     {
                     
         restProxy.stream("getUserClients", request, response, new String[] {userId});

     }

}
