package com.brevitaz.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;
@ToString
@Getter
@Setter
@AllArgsConstructor
public class SubjectResponseDTO {
    private int id;
    private String name;
    Set<StudentResponseDTO>studentResponseDTOs;
}
