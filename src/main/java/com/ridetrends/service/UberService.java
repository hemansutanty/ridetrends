package com.ridetrends.service;

import java.io.IOException;
import com.ridetrends.bean.UberHistoryBean;
import com.ridetrends.bean.UberProfileBean;
import com.ridetrends.bean.UberStats;

public interface UberService {
	public UberProfileBean getUberProfile(String accessToken) throws UnsupportedOperationException, IOException;
	public UberHistoryBean getUberHistory(String accessToken) throws UnsupportedOperationException, IOException;
	public UberStats getUberStats(String accessToken);

}
