package com.projectFortech.ProjectFortech.domain;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Entity
@Table(name="program_movies_rooms")
public class ProgramMoviesRooms {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer program_id;

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

    @Transient
    private Map<Integer,Boolean> seats=new HashMap<>();


    public ProgramMoviesRooms() {

    }

    public Integer getProgram_id() {
        return program_id;
    }



    public ProgramMoviesRooms(LocalTime startTime, LocalTime endTime, LocalDate date, Movie movieId, Room room) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.date = date;
        this.movie = movieId;
        this.room = room;
        for(int i=1;i<= room.getPlaces();i++){
            seats.put(i,false);
        }
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

    @Override
    public String toString() {
        return "ProgramMoviesRooms{" +
                "program_id=" + program_id +
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
