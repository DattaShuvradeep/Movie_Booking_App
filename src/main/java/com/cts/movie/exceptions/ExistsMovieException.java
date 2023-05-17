package com.cts.movie.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.CONFLICT, reason="Will Give Latter")

public class ExistsMovieException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
