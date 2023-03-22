package com.projectFortech.ProjectFortech.service.implementation;
import com.projectFortech.ProjectFortech.domain.Movie;
import com.projectFortech.ProjectFortech.exceptions.UnfoundMovieException;
import com.projectFortech.ProjectFortech.service.interfaces.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.projectFortech.ProjectFortech.repository.MovieRepository;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class MovieServiceImplem implements MovieService{
    private final MovieRepository movieRepository;

    @Autowired
    public MovieServiceImplem(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public Movie addMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public List<Movie> findAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public void deleteMovieById(Integer id) {
        movieRepository.deleteById(id);

    }

    @Override
    public Movie findMovieById(Integer id) {
        return movieRepository.findById(id).orElseThrow(() -> new UnfoundMovieException("Movie by id " + id + " was not found"));
    }
}
