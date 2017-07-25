<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" />
<link type="text/css" href="HikeSiteStyles.css" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Lemonada" rel="stylesheet">
<title>Edit Hike</title>
<link rel="shortcut icon" href="https://raygun.com/images/integrations/caagilecentral.png" type="image/x-icon" />
</head>
<body>
<div class="navbar-wrapper">
    <div class="navbar navbar-inverse navbar-static-top">
      <div class="container">
        <a href="index.html" class="navbar-brand"><span id="name">Top Hikes Near Denver</span></a>

      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target=".navHeaderCollapse" aria-expanded="false" aria-controls="navbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <div class="collapse navbar-collapse navHeaderCollapse">
        <ul class="nav navbar-nav navbar-right">
         <li><a href="seeAllHikes.do?">Top Hikes</a></li>
         <li><a href="search.html">Search</a></li>
         <li><a href="addHike.html">Add a Hike</a></li>
         <li><a href="editHikes.do">Update and Delete Hikes</a></li>
       </ul>
      </div>
   </div>
  </div>
</div>
	<div class="bground">

	<div class="search">
	<h2 class="header">Current info for ${hike.name}:</h2>
	<ul>
		<li>${hike.name}</li>
		<li>${hike.difficulty}</li>
		<li>${hike.length} miles in length</li>
		<li>${hike.distanceFromDenver} miles from Denver</li>
		<li>${hike.fact}</li>
	</ul>
	<hr>
	<h3 class="header">Edit Hike:</h3>
	<form action="updateHike.do" method=POST>
		<p>Hike Name</p>
		<input type="text" name="name" value="${hike.name}"><br>
		<p>Hike Difficulty</p>
		<select name="difficulty">
			<option value="easy">Easy</option>
			<option value="easy-moderate">Easy-Moderate</option>
			<option value="moderate">Moderate</option>
			<option value="moderate-hard">Moderate-Hard</option>
			<option value="hard">Hard</option>
			<option value="hard-expert">Hard-Expert</option>
			<option value="expert">Expert</option>
		</select><br>
		<p>Hike Length (in miles) </p>
		<input type="text" name="length" value="${hike.length}"><br>
		<p>Hike distance from Denver (in miles) </p>
		<input type="text" name="distance" value="${hike.distanceFromDenver}"><br>
		<p>Hike fact</p>
		<input type="text" name="fact" value="${hike.fact}"><br><br>
		<input type="hidden" name="id" value="${hike.id}">
		<input type="submit" value="Update this hike">
	</form>
	<hr>
	<h3 class="header">Delete Hike:</h3>
	<form action="deleteHike.do" method=POST>
		<input type="hidden", name="name" value="${hike.name}">
		<input type="submit", value="Delete This Hike">
	</form>
	</div>
	<BR><BR>	<BR><BR><BR><BR><BR>
</div>
</body>
</html>