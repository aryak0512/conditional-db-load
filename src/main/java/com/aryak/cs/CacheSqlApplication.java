package com.aryak.cs;

import com.aryak.cs.model.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class CacheSqlApplication implements CommandLineRunner {

    private final ApplicationContext context;
    //private StudentDaoImpl studentDaoImpl;

    public CacheSqlApplication(ApplicationContext context) {
//        this.studentDaoImpl = studentDaoImpl;
        this.context = context;
    }


    public static void main(String[] args) {
        SpringApplication.run(CacheSqlApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        //System.out.println("Bean found : " + context.getBean("studentDaoImpl"));
        Arrays.stream(context.getBeanDefinitionNames()).filter(b -> b.toLowerCase().contains("studentdaoimpl")).forEach(System.out::println);

        Student student1 = new Student();
        student1.setName("aryak");
        student1.setAddress("mumbai");
        //studentDaoImpl.save(student1);

        //studentDaoImpl.getStudentById(1L);
    }
}
