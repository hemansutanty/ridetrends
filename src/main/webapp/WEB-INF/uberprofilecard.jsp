<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
		<div class="col s5 offset-s3">
			<div class="card-panel grey lighten-5 z-depth-1">
				<div class="row valign-wrapper">
					<div class="col s3">
						<img src="${uberprofile.picture}" alt=""
							class="circle responsive-img">
						<!-- notice the "circle" class -->
					</div>
					<div class="col s10">
						Name:&nbsp;<span class="black-text">${uberprofile.first_name}&nbsp;${uberprofile.last_name}</span><br>
						Email:&nbsp;<span class="black-text">${uberprofile.email}</span><br>
						Promo:&nbsp;<span class="black-text">${uberprofile.promo_code}</span><br>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>