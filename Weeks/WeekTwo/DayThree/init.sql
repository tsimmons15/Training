create table if not exists Book (
	id serial primary key,
	title varchar(50) not null,
	author varchar(40) not null,
	return_date int
);

insert into Book (title, author, return_date) values ('Cat''s Cradle', 'Kurt Vonnegut', 0);