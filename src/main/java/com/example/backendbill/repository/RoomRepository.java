package com.example.backendbill.repository;

import com.example.backendbill.entity.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends CrudRepository<Room, Integer> {
    List<Room> findByNameContaining(String name);

    Room findRoomById(Integer id);
}
