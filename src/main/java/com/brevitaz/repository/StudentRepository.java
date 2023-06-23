package com.brevitaz.repository;

import com.brevitaz.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>, JpaSpecificationExecutor<Student> {
    Student getByFirstNameAndEmail(String firstName, String email);

    @Query("SELECT DISTINCT s FROM Student s " +
            "LEFT JOIN FETCH s.address " +
            "LEFT JOIN FETCH s.department " +
            "WHERE CAST(s.id as string) LIKE :keyword OR" +
            "      s.firstName LIKE :keyword OR " +
            "      s.email LIKE :keyword OR " +
            "      s.phone LIKE :keyword OR " +
            "      CAST(s.age as string) LIKE :keyword OR " +
            "      s.gender LIKE :keyword OR " +
            "      s.address.landMark LIKE :keyword OR " +
            "      s.address.city LIKE :keyword OR " +
            "      s.address.state LIKE :keyword OR " +
            "      CAST(s.address.pinCode as string) LIKE :keyword OR " +
            "      s.department.name LIKE :keyword ")
    List<Student> searchByKeyword(@Param("keyword") String keyword);

    Optional<Student> findById(Integer id);
//        @Query("SELECT s FROM Student s LEFT JOIN s.address a LEFT JOIN s.department d LEFT JOIN s.subject sub" +
//            " WHERE s.firstName LIKE :searchValue OR s.lastName LIKE :searchValue " +
//            "OR s.email LIKE :searchValue OR s.phone LIKE :searchValue OR s.gender LIKE :searchValue " +
//            "OR s.age LIKE :searchValue OR a.houseNo LIKE :searchValue OR " +"OR a.landMark LIKE :searchValue OR " +
//            "a.city LIKE :searchValue OR a.state LIKE :searchValue OR " +
//            "a.pinCode LIKE :searchValue OR d.name LIKE :searchValue OR " +
//            "sub.name LIKE :searchValue")
//    List<Student> searchStudents(@Param("searchValue") String searchTerm);

//    List<Student> searchStudents(String searchTerm);

}


//    List<Student> findByFirstName(String firstName);


