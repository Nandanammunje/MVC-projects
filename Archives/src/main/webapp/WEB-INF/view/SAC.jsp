<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>JCS Data</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css"/>
</head>
<body>
<h1>Welcome To JCS System Admin Console</h1>
<table>
<c:forEach var="temp" items="${cachedproperties}">
<tr>
<td>${temp}</td>
</tr>
</c:forEach>
</table>
</body>
</html>