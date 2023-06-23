package com.brevitaz.services.serviceInterface;

import com.brevitaz.DTO.SubjectDTO;
import com.brevitaz.DTO.SubjectResponseDTO;
import com.brevitaz.entity.Subject;

import java.util.List;

public interface SubjectService {
    Subject addSubject(Subject subject);
    List<SubjectResponseDTO> getAllSubject(Integer pageNumber ,Integer pageSize);
    List<SubjectResponseDTO> getBySubjectName(final String name);
    List<Subject> getAllSubjects();

}
