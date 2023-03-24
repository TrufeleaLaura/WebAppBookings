package com.projectFortech.ProjectFortech.repository;

import com.projectFortech.ProjectFortech.domain.Movie;
import com.projectFortech.ProjectFortech.domain.MoviesProgram;
import com.projectFortech.ProjectFortech.domain.Room;
import com.projectFortech.ProjectFortech.dto.FilterTimesDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface MoviesProgramRepository extends JpaRepository<MoviesProgram, Integer> {

    @Query(value="select start_time,end_time,date from program_movies_rooms where room_id=?1 and date=?2",nativeQuery = true)
    public List<FilterTimesDTO> getTimesByRoomId(int roomId, LocalDate date);

    @Query(value="select * from program_movies_rooms where movie_id=?1",nativeQuery = true)
    public List<MoviesProgram> getTimesByMovieId(int movieId);

    @Query(value="select * from program_movies_rooms where start_time =?1 and end_time=?2 and room_id=?3 and movie_id=?4 and date=?5",nativeQuery = true)
    public Optional<MoviesProgram> findByDetails(LocalTime startTime, LocalTime endTime, int roomId, int movieId, LocalDate date);

    public List<MoviesProgram> findByRoomAndDate(Room room, LocalDate date);

}
