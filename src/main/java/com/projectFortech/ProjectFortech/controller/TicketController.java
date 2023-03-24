package com.projectFortech.ProjectFortech.controller;

import com.projectFortech.ProjectFortech.domain.Ticket;
import com.projectFortech.ProjectFortech.dto.ReservationDTO;

import com.projectFortech.ProjectFortech.dto.TicketDTO;
import com.projectFortech.ProjectFortech.dto.TicketsUserDTO;
import com.projectFortech.ProjectFortech.service.implementation.TicketServiceImplem;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/tickets")
public class TicketController {
    private final TicketServiceImplem ticketService;

    public TicketController(TicketServiceImplem ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/allTickets")
    public ResponseEntity<List<Ticket>> getAllTickets(){
        List<Ticket> tickets = ticketService.findAllTickets();
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }
    @GetMapping("/purchasedTicketsPerUser/{userId}")
    public ResponseEntity<Integer> getPurchasedNumberOfTicketsPerUser(@PathVariable Integer userId){
       if(ticketService.findHowManyPurchasedTicketsPerUser(userId).isEmpty()){
              return new ResponseEntity<>(0, HttpStatus.OK);
       };
        Integer tickets = ticketService.findHowManyPurchasedTicketsPerUser(userId).get();
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }

    @GetMapping("/allReservations")
    public ResponseEntity<List<ReservationDTO>> getAllReservations(){
        List<ReservationDTO> reservations = ticketService.getAllReservations();
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }
    @GetMapping("/allTicketsForUser/{userId}")
    public ResponseEntity<List<TicketsUserDTO>> getAllTicketsForUser(@PathVariable Integer userId){
        List<TicketsUserDTO> tickets = ticketService.getAllTicketsForUser(userId);
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }
    @PostMapping("/addTicket")
    public ResponseEntity<Void> addTicket(@RequestBody TicketDTO ticketDTO){
        ticketService.saveTicket(ticketDTO);
        return ResponseEntity.ok().build();
    }

}
