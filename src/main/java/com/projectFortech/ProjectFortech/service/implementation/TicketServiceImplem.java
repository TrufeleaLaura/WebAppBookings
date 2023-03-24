package com.projectFortech.ProjectFortech.service.implementation;

import com.projectFortech.ProjectFortech.domain.Ticket;
import com.projectFortech.ProjectFortech.dto.ReservationDTO;
import com.projectFortech.ProjectFortech.domain.Movie;
import com.projectFortech.ProjectFortech.domain.MoviesProgram;
import com.projectFortech.ProjectFortech.domain.User;
import com.projectFortech.ProjectFortech.dto.TicketDTO;
import com.projectFortech.ProjectFortech.dto.TicketsUserDTO;
import com.projectFortech.ProjectFortech.repository.MoviesProgramRepository;
import com.projectFortech.ProjectFortech.repository.TicketRepository;
import com.projectFortech.ProjectFortech.service.interfaces.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class TicketServiceImplem implements TicketService {

    private final UserServiceImplem userServiceImplem;

    private final MoviesProgramServiceImplem moviesProgramServiceImplem;

    private final MovieServiceImplem movieService;
    private final TicketRepository ticketRepository;
    @Autowired
    private MoviesProgramRepository moviesProgramRepository;

    public TicketServiceImplem(UserServiceImplem userServiceImplem, MoviesProgramServiceImplem moviesProgramServiceImplem, MovieServiceImplem movieService, TicketRepository ticketRepository) {
        this.userServiceImplem = userServiceImplem;
        this.moviesProgramServiceImplem = moviesProgramServiceImplem;
        this.movieService = movieService;
        this.ticketRepository = ticketRepository;
    }


    @Override
        public Optional<Integer> findHowManyPurchasedTicketsPerUser(Integer id) {
          return ticketRepository.findHowManyPurchasedTicketsPerUser(id);
        }

    @Override
    public List<Integer> getAllProgramsIdforUser(Integer userId) {
        return ticketRepository.getAllProgramsIdforUser(userId);
    }

    @Override
    public List<Integer> getAllSeatsForProgramAndUser(Integer userId, Integer programId) {
        return ticketRepository.getAllSeatsForProgramAndUser(userId, programId);
    }

    @Override
    public List<Integer> getAllUserIdsThatBoughtTickets() {
        return ticketRepository.getAllUserIdsThatBoughtTickets();
    }
    @Override
    public List<User> findUsersWithTickets(){
        List<Integer> usersIdWithTickets=getAllUserIdsThatBoughtTickets();
        List<User> usersWithTickets=new ArrayList<>();
        for (Integer id:usersIdWithTickets) {
            usersWithTickets.add(userServiceImplem.findUserById(id));
        }
        return usersWithTickets;
    }
    @Override
    public List<ReservationDTO> getAllReservations(){
        Integer id=0;;
        List<ReservationDTO> reservations = new ArrayList<>();
        List<User> usersWithTickets = findUsersWithTickets();
        for(User user: usersWithTickets){
            for(Integer programId:getAllProgramsIdforUser(user.getUserId())){
                MoviesProgram program = moviesProgramServiceImplem.findMovieProgramById(programId);
                Movie movie=movieService.findMovieById(program.getMovieId().getMovieId());
                List<Integer> seats=new ArrayList<>();
                for(Integer seat:getAllSeatsForProgramAndUser(user.getUserId(),programId)){
                    seats.add(seat);
                }
                reservations.add(new ReservationDTO(id,user.getEmail(),seats, movie.getName(),program.getDate(),program.getStartTime()));
                id++;
            }
        }
        return reservations;
    }
    @Override
    public List<TicketsUserDTO> getAllTicketsForUser(Integer userId){
        return ticketRepository.getAllTicketsForUser(userId).stream()
                .map(ticket -> TicketsUserDTO.builder()
                        .buyDate(ticket.getBuyDate())
                        .roomName(ticket.getProgram().getRoom().getName())
                        .movieName(ticket.getProgram().getMovieId().getName())
                        .date(ticket.getProgram().getDate())
                        .seat(ticket.getSeatNumber())
                        .startTime(ticket.getProgram().getStartTime())
                        .build())
                .collect(Collectors.toList());
    }
    @Override
    public void saveTicket(TicketDTO ticketDTO){
        Ticket save = ticketRepository.save(Ticket.builder()
                .ticketId(ticketDTO.getTicketId())
                .seatNumber(ticketDTO.getSeatNumber())
                .program(moviesProgramRepository.findById(ticketDTO.getProgramId()).get())
                .user(userServiceImplem.findUserById(ticketDTO.getUserId()))
                .buyDate(LocalDateTime.now())
                .build());
        moviesProgramServiceImplem.occupySeat(ticketDTO.getProgramId(),ticketDTO.getSeatNumber());
    }
    @Override
    public List<Ticket> findAllTickets(){
        return ticketRepository.findAll();
    }


}
