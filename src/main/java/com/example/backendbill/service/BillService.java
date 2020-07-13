package com.example.backendbill.service;

import com.example.backendbill.entity.Bill;

import java.util.List;
import java.util.Optional;

public interface BillService {
    List<Bill> findByRoomId(Integer roomId);

    Bill save(Bill bill);

    Optional<Bill> findById(Integer id);

    void delete(Integer id);

    Bill findBillByMonthAndRoomId(Integer month, Integer roomId);
}
