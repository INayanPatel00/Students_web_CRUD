package com.brevitaz.DTO;

import lombok.*;

import java.util.Set;
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class DepartmentResponseDTO {
    private int id;
    private String name;
    Set<StudentResponseDTO> studentResponseDTOS;
}
