#  Introduction to JPA & Hibernate In Depth

# Create a JPA project with H2 and Spring Boot
1. Create a spring starter project
2. Add dependency for JPA,H2,Web

# Create JPA Entity Course
```
@Entity
public class Course{
  
  @Id
  @GeneratedValue
  private int id;
  private String name;
  
  protected Course(){}//need to provide default constructor
  
  public Course(String name){
    this.name = name;
  }
  //getters
  //setters
}  
```

# Create findById using JPA Entity Manager

```
public class CourseRepository{

  @Autowired
  EntityManager em;
  
  public Course findById(int id){
   return  em.find(Course.class,id);
 }
 
 }
 ```
 
 **CommandLine Runner:** CommandLineRunner is a simple spring boot interface with a run method. The run method of all beans implementing the CommandLineRunner interface will be called automatically by the spring boot system after the initial boot.
 
 Create SQL file inside /src/main/resouces
 ```
 insert into course(id,name) values(10001,'JPA in 50 steps');
 ```
 
 DemoApplication.java
 ```
 @SpringBootApplication
 public class DemoApplication implements CommandLineRunner{
 private Logger logger = LoggerFactory.getLogger(this.getClass());
  @Autowired
  CourseRepository repository;
  
  public static vaid main(String args[]){
    SpringApplication.run(DemoApplication.class.args);
  }
  
  @Override
  public void run(String... args0) throws Exception{
    Course course = repository.findById(10001l);
    logger.info("Courses {}",course);
  }
 }
 ```
# Configure application.properties to Enable  H2 console and loggings
application.property
```
#turning H2 console on
spring.h2.console.enabled=true

#turn statistics on
spring.jpa.properties.hibernate.generate_statistics=true
logging.level.org.hibernate.stat=debug

#show all queries
spring.jpa.show-sql=true
#formate the result
spring.jpa.properties.hibernate.format_sql=true

logging.level.org.hibernate.type=trace
```
# Writing Unit test case for findById method

**@RunWith(SpringRunner.class):-** launches Spring Context in Unit Test

**@SpringBootTest:-** Spring Context that we want to launch is **SpringBootTest**
Optionally We can specify the class which we want to launch **@SpringBootTest(classes=DemoApplication.class)**
Unit Test run between Context launch and context destroy.

```
import com.jitu.jpa.hibernate.demo.repository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=DemoApplication.class)
public class CourseRepositoryTest{
  private Logger logger = LoggerFactory.getLogger(this.getClass());
  @Autowired
  CourseRepository repository;
  
  @Test
  public void findById_basic(){
    Course course = repository.findById(10000l);
    assertEquals("JPA in 50 Steps",course.getName());
}
```

# Writing a deleteById method to delete an Entity

When you are modifying the data (like deleting the data) you need to do that withing transaction.

```
@Repository
@Transactional
public class CourseRepository{

@Autowired
EntityManager em;

--findById(Long id){--}

  public void deleteById(Long id){
    Course course = findById(id);
    em.remove(course);
  }
}

########DemoApplication.java###########
@SpringBootApplication
public class DemoApplication implements CommandLineRunner{
private Logger logger = LoggerFactory.getLogger(this.getClass());

@Autowired
private CourseRepository repository;
public static void main(String args[]){
//
}
@Override
public void run(String...arg0) throws Exception{
  //
  repository.deleteById(10000l);
}
```

