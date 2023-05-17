package com.cts.movie.service;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.cts.movie.model.Ticket;
@Service
public interface TicketService {
	
	public Set<Ticket> getAllTickets(int mid);
	
	public boolean addTicket(Ticket ticket);
	public boolean deleteTicket(int mid);
	

}
