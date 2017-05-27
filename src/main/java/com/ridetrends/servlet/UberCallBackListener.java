package com.ridetrends.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ridetrends.bean.UberAccessTokenBean;

public class UberCallBackListener extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
		
		//Detect the presence of an Authorization Code
		String authorizationCode = request.getParameter("code");
		if(authorizationCode != null && authorizationCode.length()>0){
			final String TOKEN_ENDPOINT = "https://login.uber.com/oauth/v2/token";
			final String GRANT_TYPE = "authorization_code";
			final String REDIRECT_URI = "http://localhost:8080/ridetrends/callback";
			final String CLIENT_ID = System.getenv("UBER_CLIENT_ID");
			final String CLIENT_SECRET = System.getenv("UBER_CLIENT_SECRET");
			final String HISTORY_ENDPOINT = "https://api.uber.com/v1.2/history";
			final String ACCEPT_LANGUAGE = "en_US";
			final String CONTENT_TYPE = "application/json";
			//Generate Post Request
		
			HttpPost httpPost = new HttpPost(TOKEN_ENDPOINT+
					"?grant_type="+URLEncoder.encode(GRANT_TYPE,StandardCharsets.UTF_8.name())+
					"&code="+URLEncoder.encode(authorizationCode,StandardCharsets.UTF_8.name())+
					"&redirect_uri="+URLEncoder.encode(REDIRECT_URI,StandardCharsets.UTF_8.name())+
					"&client_id="+URLEncoder.encode(CLIENT_ID,StandardCharsets.UTF_8.name()));
			
			//Add Authorization header with encoded client creds
			String clientCredentials = CLIENT_ID+":"+CLIENT_SECRET;
			String encodedClientCredentials = new String(Base64.encodeBase64(clientCredentials.getBytes()));
			httpPost.setHeader("Authorization","Basic "+encodedClientCredentials);
			
			//Make the access token request
			CloseableHttpClient httpClient = HttpClients.createDefault();
			HttpResponse httpResponse = httpClient.execute(httpPost);
			
			//Handle Access token Response
			Reader reader = new InputStreamReader(httpResponse.getEntity().getContent());
			BufferedReader bufferedReader = new BufferedReader(reader);
			String line = bufferedReader.readLine();
		
			//Isolate Access token
			ObjectMapper objectMapper = new ObjectMapper();
			UberAccessTokenBean uberAccessTokenBean = objectMapper.readValue(line, UberAccessTokenBean.class);
			String accessToken = uberAccessTokenBean.getAccess_token();
			
			//Use Access Token to get the ride history
			HttpGet getRequest = new HttpGet(HISTORY_ENDPOINT);
			getRequest.setHeader("Authorization","Bearer "+accessToken);
			getRequest.setHeader("Accept-Language",ACCEPT_LANGUAGE);
			getRequest.setHeader("Content-Type",CONTENT_TYPE);
			httpResponse = httpClient.execute(getRequest);
			reader = new InputStreamReader(httpResponse.getEntity().getContent());
			bufferedReader = new BufferedReader(reader);
			line = bufferedReader.readLine();
			
			
			httpClient.close();
			
		}
		else{
			
		}
	}
}
