package com.example.demo.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
public class Section {

	@XmlAttribute
	private String name;

	@XmlAttribute
	private String value;

	@XmlAttribute
	private String row;

	@XmlAttribute
	private String col;

	public Section() {
		super();
	}

	public Section(String name, String value, String row, String col) {
		super();
		this.name = name;
		this.value = value;
		this.row = row;
		this.col = col;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getRow() {
		return row;
	}

	public void setRow(String row) {
		this.row = row;
	}

	public String getCol() {
		return col;
	}

	public void setCol(String col) {
		this.col = col;
	}

}
