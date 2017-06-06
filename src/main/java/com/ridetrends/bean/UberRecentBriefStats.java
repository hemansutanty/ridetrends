package com.ridetrends.bean;

public class UberRecentBriefStats {
	private int presentMonthTotalRides;
	private int lastMonthTotalRides;
	private Double totalDistanceCoveredinMilesinLastMonth;
	private String lastMonthMostActiveStartCity;
	private Double totalMilesCoveredThisMonth;
	private Double totalMilesCoveredLastMonth;

	public Double getTotalMilesCoveredThisMonth() {
		return totalMilesCoveredThisMonth;
	}

	public void setTotalMilesCoveredThisMonth(Double totalMilesCoveredThisMonth) {
		this.totalMilesCoveredThisMonth = totalMilesCoveredThisMonth;
	}

	public Double getTotalMilesCoveredLastMonth() {
		return totalMilesCoveredLastMonth;
	}

	public void setTotalMilesCoveredLastMonth(Double totalMilesCoveredLastMonth) {
		this.totalMilesCoveredLastMonth = totalMilesCoveredLastMonth;
	}

	public int getLastMonthTotalRides() {
		return lastMonthTotalRides;
	}

	public void setLastMonthTotalRides(int lastMonthTotalRides) {
		this.lastMonthTotalRides = lastMonthTotalRides;
	}

	public Double getTotalDistanceCoveredinMilesinLastMonth() {
		return totalDistanceCoveredinMilesinLastMonth;
	}

	public void setTotalDistanceCoveredinMilesinLastMonth(Double totalDistanceCoveredinMilesinLastMonth) {
		this.totalDistanceCoveredinMilesinLastMonth = totalDistanceCoveredinMilesinLastMonth;
	}

	public String getLastMonthMostActiveStartCity() {
		return lastMonthMostActiveStartCity;
	}

	public void setLastMonthMostActiveStartCity(String lastMonthMostActiveStartCity) {
		this.lastMonthMostActiveStartCity = lastMonthMostActiveStartCity;
	}

	public int getPresentMonthTotalRides() {
		return presentMonthTotalRides;
	}

	public void setPresentMonthTotalRides(int presentMonthTotalRides) {
		this.presentMonthTotalRides = presentMonthTotalRides;
	}

}
