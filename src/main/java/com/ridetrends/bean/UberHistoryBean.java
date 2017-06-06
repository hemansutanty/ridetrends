package com.ridetrends.bean;

import java.util.List;

public class UberHistoryBean {
	private String count;
	private List<UberHistory> history;
	private String limit;
	private String offset;

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public List<UberHistory> getHistory() {
		return history;
	}

	public void setHistory(List<UberHistory> history) {
		this.history = history;
	}

	public String getLimit() {
		return limit;
	}

	public void setLimit(String limit) {
		this.limit = limit;
	}

	public String getOffset() {
		return offset;
	}

	public void setOffset(String offset) {
		this.offset = offset;
	}

}
