package com.ridetrends.utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.joda.time.DateTime;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ridetrends.bean.UberHistory;
import com.ridetrends.bean.UberHistoryBean;

public class CalculateUberStats {
	private static final String HISTORY_ENDPOINT = "https://api.uber.com/v1.2/history";
	private static final String ACCEPT_LANGUAGE = "en_US";
	private static final String CONTENT_TYPE = "application/json";
	
	public static int getMonth(String unixTimeStamp){
		Long timeStamp = Long.valueOf(unixTimeStamp)*1000;
		DateTime dt = new DateTime(timeStamp);
		return dt.getMonthOfYear();
	}
	public static List<UberHistory> getRecentMonthTrips(String accessToken, int recentMonth){
		List<UberHistory> recentMonthTrips = new ArrayList<UberHistory>();
		CloseableHttpClient httpClient = HttpClients.createDefault();
		//Use Access Token to get the ride history
		HttpGet getRequest = new HttpGet(HISTORY_ENDPOINT+"?limit=50");
		getRequest.setHeader("Authorization","Bearer "+accessToken);
		getRequest.setHeader("Accept-Language",ACCEPT_LANGUAGE);
		getRequest.setHeader("Content-Type",CONTENT_TYPE);
		HttpResponse httpResponse;
		try {
			httpResponse = httpClient.execute(getRequest);
			Reader reader = new InputStreamReader(httpResponse.getEntity().getContent());
			BufferedReader bufferedReader = new BufferedReader(reader);
			String line = bufferedReader.readLine();
			httpClient.close();
			ObjectMapper objectMapper = new ObjectMapper();
			UberHistoryBean uberHistoryBean = objectMapper.readValue(line, UberHistoryBean.class);
			for(UberHistory pastTrip : uberHistoryBean.getHistory()){
				int month = getMonth(pastTrip.getStart_time());
				if(month==recentMonth){
					recentMonthTrips.add(pastTrip);
				}
				else{
					break;
				}
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return recentMonthTrips;
	}
	public static List<UberHistory> getPreviousMonthTrips(String accessToken, int previousMonth){
		List<UberHistory> previousMonthTrips = new ArrayList<UberHistory>();
		CloseableHttpClient httpClient = HttpClients.createDefault();
		//Use Access Token to get the ride history
		HttpGet getRequest = new HttpGet(HISTORY_ENDPOINT+"?limit=50");
		getRequest.setHeader("Authorization","Bearer "+accessToken);
		getRequest.setHeader("Accept-Language",ACCEPT_LANGUAGE);
		getRequest.setHeader("Content-Type",CONTENT_TYPE);
		HttpResponse httpResponse;
		try {
			httpResponse = httpClient.execute(getRequest);
			Reader reader = new InputStreamReader(httpResponse.getEntity().getContent());
			BufferedReader bufferedReader = new BufferedReader(reader);
			String line = bufferedReader.readLine();
			httpClient.close();
			ObjectMapper objectMapper = new ObjectMapper();
			UberHistoryBean uberHistoryBean = objectMapper.readValue(line, UberHistoryBean.class);
			for(UberHistory pastTrip : uberHistoryBean.getHistory()){
				int month = getMonth(pastTrip.getStart_time());
				if(month==previousMonth){
					previousMonthTrips.add(pastTrip);
				}else if(month<previousMonth){
					break;
				}
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return previousMonthTrips;
	}
	public static Double getTotalMilesCovered(List<UberHistory> pastRides){
		Double sum = 0.0;
		for(UberHistory pastRide:pastRides){
			sum+=Double.parseDouble(pastRide.getDistance());
		}
		return sum;
	}
}
