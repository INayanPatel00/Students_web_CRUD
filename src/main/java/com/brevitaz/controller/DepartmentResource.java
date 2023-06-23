package com.brevitaz.controller;

import com.brevitaz.DTO.DepartmentResponseDTO;
import com.brevitaz.services.serviceInterface.DepartmentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentResource {
    private final DepartmentService departmentService;
    private static final Logger logger = LogManager.getLogger(DepartmentResource.class);

    public DepartmentResource(final DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("")
    public List<DepartmentResponseDTO> getAllDepartment(
            @RequestParam(value = "pageNumber",defaultValue = "0",required = false)Integer pageNumber,
            @RequestParam(value = "pageSize",defaultValue = "2",required = false)Integer pageSize

            ) {
        logger.info("Entered into getAllDepartment Method");
        return departmentService.getAllDepartment(pageNumber,pageSize);
    }

    @GetMapping("/{name}")
    public List<DepartmentResponseDTO> getByDepartmentName(@PathVariable String name) {
        logger.info("Entered into getByDepartmentName");
        return departmentService.getByDepartmentName(name);
    }

}
