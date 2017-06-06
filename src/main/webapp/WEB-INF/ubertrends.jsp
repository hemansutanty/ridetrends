<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Compiled and minified CSS -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.98.2/css/materialize.min.css">

<!-- Compiled and minified JavaScript -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.98.2/js/materialize.min.js"></script>
<script src="https://d3js.org/d3.v4.min.js"></script>
<title>Your uber trends</title>
</head>
<body>
	<jsp:useBean id="uberprofile"
		class="com.ridetrends.bean.UberProfileBean" scope="session"></jsp:useBean>
	<jsp:useBean id="uberStats" class="com.ridetrends.bean.UberStats"
		scope="session"></jsp:useBean>
	<%@ page isELIgnored="false"%>
	<div class="container">
		<!-- Page Content goes here -->
		<jsp:include page="uberprofilecard.jsp"></jsp:include>
		<div class="row">
			<div class="col s6">
				<h6>
					Rides this month&nbsp;:&nbsp;<b>${uberStats.presentMonthTotalRides}</b>
				</h6>
				<h6>
					Rides last month&nbsp;:&nbsp;<b>${uberStats.lastMonthTotalRides}</b>
				</h6>
			</div>

			<div class="col s6">
				<h6>
					Miles covered this month&nbsp;:&nbsp;<b>${uberStats.totalMilesCoveredThisMonth}</b>
				</h6>
				<h6>
					Miles covered last month&nbsp;:&nbsp;<b>${uberStats.totalMilesCoveredLastMonth}</b>
				</h6>
			</div>
		</div>
	</div>
</body>

</html>