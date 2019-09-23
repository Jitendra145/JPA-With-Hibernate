With NamedQuery we can assign a name to the query and use it.

```
@Entity
@NamedQuery(name="query_get_all_courses",query="Select c FROM Course c")
public class Course{

}
```
Using Named query
```
Query query = em.getNamedQuery("query_get_all_courses");
List resultList = query.getResultList();
```
@NamedQuery is not repeatable query, so we can use it only one time
For using multiple namequeries we need to use @NamedQueries annotation

```
@Entity
@NamedQueries(value={
  @NamedQuery(name="query_get_all_courses",query="Select c FROM Course c")
  @NamedQuery(name="query_get-100_step_course",query="Select c From Course c where name like '%100%'")
})
public class Course{

}
```
# Native Queries - Basics

Situation where to use Native Queries
1. when database tuning is required.
2. When mass update is required
Native Query is SQL query.

JPQL--> Select c From Course c;
SQL--> Select * from Course;

```
@Test
public void native_queries_basic(){
  Query query = em.createNativeQuery("select * from Course",Course.class);
  List resultList = query.getResultList();
 }
 
 @Test
public void native_queries_parameter(){
  Query query = em.createNativeQuery("select * from Course where id=?",Course.class);
  query.setParameter(1,1000L);
  List resultList = query.getResultList();
 }
 
 @Test
public void native_queries_named_parameter(){
  Query query = em.createNativeQuery("select * from Course where id=:id",Course.class);
  query.setParameter("id",1000L);
  List resultList = query.getResultList();
 }
```



  

