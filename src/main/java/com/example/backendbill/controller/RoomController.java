package com.example.backendbill.controller;

import com.example.backendbill.entity.Room;
import com.example.backendbill.entity.User;
import com.example.backendbill.repository.UserRepository;
import com.example.backendbill.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class RoomController {
    @Autowired
    private RoomService roomService;

    @Autowired
    private UserRepository userRepository;

    @PreAuthorize("isAuthenticated()")

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

    @GetMapping("room/search/{name}")
    public List<Room> findByName(@PathVariable("name") String name) {
        List<Room> rooms = roomService.findByName(name);
        return rooms;
    }

    @GetMapping("room/{name}")
    public Room findRoomByName(@PathVariable("name") String name) {
        Room room = roomService.findRoomByName(name);
        return room;
    }

    @GetMapping("room/user/{id}")
    public String findRoomById(@PathVariable("id") int id){
        Room room = roomService.findRoomById(id);
        return room.getName();
    }

    @GetMapping(value = "/user/{username}")
    public int getRoomIdByUsername(@PathVariable(name = "username") String name){
        User user = userRepository.findUserByUsername(name);
        if(user == null) return 0;
        return user.getRoom().getId();
    }

    @GetMapping("room/get/{id}")
    public Room getRoomById(@PathVariable("id") int id){
        Room room = roomService.findRoomById(id);
        System.out.println("room...................");
        return room;
    }
}
