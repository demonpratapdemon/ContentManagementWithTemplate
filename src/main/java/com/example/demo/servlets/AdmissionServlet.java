package com.example.demo.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.demo.controller.IndexController;
import com.example.demo.transform.Transform;

/**
 * Servlet implementation class AdmissionServlet
 */
@WebServlet(name = "AdmissionServlet", description = "Servlet for Template", urlPatterns = { "/AdmissionServlet" })
public class AdmissionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdmissionServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			String template = request.getParameter("template");
			System.out.println(template);
			String[] filePaths = template.split("\\\\");
			String path = Transform.transform(template, "/xslt/sample.xsl",
					filePaths[filePaths.length - 1].substring(0, filePaths[filePaths.length - 1].indexOf(".")));
			System.out.println(path);
			IndexController.setHtmlFilePath(path);
			response.sendRedirect("/redirect");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

		}
	}

}
