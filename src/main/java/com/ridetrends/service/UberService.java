package com.ridetrends.service;

import java.io.IOException;
import com.ridetrends.bean.UberMonthlyStats;
import com.ridetrends.bean.UberProfileBean;
import com.ridetrends.bean.UberSavedPlaces;
import com.ridetrends.bean.UberRecentBriefStats;

public interface UberService {

	public UberProfileBean getUberProfile() throws UnsupportedOperationException, IOException;

	public UberRecentBriefStats getUberRecentBriefStats() throws UnsupportedOperationException, IOException;

	public UberSavedPlaces getSavedPlaces(String accessToken) throws UnsupportedOperationException, IOException;

	public UberMonthlyStats getMonthWiseStats();

}
