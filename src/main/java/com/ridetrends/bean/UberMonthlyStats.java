package com.ridetrends.bean;

import java.util.Map;

public class UberMonthlyStats {
	private Map<Integer, Long> monthWiseRidesCount;
	private Map<Integer, Double> monthWiseTotalDistanceCovered;
	private Map<Integer, Double> monthWiseTotalRideTimes;
	private Map<Integer, Double> monthWiseTotalWaitingTimes;

	public Map<Integer, Long> getMonthWiseRidesCount() {
		return monthWiseRidesCount;
	}

	public void setMonthWiseRidesCount(Map<Integer, Long> monthWiseRidesCount) {
		this.monthWiseRidesCount = monthWiseRidesCount;
	}

	public Map<Integer, Double> getMonthWiseTotalDistanceCovered() {
		return monthWiseTotalDistanceCovered;
	}

	public void setMonthWiseTotalDistanceCovered(Map<Integer, Double> monthWiseTotalDistanceCovered) {
		this.monthWiseTotalDistanceCovered = monthWiseTotalDistanceCovered;
	}

	public Map<Integer, Double> getMonthWiseTotalRideTimes() {
		return monthWiseTotalRideTimes;
	}

	public void setMonthWiseTotalRideTimes(Map<Integer, Double> monthWiseTotalRideTimes) {
		this.monthWiseTotalRideTimes = monthWiseTotalRideTimes;
	}

	public Map<Integer, Double> getMonthWiseTotalWaitingTimes() {
		return monthWiseTotalWaitingTimes;
	}

	public void setMonthWiseTotalWaitingTimes(Map<Integer, Double> monthWiseTotalWaitingTimes) {
		this.monthWiseTotalWaitingTimes = monthWiseTotalWaitingTimes;
	}

}
