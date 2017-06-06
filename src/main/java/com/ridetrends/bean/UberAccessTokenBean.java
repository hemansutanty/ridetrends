package com.ridetrends.bean;

public class UberAccessTokenBean {
	private String last_authenticated;
	private String access_token;
	private String expires_in;
	private String token_type;
	private String scope;
	private String refresh_token;

	public String getLast_authenticated() {
		return last_authenticated;
	}

	public void setLast_authenticated(String last_authenticated) {
		this.last_authenticated = last_authenticated;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public String getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(String expires_in) {
		this.expires_in = expires_in;
	}

	public String getToken_type() {
		return token_type;
	}

	public void setToken_type(String token_type) {
		this.token_type = token_type;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getRefresh_token() {
		return refresh_token;
	}

	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}

}
