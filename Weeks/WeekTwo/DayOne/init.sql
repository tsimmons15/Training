create table if not exists employee (
	first_name varchar(20), 
	last_name varchar(20), 
	salary int
);

/*
insert into employee values ('Adam', 'Ranieri', 1000000);
insert into employee values ('Timothy', 'Simmons', 1000000);
insert into employee (last_name, salary) values ('Smith', 40000);
*/
select * from employee;

select * from employee
where last_name like 'S%';

/*delete from employee;*/
delete from employee where first_name is null;

update employee set first_name = 'Bill' where first_name is null;

alter table if exists employee
	add column id int;

update employee set id = 1 where first_name = 'Adam';
update employee set id = 2 where first_name = 'Timothy';
update employee set id = 3 where first_name = 'Bill';
/*alter table if exists employee	
	add serial primary key (id);*/
alter table if exists employee
	add column id serial primary key;
alter table employee drop constraint employee_pkey;
alter table employee drop column id;
insert into employee values ('Test', 'Person', 100000, 4);
insert into employee values ('Another', 'Test', 1000);
insert into employee values ('Another', 'Test', 100);
select * from employee;

alter table employee 
	add constraint employee_check_salary check (salary > 0);