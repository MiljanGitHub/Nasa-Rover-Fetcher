package com.fevo.assignment.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Optional;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fevo.assignment.model.Response;
import com.fevo.assignment.model.RoverType;

public class RoverService {
	
	/*
	 * This class is a Singleton. It would probably be some kind of Bean in Spring application.
	 */
	
	/*
	 * BASE_URL, API_KEY - could be set as a environment variable through AWS (via application.properties file in dev environment)
	 */
	
	@SuppressWarnings("unused")
	private static final String BASE_URL = "https://api.nasa.gov/mars-photos/api/v1/rovers/";
	@SuppressWarnings("unused")
	private static final String API_KEY = "lwUMOLivNw7Hwt6d61fPAdb7GClFvEWTOZtkqto8";
	
	private static RoverService roverService;
	
	private RoverService() {}
	
	public static RoverService getService() {
		return Optional.ofNullable(roverService).orElseGet(RoverService::new);
	}
	
	public Response fetchCuriosityData(Map<String, String[]> params) {
		
	HttpGet request = new HttpGet("https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?earth_date=2020-4-2&camera=NAVCAM&api_key=lwUMOLivNw7Hwt6d61fPAdb7GClFvEWTOZtkqto8");
		//HttpGet request = new HttpGet(BASE_URL+"curiosity/photos?" + "earth_date=" + params.get("earth_date")[0] + "&" + "camera=" + params.get("camera")[0] + "&" + "api_key=" + API_KEY);
 
		try (CloseableHttpClient httpClient = HttpClients.createDefault();
	         CloseableHttpResponse response = httpClient.execute(request)) {


	           HttpEntity entity = response.getEntity();
	           String res = EntityUtils.toString(entity);
	                

	            
	           InputStream input = TypeReference.class.getResourceAsStream(res);
	           ObjectMapper mapper = new ObjectMapper();
	           mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	           /*
	            * Unfortunately, I couldn't make Jackson deserialize JSON.
	            */
	           @SuppressWarnings("unused")
	           Response r = mapper.readValue(input, Response.class);

	        } catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		return new Response();
	}
	
	public Response fetchOpportunityData(Map<String, String[]> params) {
		//TODO
		return null;
	}
	
	public Response fetchSpiritData(Map<String, String[]> params) {
		//TODO
		return null;
	}
	
	public Optional<Response> fetchResponse(RoverType roverType, Map<String, String[]> params) {
	
		return Optional.of(roverType.roverDataFetcher.apply(this, params));
	}
}
