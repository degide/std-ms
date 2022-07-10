<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Object error = session.getAttribute("error");
	Object message = session.getAttribute("message");
	session.removeAttribute("error");
	session.removeAttribute("message");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
	<form action="StudentController?action=login" method="POST">
		<h1>Login</h1>
		<label>Email</label><br/>
		<input type="email" name="email" required/><br/><br/>
		<label>Password</label><br/>
		<input type="password" name="password" required/><br/><br/>
		<% if (error != null) {%>
			<p style="color: red;"><%= (String) error %></p>
		<% } %>
		<% if (message != null) {%>
			<p style="color: green;"><%= (String) message %></p>
		<% } %>
		<button type="submit">Login</button>
	</form>
	<br/><br/><a href="register.jsp">Create new account</a>
</body>
</html>