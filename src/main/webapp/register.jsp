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
<title>Register</title>
</head>
<body>
	<form action="StudentController?action=register" method="POST">
		<h1>Register</h1>
		<label>First Name</label><br/>
		<input type="text" name="firstName" required/><br/><br/>
		<label>Last name</label><br/><br/>
		<input type="text" name="lastName" required/><br/><br/>
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
		<button type="submit">Register</button>
	</form>
	<br/><br/><a href="login.jsp">Login to existing account</a>
</body>
</html>