package com.example.backendbill.service;

import com.example.backendbill.entity.Room;
import com.example.backendbill.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImp implements RoomService{

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public Iterable<Room> findAll() {
        return roomRepository.findAll();
    }

    @Override
    public Room save(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public void delete(Integer id) {
        roomRepository.deleteById(id);
    }

    @Override
    public List<Room> findByName(String name) {
        return roomRepository.findByNameContaining(name);
    }

    @Override
    public Room findRoomByName(String name) {
        return roomRepository.findRoomByName(name);
    }

    @Override
    public Room findRoomById(Integer id) {
        return roomRepository.findRoomById(id);
    }

}
