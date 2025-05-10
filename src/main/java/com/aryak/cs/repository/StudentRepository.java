package com.aryak.cs.repository;

import com.aryak.cs.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

//@ConditionalOnProperty(havingValue = "true", value = "db.enabled")
public interface StudentRepository extends JpaRepository<Student, Long> {
}
