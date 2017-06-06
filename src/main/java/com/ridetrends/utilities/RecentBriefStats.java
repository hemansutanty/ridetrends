package com.ridetrends.utilities;

import java.util.ArrayList;
import java.util.List;
import com.ridetrends.bean.UberHistory;

public class RecentBriefStats {

	public static List<UberHistory> getRecentMonthTrips(List<UberHistory> allHistory, Long recentMonth) {
		List<UberHistory> recentMonthTrips = new ArrayList<UberHistory>();
		for (UberHistory pastTrip : allHistory) {
			Long month = UberUtil.getMonth(pastTrip.getStart_time());
			if (month.equals(recentMonth)) {
				recentMonthTrips.add(pastTrip);
			} else {
				break;
			}
		}
		return recentMonthTrips;
	}

	public static List<UberHistory> getPreviousMonthTrips(List<UberHistory> allHistory, Long previousMonth) {
		List<UberHistory> previousMonthTrips = new ArrayList<UberHistory>();

		for (UberHistory pastTrip : allHistory) {
			Long month = UberUtil.getMonth(pastTrip.getStart_time());
			if (month.equals(previousMonth)) {
				previousMonthTrips.add(pastTrip);
			} else if (month < previousMonth) {
				break;
			}
		}
	return previousMonthTrips;
	}
}
