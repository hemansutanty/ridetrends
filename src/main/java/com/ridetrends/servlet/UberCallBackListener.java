package com.ridetrends.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ridetrends.bean.UberMonthlyStats;
import com.ridetrends.bean.UberProfileBean;
import com.ridetrends.bean.UberRecentBriefStats;
import com.ridetrends.service.UberService;
import com.ridetrends.service.UberServiceImpl;

public class UberCallBackListener extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Detect the presence of an Authorization Code
		String authorizationCode = request.getParameter("code");
		if (authorizationCode != null && authorizationCode.length() > 0) {
			UberService uberService = new UberServiceImpl(authorizationCode);
			UberProfileBean uberProfileBean = uberService.getUberProfile();
			UberRecentBriefStats uberStats = uberService.getUberRecentBriefStats();
			UberMonthlyStats monthWiseStats = uberService.getMonthWiseStats();
			HttpSession session = request.getSession();
			session.setAttribute("uberprofile", uberProfileBean);
			session.setAttribute("uberStats", uberStats);
			session.setAttribute("monthwisestats", monthWiseStats);
			getServletContext().getRequestDispatcher("/WEB-INF/ubertrends.jsp").forward(request, response);
		} else {

		}
	}

}
