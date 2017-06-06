package com.ridetrends.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ridetrends.bean.UberAccessTokenBean;
import com.ridetrends.bean.UberHistory;
import com.ridetrends.bean.UberMonthlyStats;
import com.ridetrends.bean.UberProfileBean;
import com.ridetrends.bean.UberSavedPlaces;
import com.ridetrends.bean.UberRecentBriefStats;
import com.ridetrends.utilities.RecentBriefStats;
import com.ridetrends.utilities.UberMonthWiseStats;
import com.ridetrends.utilities.UberUtil;

public class UberServiceImpl implements UberService {

	final String TOKEN_ENDPOINT = "https://login.uber.com/oauth/v2/token";
	final String GRANT_TYPE = "authorization_code";
	final String REDIRECT_URI = "http://localhost:8080/ridetrends/callback";
	final String CLIENT_ID = System.getenv("UBER_CLIENT_ID");
	final String CLIENT_SECRET = System.getenv("UBER_CLIENT_SECRET");
	private final String PROFILE_ENDPOINT = "https://api.uber.com/v1.2/me";
	private final String ACCEPT_LANGUAGE = "en_US";
	private final String CONTENT_TYPE = "application/json";
	private String accessToken;

	private List<UberHistory> allHistory = null;

	public UberServiceImpl(String authorizationCode) throws UnsupportedOperationException, IOException {
		// Generate Post Request
		HttpPost httpPost = new HttpPost(
				TOKEN_ENDPOINT + "?grant_type=" + URLEncoder.encode(GRANT_TYPE, StandardCharsets.UTF_8.name())
						+ "&code=" + URLEncoder.encode(authorizationCode, StandardCharsets.UTF_8.name())
						+ "&redirect_uri=" + URLEncoder.encode(REDIRECT_URI, StandardCharsets.UTF_8.name())
						+ "&client_id=" + URLEncoder.encode(CLIENT_ID, StandardCharsets.UTF_8.name()));

		// Add Authorization header with encoded client creds
		String clientCredentials = CLIENT_ID + ":" + CLIENT_SECRET;
		String encodedClientCredentials = new String(Base64.encodeBase64(clientCredentials.getBytes()));
		httpPost.setHeader("Authorization", "Basic " + encodedClientCredentials);
		// Make the access token request
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpResponse httpResponse = httpClient.execute(httpPost);
		// Handle Access token Response
		Reader reader = new InputStreamReader(httpResponse.getEntity().getContent());
		BufferedReader bufferedReader = new BufferedReader(reader);
		String line = bufferedReader.readLine();
		// Isolate Access token
		ObjectMapper objectMapper = new ObjectMapper();
		UberAccessTokenBean uberAccessTokenBean = objectMapper.readValue(line, UberAccessTokenBean.class);
		accessToken = uberAccessTokenBean.getAccess_token();
		allHistory = getAllHistory(accessToken);
		httpClient.close();
	}

	public List<UberHistory> getAllHistory(String accessToken) throws UnsupportedOperationException, IOException {

		List<UberHistory> allHistory = new ArrayList<UberHistory>();
		int offset = 0;
		List<UberHistory> tempUberHistory = null;
		while (UberUtil.getUberHistory(accessToken, offset).size()!=0) {
			tempUberHistory = UberUtil.getUberHistory(accessToken, offset);
			allHistory.addAll(tempUberHistory);
			offset = offset + 50;
		}
		return allHistory;
	}

	@Override
	public UberProfileBean getUberProfile() throws UnsupportedOperationException, IOException {

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
	public UberRecentBriefStats getUberRecentBriefStats() throws UnsupportedOperationException, IOException {
		UberRecentBriefStats uberStats = new UberRecentBriefStats();
		Long recentMonth = UberUtil.getMonth(allHistory.get(0).getStart_time());
		List<UberHistory> recentMonthTrips = RecentBriefStats.getRecentMonthTrips(allHistory, recentMonth);
		Long previousMonth = recentMonth > 1L ? recentMonth - 1 : 12;
		List<UberHistory> previousMonthTrips = RecentBriefStats.getPreviousMonthTrips(allHistory, previousMonth);
		uberStats.setPresentMonthTotalRides(recentMonthTrips.size());
		uberStats.setLastMonthTotalRides(previousMonthTrips.size());
		Double totalMilesCoveredThisMonth = UberUtil.getTotalMilesCovered(recentMonthTrips);
		Double totalMilesCoveredLastMonth = UberUtil.getTotalMilesCovered(previousMonthTrips);
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
	public UberMonthlyStats getMonthWiseStats() {
		// TODO Auto-generated method stub
		UberMonthWiseStats monthWiseStats = new UberMonthWiseStats();
		UberMonthlyStats monthlyStats = monthWiseStats.getMonthWiseStats(allHistory);
		return monthlyStats;
	}

}
