package com.projectFortech.ProjectFortech.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
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
    private MoviesProgram program;

    public Ticket() {

    }

    public Ticket(int seatNumber, LocalDateTime buyDate, User user, MoviesProgram program) {
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

    public MoviesProgram getProgram() {
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

    public void setProgram(MoviesProgram program) {
        this.program = program;
    }
}
