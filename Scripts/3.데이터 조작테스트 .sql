select user(), database();

show tables;
-- title
desc title ;

insert into title values(1, '사장');
insert into title values(2, '부장');
insert into employee values(4377, '이성래', 1, null, 4500000, 1);

update title set tname = '과장' where tno = 2;

delete  from title where tno = 2 ;

select * from title;
select tno,tname from title ;
select tno,tname from title where tno = 2;
select deptno ,deptname, floor from department ;
select empno ,empname ,title ,manager ,salary ,dno from employee ;

-- department
desc department ;

insert into department values(1,'기획',10);
insert into department values(2,'개발',5);

update department set deptname = '인사' where deptno = 2;

delete from department where deptno =2;

select * from department ;
select deptno ,deptname ,floor from department;
select deptno ,deptname ,floor from department where deptno = 2;
