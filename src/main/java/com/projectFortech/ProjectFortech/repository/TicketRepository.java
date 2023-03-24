package com.projectFortech.ProjectFortech.repository;

import com.projectFortech.ProjectFortech.domain.Ticket;
import com.projectFortech.ProjectFortech.dto.TicketsUserDTO;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    @Query(value = "select count(*) from ticket  where user_id= :userId group by user_id", nativeQuery = true)
    public Optional<Integer> findHowManyPurchasedTicketsPerUser(Integer userId);

    @Query(value = "select program_id from ticket  where user_id= :userId group by program_id", nativeQuery = true)
    public List<Integer> getAllProgramsIdforUser(Integer userId);

    @Query(value = "select seat_number from ticket  where user_id= :userId and program_id= :programId group by seat_number", nativeQuery = true)
    public List<Integer> getAllSeatsForProgramAndUser(Integer userId, Integer programId);

    @Query(value = "select user_id from ticket group by user_id", nativeQuery = true)
    public List<Integer> getAllUserIdsThatBoughtTickets();

    @Query(value="select * from ticket where user_id= :userId", nativeQuery = true)
    public List<Ticket> getAllTicketsForUser(Integer userId);
}

