package com.example.backendbill.service;

import com.example.backendbill.entity.Room;

import java.util.List;

public interface RoomService {

    Iterable<Room> findAll();

    Room save (Room room);

    void delete(Integer id);

    List<Room> findByName(String name);

    Room findRoomByName(String name);
}
