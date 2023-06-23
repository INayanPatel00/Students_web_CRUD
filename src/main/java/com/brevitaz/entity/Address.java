package com.brevitaz.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@ToString
@Table (name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "house_no")
    private int houseNo;
    @Column(name = "land_mark")
    private String landMark;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "pin_code")
    private int pinCode;



    public Address(final int houseNo, final String landMark, final String city, final String state, final int pinCode) {
        this.houseNo = houseNo;
        this.landMark = landMark;
        this.city = city;
        this.state = state;
        this.pinCode = pinCode;
    }
}
