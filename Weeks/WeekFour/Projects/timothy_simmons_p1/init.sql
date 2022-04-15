create table employee (
	employee_id serial primary key,
	employee_first_name varchar(30) not null,
	employee_last_name varchar(30) not null
);

create table expense (
	expense_id serial primary key,
	expense_amount bigint not null check(expense_amount > 0),
	expense_approved varchar(8) not null,
	expense_date bigint not null,
	employee_issuer int
);

alter table expense add constraint exp_employee_fk foreign key(employee_issuer) references Employee(employee_id);

--alter table expense drop column expense_amount;
--alter table expense add column expense_amount float not null check(expense_amount > 0);
