<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>File Upload Service</title>
</head>
<body>
<h3>File Upload</h3>
Select a File to Upload<br/>
<form action="UploadDoc" method="post" enctype="multipart/form-data">
<input type="file" name="file" size="100000"/>
<br/>
<input type="submit" value="Upload File">

</form>
</body>
</html>