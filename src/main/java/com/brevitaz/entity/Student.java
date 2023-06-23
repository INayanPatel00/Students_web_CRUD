package com.brevitaz.entity;

import com.fasterxml.jackson.annotation.JsonFilter;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private long phone;

    @Column(name = "age")
    private int age;

    @Column(name = "gender")
    private String gender;

    @OneToOne
    @JoinColumn(name = "addressId", referencedColumnName = "id")
    private Address address;

    @OneToOne
    private Department department;

    @ManyToMany( fetch = FetchType.EAGER)
    @JoinTable(
            name = "student_subject_mapping",
            joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id", referencedColumnName = "id")
    )
    private Set<Subject> subject;


    public Student(final String firstName, final String lastName, final String email, final long phone, final int age, final String gender, final Address address, final Department department, final Set<Subject> subject) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.department= department;
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone=" + phone +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                '}';
    }
}
