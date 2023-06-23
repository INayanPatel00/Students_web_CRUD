package com.brevitaz.controller;

import com.brevitaz.DTO.SubjectResponseDTO;
import com.brevitaz.services.serviceInterface.SubjectService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subjects")
public class SubjectResource {
    private static final Logger logger = LogManager.getLogger(SubjectResource.class);
    private final SubjectService subjectService;

    public SubjectResource(final SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping("")
    public List<SubjectResponseDTO> getAllSubject(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "2", required = false) Integer pageSize
    ) {
        logger.info("Entered into getAllSubject Method");
        return subjectService.getAllSubject(pageNumber, pageSize);

    }

    @GetMapping("/{name}")
    public List<SubjectResponseDTO> getBySubjectName(@PathVariable String name) {
        logger.info("Entered into getBySubjectName Method");
        return subjectService.getBySubjectName(name);
    }
}

