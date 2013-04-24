package edu.palermo.dondeestoy.rest;

import java.util.*;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import edu.palermo.dondeestoy.bo.LocationPoint;
import edu.palermo.dondeestoy.bo.NearLocationPointsResponse;

public class ApiService {

	public NearLocationPointsResponse getNearLocationPoints(double lat, double lng) {

		Map<String, String> variables = new HashMap<String, String>();
		
		variables.put("latitude", String.valueOf(lat));
        variables.put("longitude", String.valueOf(lng));
        variables.put("category", "all");
             
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setAccept(Collections.singletonList(new MediaType("application","json")));
		HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new GsonHttpMessageConverter());
		
		String url = "http://10.129.100.47:3000/api/locations/find_near_locations/{latitude}/{longitude}/{category}";

		ResponseEntity<NearLocationPointsResponse> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, NearLocationPointsResponse.class, variables);
		
		NearLocationPointsResponse nearLocationPointsResponse = responseEntity.getBody();
		
		return nearLocationPointsResponse;
	
	}
}
