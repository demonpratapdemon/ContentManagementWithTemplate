/**
 * 
 */
package com.example.demo.transform;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.example.demo.entities.Section;
import com.example.demo.entities.Template;

/**
 * @author PRATAP
 *
 */
public class ConvertToJSP {

	public static String convertToJSP(Template template, String fileName) {
		// TODO Auto-generated method stub
		List<Section> sections = template.getSections();
		Collections.sort(sections, new DefComparator());
		StringBuilder sb = new StringBuilder("");
		sb.append(
				"<html><head><meta charset=\"UTF-8\"><link href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css\" rel=\"stylesheet\" /></head><body>");
		int prevRow = -1;
		int currentRow = -1;
		String col = "";
		for (int i = 0; i < sections.size(); i++) {
			currentRow = Integer.parseInt(sections.get(i).getRow());
			if (currentRow == prevRow) {
				col = "col-md-" + sections.get(i).getCol();
				sb.append("<div class='" + col + "'  style='border:solid'>").append(sections.get(i).getName())
						.append(":").append(sections.get(i).getValue().replaceAll("&lt;", "<").replaceAll("&gt;", ">"))
						.append("</div>");
			} else {
				if (sb.lastIndexOf("<div class='row'>") > 0 && !sb
						.subSequence(sb.lastIndexOf("<div class='row'>"), sb.length()).equals("<div class='row'>")) {
					sb.append("</div>");
				}
				sb.append("<div class='row'>");
				col = "col-md-" + sections.get(i).getCol();
				sb.append("<div class='" + col + "'  style='border:solid'>").append(sections.get(i).getName())
						.append(":").append(sections.get(i).getValue().replaceAll("&lt;", "<").replaceAll("&gt;", ">"))
						.append("</div>");
			}
			prevRow = currentRow;
		}

		fileName += "_CAd.jsp";
		String download = "<a href=\"\" download=\"" + fileName + "\"><button type=\"button\">Download</button></a>";
		sb.append("</div><br><br>" + download + "</body></html>");
		System.out.println(sb.toString());
		sb = new StringBuilder(sb.toString().replaceAll("<span class=\"marker\">",
				"<span class=\"marker\" style=\"background-color:yellow\">"));
		try {
			File file = new File(
					"E:\\New_Workspace\\ContentManagementWithTemplate\\src\\main\\webapp\\WEB-INF\\view\\" + fileName);
			if (file.exists()) {
				file.delete();
				System.out.println("Deleted Successfully");
			}
			FileWriter myWriter = new FileWriter(
					"E:\\New_Workspace\\ContentManagementWithTemplate\\src\\main\\webapp\\WEB-INF\\view\\" + fileName);
			myWriter.write(sb.toString());
			myWriter.close();
			System.out.println("Successfully wrote to the file.");
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		return fileName;
	}

}

class DefComparator implements Comparator<Section> {

	@Override
	public int compare(Section s1, Section s2) {
		// TODO Auto-generated method stub
		if (Integer.parseInt(s1.getRow()) < Integer.parseInt(s2.getRow()))
			return -1;
		else
			return 1;
	}

}
