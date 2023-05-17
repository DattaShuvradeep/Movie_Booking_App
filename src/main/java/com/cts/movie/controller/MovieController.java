package com.cts.movie.controller;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.movie.exceptions.ExistsMovieException;

import com.cts.movie.model.Movie;
import com.cts.movie.model.Ticket;
import com.cts.movie.response.ResponseHandler;
import com.cts.movie.service.MovieService;
import com.cts.movie.service.TicketService;

@RestController
@RequestMapping("/api/v1.0/moviebooking/")
public class MovieController {
	
	@Autowired
	public MovieService movieService;
	
	
	@Autowired
	public TicketService ticketService;
	
	
	
	@PostMapping("admin/addmovie")
	public ResponseEntity<?> addMovies(@RequestBody Movie movie) throws ExistsMovieException{
		
		if(movieService.addMovies(movie)!=null) {
			return new ResponseEntity<String>("Movie is added successfully",HttpStatus.OK);
			
		}
		return new ResponseEntity<String>("Movie is not found",HttpStatus.NOT_FOUND);
	}
	
	
	@GetMapping("movies/search/all")
	public ResponseEntity<?> viewAllMovies(){
		List<Movie> movieList=movieService.viewAllMovies();
		
		if(movieList!=null) {
			for(Movie m :movieList)
			{
				Set<Ticket> ticketList =ticketService.getAllTickets(m.getMovieId());
				m.setTicketList(ticketList);
				
			}
			return new ResponseEntity<List<Movie>>(movieList,HttpStatus.OK);
			
			
		}
		return new ResponseEntity<String> ("Movie List empty",HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("movies/search/movieById/{mid}")
	public ResponseEntity<?> getMovieById(@PathVariable ("mid") int movieId)
	{
		Movie movieexists= movieService.getMovieById(movieId);
		if(movieexists!=null) {
			Set<Ticket> ticketList =ticketService.getAllTickets(movieexists.getMovieId());
			movieexists.setTicketList(ticketList);
			
			CacheControl cacheObj =CacheControl.maxAge(30, TimeUnit.MINUTES);
			return ResponseEntity.ok().cacheControl(cacheObj)
					.body(ResponseHandler.generateResponse("Successfully showing custom msg",HttpStatus.OK,movieexists));
			
		}
		return new ResponseEntity<String>("movieList is empty",HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("admin/delete/{mid}")
	public ResponseEntity<?> deleteMovieById(@PathVariable("mid") int mid)
	{
		if(movieService.deleteMovie(mid) & ticketService.deleteTicket(mid)){
			return new ResponseEntity<String>("Movie and Ticket record deleted",HttpStatus.OK);
			
			
		}
		return new ResponseEntity<String>("Movie could't be deleted", HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	@PutMapping("admin/updateMovie")
	public ResponseEntity<?> updateMovie(@RequestBody Movie movie)
	{
		if(movieService.updateMovie(movie)) {
			return new ResponseEntity<Movie>(movie,HttpStatus.OK);
			
		}
		return new ResponseEntity<String> ("Movie Could't be updated",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
