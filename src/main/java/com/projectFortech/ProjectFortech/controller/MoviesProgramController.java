package com.projectFortech.ProjectFortech.controller;

import com.projectFortech.ProjectFortech.domain.MoviesProgram;
import com.projectFortech.ProjectFortech.dto.FilterTimesDTO;
import com.projectFortech.ProjectFortech.dto.MovieProgramDTO;
import com.projectFortech.ProjectFortech.security.load.response.MessageResponse;
import com.projectFortech.ProjectFortech.service.implementation.MoviesProgramServiceImplem;
import com.projectFortech.ProjectFortech.service.implementation.RoomServiceImplem;
import com.projectFortech.ProjectFortech.service.interfaces.MoviesProgramService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/moviesProgram")
public class MoviesProgramController {
    private final MoviesProgramServiceImplem moviesProgramServiceImplem;
    private final RoomServiceImplem roomServiceImplem;

    public MoviesProgramController(MoviesProgramServiceImplem moviesProgramServiceImplem, RoomServiceImplem roomServiceImplem) {
        this.moviesProgramServiceImplem = moviesProgramServiceImplem;
        this.roomServiceImplem = roomServiceImplem;
    }
    @GetMapping("/all")
    public ResponseEntity<List<MoviesProgram>> getAllMoviesProgram() {
        List<MoviesProgram> movies = moviesProgramServiceImplem.findAllMoviesPrograms();
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteMovieProgram(@PathVariable Integer id) {
        moviesProgramServiceImplem.deleteMovieProgram(id);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/addMovieProgram")
    public ResponseEntity<MessageResponse> addMovieProgram(@RequestBody MovieProgramDTO moviesProgramDTO) {
        if(moviesProgramDTO.getStartTime().isBefore(LocalTime.of(10,0,0)) || moviesProgramDTO.getStartTime().isAfter(LocalTime.of(22,0,0))|| moviesProgramDTO.getEndTime().isBefore(LocalTime.of(10,0,0)) || moviesProgramDTO.getEndTime().isAfter(LocalTime.of(20,0,0)))
            return  ResponseEntity.badRequest().body(new MessageResponse("The movie must start between 10:00 and 22:00"));
        if (moviesProgramServiceImplem.doIntervalsCollide(moviesProgramDTO)) {
            return ResponseEntity.badRequest().body(new MessageResponse("The room is occupied in that interval of time"));
        }
        moviesProgramServiceImplem.addMovieProgram(moviesProgramDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/findTimes/{id}")
    public ResponseEntity<?> findTimesByMovieId(@PathVariable Integer id) {
        if(moviesProgramServiceImplem.getTimesByMovieId(id).isEmpty())
            return ResponseEntity.badRequest().body(new MessageResponse("No times found for this movie"));
        List<MovieProgramDTO> times = moviesProgramServiceImplem.getTimesByMovieId(id);
        return new ResponseEntity<>(times, HttpStatus.OK);
    }


}
