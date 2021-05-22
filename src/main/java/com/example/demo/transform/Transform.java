/**
 * 
 */
package com.example.demo.transform;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 * @author PRATAP
 *
 */
@Component
public class Transform {

	public static String transform(final String xml, final String xslt, String filename) throws SAXException,
			IOException, ParserConfigurationException, TransformerFactoryConfigurationError, TransformerException {

		String data = "";
		data = new String(Files.readAllBytes(Paths.get(xml)));
		InputStream targetStream = new ByteArrayInputStream(data.getBytes());
		System.out.println(targetStream + "-----");
		URL xsltURL = ResourceLoader.class.getResource(xslt);

		Document xmlDocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(targetStream);
		Source stylesource = new StreamSource(xsltURL.openStream(), xsltURL.toExternalForm());
		Transformer transformer = TransformerFactory.newInstance().newTransformer(stylesource);

		StringWriter stringWriter = new StringWriter();
		transformer.transform(new DOMSource(xmlDocument), new StreamResult(stringWriter));

		// write to file

//		String path = System.getenv("INPUT") + File.separator + "ContentManagement" + File.separator + "templates"
//				+ File.separator + filename + File.separator + filename + "_WoCSS.jsp";
		String path = "E:\\New_Workspace\\ContentManagementWithTemplate\\src\\main\\webapp\\WEB-INF\\view\\" + filename
				+ "_WoCSS.jsp";

		File file = new File(path);
		if (!file.exists()) {
			file.createNewFile();
		}

		FileWriter fw = new FileWriter(file);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(stringWriter.toString());
		bw.close();
		return file.getAbsolutePath();
	}

}
