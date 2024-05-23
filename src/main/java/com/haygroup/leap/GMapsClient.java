package com.haygroup.leap;

import java.math.BigDecimal;
import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.HtmlUtils;

import com.google.gson.Gson;
import com.haygroup.leap.domain.AddressComponent;
import com.haygroup.leap.domain.AutoCompleteResponse;
import com.haygroup.leap.domain.DetailsResponse;
import com.haygroup.leap.domain.Location;
import com.haygroup.leap.domain.Predictions;
import com.haygroup.leap.responses.LocationResponse;

public class GMapsClient 
{

	private static final Logger logger 		= LoggerFactory.getLogger(GMapsClient.class);
	private String GMAPS_DETAILS_URL		= "https://maps.googleapis.com/maps/api/place/details/json?sensor=false&reference={0}&key=AIzaSyD9TJ0ag_QA9Qxm2y2et0qUt8gvUfgO-4A";
	private String GMAPS_AUTOCOMPLETE_URL	= "https://maps.googleapis.com/maps/api/place/autocomplete/json?input={0}&sensor=false&key=AIzaSyD9TJ0ag_QA9Qxm2y2et0qUt8gvUfgO-4A&types=(cities)";	

	//private String GMAPS_DETAILS_URL		= "https://maps.googleapis.com/maps/api/place/details/json?sensor=false&reference={0}&key=AIzaSyDXmAy9GOGKDN2lScmfXIQVaSra8Ni3V9M";
	//private String GMAPS_AUTOCOMPLETE_URL	= "https://maps.googleapis.com/maps/api/place/autocomplete/json?input={0}&sensor=false&key=AIzaSyDXmAy9GOGKDN2lScmfXIQVaSra8Ni3V9M&types=(cities)";	

	
	/**
	 * 
	 */
	public String getCities(String searchText)
	{
		logger.info("Searching for String "+HtmlUtils.htmlEscape(searchText));
		String retVal = "";
		RestTemplate template = new RestTemplate();
		Location[] locations = null;
		LocationResponse locResponse = null;
		Gson gson = new Gson();
		
		//String predictionsJsonResponse = template.getForObject("https://maps.googleapis.com/maps/api/place/autocomplete/json?input="+searchText+"&sensor=false&key=AIzaSyD9TJ0ag_QA9Qxm2y2et0qUt8gvUfgO-4A&types=(cities)", String.class);
		String autoCompleteURL = MessageFormat.format(GMAPS_AUTOCOMPLETE_URL, searchText);
		System.out.println(autoCompleteURL);
		String predictionsJsonResponse = template.getForObject(autoCompleteURL, String.class);
		
		AutoCompleteResponse response = gson.fromJson(predictionsJsonResponse, AutoCompleteResponse.class);
		
		Predictions[] predictions = response.getPredictions();
		
		
		logger.info("Found "+predictions.length+" responses");
		String description = "";
		String long_country_name   = "";
		for(int i=0;i<predictions.length;i++)
		{
			description = "";
			if(i==0)
			{
				locations = new Location[5];
				locResponse = new LocationResponse();
			}
			locations[i] = new Location();
			locations[i].setDescription(predictions[i].getDescription());
			
			System.out.println("Prediction :"+predictions[i].getDescription());
			//Make a call to get the details with the reference id
			//String detailResponse = template.getForObject("https://maps.googleapis.com/maps/api/place/details/json?sensor=false&reference="+predictions[i].getReference()+"&key=AIzaSyD9TJ0ag_QA9Qxm2y2et0qUt8gvUfgO-4A", String.class);
			String detailsURL 		= MessageFormat.format(GMAPS_DETAILS_URL,predictions[i].getReference()); 
			String detailResponse 	= template.getForObject(detailsURL, String.class);
			

			System.out.println("detailResponse :"+detailResponse);
			//Convert the response to a JAVA domain object
			DetailsResponse detailsResponse = gson.fromJson(detailResponse, DetailsResponse.class);
			
			if(detailsResponse.getStatus().equalsIgnoreCase("OK"))
			{
			if(detailsResponse!=null)
			{
				if(detailsResponse.getResult()!=null && detailsResponse.getResult().getAddress_components()!=null)
				{
					//Get the Address Components - locality, county, state, country
					AddressComponent[] components = detailsResponse.getResult().getAddress_components();
					
					for(int index=0;index<components.length;index++)
					{
						
						//TODOS: Change it to contains in an array
						
						if(components[index].getTypes()[0].equals("locality"))
						{
							locations[i].setLocality(components[index].getLong_name());
						}
						else if(components[index].getTypes()[0].equals("administrative_area_level_3"))
						{
							locations[i].setLocality(components[index].getLong_name());
						}
						else if(components[index].getTypes()[0].equals("administrative_area_level_2"))
						{
							//county
							if(components[index].getShort_name()==null)
							{
								locations[i].setCounty(components[index].getLong_name());	
							}
							else
							{
								locations[i].setCounty(components[index].getShort_name());
							}
							
						}
						else if(components[index].getTypes()[0].equals("administrative_area_level_1"))
						{
							//state
							if(components[index].getShort_name()==null)
							{
								locations[i].setState(components[index].getLong_name());	
							}
							else
							{
								locations[i].setState(components[index].getShort_name());
							}
						}
						else if(components[index].getTypes()[0].equals("country"))
						{
							locations[i].setCountryCode(components[index].getShort_name());
							long_country_name = components[index].getLong_name();
						}
						
					}

					//Using the description from the predictions JSON API.
//					if(locations[i].getLocality()!=null)
//					{
//						description = locations[i].getLocality();
//					}
//					else 
//					{
//						description = locations[i].getCounty();
//					}
//					
//					if(locations[i].getState()!=null)
//					{
//						description = description + ", " + locations[i].getState();
//					}
//					
//					if(locations[i].getCountryCode()!=null)
//					{
//						description = description + ", " + long_country_name;
//					}

					//locations[i].setDescription(description);
				}
				
				if(detailsResponse.getResult().getGeometry()!=null)
				{
					Double langLat = (Double) detailsResponse.getResult().getGeometry().getLocation().get("lat");
					if(langLat!=null)
					{
						BigDecimal bDecimal = new BigDecimal(langLat);
						bDecimal = bDecimal.setScale(6,BigDecimal.ROUND_HALF_UP);
						locations[i].setLatitude(bDecimal.toString());
					}

					langLat = (Double) detailsResponse.getResult().getGeometry().getLocation().get("lng");
					if(langLat!=null)
					{
						//NumberFormat format = nlangLat.
						BigDecimal bDecimal = new BigDecimal(langLat);
						bDecimal = bDecimal.setScale(6,BigDecimal.ROUND_HALF_UP);
						locations[i].setLongitude(bDecimal.toString());
					}
				}
			}
			}//checking for status
			
			
			locResponse.setResponseCode("RES.20018");
			locResponse.setResponseMessage("Locations found");
			locResponse.setData(locations);
			
		}		
		if(locResponse!=null)
		{
			retVal = gson.toJson(locResponse);
			//System.out.println("Final JSON :"+gson.toJson(locations));
		}
		return retVal;
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		GMapsClient obj = new GMapsClient();
		String jsonResponse = obj.getCities("Paris");
		//Gson gson = new Gson();
		//AutoCompleteResponse response = gson.fromJson(jsonResponse, AutoCompleteResponse.class);
		System.out.println(jsonResponse);
		
//		Predictions[] predictions = response.getPredictions();
//		
//		for(int i=0;i<predictions.length;i++)
//		{
//			System.out.println("Predictions  "+predictions[i].getDescription());
//			
//		}
		
	}

}
