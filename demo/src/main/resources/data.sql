insert into course (id,name,created_date,last_updated_date,is_deleted) values(10000,'JPA with Hibernate',sysdate(),sysdate(),false);
insert into course (id,name,created_date,last_updated_date,is_deleted) values(10001,'SpringBoot with Hibernate',sysdate(),sysdate(),false);
insert into course (id,name,created_date,last_updated_date,is_deleted) values(10002,'Microservice in 100 steps',sysdate(),sysdate(),false);


insert into passport(id,number) values(40001,'E123456');
insert into passport(id,number) values(40002,'B123442');
insert into passport(id,number) values(40003,'A556191');

insert into student(id,name,passport_id,line1,line2,city) values(20001,'Jane',40001,'101','Park Town','Chennai');
insert into student(id,name,passport_id,line1,line2,city) values(20002,'Bob',40002,'101','Park Town','Chennai');
insert into student(id,name,passport_id,line1,line2,city) values(20003,'Amit',40003,'101','Park Town','Chennai');

insert into review(id,rating,description,course_id) values(50001,'FIVE','Great Course',10000);
insert into review(id,rating,description,course_id) values(50002,'FOUR','Good Course',10000);
insert into review(id,rating,description,course_id) values(50003,'THREE','Average Course',10002);

insert into student_course(student_id,course_id) values(20001,10000);
insert into student_course(student_id,course_id) values(20002,10000);
insert into student_course(student_id,course_id) values(20003,10000);
insert into student_course(student_id,course_id) values(20001,10002);