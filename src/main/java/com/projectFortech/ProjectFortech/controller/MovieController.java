package com.projectFortech.ProjectFortech.controller;

import com.projectFortech.ProjectFortech.domain.Movie;
import com.projectFortech.ProjectFortech.dto.MovieDTO;
import com.projectFortech.ProjectFortech.enums.Categories;
import com.projectFortech.ProjectFortech.enums.Types;
import com.projectFortech.ProjectFortech.service.interfaces.MovieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/movies")
@Slf4j
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }
    @GetMapping("/all")
    public ResponseEntity<List<MovieDTO>> getAllMovies(){
        List<MovieDTO> movies = movieService.findAllMovies().stream()
                .map(movie -> MovieDTO.builder()
                        .movieId(movie.getMovieId())
                        .name(movie.getName())
                        .type(movie.getType().getType())
                        .category(movie.getCategory().getCategory())
                        .imageUrl(movie.getImageUrl())
                        .build())
                .collect(Collectors.toList());
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }
    @PostMapping("/addMovie")
    public ResponseEntity<Void> addMovie(@RequestBody MovieDTO movie){
        log.info(movie.toString());
        Movie newMovie = Movie.builder()
                .name(movie.getName())
                .type(Types.valueOf(movie.getType()))
                .category(Categories.valueOf(movie.getCategory()))
                .imageUrl(movie.getImageUrl())
                .build();
        movieService.addMovie(newMovie);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/delete/{movieId}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Integer movieId){
        movieService.deleteMovieById(movieId);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/find/{movieId}")
    public ResponseEntity<Movie> findMovieById(@PathVariable Integer movieId){
        Movie movie = movieService.findMovieById(movieId);
        return new ResponseEntity<>(movie, HttpStatus.OK);
    }
}
