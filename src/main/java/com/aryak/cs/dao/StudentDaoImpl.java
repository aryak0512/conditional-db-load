package com.aryak.cs.dao;

import com.aryak.cs.model.Student;
import com.aryak.cs.repository.StudentRepository;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@ConditionalOnProperty(havingValue = "true", value = "db.enabled")
public class StudentDaoImpl {

    private final StudentRepository studentRepository;
    private final EntityManager entityManager;

    public StudentDaoImpl(StudentRepository studentRepository, EntityManager entityManager) {
        this.studentRepository = studentRepository;
        this.entityManager = entityManager;
        log.info("StudentDaoImpl was loaded.");
    }

    public void save(Student s) {

        System.out.println("In save method : " + entityManager);
        studentRepository.save(s);
    }

    public void getStudentById(Long id) {

        String sql = "SELECT * FROM student where id=?";

        Student s = (Student) entityManager.createNativeQuery(sql, Student.class)
                .setParameter(1, id).getSingleResult();
        System.out.println("In get method : " + s);

    }
}
