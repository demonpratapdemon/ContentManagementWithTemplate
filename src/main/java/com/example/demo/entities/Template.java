/**
 * 
 */
package com.example.demo.entities;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author PRATAP
 *
 */
@XmlRootElement(name = "template")
@XmlAccessorType(XmlAccessType.FIELD)
public class Template {

	@XmlElement
	private List<Section> section;

	public Template() {
		super();
	}

	public Template(List<Section> sections) {
		super();

		this.section = sections;
	}

	public List<Section> getSections() {
		return section;
	}

	public void setSections(List<Section> sections) {
		this.section = sections;
	}

}
