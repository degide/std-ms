<%@page import="com.egide.sms.models.Student"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	ArrayList<Student> students = null;
	if(session.getAttribute("students")==null){
		response.sendRedirect("StudentController?action=all");
	}else{
		students = (ArrayList<Student>) session.getAttribute("students");
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>All students</title>
<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>
</head>
<body>
	<br/><br/>
	<h4 class="text-center mb-3">ALL STUDENTS</h4>
	<% if(students!=null){ %>
		<table class="table table-striped table-bordered table-dark container">
			<thead>
				<tr>
					<th class="font-bold">ID</th>
					<th class="font-bold">First Name</th>
					<th class="font-bold">Last Name</th>
					<th class="font-bold">Email</th>
					<th class="font-bold">Password</th>
					<th colspan="2" class="font-bold">Action</th>
				</tr>
			</thead>
			<tbody>
				<% for(Student std: students) { %>
					<tr>
						<td><%= std.getId() %></td>
						<td><%= std.getFirstName() %></td>
						<td><%= std.getLastName() %></td>
						<td><%= std.getEmail() %></td>
						<td><%= std.getPassword() %></td>
						<td>
							<a class="text-success" href="edit-student.jsp?id=<%= std.getId() %>">Edit</a>
						</td>
						<td>
							<a class="text-danger" href="StudentController?action=delete&id=<%= std.getId() %>">Delete</a>
						</td>
					</tr>
				<% } %>
			</tbody>
		</table>
	<% } %>
	<br/>
	<br/>
	<p class="text-center"><a href="index.jsp">Go Back Home</a></p>
</body>
</html>