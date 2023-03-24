package com.projectFortech.ProjectFortech.service.interfaces;

import com.projectFortech.ProjectFortech.domain.MoviesProgram;
import com.projectFortech.ProjectFortech.domain.Ticket;
import com.projectFortech.ProjectFortech.dto.FilterTimesDTO;
import com.projectFortech.ProjectFortech.dto.MovieProgramDTO;

import java.time.LocalDate;
import java.util.List;

public interface MoviesProgramService {
    public List<MoviesProgram> findAllMoviesPrograms();

    void addMovieProgram(MovieProgramDTO movieProgram);

    void deleteMovieProgram(Integer id);
    public MoviesProgram findMovieProgramById(Integer id);

    public List<FilterTimesDTO> getTimesByRoomId(int roomId, LocalDate date);

    public List<MovieProgramDTO> getTimesByMovieId(int movieId);

    void occupySeat(int programId, int seatNumber);
}
