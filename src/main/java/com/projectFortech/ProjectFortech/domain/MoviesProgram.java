package com.projectFortech.ProjectFortech.domain;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

@Entity
@Builder
@AllArgsConstructor
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
@Table(name="program_movies_rooms")
public class MoviesProgram {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="program_id")
    private Integer programId;

    @Column(name="start_time")
    private LocalTime startTime;

    @Column(name="end_time")
    private LocalTime endTime;

    @Column(name="date")
    private LocalDate date;
    @ManyToOne
    @JoinColumn(name="movieId")
    private Movie  movie;

    @ManyToOne
    @JoinColumn(name="roomId")
    private Room room;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private Map<Integer,Boolean> seats=new HashMap<>();


    public MoviesProgram() {

    }

    public Integer getProgramId() {
        return programId;
    }

    public void setProgramId(Integer programId) {
        this.programId = programId;
    }



    public MoviesProgram(LocalTime startTime, LocalTime endTime, LocalDate date, Movie movieId, Room room) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.date = date;
        this.movie = movieId;
        this.room = room;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Movie getMovieId() {
        return movie;
    }

    public void setMovieId(Movie movieId) {
        this.movie = movieId;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Map<Integer, Boolean> getSeats() {
        return seats;
    }

    public void setSeats(Map<Integer, Boolean> seats) {
        this.seats = seats;
    }

    @Override
    public String toString() {
        return "ProgramMoviesRooms{" +
                "program_id=" + programId +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", date=" + date +
                ", movie=" + movie +
                ", room=" + room +
                ", seats=" + seats +
                '}';
    }

    public void setOccupiedSeat(int seatNumber) {
        this.seats.put(seatNumber,true);//true means occupied
    }

}
