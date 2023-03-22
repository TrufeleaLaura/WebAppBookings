package com.projectFortech.ProjectFortech.repository;

import com.projectFortech.ProjectFortech.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

}
