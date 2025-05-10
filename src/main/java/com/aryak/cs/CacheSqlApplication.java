package com.aryak.cs;

import com.aryak.cs.model.Student;
import com.aryak.cs.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class
})
public class CacheSqlApplication implements CommandLineRunner {

    private final StudentRepository repository;

    public CacheSqlApplication(StudentRepository repository) {
        this.repository = repository;
    }

    public static void main(String[] args) {
        SpringApplication.run(CacheSqlApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Student student1 = new Student();
        student1.setName("aryak");
        student1.setAddress("Mumbai");
        repository.save(student1);
    }
}
