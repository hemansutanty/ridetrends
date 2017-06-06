<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- Compiled and minified CSS -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.98.2/css/materialize.min.css">

<!-- Compiled and minified JavaScript -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.98.2/js/materialize.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
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
					Miles covered this month&nbsp;:&nbsp;<b><fmt:formatNumber value="${uberStats.totalMilesCoveredThisMonth}" maxFractionDigits="2"/></b>
				</h6>
				<h6>
					Miles covered last month&nbsp;:&nbsp;<b><fmt:formatNumber value="${uberStats.totalMilesCoveredLastMonth}" maxFractionDigits="2"/></b>
				</h6>
			</div>
		</div>
</body>
</html>