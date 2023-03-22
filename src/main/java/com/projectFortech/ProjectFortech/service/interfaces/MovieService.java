package com.projectFortech.ProjectFortech.service.interfaces;

import com.projectFortech.ProjectFortech.domain.Movie;
import com.projectFortech.ProjectFortech.domain.User;

import java.util.List;

public interface MovieService {
    public Movie addMovie(Movie movie);
    public List<Movie> findAllMovies();
    public void deleteMovieById(Integer id);
    public Movie findMovieById(Integer id);
}
