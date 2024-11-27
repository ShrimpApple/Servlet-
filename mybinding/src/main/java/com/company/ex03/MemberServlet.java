package com.company.ex03;

import jakarta.servlet.DispatcherType;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

import org.apache.tomcat.util.net.DispatchType;


@WebServlet("/member3")
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request,response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request,response);
	}
	

	
	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset-utf-8");
		PrintWriter out = response.getWriter();
		
		MemberDAO dao = new MemberDAO();
		
		List memberList=dao.listMembers();
		request.setAttribute("memberList", memberList);
		RequestDispatcher dispatch = request.getRequestDispatcher("viewMembers");
		dispatch.forward(request, response);
	}

}
