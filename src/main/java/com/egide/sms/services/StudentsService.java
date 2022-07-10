package com.egide.sms.services;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.egide.sms.models.Student;
import com.egide.sms.dao.StudentDAO;

public class StudentsService {
	private StudentDAO stdao = new StudentDAO();
	
	public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		Student std = new Student();
		HttpSession session = request.getSession();
		std.setFirstName(request.getParameter("firstName"));
		std.setLastName(request.getParameter("lastName"));
		std.setEmail(request.getParameter("email"));
		std.setPassword(request.getParameter("password"));
		
		Student existingStudent = stdao.findByEmail(std.getEmail());
		if(existingStudent != null) {
			session.setAttribute("error", "Email already used");
			response.sendRedirect("register.jsp");
			return;
		}else {
			Student newStudent = stdao.save(std);
			if(newStudent == null) {
				session.setAttribute("error", "Failed to create student");
				response.sendRedirect("register.jsp");
				return;
			}else {
				session.setAttribute("message", "Student created");
				response.sendRedirect("login.jsp");
			}
		}
	}
	
	public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		Student std = new Student();
		HttpSession session = request.getSession();
		std.setEmail(request.getParameter("email"));
		std.setPassword(request.getParameter("password"));
		
		Student existingStudent = stdao.findByEmail(std.getEmail());
		if(existingStudent == null) {
			session.setAttribute("error", "Invalid email or password");
			response.sendRedirect("login.jsp");
			return;
		}else if(!existingStudent.getPassword().equals(std.getPassword())) {
			session.setAttribute("error", "Invalid email or password");
			response.sendRedirect("login.jsp");
			return;
		}else {
			session.setAttribute("user_id", existingStudent.getId());
			session.setAttribute("is_logged_in", true);
			response.sendRedirect("index.jsp");
		}
	}
	
	public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("user_id");
		session.removeAttribute("is_logged_in");
		response.sendRedirect("login.jsp");
	}
	
	public void getAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		ArrayList<Student> list = stdao.getAll();
		session.setAttribute("students", list);
		response.sendRedirect("all-students.jsp");
	}
	
	public void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int id = Integer.parseInt(request.getParameter("id"));
		stdao.deleteById(id);
		ArrayList<Student> list = stdao.getAll();
		session.setAttribute("students", list);
		response.sendRedirect("all-students.jsp");
	}
}
