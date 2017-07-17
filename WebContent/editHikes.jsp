<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edit Hikes</title>
</head>
<body>
<h2>Select a hike you'd like to edit:</h2>
<c:choose>
	  <c:when test="${! empty allHikes}">
     	<form action="editSingleHike.do" method="GET">
     	<select name="hikeToEdit">
     <c:forEach items="${allHikes}" var="hike">
     	<option value="${hike.name}">${hike.name}</option>
     </c:forEach>
     	</select>
     	<input type="submit" value="edit this hike">
     	</form>
     </c:when>
     <c:otherwise>
     	No hikes found.
     </c:otherwise>
     </c:choose>
</body>
</html>