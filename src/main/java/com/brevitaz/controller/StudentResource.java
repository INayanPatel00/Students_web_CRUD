package com.brevitaz.controller;

import com.brevitaz.DTO.StudentDTO;
import com.brevitaz.entity.Subject;
import com.brevitaz.services.serviceInterface.StudentService;
import com.brevitaz.services.serviceInterface.SubjectService;
import io.swagger.v3.oas.annotations.Operation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;
import org.springframework.ui.Model;


@Controller
@RequestMapping("/students")
public class StudentResource {
    private static final Logger logger = LogManager.getLogger(StudentResource.class);
    private final StudentService studentService;
    private final SubjectService subjectService;


    public StudentResource(final StudentService studentService, SubjectService subjectService) {
        this.studentService = studentService;

        this.subjectService = subjectService;
    }

    @PostMapping("/insert")

    @Operation(summary = "Insert student", description = "API to Insert student")

    public String insertStudentsDetails(@ModelAttribute("user")  StudentDTO studentDTO) throws SQLException {
        logger.info("Entered into insertStudentsDetails method");

        studentService.insertStudent(studentDTO);

        return "redirect:/students";


    }

    @GetMapping("")
    @Operation(summary = "GetAll student", description = "API to GetAll student Details")

    public String viewAll(Model model,
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "100", required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
            @RequestParam(value = "sortDir",defaultValue = "asc", required = false) String sortDir
    ) {
        logger.info("Entered into viewAll method");
        List<StudentDTO> list= studentService.getAll( pageNumber,  pageSize,  sortBy,  sortDir);

        model.addAttribute("list", list);
        return "view";

    }
    @GetMapping("/add")
    public String add(Model model) {
        StudentDTO studentWebDTO = new StudentDTO();
        StudentDTO studentDTO = new StudentDTO();


        List<Subject> list = subjectService.getAllSubjects();

        model.addAttribute("subjects",list);
        model.addAttribute("user", studentWebDTO);
        return "addForm";
    }
    @GetMapping("/searcher")
    public List<StudentDTO> search(@RequestParam(value = "firstName",defaultValue = "",required = false) String firstName,
                                   @RequestParam(value = "lastName",defaultValue = "",required = false) String lastName,
                                   @RequestParam(value = "department",defaultValue = "",required = false) String department,
                                   @RequestParam(value = "subjectName",defaultValue = "",required = false) String subjectName
                                   ){
        return studentService.searchStudents(firstName,lastName,department,subjectName);
    }


    @GetMapping("/search")
    public MappingJacksonValue searchByKeyword(@RequestParam("keyword")String keyword, @RequestParam(value = "fields",defaultValue = "",required = false) Set<String> fields){
        return studentService.searchStudentsByKeyword(keyword,fields);

//        return studentService.searchStudentsByKeyword(firstName,lastName,department,subjectName);
    }


    @GetMapping("/update/{id}")
    @Operation(summary = "update student", description = "API to update student using studentId")
    public String update(Model model, @PathVariable("id") int id) throws SQLException {
        StudentDTO user=studentService.getbyid(id);
        List<Subject> subjects = subjectService.getAllSubjects();
        model.addAttribute("user", user);
        model.addAttribute("subjects",subjects);
        return "updateForm";
    }




    @PostMapping ("/edit/{id}")
    @Operation(summary = "update student", description = "API to update student using studentId")
    public String updateStudentDetails(@ModelAttribute("user") StudentDTO user, @PathVariable("id") int id) throws SQLException {
        logger.info("Entered into updateStudentDetails method");

        studentService.updateDetails(user,id);
            return "redirect:/students";

    }


    @GetMapping("/delete/{id}")
//    @Operation(summary = "delete student", description = "API to delete student using studentId")
    public String deleteById(@PathVariable("id") int id) {
//        logger.info("Entered into DeleteBYId method");
        studentService.deleteStudent(id);
        return "redirect:/students";
//        return "StudentsID Deleted Successfully/ ";
    }


}
