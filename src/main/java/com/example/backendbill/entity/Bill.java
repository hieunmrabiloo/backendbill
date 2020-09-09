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

    @Column(name = "room_rates")
    private int roomRates;

    @Column(name = "elec_num")
    private int elecNum;

    @Column(name = "elec_price")
    private int elecPrice;

    @Column(name = "water_num")
    private int waterNum;

    @Column(name = "water_price")
    private int waterPrice;

    @Column(name = "other")
    private int other;

    @Column(name = "total_price")
    private int totalPrice;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    public Bill(int monthBill, int roomRates, int elecNum, int elecPrice, int waterNum, int waterPrice,int other, int totalPrice, Room room) {
        this.monthBill = monthBill;
        this.roomRates = roomRates;
        this.elecNum = elecNum;
        this.elecPrice = elecPrice;
        this.waterNum = waterNum;
        this.waterPrice = waterPrice;
        this.other = other;
        this.totalPrice = totalPrice;
        this.room = room;
    }
}
