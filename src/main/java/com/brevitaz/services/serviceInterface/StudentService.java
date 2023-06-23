package com.brevitaz.services.serviceInterface;

import com.brevitaz.DTO.StudentDTO;
import com.brevitaz.entity.Student;
import org.springframework.http.converter.json.MappingJacksonValue;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface StudentService {
     void insertStudent(StudentDTO studentDTO);
     List<StudentDTO> getAll(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);
     StudentDTO getbyid(Integer id);
    boolean updateDetails(StudentDTO studentDTO, final int id);
    void deleteStudent(int id);

     List<StudentDTO> searchStudents(String firstName,String lastName,String department,String subjectName);


    MappingJacksonValue searchStudentsByKeyword(String keyword, Set<String> fields);
}
