package com.fevo.assignment.cashing.impl;

import java.util.Optional;

import com.fevo.assignment.cashing.Fetchable;
import com.fevo.assignment.model.Response;

public class FileFetcher implements Fetchable<Response, String>{

	@Override
	public Optional<Response> fetch(String k) {
		// TODO 
		return Optional.of(null);
	}

}
