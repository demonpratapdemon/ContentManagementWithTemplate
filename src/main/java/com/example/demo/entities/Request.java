/**
 * 
 */
package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author PRATAP
 *
 */
public class Request {

	String body;
	String filename;

	
	public Request() {
		super();
	}

	@JsonCreator()
	public Request(@JsonProperty("body") String body, @JsonProperty("filename")String filename) {
		super();
		this.body = body;
		this.filename = filename;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

}
