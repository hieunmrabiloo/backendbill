package com.example.backendbill.controller;

import com.example.backendbill.entity.Room;
import com.example.backendbill.repository.BillRepository;
import com.example.backendbill.repository.RoomRepository;
import com.example.backendbill.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class RoomController {
    @Autowired
    private RoomService roomService;

    @GetMapping("/rooms")
    public List<Room> getAllRooms() {
        System.out.println("Get all Rooms...");

        List<Room> rooms = new ArrayList<>();
        roomService.findAll().forEach(rooms::add);

        return rooms;
    }

    @PostMapping("/room")
    public Room postRoom(@RequestBody Room room) {
        System.out.println("Saving room ...");
        Room _room = roomService.save(new Room(room.getName()));
        return _room;
    }

    @DeleteMapping("/room/{id}")
    public ResponseEntity<String> deleteRoom(@PathVariable("id") int id) {
        System.out.println("Delete Room with ID = " + id + "...");

        roomService.delete(id);

        return new ResponseEntity<>("Room has been deleted!", HttpStatus.OK);
    }

    @GetMapping("room/search/{name}")
    public List<Room> findByName(@PathVariable("name") String name) {
        List<Room> rooms = roomService.findByName(name);
        return rooms;
    }

}
