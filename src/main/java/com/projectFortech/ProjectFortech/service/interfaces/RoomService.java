package com.projectFortech.ProjectFortech.service.interfaces;

import com.projectFortech.ProjectFortech.domain.Room;

import java.util.List;

public interface RoomService {
    public Room findRoomById(Integer id);

    List<Room> findAllRooms();
}
