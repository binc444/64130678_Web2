<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@  page import="java.util.Date" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Hello JSP example</title>
</head>
<body>
	<h1>Hello JSP-64CNTTCLC</h1>
	<%= new Date().toString() %>
	<%
		int x = 5;
		int y = x + 99;
	%>
	
	<br>
	<!-- Cach 1 -->
	<%=y %>
	<br>
	<!-- cach 2 -->
	<% out.print(y); %>
</body>
</html>