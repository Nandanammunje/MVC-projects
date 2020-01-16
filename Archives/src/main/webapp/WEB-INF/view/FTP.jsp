<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome to Libra Archives</title>
<%@ page isELIgnored="false" %>
</head>
<body>
<form:form action="savedocument" modelAttribute="docname" method="POST">
<label>Enter The Name of the Document</label>
<form:input path="path"/>



<br><br>
<input type="submit" value="download"/>
</form:form>
<table>
<c:forEach var="temp" items="${names}">
<tr>
<td>${temp}</td>
</tr>
</c:forEach>
</table>
</body>
</html>