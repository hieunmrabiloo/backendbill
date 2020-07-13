package com.example.backendbill.service;

import com.example.backendbill.entity.Bill;
import com.example.backendbill.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BillServiceImp implements BillService {
    @Autowired
    private BillRepository billRepository;

    @Override
    public List<Bill> findByRoomId(Integer roomId) {
        return billRepository.findBillsByRoomId(roomId);
    }

    @Override
    public Bill save(Bill bill) {
        return billRepository.save(bill);
    }

    @Override
    public Optional<Bill> findById(Integer id) {
        return billRepository.findById(id);
    }

    @Override
    public void delete(Integer id) {
        billRepository.deleteById(id);
    }

    @Override
    public Bill findBillByMonthAndRoomId(Integer month, Integer roomId) {
        return billRepository.findBillByMonthBillAndRoomId(month,roomId);
    }
}
