package com.projectFortech.ProjectFortech.repository;

import com.projectFortech.ProjectFortech.domain.User;
import com.projectFortech.ProjectFortech.exceptions.UnfoundUserException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email) throws UnfoundUserException;

    boolean existsByEmail(String email);
}
