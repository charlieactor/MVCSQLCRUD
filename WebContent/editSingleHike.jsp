<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edit Hike</title>
</head>
<body>
	<h2>Current info for ${hike.name}:</h2>
	<ul>
		<li>${hike.name}</li>
		<li>${hike.difficulty}</li>
		<li>${hike.length} miles in length</li>
		<li>${hike.distanceFromDenver} miles from Denver</li>
		<li>${hike.fact}</li>
	</ul>
	<hr>
	<h3>Edit Hike:</h3>
	<form action="updateHike.do" method=POST>
		<input type="text" name="name" value="${hike.name}"><br>
		<input type="text" name="difficulty" value="${hike.difficulty}"><br>
		<input type="text" name="length" value="${hike.length}"><br>
		<input type="text" name="distance" value="${hike.distanceFromDenver}"><br>
		<input type="text" name="fact" value="${hike.fact}"><br>
		<input type="submit" value="Update this hike">
	</form>
	<hr>
	<h3>Delete Hike:</h3>
	<form action="deleteHike.do" method=POST>
		<input type="hidden", name="delete">
		<input type="submit", value="Delete This Hike">
	</form>
</body>
</html>