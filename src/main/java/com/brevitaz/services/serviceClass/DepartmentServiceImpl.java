package com.brevitaz.services.serviceClass;

import com.brevitaz.DTO.DepartmentResponseDTO;
import com.brevitaz.DTO.StudentResponseDTO;
import com.brevitaz.entity.Department;
import com.brevitaz.entity.Student;
import com.brevitaz.repository.DepartmentRepository;
import com.brevitaz.services.serviceInterface.DepartmentService;
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
public class DepartmentServiceImpl implements DepartmentService {
    private static final Logger logger = LogManager.getLogger(DepartmentServiceImpl.class);

    private final DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(final DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public Department addDepartment(Department department) {
        logger.info("Entered into addDepartment method");
        Department department1 = departmentRepository.findByName(department.getName());
        if (department1 == null) {
            logger.info("Department saved successfully");
            return departmentRepository.save(department);
        }
        logger.info("Department already Exists");
        return department1;
    }

    public List<DepartmentResponseDTO> getAllDepartment(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        logger.info("Entered into getAllDepartment method");
        List<DepartmentResponseDTO> departmentResponseDTOS = new ArrayList<>();
        logger.info("fetching the list of  Department");

        Page<Department> departmentPage = departmentRepository.findAll(pageable);
        List<Department> departments = departmentPage.getContent();

        for (Department department : departments) {
            Set<StudentResponseDTO> studentResponseDTOS = new HashSet<>();
            for (Student student : department.getStudent()) {
                studentResponseDTOS.add(new StudentResponseDTO(student.getId(),
                        student.getFirstName(),
                        student.getLastName(),
                        student.getEmail(),
                        student.getPhone(),
                        student.getAge(),
                        student.getGender()));
            }
            DepartmentResponseDTO departmentResponseDTO = new DepartmentResponseDTO(
                    department.getId(),
                    department.getName(),
                    studentResponseDTOS);


            departmentResponseDTOS.add(departmentResponseDTO);

        }
        logger.info("Department fetched successfully");

        return departmentResponseDTOS;
    }

    public List<DepartmentResponseDTO> getByDepartmentName(final String name) {
        logger.info("Entered into getByName method");
        logger.info("fetching Department by DepartmentName");
        Department departments = departmentRepository.findByName(name);
        if (departments != null) {
            List<DepartmentResponseDTO> departmentResponseDTOS = new ArrayList<>();

            Set<StudentResponseDTO> studentResponseDTOS = new HashSet<>();
            for (Student student : departments.getStudent()) {
                studentResponseDTOS.add(new StudentResponseDTO(student.getId(),
                        student.getFirstName(),
                        student.getLastName(),
                        student.getEmail(),
                        student.getPhone(),
                        student.getAge(),
                        student.getGender()));
            }
            DepartmentResponseDTO departmentResponseDTO = new DepartmentResponseDTO(
                    departments.getId(),
                    departments.getName(),
                    studentResponseDTOS);


            departmentResponseDTOS.add(departmentResponseDTO);

            logger.info("Department fetched successfully");
            return departmentResponseDTOS;
        } else {
            throw new DataIntegrityViolationException("Department Not Found");
        }
    }

}

