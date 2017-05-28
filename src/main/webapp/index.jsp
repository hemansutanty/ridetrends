<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Ride Trends</title>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"
	integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
	crossorigin="anonymous"></script>
<script>
	$(document).ready(function() {
		$("#goButton").click(makeRequest);
	});
	function makeRequest() {
		// TODO: Make authorization request
		// Define properties
		var AUTH_ENDPOINT = "https://login.uber.com/oauth/v2/authorize";
		var RESPONSE_TYPE = "code";
		var CLIENT_ID = "ZXBsvaMnsjgjXq66fkF0VNu7bXz3I3Nb";
		var REDIRECT_URI = "http://localhost:8080/ridetrends/callback";
		var SCOPE = "profile history";
		// Build authorization request endpoint
		var requestEndpoint = AUTH_ENDPOINT + "?" + "response_type="
				+ encodeURIComponent(RESPONSE_TYPE) + "&" + "client_id="
				+ encodeURIComponent(CLIENT_ID) + "&" + "redirect_uri="
				+ encodeURIComponent(REDIRECT_URI) + "&" + "scope="
				+ encodeURIComponent(SCOPE);
		// Send to authorization request endpoint
		window.location.href = requestEndpoint;
	}
</script>
</head>

<body>
	<button id="goButton" type="button">Go!</button>
	<div id="results"></div>
</body>
</html>