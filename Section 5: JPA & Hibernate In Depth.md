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

