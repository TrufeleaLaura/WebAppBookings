package com.projectFortech.ProjectFortech.repository;

import com.projectFortech.ProjectFortech.domain.Ticket;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {
}
