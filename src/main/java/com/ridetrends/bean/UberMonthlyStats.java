package com.ridetrends.bean;

import java.util.Map;

public class UberMonthlyStats {
	private Map<Long, Long> monthWiseRidesCount;
	private Map<Long, Double> monthWiseTotalDistanceCovered;
	private Map<Long, Double> monthWiseTotalRideTimes;
	private Map<Long, Double> monthWiseTotalWaitingTimes;

	public Map<Long, Long> getMonthWiseRidesCount() {
		return monthWiseRidesCount;
	}

	public void setMonthWiseRidesCount(Map<Long, Long> monthWiseRidesCount) {
		this.monthWiseRidesCount = monthWiseRidesCount;
	}

	public Map<Long, Double> getMonthWiseTotalDistanceCovered() {
		return monthWiseTotalDistanceCovered;
	}

	public void setMonthWiseTotalDistanceCovered(Map<Long, Double> monthWiseTotalDistanceCovered) {
		this.monthWiseTotalDistanceCovered = monthWiseTotalDistanceCovered;
	}

	public Map<Long, Double> getMonthWiseTotalRideTimes() {
		return monthWiseTotalRideTimes;
	}

	public void setMonthWiseTotalRideTimes(Map<Long, Double> monthWiseTotalRideTimes) {
		this.monthWiseTotalRideTimes = monthWiseTotalRideTimes;
	}

	public Map<Long, Double> getMonthWiseTotalWaitingTimes() {
		return monthWiseTotalWaitingTimes;
	}

	public void setMonthWiseTotalWaitingTimes(Map<Long, Double> monthWiseTotalWaitingTimes) {
		this.monthWiseTotalWaitingTimes = monthWiseTotalWaitingTimes;
	}

}
