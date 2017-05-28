package com.ridetrends.service;

import java.io.IOException;

import com.ridetrends.bean.UberProfileBean;

public interface UberService {
	public UberProfileBean getUberProfile(String accessToken) throws UnsupportedOperationException, IOException;
}
