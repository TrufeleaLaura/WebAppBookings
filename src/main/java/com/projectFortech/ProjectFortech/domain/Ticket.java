package com.projectFortech.ProjectFortech.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer ticketId;

    @Column(name="seat_number")
    private int seatNumber;

    @Column(name="buy_date")
    private LocalDateTime buyDate;

    @ManyToOne
    @JoinColumn(name="userId")
    private User user;

    @ManyToOne
    @JoinColumn(name="programId")
    private ProgramMoviesRooms program;

    public Ticket() {

    }

    public Ticket(int seatNumber, LocalDateTime buyDate, User user, ProgramMoviesRooms program) {
        this.seatNumber = seatNumber;
        program.setOccupiedSeat(seatNumber);
        this.buyDate = buyDate;
        this.user = user;
        this.program = program;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public LocalDateTime getBuyDate() {
        return buyDate;
    }

    public User getUser() {
        return user;
    }

    public ProgramMoviesRooms getProgram() {
        return program;
    }

    public Integer getTicketId() {
        return ticketId;
    }



    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public void setBuyDate(LocalDateTime buyDate) {
        this.buyDate = buyDate;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setProgram(ProgramMoviesRooms program) {
        this.program = program;
    }
}
