package com.brevitaz.services.serviceInterface;

import com.brevitaz.DTO.DepartmentResponseDTO;
import com.brevitaz.entity.Department;

import java.util.List;

public interface DepartmentService {
    Department addDepartment(Department department);
    List<DepartmentResponseDTO> getAllDepartment(Integer pageNumber, Integer pageSize);
    List<DepartmentResponseDTO> getByDepartmentName(final String name);


}
