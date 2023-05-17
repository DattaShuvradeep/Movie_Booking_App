package com.cts.movie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.movie.model.Movie;
import com.cts.movie.model.Ticket;
import com.cts.movie.service.MovieService;
import com.cts.movie.service.TicketService;

@RestController
@RequestMapping("/api/v1.0/Ticket")
public class TicketController {

	@Autowired
	private TicketService ticketService;
	
	@Autowired
	private MovieService movieService;
	
	@PostMapping("/add/{mid}")
	public ResponseEntity<?> buyTicket(@PathVariable ("mid") int movieId , @RequestBody Ticket ticket) {
		Movie m1= movieService.getMovieById(movieId);
		if(m1 !=null)
		{
			m1.setMovieName(ticket.getMovieName());
			ticket.setMovie_id_fk(ticket.getMovie_id_fk());
			
			ticket.setSeatsBooked(ticket.getSeatsBooked());
			ticket.setTheatreName(ticket.getTheatreName());
			ticket.setTotalSeatsAvailable(ticket.getTotalSeatsAvailable());
			if(ticketService.addTicket(ticket) && movieService.updateMovie(m1)) {
				return new ResponseEntity<Ticket>(ticket,HttpStatus.CREATED);
				
			}
		}
		return new ResponseEntity<String>("Ticket cannot be created", HttpStatus.INTERNAL_SERVER_ERROR);
		
		
		
		
		
	}
	
	@DeleteMapping("/delete/{mid}")
	public void cancelTicket(@PathVariable ("mid") int movieId) {
		ticketService.deleteTicket(movieId);
	}
	
}
