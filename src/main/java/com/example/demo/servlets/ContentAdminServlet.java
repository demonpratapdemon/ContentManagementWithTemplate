package com.example.demo.servlets;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import com.example.demo.controller.IndexController;
import com.example.demo.entities.Template;
import com.example.demo.transform.ConvertToJSP;

/**
 * Servlet implementation class AdmissionServlet
 * 
 *  @author PRATAP
 *  
 */
@WebServlet(name = "ContentAdminServlet", description = "Servlet for Content Admin", urlPatterns = {
		"/ContentAdminServlet" })
public class ContentAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ContentAdminServlet() {
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
			String xmlFile = request.getParameter("article");
			System.out.println(xmlFile);
			String[] filePaths = xmlFile.split("\\\\");
			String fileName = filePaths[filePaths.length - 1].substring(0,
					filePaths[filePaths.length - 1].indexOf("."));
			File file = new File(xmlFile);
			JAXBContext jaxbContext = JAXBContext.newInstance(Template.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

			Template template = (Template) jaxbUnmarshaller.unmarshal(file);
			System.out.println(template.getSections().size());
			fileName = ConvertToJSP.convertToJSP(template, fileName);
			System.out.println(fileName);
			IndexController.setHtmlFilePath(fileName);
			response.sendRedirect("/redirect");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

		}
	}

}
