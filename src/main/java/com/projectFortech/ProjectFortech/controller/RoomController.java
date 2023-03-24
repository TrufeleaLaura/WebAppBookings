package com.projectFortech.ProjectFortech.controller;

import com.projectFortech.ProjectFortech.domain.Movie;
import com.projectFortech.ProjectFortech.domain.Room;
import com.projectFortech.ProjectFortech.service.implementation.RoomServiceImplem;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/rooms")
public class RoomController {
    private final RoomServiceImplem roomServiceImplem;

    public RoomController(RoomServiceImplem roomService) {
        this.roomServiceImplem = roomService;
    }

    @GetMapping("/findRoom/{id}")
    public ResponseEntity<Room> findRoomById(@PathVariable Integer id) {
        Room room = roomServiceImplem.findRoomById(id);
        return new ResponseEntity<>(room, HttpStatus.OK);
    }
    @GetMapping("/allRooms")
    public ResponseEntity<List<Room>> getAllRooms() {
        List<Room> rooms = roomServiceImplem.findAllRooms();
        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }

}
