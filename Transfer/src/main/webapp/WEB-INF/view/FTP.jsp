<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>File Transfer Portal</title>
</head>
<body>
<h1>WELCOME TO FILE TRANSFER PORTAL</h1>
<form:form action="getdoc" modelAttribute="pathfinder" method="GET">
<table>
<tbody>
<tr>
<td><label>Enter the name of the document</label></td>
<td><form:input path="path"/></td>
</tr>
<tr>
<td>list of documents</td>
</tr>
<tr>
</tr>
<c:forEach var="docname" items="${service}">
<tr>
<td>${docname}</td>
</tr>
</c:forEach>
<tr>
<td><input type="submit" value="submit"></td>
</tr>
</tbody>
</table>
</form:form>
</body>
</html>