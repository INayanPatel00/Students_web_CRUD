package com.brevitaz.DTO;

import com.brevitaz.entity.Subject;
import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@ToString
@Getter
@Setter
@AllArgsConstructor
//@JsonFilter("StudentDetails")
public class StudentDTO {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private long phone;
    private int age;
    private String gender;
    private AddressDTO addressDTO;
    private DepartmentDTO departmentDTO;

    private Set<SubjectDTO> subjectDTOs;


    public StudentDTO(final String firstName, final String lastName, final String email, final long phone, final int age, final String gender, final AddressDTO addressDTO, final DepartmentDTO departmentDTO, final Set<SubjectDTO> subjectDTOs) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.age = age;
        this.gender = gender;
        this.addressDTO = addressDTO;
        this.departmentDTO = departmentDTO;
        this.subjectDTOs = subjectDTOs;
    }


    public StudentDTO() {
    }

    public StudentDTO(final int id, final String firstName, final String lastName, final String email, final long phone, final int age, final String gender) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.age = age;
        this.gender = gender;
    }


    public StudentDTO(int id, String firstName, String lastName, String email, long phone, int age, String gender, int houseNo, String landMark, String city, String state, int pinCode, String name, Set<Subject> subjects) {

    }
}
