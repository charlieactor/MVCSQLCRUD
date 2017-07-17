<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Top 10 Hikes Near Denver</title>
</head>
<body>
	<h5>Top 10 Hikes Near Denver</h5>
     <c:forEach items="${allhikes}" var="hike" varStatus="status">
     	<c:out value="${hike.name}"/>
     	
     	<h3>${hike.name}</h3>
       <ul>
       	  <li>${hike.name}</li>
	      <li>${hike.difficulty}</li>
	      <li>${hike.length}</li>
	      <li>${hike.distanceFromDenver}</li>
	      <li>${hike.fact}</li>
       </ul> 
     </c:forEach>
</body>
</html>