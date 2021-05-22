/**
 * 
 */
package com.example.demo.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entities.Request;

/**
 * @author PRATAP
 *
 */
@Controller
public class IndexController {

	static String htmlFilePath = "";

	@RequestMapping("/")
	public String home(Map<String, Object> model) {
		return "index";
	}

	@RequestMapping("/login")
	public String login(Map<String, Object> model) {
		return "Login";
	}

	@RequestMapping("/register")
	public String registerPage(Map<String, Object> model) {
		return "Registration";
	}

	@RequestMapping("/registration")
	public String registration(HttpServletRequest request, HttpServletResponse response) {
		return "Registration";
	}

	@RequestMapping(value = "/contentAdmin")
	public String contentAdmin(@RequestParam(name = "id", required = false) String id) {
		System.out.println(id);
		return "ContentAdmin";
	}

	@RequestMapping(value = "/contentAuthor")
	public String contentAuthor(@RequestParam(name = "id", required = false) String id) {
		System.out.println(id);
		return "ContentAuthor";
	}

	@RequestMapping(value = "/templateAuthor")
	public String templateAuthor(@RequestParam(name = "id", required = false) String id) {
		System.out.println(id);
		return "TemplateAuthor";
	}

	@RequestMapping(value = "/templateAuthorXml", method = RequestMethod.POST)
	public void saveTAXml(@RequestBody Request request) throws FileNotFoundException {
		System.out.println(request.getBody());
		String folderPath = System.getenv("INPUT") + File.separator + "ContentManagement" + File.separator + "templates"
				+ File.separator + request.getFilename().substring(0, request.getFilename().indexOf("."));
		File folder = new File(folderPath);
		if (!folder.exists()) {
			folder.mkdirs();
		}
		String filePath = folder + File.separator + request.getFilename();
		try (PrintStream out = new PrintStream(new FileOutputStream(filePath))) {
			out.print(request.getBody());
		}
	}

	@RequestMapping(value = "/redirect", method = RequestMethod.GET)
	public String redirect() {
		System.out.println("redirect = " + htmlFilePath);
		String[] filePaths = htmlFilePath.split("\\\\");
		System.out.println(filePaths[filePaths.length - 1].substring(0, filePaths[filePaths.length - 1].indexOf(".")));
		return filePaths[filePaths.length - 1].substring(0, filePaths[filePaths.length - 1].indexOf("."));
	}

	@RequestMapping(value = "/contentAuthorXml", method = RequestMethod.POST)
	public void saveCAXml(@RequestBody Request request) throws FileNotFoundException {
		System.out.println(request.getBody());
		String[] filePaths = htmlFilePath.split("\\\\");
		String folderPath = System.getenv("INPUT") + File.separator + "ContentManagement" + File.separator + "articles";
		File folder = new File(folderPath);
		if (!folder.exists()) {
			folder.mkdirs();
		}
		String filePath = folder + File.separator
				+ filePaths[filePaths.length - 1].substring(0, filePaths[filePaths.length - 1].indexOf("_")) + ".xml";
		try (PrintStream out = new PrintStream(new FileOutputStream(filePath))) {
			out.print(request.getBody());
		}
	}

	@RequestMapping(value = { "/testThymeleaf" }, method = RequestMethod.GET)
	public String testThymeleafView() {
		return "th_page1";
	}

	@RequestMapping(value = { "/testRead" }, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public URI testFile() throws FileNotFoundException, URISyntaxException {
//		ClassLoader classLoader = getClass().getClassLoader();
		URL xsltURL = ResourceLoader.class.getResource("/templates/tempi.xml");
		System.out.println(xsltURL.toURI());
		return xsltURL.toURI();
//        return "th_page1";
	}

	public static String getHtmlFilePath() {
		return htmlFilePath;
	}

	public static void setHtmlFilePath(String htmlFilePath) {
		IndexController.htmlFilePath = htmlFilePath;
	}

}
