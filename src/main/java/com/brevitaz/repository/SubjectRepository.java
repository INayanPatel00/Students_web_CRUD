package com.brevitaz.repository;

import com.brevitaz.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject,Integer> {
    Subject findByName(String name);
}
