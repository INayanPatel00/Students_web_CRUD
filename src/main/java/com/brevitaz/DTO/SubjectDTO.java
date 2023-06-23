package com.brevitaz.DTO;

import lombok.*;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class SubjectDTO {
    private int id;
    private String name;


    public SubjectDTO(final String name) {
        this.name = name;
    }
}
