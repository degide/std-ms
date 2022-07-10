package com.egide.sms.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.egide.sms.services.StudentsService;

/**
 * Servlet implementation class StudentController
 */
@WebServlet("/StudentController")
public class StudentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudentsService stdService = new StudentsService();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		switch(action) {
			case "logout":
				stdService.logout(request, response);
				break;
			case "all":
				stdService.getAll(request, response);
				break;
			case "delete":
				stdService.deleteStudent(request, response);
				break;
			default:
				response.getWriter().append("404 NOT FOUND");
				break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		switch(action) {
			case "register":
				stdService.register(request, response);
				break;
			case "login":
				stdService.login(request, response);
				break;
			default:
				response.getWriter().append("404 NOT FOUND");
				break;
		}
	}

}
