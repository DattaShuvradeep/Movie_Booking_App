package com.cts.movie.service;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.cts.movie.exceptions.ExistsMovieException;

import com.cts.movie.model.Movie;
@Service
public interface MovieService {
	public Movie addMovies(Movie movie) throws ExistsMovieException;
	public List<Movie>viewAllMovies();
	public boolean deleteMovie(int movieId) ;
	public Movie getMovieById(int movieId);
	public boolean updateMovie(Movie movie);
	
}
