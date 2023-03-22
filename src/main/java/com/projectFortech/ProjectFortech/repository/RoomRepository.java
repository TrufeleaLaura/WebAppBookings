package com.projectFortech.ProjectFortech.repository;

import com.projectFortech.ProjectFortech.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {
}
