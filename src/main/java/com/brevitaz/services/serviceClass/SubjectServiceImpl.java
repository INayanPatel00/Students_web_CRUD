package com.brevitaz.services.serviceClass;

import com.brevitaz.DTO.StudentResponseDTO;
import com.brevitaz.DTO.SubjectDTO;
import com.brevitaz.DTO.SubjectResponseDTO;
import com.brevitaz.entity.Student;
import com.brevitaz.entity.Subject;
import com.brevitaz.repository.SubjectRepository;
import com.brevitaz.services.serviceInterface.SubjectService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class SubjectServiceImpl implements SubjectService{
    private final SubjectRepository subjectRepository;
    private static final Logger logger = LogManager.getLogger(SubjectService.class);

    public SubjectServiceImpl(final SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public Subject addSubject(Subject subject) {
        logger.info("Entered into addSubject method");
        logger.info("fetching Subject by SubjectName");
        Subject subject1 = subjectRepository.findByName(subject.getName());

        if (subject1 == null) {
            logger.info("Subject saved Successfully");
            return subjectRepository.save(subject);
        }
        logger.info("Subject already Exists");
        return subject1;

    }


    public List<SubjectResponseDTO> getAllSubject(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber ,pageSize);

        logger.info("Entered into getAllSubject method");

        List<SubjectResponseDTO> subjectResponseDTOS = new ArrayList<>();
        logger.info("fetching the list of Subject");


        Page<Subject>subjectPage = subjectRepository.findAll(pageable);
        List<Subject> subjects = subjectPage.getContent();

        for (Subject subject : subjects) {
            Set<StudentResponseDTO> studentResponseDTOS = new HashSet<>();
            for (Student student : subject.getStudent()) {
                studentResponseDTOS.add(new StudentResponseDTO(student.getId(),
                        student.getFirstName(),
                        student.getLastName(),
                        student.getEmail(),
                        student.getPhone(),
                        student.getAge(),
                        student.getGender()));
            }
            SubjectResponseDTO subjectResponseDTO = new SubjectResponseDTO(
                    subject.getId(),
                    subject.getName(),
                    studentResponseDTOS);

            subjectResponseDTOS.add(subjectResponseDTO);

        }
        logger.info("Subject Fetched Successfully");
        return subjectResponseDTOS;

    }

    public List<SubjectResponseDTO> getBySubjectName(final String name) {
        logger.info("Entered into getBySubjectName method");
        logger.info("Fetching Subject By SubjectName");
        Subject subjects = subjectRepository.findByName(name);
        if (subjects != null) {
            List<SubjectResponseDTO> subjectResponseDTOS = new ArrayList<>();


            Set<StudentResponseDTO> studentResponseDTOS = new HashSet<>();
            for (Student student : subjects.getStudent()) {
                studentResponseDTOS.add(new StudentResponseDTO(student.getId(),
                        student.getFirstName(),
                        student.getLastName(),
                        student.getEmail(),
                        student.getPhone(),
                        student.getAge(),
                        student.getGender()));
            }
            SubjectResponseDTO subjectResponseDTO = new SubjectResponseDTO(
                    subjects.getId(),
                    subjects.getName(),
                    studentResponseDTOS);

            subjectResponseDTOS.add(subjectResponseDTO);
            logger.info("Subject Fetched Successfully");
            return subjectResponseDTOS;
        }
        else {
            throw new DataIntegrityViolationException("Subject Not Found");
        }
    }

    public List<Subject> getAllSubjects(){

        List<Subject> subjects =subjectRepository.findAll();

        return subjects;

    }
}
