# JPA-With-Hibernate
It's step by step process of creating Hibernate application using Spring Boot, H2 DB
Section 5: JPA and hibernate in depth

Introduction to Jpa and Hibernate in depth

# Step 1: Create a JPA project with H2 and Spring Boot 
# Step 2: Create JPA Entity Course

    package com.jitendra.jpa.hibernate.entity;
    import javax.persistence.Entity;
    import javax.persistence.GeneratedValue;
    import javax.persistence.Id;

    @Entity
    public class Course {

        @Id
        @GeneratedValue
        private int id;

        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public Course(String name) {
            super();
            this.name = name;
        }

        protected Course() {
        }

    }

# Step 3:Create findById using JPA EntityManager

    package com.jitendra.jpa.hibernate.repository;
    import javax.persistence.EntityManager;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Repository;
    import com.jitendra.jpa.hibernate.entity.Course;

    @Repository
    public class CourseRepository {

        @Autowired
        EntityManager em;

        public Course findById(int id) {
            return em.find(Course.class, id);
        }
    }

    package com.jitendra.jpa.hibernate;
    import org.slf4j.Logger;
    import org.slf4j.LoggerFactory;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.boot.CommandLineRunner;
    import org.springframework.boot.SpringApplication;
    import org.springframework.boot.autoconfigure.SpringBootApplication;
    import com.jitendra.jpa.hibernate.entity.Course;
    import com.jitendra.jpa.hibernate.repository.CourseRepository;

    @SpringBootApplication
    public class DemoApplication implements CommandLineRunner {

        private Logger logger = LoggerFactory.getLogger(this.getClass());
        @Autowired
        private CourseRepository repository;

        public static void main(String[] args) {
            SpringApplication.run(DemoApplication.class, args);
        }

        @Override
        public void run(String... args) throws Exception {
            Course result = repository.findById(10000);
            logger.info("Course for 10000-->{}", result);
        }

    }

# Step 4. configuring application.properties to enable H2 console

    #enabling H2 console
    spring.h2.console.enabled=true

    #turn statistics on
    spring.jpa.properties.hibernate.generate_statistics=true
    logging.level.org.hibernate.stat=debug

    #show all query
    spring.jpa.show-sql=true
    spring.jpa.properties.hibernate.format_sql=true
    #enable to see the data bound to query parameter
    logging.level.org.hibernate.type=trace 





