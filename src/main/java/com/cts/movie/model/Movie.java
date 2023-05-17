package com.cts.movie.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Movie {
	@Id
	
	private int movieId;
	private String movieName;
	private int totalSeatsAvailable;
	private String theatreName;

	@OneToMany(targetEntity = Ticket.class)
	private Set<Ticket> ticketList;

	

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	
	public String getTheatreName() {
		return theatreName;
	}

	public void setTheatreName(String theatreName) {
		this.theatreName = theatreName;
	}

	public Set<Ticket> getTicketList() {
		return ticketList;
	}

	public void setTicketList(Set<Ticket> ticketList) {
		this.ticketList = ticketList;
	}

	

	public Movie() {

	}

	public int getTotalSeatsAvailable() {
		return totalSeatsAvailable;
	}

	public void setTotalSeatsAvailable(int totalSeatsAvailable) {
		this.totalSeatsAvailable = totalSeatsAvailable;
	}

}
