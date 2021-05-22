package com.example.demo.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Random;

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

@WebServlet(name = "RegistrationServlet", description = "Example Servlet for Registration", urlPatterns = {
		"/RegistrationServlet" })
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RegistrationServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String firstname = request.getParameter("first_name");
			String lastname = request.getParameter("last_name");
			String e_mail = request.getParameter("email");
			String pwd = request.getParameter("password");
			String rol = request.getParameter("role");
			PrintWriter out = response.getWriter();

			int r = 0;
			Session s = FactoryProvider.getFactory().openSession();
			Transaction tx = s.beginTransaction();

			Query q3 = s.createQuery("From User");
			List<User> ul = q3.list();

			for (User x : ul) {
				if (e_mail.equals(x.getEmail())) {
					r = 1;
				}
			}

			if (r == 0) {

				User user = new User();
				Random a_id = new Random();
				user.setUser_id(a_id.nextInt(100));
				user.setFirst_name(firstname);
				user.setLast_name(lastname);
				user.setEmail(e_mail);
				user.setPassword(pwd);
				user.setRole(rol);

				s.save(user);
				out.println("<div class='text-center'><h1> User created Successfully !! </h1></div><br>");
				out.println("<br><a class='text-center' href='/login'>Home</a>");

				tx.commit();
				s.close();
			} else {
				out.println(
						"<div class='text-center'><h1>User with same email already exists<br><a href='/login'>Home</a></h1></div>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}