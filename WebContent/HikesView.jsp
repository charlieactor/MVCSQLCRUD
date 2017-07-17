<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Hike Results</title>
</head>
<body>
	<h3>${hike.name}</h3>
	<c:choose>
	  <c:when test="${! empty hike}">
	    <ul>
	      <li>${hike.name}</li>
	      <li>${hike.difficulty}</li>
	      <li>${hike.length}</li>
	      <li>${hike.distanceFromDenver}</li>
	      <li>${hike.fact}</li>
	    </ul>
	  </c:when>
	  <c:otherwise>
	   <p>Hike not found</p>
	  </c:otherwise>
	
	</c:choose>
	
</body>
</html>