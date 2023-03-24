package com.projectFortech.ProjectFortech.service.implementation;

import com.projectFortech.ProjectFortech.domain.Room;
import com.projectFortech.ProjectFortech.repository.RoomRepository;
import com.projectFortech.ProjectFortech.service.interfaces.RoomService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class RoomServiceImplem implements RoomService {
    private final RoomRepository roomRepository;

    public RoomServiceImplem(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public Room findRoomById(Integer id) {
        return roomRepository.findById(id).get();
    }
    @Override
    public List<Room> findAllRooms() {
        return roomRepository.findAll();
    }
}
