package com.projectFortech.ProjectFortech.service.implementation;

import com.projectFortech.ProjectFortech.domain.Movie;
import com.projectFortech.ProjectFortech.domain.MoviesProgram;
import com.projectFortech.ProjectFortech.domain.Room;
import com.projectFortech.ProjectFortech.dto.FilterTimesDTO;
import com.projectFortech.ProjectFortech.dto.MovieProgramDTO;
import com.projectFortech.ProjectFortech.repository.MovieRepository;
import com.projectFortech.ProjectFortech.repository.MoviesProgramRepository;
import com.projectFortech.ProjectFortech.repository.RoomRepository;
import com.projectFortech.ProjectFortech.service.interfaces.MoviesProgramService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class MoviesProgramServiceImplem implements MoviesProgramService {

    private final MoviesProgramRepository moviesProgramRepository;
    private final RoomRepository roomRepository;
    private final MovieRepository movieRepository;

    @Autowired
    public MoviesProgramServiceImplem(MoviesProgramRepository moviesProgramRepository, RoomRepository roomRepository, MovieRepository movieRepository) {
        this.moviesProgramRepository = moviesProgramRepository;
        this.roomRepository = roomRepository;
        this.movieRepository = movieRepository;
    }

    @Override
    public List<MoviesProgram> findAllMoviesPrograms() {
        return moviesProgramRepository.findAll();
    }
    @Override
    public void deleteMovieProgram(Integer id) {
        moviesProgramRepository.deleteById(id);
    }
    @Override
    public MoviesProgram findMovieProgramById(Integer id) {
        return moviesProgramRepository.findById(id).get();
    }
    @Override
    public void addMovieProgram(MovieProgramDTO movieProgramDTO) {
        Optional<Movie> maybeMovie = movieRepository.findById(movieProgramDTO.getMovieId());
        Optional<Room> maybeRoom = roomRepository.findById(movieProgramDTO.getRoomId());
        if (moviesProgramRepository.findByDetails(movieProgramDTO.getStartTime(),
                movieProgramDTO.getEndTime(), movieProgramDTO.getRoomId(), movieProgramDTO.getMovieId(), movieProgramDTO.getDate()).isPresent()) {
            return;
        }
        if (maybeRoom.isPresent() && maybeMovie.isPresent()) {
            moviesProgramRepository.save(MoviesProgram.builder()
                    .movie(maybeMovie.get())
                    .room(maybeRoom.get())
                    .date(movieProgramDTO.getDate())
                    .startTime(movieProgramDTO.getStartTime())
                    .endTime(movieProgramDTO.getEndTime())
                    .seats(initializeSeats(movieProgramDTO))
                    .build());
        }
    }

    public boolean doIntervalsCollide(MovieProgramDTO movieProgramDTO) {
        Optional<Room> maybeRoom = roomRepository.findById(movieProgramDTO.getRoomId());

        return moviesProgramRepository.findByRoomAndDate(maybeRoom.get(), movieProgramDTO.getDate())
                .stream()
                .anyMatch(moviesProgram -> {
                    return (moviesProgram.getStartTime().isBefore(movieProgramDTO.getStartTime())
                            && moviesProgram.getEndTime().isAfter(movieProgramDTO.getStartTime()) ||
                            moviesProgram.getStartTime().isBefore(movieProgramDTO.getEndTime()) &&
                                    moviesProgram.getEndTime().isAfter(movieProgramDTO.getEndTime()));
                });
    }

    @Override
    public List<FilterTimesDTO> getTimesByRoomId(int roomId, LocalDate date) {
        return moviesProgramRepository.getTimesByRoomId(roomId,date);
    }
    @Override
    public List<MovieProgramDTO> getTimesByMovieId(int movieId) {
        return moviesProgramRepository.getTimesByMovieId(movieId).stream()
                .map(moviesProgram -> MovieProgramDTO.builder()
                        .programId(moviesProgram.getProgramId())
                        .startTime(moviesProgram.getStartTime())
                        .endTime(moviesProgram.getEndTime())
                        .date(moviesProgram.getDate())
                        .movieId(moviesProgram.getMovieId().getMovieId())
                        .roomId(moviesProgram.getRoom().getRoomId())
                        .seats(getAvailableSeatsFromMap(moviesProgram.getSeats()))
                        .build())
                .collect(Collectors.toList());
    }

    private List<Integer> getAvailableSeatsFromMap(Map<Integer, Boolean> seats) {
        return seats.entrySet().stream()
                .filter(entry -> !entry.getValue())
                .map(Map.Entry::getKey)
                .toList();
    }

    @Override
    public void occupySeat(int programId, int seatNumber) {
        Optional<MoviesProgram> maybeMoviesProgram = moviesProgramRepository.findById(programId);
        if (maybeMoviesProgram.isPresent()) {
            MoviesProgram moviesProgram = maybeMoviesProgram.get();
            moviesProgram.getSeats().put(seatNumber, true);
            moviesProgramRepository.save(moviesProgram);
        }
    }

    private Map<Integer, Boolean> initializeSeats(MovieProgramDTO movieProgramDTO) {
        Map<Integer, Boolean> seats = new HashMap<>();
        for (int i = 1; i <= roomRepository.findById(movieProgramDTO.getRoomId()).get().getPlaces(); i++) {
            seats.put(i, false);
        }
        return seats;
    }

}
