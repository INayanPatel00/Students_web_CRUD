package com.brevitaz.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@ToString
@Table(name = "subject")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "subject_name")
    private String name;

    @ManyToMany(mappedBy = "subject", fetch = FetchType.LAZY)
    @JsonIgnore
    Set<Student> student;

    public Subject(final String name) {
        this.name = name;
    }

    public Subject(final int id, final String name) {
        this.id = id;
        this.name = name;
    }
}
