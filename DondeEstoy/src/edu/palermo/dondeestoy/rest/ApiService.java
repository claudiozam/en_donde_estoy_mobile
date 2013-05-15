package edu.palermo.dondeestoy.rest;


import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import android.util.Log;
import edu.palermo.dondeestoy.bo.BaseResponse;
import edu.palermo.dondeestoy.bo.FindLocationsResponse;
import edu.palermo.dondeestoy.bo.NearLocationPointsResponse;
import edu.palermo.dondeestoy.bo.Requestclass;
import edu.palermo.dondeestoy.bo.Responseclass;


public class ApiService {

	private final static String Tag="ApiService";
	private final static int TIMEOUT=1;
	private static String serverAddress = "";

	public static String getServerAddress() {
		return serverAddress;
	}

	public static void setServerAddress(String serverAddress) {
		ApiService.serverAddress = serverAddress;
	}

	public NearLocationPointsResponse getNearLocationPoints(double lat, double lng) throws ApiServiceException {
		
			Map<String, String> variables = new HashMap<String, String>();
			
			variables.put("latitude", String.valueOf(lat));
	        variables.put("longitude", String.valueOf(lng));
	        variables.put("category", "all");
	             
			HttpHeaders requestHeaders = new HttpHeaders();
			requestHeaders.setAccept(Collections.singletonList(new MediaType("application","json")));
			HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);
			
			HttpComponentsClientHttpRequestFactory rf = new HttpComponentsClientHttpRequestFactory();
			
			RestTemplate restTemplate = new RestTemplate(rf);
			restTemplate.getMessageConverters().add(new GsonHttpMessageConverter());
			
			rf.setReadTimeout(TIMEOUT * 1000);
	        rf.setConnectTimeout(TIMEOUT * 1000);
	        
	    	//Log.d(Tag,"VOY POR GET NEAR");
			//String url = "http://192.168.1.106:3000/api/locations/find_near_locations/{latitude}/{longitude}/{category}";
	        String url = "http://" + serverAddress + "/api/locations/find_near_locations/{latitude}/{longitude}/{category}";
	        Log.i("Nearlocations",url);
	        NearLocationPointsResponse nearLocationPointsResponse = null;
			ResponseEntity<NearLocationPointsResponse> responseEntity = null;			
			try
			{
				responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, NearLocationPointsResponse.class, variables);
				nearLocationPointsResponse = responseEntity.getBody();
				return nearLocationPointsResponse;
			}catch(HttpStatusCodeException e)
			{
				Log.e(Tag,"EXCEPTION HTTP exchange", e);
			
				if(e.getStatusCode()==HttpStatus.NOT_FOUND)
				{
					Log.e(Tag,"RESPONSE NOT FOUND");					
				}
				else if(e.getStatusCode()==HttpStatus.REQUEST_TIMEOUT)
				{
					Log.e(Tag,"REQUEST TIMEOUT");
					
				}
				else if(e.getStatusCode()==HttpStatus.GATEWAY_TIMEOUT)
				{
					Log.e(Tag,"GATEWAY TIMEOUT");
				}
				else{
					Log.e(Tag,"ERROR GENERAL");
				}				
				throw new ApiServiceException("ERROR GENERAL - FALLO HTTP REQUEST ó  HTTP RESPONSE");
			
			}catch(Exception  e)
			{
				Log.e(Tag,"EXCEPTION exchange", e);
				throw new ApiServiceException("ERROR GENERAL - FALLO HTTP REQUEST ó  HTTP RESPONSE");
			}
				
				
	}
	

	 
	public Responseclass.Response_CategoriasDisponibles getCategoriasDisponibles() throws ApiServiceException {
		
			HttpHeaders requestHeaders = new HttpHeaders();
			requestHeaders.setAccept(Collections.singletonList(new MediaType("application","json")));
			HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);
	
			RestTemplate restTemplate = new RestTemplate();
			restTemplate.getMessageConverters().add(new GsonHttpMessageConverter());
			
			//String urlCatLoc = new String(Definiciones.Definicionesgenerales.servidor+"/api/locations/get_all_categories");
			String url = "http://" + serverAddress + "/api/locations/get_all_categories";
	
		try
		{
			ResponseEntity<Responseclass.Response_CategoriasDisponibles> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Responseclass.Response_CategoriasDisponibles.class);			
			Responseclass.Response_CategoriasDisponibles response_CategoriasDisponibles = responseEntity.getBody();
			
			return response_CategoriasDisponibles;
		}catch(HttpStatusCodeException e)
		{
			Log.e(Tag,"EXCEPTION HTTP exchange", e);
		
			if(e.getStatusCode()==HttpStatus.NOT_FOUND)
			{
				Log.e(Tag,"RESPONSE NOT FOUND");					
			}
			else if(e.getStatusCode()==HttpStatus.REQUEST_TIMEOUT)
			{
				Log.e(Tag,"REQUEST TIMEOUT");
				
			}
			else if(e.getStatusCode()==HttpStatus.GATEWAY_TIMEOUT)
			{
				Log.e(Tag,"GATEWAY TIMEOUT");
			}
			else{
				Log.e(Tag,"ERROR GENERAL");
			}				
			throw new ApiServiceException("ERROR GENERAL - FALLO HTTP REQUEST ó  HTTP RESPONSE");
		
		}catch(Exception  e)
		{
			Log.e(Tag,"EXCEPTION exchange ", e);
			throw new ApiServiceException("ERROR GENERAL - FALLO HTTP REQUEST ó  HTTP RESPONSE");
		}
	}
	
	public Responseclass.Response_GetLocacionDevice getLocationByDevice(String device) throws ApiServiceException {
		
			Map<String, String> variables = new HashMap<String, String>();
		
	        variables.put("device", device);
	             
			HttpHeaders requestHeaders = new HttpHeaders();
			requestHeaders.setAccept(Collections.singletonList(new MediaType("application","json")));
			HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);
	
			RestTemplate restTemplate = new RestTemplate();
			restTemplate.getMessageConverters().add(new GsonHttpMessageConverter());
			
			//String urlCatLoc = new String(Definiciones.Definicionesgenerales.servidor+"/api/locations/get_all_categories");
			String url = "http://" + serverAddress + "/api/locations/{device}/get_location";
			
			//String Prueba="{" +"/"+"location_point/"+":[{"+"/"+"latitude/"+":-22.22,"+"/"+"longitude/"+":23.23,"+"/"+"created_at/"+":" +"/"+"2013-03-24 18:06/"+"}],"+"/"+"code/"+":"+"/"+"000"+"/"+ "}";
			
			//ResponseEntity<Responseclass.Response_GetLocacionDevice> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Responseclass.Response_GetLocacionDevice.class, variables);
			try
			{
				ResponseEntity<Responseclass.Response_GetLocacionDevice> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Responseclass.Response_GetLocacionDevice.class, variables);
				Log.i("API","RESPONSE: "+responseEntity.toString());			
				Responseclass.Response_GetLocacionDevice response_GetLocacionDevice = responseEntity.getBody();
				return response_GetLocacionDevice;
			}catch(HttpStatusCodeException e)
			{
				Log.e(Tag,"EXCEPTION HTTP exchange", e);
			
				if(e.getStatusCode()==HttpStatus.NOT_FOUND)
				{
					Log.e(Tag,"RESPONSE NOT FOUND");					
				}
				else if(e.getStatusCode()==HttpStatus.REQUEST_TIMEOUT)
				{
					Log.e(Tag,"REQUEST TIMEOUT");
					
				}
				else if(e.getStatusCode()==HttpStatus.GATEWAY_TIMEOUT)
				{
					Log.e(Tag,"GATEWAY TIMEOUT");
				}
				else{
					Log.e(Tag,"ERROR GENERAL");
				}				
				throw new ApiServiceException("ERROR GENERAL - FALLO HTTP REQUEST ó  HTTP RESPONSE");
			
			}catch(Exception  e)
			{
				Log.e(Tag,"EXCEPTION exchange", e);
				throw new ApiServiceException("ERROR GENERAL - FALLO HTTP REQUEST ó  HTTP RESPONSE");
			}
	}
	
	
	public Responseclass.Response_TiposDisponibles getTiposDisponibles() throws ApiServiceException {

		Map<String, String> variables = new HashMap<String, String>();
		
       // variables.put("category", "all");
             
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setAccept(Collections.singletonList(new MediaType("application","json")));
		HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new GsonHttpMessageConverter());
		
		//String urlCatLoc = new String(Definiciones.Definicionesgenerales.servidor+"/api/locations/get_all_categories");
		String url = "http://" + serverAddress + "/api/locations/get_all_types";

		try
		{
			ResponseEntity<Responseclass.Response_TiposDisponibles> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Responseclass.Response_TiposDisponibles.class, variables);		
			Responseclass.Response_TiposDisponibles response_TiposDisponibles = responseEntity.getBody();		
			return response_TiposDisponibles;
		}catch(HttpStatusCodeException e)
		{
			Log.e(Tag,"EXCEPTION HTTP exchange ", e);
		
			if(e.getStatusCode()==HttpStatus.NOT_FOUND)
			{
				Log.e(Tag,"RESPONSE NOT FOUND");					
			}
			else if(e.getStatusCode()==HttpStatus.REQUEST_TIMEOUT)
			{
				Log.e(Tag,"REQUEST TIMEOUT");
				
			}
			else if(e.getStatusCode()==HttpStatus.GATEWAY_TIMEOUT)
			{
				Log.e(Tag,"GATEWAY TIMEOUT");
			}
			else{
				Log.e(Tag,"ERROR GENERAL");
			}				
			throw new ApiServiceException("ERROR GENERAL - FALLO HTTP REQUEST ó  HTTP RESPONSE");
		
		}catch(Exception  e)
		{
			Log.e(Tag,"EXCEPTION exchange", e);
			throw new ApiServiceException("ERROR GENERAL - FALLO HTTP REQUEST ó  HTTP RESPONSE");
		}
	
	}
	
	public FindLocationsResponse findLocations(String description, int categoriaId, int cantidadMaxDeResultados) throws ApiServiceException {

		try
		{
			Requestclass rq=new Requestclass();		
			Requestclass.Request_FindLocations requestFindLoc = rq.new Request_FindLocations();		 
			
			requestFindLoc.setLimit(cantidadMaxDeResultados);
			requestFindLoc.setDescription(description);
			requestFindLoc.setCategory_id(categoriaId);
	             
			HttpHeaders requestHeaders = new HttpHeaders();
			//requestHeaders.setAccept(Collections.singletonList(new MediaType("application","json")));
			requestHeaders.setContentType(new MediaType("application","json"));
			
			//HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);
			HttpEntity<Requestclass.Request_FindLocations> requestEntity = new HttpEntity<Requestclass.Request_FindLocations>(requestFindLoc, requestHeaders);
			
			RestTemplate restTemplate = new RestTemplate();
			restTemplate.getMessageConverters().add(new GsonHttpMessageConverter());
			
			String url = "http://" + serverAddress + "/api/locations/find_locations";			
			
			ResponseEntity<FindLocationsResponse> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, FindLocationsResponse.class);
			FindLocationsResponse result = responseEntity.getBody();
			
			
			return result;
		
			
		}catch(HttpStatusCodeException e)
		{
			Log.e(Tag,"EXCEPTION HTTP exchange ", e);
		
			if(e.getStatusCode()==HttpStatus.NOT_FOUND)
			{
				Log.e(Tag,"RESPONSE NOT FOUND");					
			}
			else if(e.getStatusCode()==HttpStatus.REQUEST_TIMEOUT)
			{
				Log.e(Tag,"REQUEST TIMEOUT");
				
			}
			else if(e.getStatusCode()==HttpStatus.GATEWAY_TIMEOUT)
			{
				Log.e(Tag,"GATEWAY TIMEOUT");
			}
			else{
				Log.e(Tag,"ERROR GENERAL");
			}				
			throw new ApiServiceException("ERROR GENERAL - FALLO HTTP REQUEST ó  HTTP RESPONSE");
		
		}catch(Exception  e)
		{
			Log.e(Tag,"EXCEPTION exchange", e);
			throw new ApiServiceException("ERROR GENERAL - FALLO HTTP REQUEST ó  HTTP RESPONSE");
		}
		
		
		
	}
	
	public BaseResponse postUpdateLocation(double latitude,double longitude, String device) {

		Requestclass rq=new Requestclass();		
		Requestclass.Request_UpdateLocation uplocation = rq.new Request_UpdateLocation();		 
		uplocation.setLatitude(latitude);
		uplocation.setLongitude(longitude);
		
		HttpHeaders requestHeaders = new HttpHeaders();
		//requestHeaders.setAccept(Collections.singletonList(new MediaType("application","json")));
		requestHeaders.setContentType(new MediaType("application","json"));
		
		//HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);
		HttpEntity<Requestclass.Request_UpdateLocation> requestEntity = new HttpEntity<Requestclass.Request_UpdateLocation>(uplocation, requestHeaders);
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new GsonHttpMessageConverter());
		
		String url = "http://" + serverAddress + "/api/devices/"+device+"/update_location";			
		
		ResponseEntity<BaseResponse> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, BaseResponse.class);
		BaseResponse result = responseEntity.getBody();
		
		
		return result;
	
	}
	
	
	
	
	public BaseResponse postCreateDevice(String name,String description,int id_categoria,int id_type) throws ApiServiceException {

		
		 
		Requestclass rq=new Requestclass();		
		Requestclass.Request_CrearNuevoDevice nuevodevice = rq.new Request_CrearNuevoDevice();		 
		
		nuevodevice.setCategory_id(id_categoria);
		nuevodevice.setType_id(id_type);
		nuevodevice.setDescription(description);
		nuevodevice.setName(name);
		     
             
		HttpHeaders requestHeaders = new HttpHeaders();
		
		//requestHeaders.setAccept(Collections.singletonList(new MediaType("application","json")));
		requestHeaders.setContentType(new MediaType("application","json"));
		
		//HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);
		HttpEntity<Requestclass.Request_CrearNuevoDevice> requestEntity = new HttpEntity<Requestclass.Request_CrearNuevoDevice>(nuevodevice, requestHeaders);
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new GsonHttpMessageConverter());
		
		String url = "http://" + serverAddress + "/api/devices/create";			
		
		try{
			ResponseEntity<BaseResponse> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, BaseResponse.class);	
			BaseResponse result = responseEntity.getBody();				
			return result;
		}catch(HttpStatusCodeException e)
		{
			Log.e(Tag,"EXCEPTION HTTP exchange", e);
		
			if(e.getStatusCode()==HttpStatus.NOT_FOUND)
			{
				Log.e(Tag,"RESPONSE NOT FOUND");					
			}
			else if(e.getStatusCode()==HttpStatus.REQUEST_TIMEOUT)
			{
				Log.e(Tag,"REQUEST TIMEOUT");
				
			}
			else if(e.getStatusCode()==HttpStatus.GATEWAY_TIMEOUT)
			{
				Log.e(Tag,"GATEWAY TIMEOUT");
			}
			else{
				Log.e(Tag,"ERROR GENERAL");
			}				
			throw new ApiServiceException("ERROR GENERAL - FALLO HTTP REQUEST ó  HTTP RESPONSE");
		
		}catch(Exception  e)
		{
			Log.e(Tag,"EXCEPTION exchange", e);
			throw new ApiServiceException("ERROR GENERAL - FALLO HTTP REQUEST ó  HTTP RESPONSE");
		}
	
	}
	
	
}
