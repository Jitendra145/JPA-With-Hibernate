**@Table-->** used to define name of the table
**@Column-->** used to specify name of the column
```
@Entity
@Table(name="CourseDetails")
public class Course{
@Id
@GeneratedValue
private int id;

@Column(name="fullname")
private String name;
//We can also dectate that column should not have null value using @Column

@Column(name="fullName",nullable=false)
private  String name;
}
```

**Note:** Apart from nullable we can define **unique,insertable,updatable,length,precision** and other

# @UpdateTimestamp & @CreationTimestamp
Above both annotations are from Hibernate not from JPA

**@UpdateTimestamp-**

**@CreationTimestamp-**

```
@UpdateTimestamp
private LocalDateTime lastUpdatedDate;

@CreationTimestamp
private LocalDateTime createdDate;
```

# @NamedQuery & @NamedQueries
 
 With @NameQuery you can assign name to the query and use it.
 ```
 @Entity
 @NamedQuery(name="query_get_all_courses",query="Select c From Course c")
 public class Course{
 
 }
 ```
 
 when you have to define multiple queries the use @NamedQueries
  ```
 @Entity
 @NamedQueries(value={
  @NamedQuery(name="query_get_all_courses",query="Select c From Course c")
  @NamedQuery(name="query_get_100_steps_courses",query="Select c From Course c where name like '100 Steps'")
 })
 
 public class Course{
 
 }
 ```
 
 # Native Queries - Basics
 Native Query is Sql query that we use in JPA.
 ```
 @Autowired
 EntityManager em;
 
 @Test
 public void native_queries_basic(){
 Query query = em.createNativeQuery("Select * from Course",Course.class)
 List<Course> resultList = query.getResultList();
 }
 
 @Test
 public void native_queries_with_parameter(){
 Query query = em.createNativeQuery("Select * from Course where id=?",Course.class)
 query.setParameter(1,1000l);
 List<Course> resultList = query.getResultList();
 }
 
 @Test
 public void native_queries_with_named_parameter(){
 Query query = em.createNativeQuery("Select * from Course where id=:id",Course.class)
 query.setParameter("id",1000l);
 List<Course> resultList = query.getResultList();
 }
 
  @Test
  @Transactional
 public void native_queries_to_update(){
 Query query = em.createNativeQuery("update course set last_updated_date=sysdate()",Course.class)
// query.setParameter("id",1000l
 int noOfRowUpdated = query.executeUpdate();
 
 }
 ```
 
 **Note:-** There is no way to do mass update throug JPA. So in that case we will use Native Query
 
