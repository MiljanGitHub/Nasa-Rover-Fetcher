package com.fevo.assignment.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Photo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Photo(String img_src) {
		super();
		this.img_src = img_src;
	}

	@JsonProperty(value = "img_src")
	private String img_src;

	public Photo() {}

	@JsonProperty(value = "img_src")
	public String getImg_src() {
		return img_src;
	}

	@JsonProperty(value = "img_src")
	public void setImg_src(String img_src) {
		this.img_src = img_src;
	}

	
}
