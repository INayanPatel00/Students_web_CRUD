package com.brevitaz.services;

import com.brevitaz.entity.Student;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GetSearchSpecification {


    public Specification<Student> getSearchStudent(String firstName,String lastName,String departmentName,String subjectName){

        return((root, query, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();
            if(firstName != null && !firstName.isEmpty() && !firstName.isBlank()){
               predicateList.add(criteriaBuilder.like(root.get("firstName"),"%"+firstName+"%"));
            }
            if(lastName != null && !lastName.isEmpty() && !lastName.isBlank()){
                predicateList.add(criteriaBuilder.like(root.get("lastName"),"%"+lastName+"%"));
            }
            if(departmentName != null && !departmentName.isEmpty() && !departmentName.isBlank()){
                predicateList.add(criteriaBuilder.like(root.get("department").get("name"),"%"+departmentName+"%"));
            }
            if(subjectName != null && !subjectName.isEmpty() && !subjectName.isBlank()){
                predicateList.add(criteriaBuilder.like(root.get("subject").get("name"),"%"+subjectName+"%"));
            }
            return criteriaBuilder.and(predicateList.toArray(new  Predicate[0]));
        });

    }
}
