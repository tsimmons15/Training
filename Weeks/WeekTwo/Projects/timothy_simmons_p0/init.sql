create table if not exists Client (
	client_id serial primary key,
	client_name varchar(50) not null,
	client_username varchar(30) not null unique,
	client_password varchar(32) not null,
	client_salt varchar(32) not null
); 
select * from client;

delete from account_owner;
delete from client;
delete from account;
alter table client add column client_password varchar(32) not null;
alter table client add column client_salt char(24) not null;

create table if not exists Account_Owner (
	client_id int,
	account_id int
);
alter table Account_Owner add primary key (client_id, account_id);
alter table Account_Owner add constraint ao_client_fk foreign key(client_id) references Client(client_id);
alter table Account_Owner add constraint ao_account_fk foreign key(account_id) references Account(account_id);
select * from account_owner;

create table if not exists Account (
	account_id serial primary key,
	account_balance float(32) default 0,
	account_type varchar(10) not null default 'Checking'
);
select * from account;