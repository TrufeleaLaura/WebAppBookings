package com.projectFortech.ProjectFortech.repository;

import com.projectFortech.ProjectFortech.domain.ProgramMoviesRooms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgramRepository extends JpaRepository<ProgramMoviesRooms, Integer> {
}
