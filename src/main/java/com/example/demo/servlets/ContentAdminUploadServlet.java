package com.example.demo.servlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

import com.example.demo.controller.IndexController;

/**
 * Servlet implementation class ContenAdminServlet
 */
@WebServlet(name = "ContentAdminUploadServlet", description = "Servlet forContent Admin Upload", urlPatterns = {
		"/ContentAdminUploadServlet" })
class ContentAdminUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletFileUpload uploader = null;

	@Override
	public void init() throws ServletException {
		DiskFileItemFactory fileFactory = new DiskFileItemFactory();
		File filesDir = (File) getServletContext().getAttribute("FILES_DIR_FILE");
		fileFactory.setRepository(filesDir);
		this.uploader = new ServletFileUpload(fileFactory);
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ContentAdminUploadServlet() {
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
		/*
		 * String file = (request.getParameter("attachment")); System.out.println(file);
		 */
		if (!ServletFileUpload.isMultipartContent(request)) {
			throw new ServletException("Content type is not multipart/form-data");
		}
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.write("<html><head></head><body>");
		String fileName = "";
		try {
			List<FileItem> fileItemsList = uploader.parseRequest(new ServletRequestContext(request));
			Iterator<FileItem> fileItemsIterator = fileItemsList.iterator();

			while (fileItemsIterator.hasNext()) {
				FileItem fileItem = fileItemsIterator.next();
				System.out.println("FieldName=" + fileItem.getFieldName());
				System.out.println("FileName=" + fileItem.getName());
				System.out.println("ContentType=" + fileItem.getContentType());
				System.out.println("Size in bytes=" + fileItem.getSize());
				File file = new File(
						"E:\\New_Workspace\\ContentManagementWithTemplate\\src\\main\\webapp\\WEB-INF\\view\\"
								+ fileItem.getName());
				fileName = fileItem.getName();
				fileItem.write(file);
			}
		} catch (FileUploadException e) {
			out.write("Exception in uploading file.");
		} catch (Exception e) {
			out.write("Exception in uploading file.");
		}
		System.out.println("File saved successfully");
		IndexController.setHtmlFilePath(
				"E:\\New_Workspace\\ContentManagementWithTemplate\\src\\main\\webapp\\WEB-INF\\view\\" + fileName);
		response.sendRedirect("/redirect");
	}

}
