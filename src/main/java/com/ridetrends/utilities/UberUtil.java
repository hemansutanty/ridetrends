package com.ridetrends.utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.joda.time.DateTime;
import org.joda.time.Duration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ridetrends.bean.UberHistory;
import com.ridetrends.bean.UberHistoryBean;

public class UberUtil {
	private static final String HISTORY_ENDPOINT = "https://api.uber.com/v1.2/history";
	private static final String ACCEPT_LANGUAGE = "en_US";
	private static final String CONTENT_TYPE = "application/json";

	public static Long getMonth(String unixTimeStamp) {
		Long timeStamp = Long.valueOf(unixTimeStamp) * 1000;
		DateTime dt = new DateTime(timeStamp);
		return new Long(dt.getMonthOfYear());
	}

	public static Double getTimeDifferenceInHours(String endTime, String startTime) {
		Long endTimeStamp = Long.valueOf(endTime) * 1000;
		Long startTimeStamp = Long.valueOf(startTime) * 1000;
		DateTime endDT = new DateTime(endTimeStamp);
		DateTime startDT = new DateTime(startTimeStamp);
		Duration duration = new Duration(startDT, endDT);
		return Double.valueOf(duration.getStandardHours());
	}

	public static Double getTotalMilesCovered(List<UberHistory> pastRides) {
		Double sum = 0.0;
		for (UberHistory pastRide : pastRides) {
			sum += Double.parseDouble(pastRide.getDistance());
		}
		return sum;
	}

	public static int getTotalHistoryItems(String accessToken){
		CloseableHttpClient httpClient = HttpClients.createDefault();
		// Use Access Token to get the ride history
		HttpGet getRequest = new HttpGet(HISTORY_ENDPOINT + "?limit=1");
		getRequest.setHeader("Authorization", "Bearer " + accessToken);
		getRequest.setHeader("Accept-Language", ACCEPT_LANGUAGE);
		getRequest.setHeader("Content-Type", CONTENT_TYPE);
		HttpResponse httpResponse;
		UberHistoryBean uberHistoryBean = null;

		try {
			httpResponse = httpClient.execute(getRequest);
			Reader reader = new InputStreamReader(httpResponse.getEntity().getContent());
			BufferedReader bufferedReader = new BufferedReader(reader);
			String line = bufferedReader.readLine();
			httpClient.close();
			ObjectMapper objectMapper = new ObjectMapper();
			uberHistoryBean = objectMapper.readValue(line, UberHistoryBean.class);

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Integer.parseInt(uberHistoryBean.getCount());
	}
	public static List<UberHistory> getUberHistory(String accessToken, int offset) {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		// Use Access Token to get the ride history
		HttpGet getRequest = new HttpGet(HISTORY_ENDPOINT + "?limit=50&offset=" + offset);
		getRequest.setHeader("Authorization", "Bearer " + accessToken);
		getRequest.setHeader("Accept-Language", ACCEPT_LANGUAGE);
		getRequest.setHeader("Content-Type", CONTENT_TYPE);
		HttpResponse httpResponse;
		UberHistoryBean uberHistoryBean = null;

		try {
			httpResponse = httpClient.execute(getRequest);
			Reader reader = new InputStreamReader(httpResponse.getEntity().getContent());
			BufferedReader bufferedReader = new BufferedReader(reader);
			String line = bufferedReader.readLine();
			httpClient.close();
			ObjectMapper objectMapper = new ObjectMapper();
			uberHistoryBean = objectMapper.readValue(line, UberHistoryBean.class);

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return uberHistoryBean.getHistory();
	}
}
