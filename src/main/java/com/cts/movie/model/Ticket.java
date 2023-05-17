package com.cts.movie.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Ticket {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int transactionId;
	private int movie_id_fk;
	private String movieName;
	private String theatreName;
	private int totalSeatsAvailable;
	private int seatsBooked;
	
	public int getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	public int getMovie_id_fk() {
		return movie_id_fk;
	}
	public void setMovie_id_fk(int movie_id_fk) {
		this.movie_id_fk = movie_id_fk;
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
	public int getTotalSeatsAvailable() {
		return totalSeatsAvailable;
	}
	public void setTotalSeatsAvailable(int totalSeatsAvailable) {
		this.totalSeatsAvailable = totalSeatsAvailable;
	}
	public int getSeatsBooked() {
		return seatsBooked;
	}
	public void setSeatsBooked(int seatsBooked) {
		this.seatsBooked = seatsBooked;
	}
	public Ticket(int transactionId, int movie_id_fk, String movieName, String theatreName, int totalSeatsAvailable,
			int seatsBooked) {
		super();
		this.transactionId = transactionId;
		this.movie_id_fk = movie_id_fk;
		this.movieName = movieName;
		this.theatreName = theatreName;
		this.totalSeatsAvailable = totalSeatsAvailable;
		this.seatsBooked = seatsBooked;
	}
	public Ticket() {
		super();
	}
	@Override
	public String toString() {
		return "Ticket [transactionId=" + transactionId + ", movie_id_fk=" + movie_id_fk + ", movieName=" + movieName
				+ ", theatreName=" + theatreName + ", totalSeatsAvailable=" + totalSeatsAvailable + ", seatsBooked="
				+ seatsBooked + "]";
	}
	
	
}
