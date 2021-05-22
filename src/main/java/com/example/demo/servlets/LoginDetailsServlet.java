package com.example.demo.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.example.demo.entities.User;
import com.example.demo.helper.FactoryProvider;

@WebServlet(name = "LoginDetailsServlet", description = "Servlet for Login", urlPatterns = { "/LoginDetailsServlet" })
public class LoginDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginDetailsServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int i = 0;
		System.out.println("here");
		try {
			String email = request.getParameter("email");

			String pwd = request.getParameter("pwd");

			PrintWriter out = response.getWriter();

			Session s = FactoryProvider.getFactory().openSession();
			Transaction tx = s.beginTransaction();

			Query q = s.createQuery("from User");
			List<User> list = q.list();
			User current_user = new User();

			for (User user : list) {
				if (user.getEmail().equals(email) && user.getPassword().equals(pwd)) {
					i = 1;
					current_user = user;
					break;
				}

			}

			if (i == 1) {
				if (current_user.getRole().equals("content-author")) {
					ServletContext servletcontext = getServletContext();
					servletcontext.setAttribute("current_user_id", current_user.getUser_id());
//					response.sendRedirect("contentauthor.jsp?id=" + Integer.toString(current_user.getUser_id()));
					response.sendRedirect("contentAuthor?id=" + Integer.toString(current_user.getUser_id()));
				} else if (current_user.getRole().equals("content-admin")) {
					ServletContext servletcontext = getServletContext();
					servletcontext.setAttribute("current_user_id", current_user.getUser_id());
//					response.sendRedirect("ContentAdmin.jsp?id=" + Integer.toString(current_user.getUser_id()));
					response.sendRedirect("contentAdmin?id=" + Integer.toString(current_user.getUser_id()));
				} else if (current_user.getRole().equals("template-author")) {
					ServletContext servletcontext = getServletContext();
					servletcontext.setAttribute("current_user_id", current_user.getUser_id());
//					response.sendRedirect("createXML.html?id=" + Integer.toString(current_user.getUser_id()));
					response.sendRedirect("templateAuthor?id=" + Integer.toString(current_user.getUser_id()));
				}

			} else {
				out.println(
						"<div class='text-center'><h1 style='text-align:center;'>Invalid Email Address or Password !!<br><a  href='login'>Back</a></h1></div><br>");
			}

			tx.commit();
			s.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
