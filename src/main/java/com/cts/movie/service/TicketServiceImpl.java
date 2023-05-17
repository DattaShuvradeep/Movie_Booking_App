package com.cts.movie.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.movie.model.Ticket;
import com.cts.movie.repository.TicketRepository;
@Service
public class TicketServiceImpl  implements TicketService{

	@Autowired
	private TicketRepository ticketRepository ;
	
	@Override
	public Set<Ticket> getAllTickets(int mid) {
		Set<Ticket> ticketlist=ticketRepository.getTickeList(mid);
		return ticketlist;
	}

	@Override
	public boolean addTicket(Ticket ticket) {
		Ticket ticketObj= new Ticket();
		ticketObj.setMovieName(ticket.getMovieName());
		ticketObj.setMovie_id_fk(ticket.getMovie_id_fk());
		ticketObj.setTheatreName(ticket.getTheatreName());
		ticketObj.setSeatsBooked(ticket.getSeatsBooked());
		ticketObj.setTotalSeatsAvailable(ticket.getTotalSeatsAvailable());
		ticketRepository.saveAndFlush(ticket);
		
		return true;
	}

	@Override
	public boolean deleteTicket(int mid) {
		ticketRepository.deleteTicketData(mid);
		
		return true;
	}

}
