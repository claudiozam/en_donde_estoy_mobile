package edu.palermo.dondeestoy.rest;

import java.util.*;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import android.util.Log;

import edu.palermo.dondeestoy.bo.LocationPoint;
import edu.palermo.dondeestoy.bo.NearLocationPointsResponse;

public class ApiService {

	private String baseURL = "";
	
	public ApiService(String baseURL) {
		this.baseURL = baseURL;
	}
	
	public NearLocationPointsResponse getNearLocationPoints(double lat, double lng) throws ApiServiceException {

		Log.i("ApiService", "Ejecutando getNearLocationPoints");
		
		Map<String, String> variables = new HashMap<String, String>();
		
		variables.put("latitude", String.valueOf(lat));
        variables.put("longitude", String.valueOf(lng));
        variables.put("category", "all");
             
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setAccept(Collections.singletonList(new MediaType("application","json")));
		HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new GsonHttpMessageConverter());
		
		String url = baseURL + "/api/locations/find_near_locations/{latitude}/{longitude}/{category}";

		Log.i("ApiService", "URL: " + url);

		ResponseEntity<NearLocationPointsResponse> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, NearLocationPointsResponse.class, variables);
		
		if(responseEntity.getStatusCode() != HttpStatus.OK) {
			Log.i("ApiService", "La llamada no retorno HttpStatus.OK => " + responseEntity.getStatusCode().value());
			throw new ApiServiceException("Error en la llamada a la URL: " + url);
		}
		
		NearLocationPointsResponse nearLocationPointsResponse = responseEntity.getBody();
		
		return nearLocationPointsResponse;
	
	}
	
}
