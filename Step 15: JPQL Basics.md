In JPQL we queries from Entities(are Java classes with @Entity annotation)

**In SQL:** select from Course

**In JPQL:** Select c From Course c

```
@Autowired
EntityManager em;

@Test
public void jpql_Basic(){
 List resultList = em.createQuery("Select c From Course c").getResultList();
 }
 //TypedQuery when we passes Class type
 @Test
public void jpql_Typed(){
  TypedQuery<Course> query = em.createQuery("Select c From Course c",Course.class);
  List<Course> resultList = query.getResultList();
 }
 
  @Test
public void jpql_where(){
  TypedQuery<Course> query = em.createQuery("Select c From Course c where name like '%100'",Course.class);
  List<Course> resultList = query.getResultList();
 }
```
