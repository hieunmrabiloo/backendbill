package com.example.backendbill.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Bill")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "month")
    private int monthBill;

    @Column(name = "roomRates")
    private int roomRates;

    @Column(name = "elecNum")
    private int elecNum;

    @Column(name = "elecPrice")
    private int elecPrice;

    @Column(name = "waterNum")
    private int waterNum;

    @Column(name = "waterPrice")
    private int waterPrice;

    @Column(name = "totalPrice")
    private int totalPrice;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    public Bill(int monthBill, int roomRates, int elecNum, int elecPrice, int waterNum, int waterPrice, int totalPrice, Room room) {
        this.monthBill = monthBill;
        this.roomRates = roomRates;
        this.elecNum = elecNum;
        this.elecPrice = elecPrice;
        this.waterNum = waterNum;
        this.waterPrice = waterPrice;
        this.totalPrice = totalPrice;
        this.room = room;
    }
}
