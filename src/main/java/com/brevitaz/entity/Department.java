package com.brevitaz.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@ToString
@Table(name = "department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "department_name")
    private String name;
    @OneToMany(mappedBy = "department")
    Set<Student>student;

    public Department(final String name) {
        this.name = name;
    }
}
