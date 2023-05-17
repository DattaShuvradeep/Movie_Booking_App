package com.cts.movie.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.cts.movie.exceptions.ExistsMovieException;

import com.cts.movie.model.Movie;
import com.cts.movie.repository.MovieRepository;

@Service
public class MovieServiceImpl implements MovieService {
	
	@Autowired
	private MovieRepository movieRepo;
	
	
	@Autowired
	private KafkaTemplate<String, Movie> kafkaTemplate;

	@Override
	public Movie addMovies(Movie movie) throws ExistsMovieException {
		Optional<Movie> Movies=movieRepo.findById(movie.getMovieId());
		
		if(Movies.isPresent()) {
			throw new ExistsMovieException();
		}
		kafkaTemplate.send("movie-app", movie);
		return movieRepo.saveAndFlush(movie);
	}

	@Override
	public List<Movie> viewAllMovies() {
		List<Movie> movieList=movieRepo.findAll();
		if(movieList !=null && movieList.size()>0) {
			return movieList;
		}
		return null;
	}

	@Override
	public boolean deleteMovie(int movieId)  {
		try {
			movieRepo.deleteById(movieId);
			
			return true;
			
		} catch (Exception e) {
			
			e.printStackTrace();
			return false;
		}
		
		
	}

	@Override
	public Movie getMovieById(int movieId) {
		
		Optional<Movie> movie=movieRepo.findById(movieId);
		if(movie.isPresent())
		{
			return movie.get();
		}
		return null;
	}

	@Override
	public boolean updateMovie(Movie movie) {
		
		Movie movie1=movieRepo.getById(movie.getMovieId());
		if(movie1!=null)
		{
			movie1.setMovieName(movie.getMovieName());
			movieRepo.saveAndFlush(movie1);
			return true;
		}
		return false;
	}

	

}
