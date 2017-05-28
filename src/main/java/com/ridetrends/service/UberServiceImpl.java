package com.ridetrends.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ridetrends.bean.UberHistory;
import com.ridetrends.bean.UberHistoryBean;
import com.ridetrends.bean.UberProfileBean;
import com.ridetrends.bean.UberStats;
import com.ridetrends.utilities.CalculateUberStats;

public class UberServiceImpl implements UberService{

	private final String PROFILE_ENDPOINT = "https://api.uber.com/v1.2/me";
	private final String HISTORY_ENDPOINT = "https://api.uber.com/v1.2/history";
	private final String ACCEPT_LANGUAGE = "en_US";
	private final String CONTENT_TYPE = "application/json";
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
	
	@Override
	public UberHistoryBean getUberHistory(String accessToken) throws UnsupportedOperationException, IOException {
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		//Use Access Token to get the ride history
		HttpGet getRequest = new HttpGet(HISTORY_ENDPOINT);
		getRequest.setHeader("Authorization","Bearer "+accessToken);
		getRequest.setHeader("Accept-Language",ACCEPT_LANGUAGE);
		getRequest.setHeader("Content-Type",CONTENT_TYPE);
		HttpResponse httpResponse = httpClient.execute(getRequest);
		Reader reader = new InputStreamReader(httpResponse.getEntity().getContent());
		BufferedReader bufferedReader = new BufferedReader(reader);
		String line = bufferedReader.readLine();
		httpClient.close();
		ObjectMapper objectMapper = new ObjectMapper();
		UberHistoryBean uberHistoryBean = objectMapper.readValue(line, UberHistoryBean.class);
		return uberHistoryBean;
	}
	@Override
	public UberStats getUberStats(String accessToken){
		UberStats uberStats = new UberStats();
		try {
			int recentMonth = CalculateUberStats.getMonth(getUberHistory(accessToken).getHistory().get(0).getStart_time());
			List<UberHistory> recentMonthTrips = CalculateUberStats.getRecentMonthTrips(accessToken,recentMonth);
			int previousMonth = recentMonth>1?recentMonth-1:12;
			List<UberHistory> previousMonthTrips = CalculateUberStats.getPreviousMonthTrips(accessToken, previousMonth);
			uberStats.setPresentMonthTotalRides(recentMonthTrips.size());
			uberStats.setLastMonthTotalRides(previousMonthTrips.size());
			Double totalMilesCoveredThisMonth = CalculateUberStats.getTotalMilesCovered(recentMonthTrips);
			Double totalMilesCoveredLastMonth = CalculateUberStats.getTotalMilesCovered(previousMonthTrips);
			uberStats.setTotalMilesCoveredThisMonth(totalMilesCoveredThisMonth);
			uberStats.setTotalMilesCoveredLastMonth(totalMilesCoveredLastMonth);
		} catch (UnsupportedOperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return uberStats;
		
	}

}
