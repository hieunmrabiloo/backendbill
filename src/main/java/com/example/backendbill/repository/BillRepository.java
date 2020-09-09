package com.example.backendbill.repository;

import com.example.backendbill.entity.Bill;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BillRepository extends PagingAndSortingRepository<Bill, Integer> {
    List<Bill> findBillsByRoomId(Integer roomId);

    Optional<Bill> findById(Integer id);

    Bill findBillByMonthBillAndRoomId(Integer month ,Integer roomId);


}
