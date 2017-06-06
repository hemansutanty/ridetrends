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
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ridetrends.bean.UberAccessTokenBean;
import com.ridetrends.bean.UberProfileBean;
import com.ridetrends.bean.UberStats;
import com.ridetrends.service.UberService;
import com.ridetrends.service.UberServiceImpl;
import com.ridetrends.utilities.CalculateUberStats;

public class UberCallBackListener extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Detect the presence of an Authorization Code
		String authorizationCode = request.getParameter("code");
		if (authorizationCode != null && authorizationCode.length() > 0) {
			final String TOKEN_ENDPOINT = "https://login.uber.com/oauth/v2/token";
			final String GRANT_TYPE = "authorization_code";
			final String REDIRECT_URI = "http://localhost:8080/ridetrends/callback";
			final String CLIENT_ID = System.getenv("UBER_CLIENT_ID");
			final String CLIENT_SECRET = System.getenv("UBER_CLIENT_SECRET");
			// Generate Post Request
			HttpPost httpPost = new HttpPost(
					TOKEN_ENDPOINT + "?grant_type=" + URLEncoder.encode(GRANT_TYPE, StandardCharsets.UTF_8.name())
							+ "&code=" + URLEncoder.encode(authorizationCode, StandardCharsets.UTF_8.name())
							+ "&redirect_uri=" + URLEncoder.encode(REDIRECT_URI, StandardCharsets.UTF_8.name())
							+ "&client_id=" + URLEncoder.encode(CLIENT_ID, StandardCharsets.UTF_8.name()));

			// Add Authorization header with encoded client creds
			String clientCredentials = CLIENT_ID + ":" + CLIENT_SECRET;
			String encodedClientCredentials = new String(Base64.encodeBase64(clientCredentials.getBytes()));
			httpPost.setHeader("Authorization", "Basic " + encodedClientCredentials);
			// Make the access token request
			CloseableHttpClient httpClient = HttpClients.createDefault();
			HttpResponse httpResponse = httpClient.execute(httpPost);
			// Handle Access token Response
			Reader reader = new InputStreamReader(httpResponse.getEntity().getContent());
			BufferedReader bufferedReader = new BufferedReader(reader);
			String line = bufferedReader.readLine();
			// Isolate Access token
			ObjectMapper objectMapper = new ObjectMapper();
			UberAccessTokenBean uberAccessTokenBean = objectMapper.readValue(line, UberAccessTokenBean.class);
			String accessToken = uberAccessTokenBean.getAccess_token();
			httpClient.close();
			UberService uberService = new UberServiceImpl(accessToken);
			UberProfileBean uberProfileBean = uberService.getUberProfile(accessToken);
			UberStats uberStats = uberService.getUberStats(accessToken);
			uberService.getMonthlyStats();
			HttpSession session = request.getSession();
			session.setAttribute("uberprofile", uberProfileBean);
			session.setAttribute("uberStats", uberStats);
			getServletContext().getRequestDispatcher("/WEB-INF/ubertrends.jsp").forward(request, response);
		} else {

		}
	}

}
