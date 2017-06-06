package com.ridetrends.utilities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ridetrends.bean.UberHistory;
import com.ridetrends.bean.UberMonthlyStats;

public class UberMonthWiseStats {
	
	public UberMonthlyStats getMonthWiseStats(List<UberHistory> allHistory){
		UberMonthlyStats uberMonthlyStats = new UberMonthlyStats();
		Map<Long, Long> monthWiseRidesCount = new HashMap<Long, Long>();
		Map<Long, Double> monthWiseTotalDistanceCovered = new HashMap<Long, Double>();
		Map<Long, Double> monthWiseTotalRideTimes = new HashMap<Long, Double>();
		Map<Long, Double> monthWiseTotalWaitingTimes = new HashMap<Long, Double>();

		Long month;
		for (UberHistory ride : allHistory) {
			month = UberUtil.getMonth(ride.getStart_time());
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
						+ UberUtil.getTimeDifferenceInHours(ride.getEnd_time(), ride.getStart_time()));
			} else {
				monthWiseTotalRideTimes.put(month,
						UberUtil.getTimeDifferenceInHours(ride.getEnd_time(), ride.getStart_time()));
			}
			if (monthWiseTotalWaitingTimes.containsKey(month)) {
				monthWiseTotalWaitingTimes.put(month, monthWiseTotalWaitingTimes.get(month)
						+ UberUtil.getTimeDifferenceInHours(ride.getStart_time(), ride.getRequest_time()));
			} else {
				monthWiseTotalWaitingTimes.put(month,
						UberUtil.getTimeDifferenceInHours(ride.getStart_time(), ride.getRequest_time()));
			}
		}
		uberMonthlyStats.setMonthWiseRidesCount(monthWiseRidesCount);
		uberMonthlyStats.setMonthWiseTotalDistanceCovered(monthWiseTotalDistanceCovered);
		uberMonthlyStats.setMonthWiseTotalRideTimes(monthWiseTotalRideTimes);
		uberMonthlyStats.setMonthWiseTotalWaitingTimes(monthWiseTotalWaitingTimes);
		return uberMonthlyStats;
	}
	

}
