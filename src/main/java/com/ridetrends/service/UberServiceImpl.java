package com.ridetrends.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ridetrends.bean.UberHistory;
import com.ridetrends.bean.UberHistoryBean;
import com.ridetrends.bean.UberMonthlyStats;
import com.ridetrends.bean.UberProfileBean;
import com.ridetrends.bean.UberSavedPlaces;
import com.ridetrends.bean.UberStats;
import com.ridetrends.utilities.CalculateUberStats;

public class UberServiceImpl implements UberService {

	private final String PROFILE_ENDPOINT = "https://api.uber.com/v1.2/me";
	private final String HISTORY_ENDPOINT = "https://api.uber.com/v1.2/history";
	private final String ACCEPT_LANGUAGE = "en_US";
	private final String CONTENT_TYPE = "application/json";
	private List<UberHistory> allHistory = null;

	public UberServiceImpl(String accessToken) throws UnsupportedOperationException, IOException {
		allHistory = getAllHistory(accessToken);
	}

	public List<UberHistory> getAllHistory(String accessToken) throws UnsupportedOperationException, IOException {

		List<UberHistory> allHistory = new ArrayList<UberHistory>();
		int totalRides = CalculateUberStats.getTotalRides(accessToken);
		int presentCount = 0, offset = 0;
		List<UberHistory> tempUberHistory = null;
		while (offset < 10) {
			tempUberHistory = CalculateUberStats.getUberHistory(accessToken, offset);
			allHistory.addAll(tempUberHistory);
			presentCount = presentCount + tempUberHistory.size();
			offset++;
		}
		return allHistory;
	}

	@Override
	public UberProfileBean getUberProfile(String accessToken) throws UnsupportedOperationException, IOException {

		CloseableHttpClient httpClient = HttpClients.createDefault();

		// Use Access Token to get the uber profile
		HttpGet getRequest = new HttpGet(PROFILE_ENDPOINT);
		getRequest.setHeader("Authorization", "Bearer " + accessToken);
		getRequest.setHeader("Accept-Language", ACCEPT_LANGUAGE);
		getRequest.setHeader("Content-Type", CONTENT_TYPE);
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
		// Use Access Token to get the ride history
		HttpGet getRequest = new HttpGet(HISTORY_ENDPOINT);
		getRequest.setHeader("Authorization", "Bearer " + accessToken);
		getRequest.setHeader("Accept-Language", ACCEPT_LANGUAGE);
		getRequest.setHeader("Content-Type", CONTENT_TYPE);
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
	public UberStats getUberStats(String accessToken) throws UnsupportedOperationException, IOException {
		UberStats uberStats = new UberStats();
		int recentMonth = CalculateUberStats.getMonth(getUberHistory(accessToken).getHistory().get(0).getStart_time());
		List<UberHistory> recentMonthTrips = CalculateUberStats.getRecentMonthTrips(accessToken, recentMonth);
		int previousMonth = recentMonth > 1 ? recentMonth - 1 : 12;
		List<UberHistory> previousMonthTrips = CalculateUberStats.getPreviousMonthTrips(accessToken, previousMonth);
		uberStats.setPresentMonthTotalRides(recentMonthTrips.size());
		uberStats.setLastMonthTotalRides(previousMonthTrips.size());
		Double totalMilesCoveredThisMonth = CalculateUberStats.getTotalMilesCovered(recentMonthTrips);
		Double totalMilesCoveredLastMonth = CalculateUberStats.getTotalMilesCovered(previousMonthTrips);
		uberStats.setTotalMilesCoveredThisMonth(totalMilesCoveredThisMonth);
		uberStats.setTotalMilesCoveredLastMonth(totalMilesCoveredLastMonth);
		return uberStats;

	}

	@Override
	public UberSavedPlaces getSavedPlaces(String accessToken) {
		// TODO Auto-generated method stub
		UberSavedPlaces uberSavedPlaces = new UberSavedPlaces();

		return uberSavedPlaces;
	}

	@Override
	public UberMonthlyStats getMonthlyStats() {
		// TODO Auto-generated method stub
		UberMonthlyStats uberMonthlyStats = new UberMonthlyStats();
		Map<Integer, Long> monthWiseRidesCount = new HashMap<Integer, Long>();
		Map<Integer, Double> monthWiseTotalDistanceCovered = new HashMap<Integer, Double>();
		Map<Integer, Double> monthWiseTotalRideTimes = new HashMap<Integer, Double>();
		Map<Integer, Double> monthWiseTotalWaitingTimes = new HashMap<Integer, Double>();

		int month;
		for (UberHistory ride : allHistory) {
			month = CalculateUberStats.getMonth(ride.getStart_time());
			if (monthWiseRidesCount.containsKey(month)) {
				monthWiseRidesCount.put(month, monthWiseRidesCount.get(month) + 1);
			} else {
				monthWiseRidesCount.put(month, (long) 1);
			}
			if (monthWiseTotalDistanceCovered.containsKey(month)) {
				monthWiseTotalDistanceCovered.put(month,
						monthWiseTotalDistanceCovered.get(month) + Double.parseDouble(ride.getDistance()));
			} else {
				monthWiseTotalDistanceCovered.put(month, Double.parseDouble(ride.getDistance()));
			}
			if (monthWiseTotalRideTimes.containsKey(month)) {
				monthWiseTotalRideTimes.put(month, monthWiseTotalRideTimes.get(month)
						+ CalculateUberStats.getTimeDifferenceInHours(ride.getEnd_time(), ride.getStart_time()));
			} else {
				monthWiseTotalRideTimes.put(month,
						CalculateUberStats.getTimeDifferenceInHours(ride.getEnd_time(), ride.getStart_time()));
			}
			if (monthWiseTotalWaitingTimes.containsKey(month)) {
				monthWiseTotalWaitingTimes.put(month, monthWiseTotalWaitingTimes.get(month)
						+ CalculateUberStats.getTimeDifferenceInHours(ride.getStart_time(), ride.getRequest_time()));
			} else {
				monthWiseTotalWaitingTimes.put(month,
						CalculateUberStats.getTimeDifferenceInHours(ride.getStart_time(), ride.getRequest_time()));
			}
		}
		uberMonthlyStats.setMonthWiseRidesCount(monthWiseRidesCount);
		uberMonthlyStats.setMonthWiseTotalDistanceCovered(monthWiseTotalDistanceCovered);
		uberMonthlyStats.setMonthWiseTotalRideTimes(monthWiseTotalRideTimes);
		uberMonthlyStats.setMonthWiseTotalWaitingTimes(monthWiseTotalWaitingTimes);

		for (Integer key : monthWiseRidesCount.keySet()) {
			System.out.println(key + "\t" + monthWiseRidesCount.get(key));
		}
		return uberMonthlyStats;
	}

}
