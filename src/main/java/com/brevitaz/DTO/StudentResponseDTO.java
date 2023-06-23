package com.brevitaz.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@AllArgsConstructor
public class StudentResponseDTO {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private long phone;
    private int age;
    private String gender;
}
