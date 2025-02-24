<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
//Lấy dữ liệu từ form
String username = request.getParameter("tk");
String password = request.getParameter("mk");

// Kiểm tra đăng nhập
if ("ABC".equals(username) && "MNK".equals(password)) {
    response.sendRedirect("UserProfile.jsp");
} else {
    response.sendRedirect("Login.html");
}
%>
</body>
</html>