create table if not exists Book (
	id serial primary key,
	title varchar(50) not null,
	author varchar(40) not null,
	return_date bigint default 0
);


insert into Book (title, author, return_date) values ('Cat''s Cradle', 'Kurt Vonnegut', 0);

select * from book;
delete from book where id > 2;

select * from book where id = 8;