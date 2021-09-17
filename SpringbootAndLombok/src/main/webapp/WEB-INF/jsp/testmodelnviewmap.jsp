<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
<title>Test Model, View, Map</title>
</head>
<body>
	<div>JSP Map Test </div>
	<c:forEach var="item" items="${result1}" varStatus="idx"> 
		${idx.index} || ${item.key} || ${item.value} <br /> 
	</c:forEach>
	
	<c:forEach var="item" items="${result2}" varStatus="idx"> 
		${idx.index} || ${item.key} || ${item.value} <br /> 
	</c:forEach>
	
	<c:forEach var="item1" items="${result2}" varStatus="idx"> 
		<c:forEach var="item2" items="${item1}" varStatus="idx"> 
			${idx.index} || ${item2.key} || ${item2.value} <br /> 
		</c:forEach> 
	</c:forEach>
</body>
</html>