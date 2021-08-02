package com.fevo.assignment.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Response implements Serializable{
	
	public Response(List<Photo> photos) {
		super();
		this.photos = photos;
	}

	private static final long serialVersionUID = 1L;
	
	@JsonProperty(value = "photos")
	private List<Photo> photos;

	public Response() {}
	
	@JsonProperty(value = "photos")
	public List<Photo> getPhotos() {
		return photos;
	}
	@JsonProperty(value = "photos")
	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}
	
}
