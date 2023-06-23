package com.brevitaz.services.serviceClass;

import com.brevitaz.DTO.AddressDTO;
import com.brevitaz.DTO.DepartmentDTO;
import com.brevitaz.DTO.StudentDTO;
import com.brevitaz.DTO.SubjectDTO;
import com.brevitaz.entity.Address;
import com.brevitaz.entity.Department;
import com.brevitaz.entity.Student;
import com.brevitaz.entity.Subject;
import com.brevitaz.exception.StudentNotFoundException;
import com.brevitaz.repository.StudentRepository;
import com.brevitaz.services.GetSearchSpecification;
import com.brevitaz.services.serviceInterface.StudentService;
import com.brevitaz.services.serviceInterface.SubjectService;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final AddressServiceImpl addressService;
    private final DepartmentServiceImpl departmentService;
    private final SubjectService subjectService;

    private static final Logger logger = LogManager.getLogger(StudentServiceImpl.class);

    @Autowired
    public StudentServiceImpl(final StudentRepository studentRepository, final AddressServiceImpl addressService, final DepartmentServiceImpl departmentService, final SubjectService subjectService) {
        this.studentRepository = studentRepository;
        this.addressService = addressService;
        this.departmentService = departmentService;
        this.subjectService = subjectService;
    }

    public void insertStudent(StudentDTO studentDTO) {
        logger.info("Entered into insertStudent method");
        Student studentDetails = studentRepository.getByFirstNameAndEmail(studentDTO.getFirstName(), studentDTO.getEmail());
        if (studentDetails == null) {

            Address address = new Address(
                    studentDTO.getAddressDTO().getHouseNo(),
                    studentDTO.getAddressDTO().getLandMark(),
                    studentDTO.getAddressDTO().getCity(),
                    studentDTO.getAddressDTO().getState(),
                    studentDTO.getAddressDTO().getPinCode()
            );
            Address address1 = addressService.addAddress(address);

            Department department = new Department(studentDTO.getDepartmentDTO().getName());
            Department department1 = departmentService.addDepartment(department);

            Set<Subject> subjects = new HashSet<>();
            for (SubjectDTO s : studentDTO.getSubjectDTOs()) {
                Subject subject = new Subject(s.getName());
                subjects.add(subject);
            }
            Set<Subject> subjectSet = new HashSet<>();

            for (Subject s : subjects) {
                Subject subject = subjectService.addSubject(s);
                subjectSet.add(subject);
            }

            Student student = new Student(
                    studentDTO.getFirstName(),
                    studentDTO.getLastName(),
                    studentDTO.getEmail(),
                    studentDTO.getPhone(),
                    studentDTO.getAge(),
                    studentDTO.getGender(), address1, department1, subjectSet);

            studentRepository.save(student);
            logger.info("Student Details Inserted Successfully");

        } else {
            throw new DataIntegrityViolationException("Student Already Exists ");
        }

    }



    public List<StudentDTO> getAll(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
        Sort sort = null;
        if (sortDir.equals("dsc")) {
            sort = Sort.by(sortBy).descending();
        } else {
            sort = Sort.by(sortBy).ascending();
        }
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

        logger.info("Entered into the method getAll()");
        List<StudentDTO> studentDTOS = new ArrayList<>();
        Page<Student> page = studentRepository.findAll(pageable);
        List<Student> studentList = page.getContent();


        logger.info("Fetching the list of student");
        for (Student student : studentList) {
            Set<SubjectDTO> subjectDTOS = new HashSet<>();
            for (Subject subject : student.getSubject()) {
                subjectDTOS.add(new SubjectDTO(subject.getId(), subject.getName()));
            }
            StudentDTO studentDTO = new StudentDTO(
                    student.getId(),
                    student.getFirstName(),
                    student.getLastName(),
                    student.getEmail(),
                    student.getPhone(),
                    student.getAge(),
                    student.getGender(),
                    new AddressDTO(student.getAddress().getId(), student.getAddress().getHouseNo(), student.getAddress().getLandMark(), student.getAddress().getCity(), student.getAddress().getState(), student.getAddress().getPinCode()),
                    new DepartmentDTO(student.getDepartment().getId(), student.getDepartment().getName()),
                    subjectDTOS
            );
            studentDTOS.add(studentDTO);
        }

        logger.info("Student fetched successfully");
        return studentDTOS;
    }

    public List<StudentDTO> searchStudents(String firstName, String lastName, String department, String subjectName) {
        GetSearchSpecification searchSpecification = new GetSearchSpecification();
        Specification<Student> specification = searchSpecification.getSearchStudent(firstName, lastName, department, subjectName);

        List<Student> studentList = studentRepository.findAll(specification);

        List<StudentDTO> studentDTOS = new ArrayList<>();
        logger.info("Fetching the list of student");
        for (Student student : studentList) {
            Set<SubjectDTO> subjectDTOS = new HashSet<>();
            for (Subject subject : student.getSubject()) {
                subjectDTOS.add(new SubjectDTO(subject.getId(), subject.getName()));
            }
            StudentDTO studentDTO = new StudentDTO(
                    student.getId(),
                    student.getFirstName(),
                    student.getLastName(),
                    student.getEmail(),
                    student.getPhone(),
                    student.getAge(),
                    student.getGender(),
                    new AddressDTO(student.getAddress().getId(), student.getAddress().getHouseNo(), student.getAddress().getLandMark(), student.getAddress().getCity(), student.getAddress().getState(), student.getAddress().getPinCode()),
                    new DepartmentDTO(student.getDepartment().getId(), student.getDepartment().getName()),
                    subjectDTOS
            );
            studentDTOS.add(studentDTO);
        }

        logger.info("Student fetched successfully");
        return studentDTOS;
    }

    @Override
    public MappingJacksonValue searchStudentsByKeyword(String keyword, Set<String> fields) {
        List<Student> studentList = studentRepository.searchByKeyword("%" + keyword + "%");
        List<StudentDTO> studentDTOS = new ArrayList<>();
        logger.info("Fetching the list of student");
        for (Student student : studentList) {
            Set<SubjectDTO> subjectDTOS = new HashSet<>();
            for (Subject subject : student.getSubject()) {
                subjectDTOS.add(new SubjectDTO(subject.getId(), subject.getName()));
            }
            StudentDTO studentDTO = new StudentDTO(
                    student.getId(),
                    student.getFirstName(),
                    student.getLastName(),
                    student.getEmail(),
                    student.getPhone(),
                    student.getAge(),
                    student.getGender(),
                    new AddressDTO(student.getAddress().getId(), student.getAddress().getHouseNo(), student.getAddress().getLandMark(), student.getAddress().getCity(), student.getAddress().getState(), student.getAddress().getPinCode()),
                    new DepartmentDTO(student.getDepartment().getId(), student.getDepartment().getName()),
                    subjectDTOS
            );
            studentDTOS.add(studentDTO);
        }

        logger.info("Student fetched successfully");
        SimpleBeanPropertyFilter filter =SimpleBeanPropertyFilter.filterOutAllExcept(fields);
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("StudentDetails",filter );
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(studentDTOS);
        mappingJacksonValue.setFilters(filterProvider);
        return mappingJacksonValue;
    }

    public boolean updateDetails(StudentDTO studentDTO, final int id) {
        logger.info("Entered into the method updateDetails()");


        Student student = (studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("User", "id", id)));

        logger.info("fetching student Details by studentID");
        logger.info("Student Details fetched successfully");
        Address address = new Address(
                studentDTO.getAddressDTO().getHouseNo(),
                studentDTO.getAddressDTO().getLandMark(),
                studentDTO.getAddressDTO().getCity(),
                studentDTO.getAddressDTO().getState(),
                studentDTO.getAddressDTO().getPinCode());
        Address address1 = addressService.addAddress(address);

        Department department = new Department(studentDTO.getDepartmentDTO().getName());
        Department department1 = departmentService.addDepartment(department);

        Set<Subject> subjects = new HashSet<>();
        for (SubjectDTO subjectDTO : studentDTO.getSubjectDTOs()) {
            Subject subject = new Subject(subjectDTO.getName());
            subjects.add(subject);
        }
        Set<Subject> subjectSet = new HashSet<>();

        for (Subject s : subjects) {

            Subject subject = subjectService.addSubject(s);
            subjectSet.add(subject);
        }
        Student studentToSave = new Student(id, studentDTO.getFirstName(),
                studentDTO.getLastName(),
                studentDTO.getEmail(),
                studentDTO.getPhone(),
                studentDTO.getAge(),
                studentDTO.getGender(),
                address1,
                department1,
                subjectSet);
        studentRepository.save(studentToSave);
        logger.info("Student Details updated Successfully");
        return true;


    }

    public void deleteStudent(int id) {
        logger.info("Entered into the method deleteStudent()");

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student", "Id", id));
        logger.info("Student Details fetched Successfully");
        studentRepository.deleteById(id);
    }


    public StudentDTO getbyid(Integer id) {
        Optional<Student> student = studentRepository.findById(id);

            Set<SubjectDTO> subjectDTOS = new HashSet<>();
            for (Subject subject : student.get().getSubject()) {
                subjectDTOS.add(new SubjectDTO(subject.getId(), subject.getName()));
            }
            StudentDTO studentDTO = new StudentDTO(
                    student.get().getId(),
                    student.get().getFirstName(),
                    student.get().getLastName(),
                    student.get().getEmail(),
                    student.get().getPhone(),
                    student.get().getAge(),
                    student.get().getGender(),
                    new AddressDTO(student.get().getAddress().getId(), student.get().getAddress().getHouseNo(), student.get().getAddress().getLandMark(), student.get().getAddress().getCity(), student.get().getAddress().getState(), student.get().getAddress().getPinCode()),
                    new DepartmentDTO(student.get().getDepartment().getId(), student.get().getDepartment().getName()),
                    subjectDTOS
            );
            return studentDTO;
        }


}