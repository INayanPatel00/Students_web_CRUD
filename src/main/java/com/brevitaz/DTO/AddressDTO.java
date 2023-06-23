package com.brevitaz.DTO;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AddressDTO {

    private int id;
    private int houseNo;
    private String landMark;
    private String city;
    private String state;
    private int pinCode;
}
