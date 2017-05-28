package com.ridetrends.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ridetrends.bean.UberProfileBean;

public class UberServiceImpl implements UberService{

	private final String PROFILE_ENDPOINT = "https://api.uber.com/v1.2/me";
	final String ACCEPT_LANGUAGE = "en_US";
	final String CONTENT_TYPE = "application/json";
	@Override
	public UberProfileBean getUberProfile(String accessToken) throws UnsupportedOperationException, IOException {
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		
		//Use Access Token to get the uber profile
		HttpGet getRequest = new HttpGet(PROFILE_ENDPOINT);
		getRequest.setHeader("Authorization","Bearer "+accessToken);
		getRequest.setHeader("Accept-Language",ACCEPT_LANGUAGE);
		getRequest.setHeader("Content-Type",CONTENT_TYPE);
		HttpResponse httpResponse = httpClient.execute(getRequest);
		Reader reader = new InputStreamReader(httpResponse.getEntity().getContent());
		BufferedReader bufferedReader = new BufferedReader(reader);
		String line = bufferedReader.readLine();
		httpClient.close();
		ObjectMapper objectMapper = new ObjectMapper();
		UberProfileBean uberProfileBean = objectMapper.readValue(line, UberProfileBean.class);
		return uberProfileBean;
	
	}

}
