package com.example.backendbill.controller;

import com.example.backendbill.entity.Bill;
import com.example.backendbill.service.BillService;
import com.example.backendbill.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class BillController {

    @Autowired
    private BillService billService;


    @GetMapping("/bill/{roomId}")
    public List<Bill> getBills(@PathVariable("roomId") Integer roomId){
        return billService.findByRoomId(roomId);
    }

    @PostMapping("/bill")
    public Bill postBill(@RequestBody Bill bill) {
        System.out.println("Saving bill ...");
        Bill _bill = billService.save(new Bill(bill.getMonthBill(),
                bill.getRoomRates(),bill.getElecNum(),bill.getElecPrice(),
                bill.getWaterNum(),bill.getWaterPrice(),bill.getTotalPrice(),
                bill.getRoom()));
        return _bill;
    }

//    @GetMapping("/bill/month/{monthBill}")
//    public int getBillByMonth(@PathVariable("monthBill") Integer monthBill){
//        System.out.println("Getting bill...");
//        Bill bill = billService.findByMonth(monthBill);
//        if(bill == null) return 0;
//        return bill.getMonthBill();
//    }

    @GetMapping("/bill/month/{month}/{roomId}")
    public int getMonthByMonthAndRoomId(@PathVariable("month") Integer month, @PathVariable("roomId") Integer roomId){
        System.out.println("Getting bill... by id");
        Bill bill = billService.findBillByMonthAndRoomId(month,roomId);
        if(bill == null) return 0;
        return bill.getMonthBill();
    }

    @GetMapping("/bill/check/{month}/{roomId}")
    public boolean getCheckByMonthAndRoomId(@PathVariable("month") Integer month, @PathVariable("roomId") Integer roomId){
        System.out.println("Getting check...");
        Bill bill = billService.findBillByMonthAndRoomId(month,roomId);
        if(bill == null) return false;
        return true;
    }

    @GetMapping("/bill/id/{month}/{roomId}")
    public int getIdByMonthAndRoomId(@PathVariable("month") Integer month, @PathVariable("roomId") Integer roomId){
        System.out.println("Getting id...");
        Bill bill = billService.findBillByMonthAndRoomId(month,roomId);
        if(bill == null) return 0;
        return bill.getId();
    }

    @GetMapping("/bill/elec/{month}/{roomId}")
    public int getPreElecByMonthAndRoomId(@PathVariable("month") Integer month, @PathVariable("roomId") Integer roomId){
        System.out.println("Getting id...");
        Bill bill = billService.findBillByMonthAndRoomId(month,roomId);
        if(bill == null) return 0;
        return bill.getElecNum();
    }

    @GetMapping("/bill/water/{month}/{roomId}")
    public int getPreWaterByMonthAndRoomId(@PathVariable("month") Integer month, @PathVariable("roomId") Integer roomId){
        System.out.println("Getting id...");
        Bill bill = billService.findBillByMonthAndRoomId(month,roomId);
        if(bill == null) return 0;
        return bill.getWaterNum();
    }

    @PutMapping("/bill/update/{id}")
    public ResponseEntity<Bill> updateBill(@PathVariable("id") Integer id, @RequestBody Bill bill) {
        System.out.println("Update Bill with ID = " + id + "...");

        Optional<Bill> billData = billService.findById(id);

        if (billData.isPresent()) {
            Bill _bill = billData.get();
            _bill.setMonthBill(bill.getMonthBill());
            _bill.setRoomRates(bill.getRoomRates());
            _bill.setElecNum(bill.getElecNum());
            _bill.setElecPrice(bill.getElecPrice());
            _bill.setWaterNum(bill.getWaterNum());
            _bill.setWaterPrice(bill.getWaterPrice());
            _bill.setTotalPrice(bill.getTotalPrice());
            _bill.setRoom(bill.getRoom());
            return new ResponseEntity<>(billService.save(_bill), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/bill/delete/{id}")
    public ResponseEntity<String> deleteBill(@PathVariable("id") int id) {
        System.out.println("Delete Bill with id = " + id + "...");

        billService.delete(id);

        return new ResponseEntity<>("Bill has been deleted!", HttpStatus.OK);
    }
}
