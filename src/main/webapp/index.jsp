<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int userId;
	boolean isLoggedIn;

	if(session.getAttribute("user_id")==null || session.getAttribute("is_logged_in")==null){
		response.sendRedirect("login.jsp");
	}else{
		userId = (int) session.getAttribute("user_id");
		isLoggedIn = (boolean) session.getAttribute("is_logged_in");
	}
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
</head>
<body>
	<h1>Welcome Student</h1>
	<a href="StudentController?action=all">View all students</a><br/><br/>
	<a href="StudentController?action=logout">Logout</a>
</body>
</html>