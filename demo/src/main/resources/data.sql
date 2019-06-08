insert into course (id,name,created_date,last_updated_date) values(10000,'JPA with Hibernate',sysdate(),sysdate());
insert into course (id,name,created_date,last_updated_date) values(10001,'SpringBoot with Hibernate',sysdate(),sysdate());
insert into course (id,name,created_date,last_updated_date) values(10002,'Microservice in 100 steps',sysdate(),sysdate());

insert into passport(id,number) values(40001,'E123456');
insert into passport(id,number) values(40002,'B985542');
insert into passport(id,number) values(40003,'A556191');

insert into student(id,name,passport_id) values(20001,'Jane',40001);
insert into student(id,name,passport_id) values(20002,'Bob',40002);
insert into student(id,name,passport_id) values(20003,'Amit',40003);

insert into review(id,rating,description,course_id) values(50001,'5','Great Course',10000);
insert into review(id,rating,description,course_id) values(50002,'4','Good Course',10000);
insert into review(id,rating,description,course_id) values(50003,'3','Average Course',10002);

insert into student_course(student_id,course_id) values(20001,10000);
insert into student_course(student_id,course_id) values(20002,10000);
insert into student_course(student_id,course_id) values(20003,10000);
insert into student_course(student_id,course_id) values(20001,10002);