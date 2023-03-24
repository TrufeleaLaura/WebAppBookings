package com.projectFortech.ProjectFortech.service.interfaces;

import com.projectFortech.ProjectFortech.domain.Ticket;
import com.projectFortech.ProjectFortech.dto.ReservationDTO;
import com.projectFortech.ProjectFortech.domain.User;
import com.projectFortech.ProjectFortech.dto.TicketDTO;
import com.projectFortech.ProjectFortech.dto.TicketsUserDTO;

import java.util.List;
import java.util.Optional;

public interface TicketService {
    public Optional<Integer> findHowManyPurchasedTicketsPerUser(Integer id);

    public List<Integer> getAllProgramsIdforUser(Integer userId);

    public List<Integer> getAllSeatsForProgramAndUser(Integer userId, Integer programId);

    public List<Integer> getAllUserIdsThatBoughtTickets();
    public List<User> findUsersWithTickets();
    public List<ReservationDTO> getAllReservations();
    public List<TicketsUserDTO> getAllTicketsForUser(Integer userId);
    public void saveTicket(TicketDTO ticketDTO);
    public List<Ticket> findAllTickets();
}
