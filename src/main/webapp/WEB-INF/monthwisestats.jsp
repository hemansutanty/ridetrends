<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- Compiled and minified CSS -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.98.2/css/materialize.min.css">

<!-- Compiled and minified JavaScript -->
<script src="https://d3js.org/d3.v4.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.98.2/js/materialize.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="row">
		<div class="col s8 offset-s4">
			<h6>
				<b><u>Month Wise Statistics</u></b>
			</h6>
		</div>

	</div>
	<div class="row">
		<div class="col s3">
			<h6>
				<b>Rides Count</b>
			</h6>
			Jan : ${monthwisestats.monthWiseRidesCount[1]}<br> Feb :
			${monthwisestats.monthWiseRidesCount[2]}<br> Mar :
			${monthwisestats.monthWiseRidesCount[3]}<br> Apr :
			${monthwisestats.monthWiseRidesCount[4]}<br> May :
			${monthwisestats.monthWiseRidesCount[5]}<br> Jun :
			${monthwisestats.monthWiseRidesCount[6]}<br> Jul :
			${monthwisestats.monthWiseRidesCount[7]}<br> Aug :
			${monthwisestats.monthWiseRidesCount[8]}<br> Sep :
			${monthwisestats.monthWiseRidesCount[9]}<br> Oct :
			${monthwisestats.monthWiseRidesCount[10]}<br> Nov :
			${monthwisestats.monthWiseRidesCount[11]}<br> Dec :
			${monthwisestats.monthWiseRidesCount[12]}<br>

		</div>

		<div class="col s3">
			<h6>
				<b>Distance Covered(miles)</b>
			</h6>
			Jan :
			<fmt:formatNumber
				value="${monthwisestats.monthWiseTotalDistanceCovered[1]}"
				maxFractionDigits="2" />
			<br> Feb :
			<fmt:formatNumber
				value="${monthwisestats.monthWiseTotalDistanceCovered[2]}"
				maxFractionDigits="2" />
			<br> Mar :
			<fmt:formatNumber
				value="${monthwisestats.monthWiseTotalDistanceCovered[3]}"
				maxFractionDigits="2" />
			<br> Apr :
			<fmt:formatNumber
				value="${monthwisestats.monthWiseTotalDistanceCovered[4]}"
				maxFractionDigits="2" />
			<br> May :
			<fmt:formatNumber
				value="${monthwisestats.monthWiseTotalDistanceCovered[5]}"
				maxFractionDigits="2" />
			<br> Jun :
			<fmt:formatNumber
				value="${monthwisestats.monthWiseTotalDistanceCovered[6]}"
				maxFractionDigits="2" />
			<br> Jul :
			<fmt:formatNumber
				value="${monthwisestats.monthWiseTotalDistanceCovered[7]}"
				maxFractionDigits="2" />
			<br> Aug :
			<fmt:formatNumber
				value="${monthwisestats.monthWiseTotalDistanceCovered[8]}"
				maxFractionDigits="2" />
			<br> Sep :
			<fmt:formatNumber
				value="${monthwisestats.monthWiseTotalDistanceCovered[9]}"
				maxFractionDigits="2" />
			<br> Oct :
			<fmt:formatNumber
				value="${monthwisestats.monthWiseTotalDistanceCovered[10]}"
				maxFractionDigits="2" />
			<br> Nov :
			<fmt:formatNumber
				value="${monthwisestats.monthWiseTotalDistanceCovered[11]}"
				maxFractionDigits="2" />
			<br> Dec :
			<fmt:formatNumber
				value="${monthwisestats.monthWiseTotalDistanceCovered[12]}"
				maxFractionDigits="2" />
			<br>

		</div>

		<div class="col s3">
			<h6>
				<b>Ride Times(Hours)</b>
			</h6>
			Jan : 
			<fmt:formatNumber
				value="${monthwisestats.monthWiseTotalRideTimes[1]}"
				maxFractionDigits="2" />
			<br>
			Feb : 
			<fmt:formatNumber
				value="${monthwisestats.monthWiseTotalRideTimes[2]}"
				maxFractionDigits="2" />
			<br>
			Mar : 
			<fmt:formatNumber
				value="${monthwisestats.monthWiseTotalRideTimes[3]}"
				maxFractionDigits="2" />
			<br> 
			Apr : 
			<fmt:formatNumber
				value="${monthwisestats.monthWiseTotalRideTimes[4]}"
				maxFractionDigits="2" />
			<br>
			May : 
			<fmt:formatNumber
				value="${monthwisestats.monthWiseTotalRideTimes[5]}"
				maxFractionDigits="2" />
			<br>
			Jun : 
			<fmt:formatNumber
				value="${monthwisestats.monthWiseTotalRideTimes[6]}"
				maxFractionDigits="2" />
			<br>
			Jul : 
			<fmt:formatNumber
				value="${monthwisestats.monthWiseTotalRideTimes[7]}"
				maxFractionDigits="2" />
			<br>
			Aug : 
			<fmt:formatNumber
				value="${monthwisestats.monthWiseTotalRideTimes[8]}"
				maxFractionDigits="2" />
			<br>
			Sep : 
			<fmt:formatNumber
				value="${monthwisestats.monthWiseTotalRideTimes[9]}"
				maxFractionDigits="2" />
			<br>
			Oct : 
			<fmt:formatNumber
				value="${monthwisestats.monthWiseTotalRideTimes[10]}"
				maxFractionDigits="2" />
			<br>
			Nov : 
			<fmt:formatNumber
				value="${monthwisestats.monthWiseTotalRideTimes[11]}"
				maxFractionDigits="2" />
			<br>
			Dec : 
			<fmt:formatNumber
				value="${monthwisestats.monthWiseTotalRideTimes[12]}"
				maxFractionDigits="2" />
		</div>

		<div class="col s3">
			<h6>
				<b>Waiting Times(Hours)</b>
			</h6>
			Jan : 
			<fmt:formatNumber
				value="${monthwisestats.monthWiseTotalWaitingTimes[1]}"
				maxFractionDigits="2" />
			<br>
			Feb : 
			<fmt:formatNumber
				value="${monthwisestats.monthWiseTotalWaitingTimes[2]}"
				maxFractionDigits="2" />
			<br>
			Mar : 
			<fmt:formatNumber
				value="${monthwisestats.monthWiseTotalWaitingTimes[3]}"
				maxFractionDigits="2" />
			<br> 
			Apr : 
			<fmt:formatNumber
				value="${monthwisestats.monthWiseTotalWaitingTimes[4]}"
				maxFractionDigits="2" />
			<br>
			May : 
			<fmt:formatNumber
				value="${monthwisestats.monthWiseTotalWaitingTimes[5]}"
				maxFractionDigits="2" />
			<br>
			Jun : 
			<fmt:formatNumber
				value="${monthwisestats.monthWiseTotalWaitingTimes[6]}"
				maxFractionDigits="2" />
			<br>
			Jul : 
			<fmt:formatNumber
				value="${monthwisestats.monthWiseTotalWaitingTimes[7]}"
				maxFractionDigits="2" />
			<br>
			Aug : 
			<fmt:formatNumber
				value="${monthwisestats.monthWiseTotalWaitingTimes[8]}"
				maxFractionDigits="2" />
			<br>
			Sep : 
			<fmt:formatNumber
				value="${monthwisestats.monthWiseTotalWaitingTimes[9]}"
				maxFractionDigits="2" />
			<br>
			Oct : 
			<fmt:formatNumber
				value="${monthwisestats.monthWiseTotalWaitingTimes[10]}"
				maxFractionDigits="2" />
			<br>
			Nov : 
			<fmt:formatNumber
				value="${monthwisestats.monthWiseTotalWaitingTimes[11]}"
				maxFractionDigits="2" />
			<br>
			Dec : 
			<fmt:formatNumber
				value="${monthwisestats.monthWiseTotalRideTimes[12]}"
				maxFractionDigits="2" />
		</div>
	</div>
	<div class="row">
		<div class="col s3" id="chart"></div>
	</div>
</body>
</html>