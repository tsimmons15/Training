create table if not exists Client (
	client_id serial primary key,
	client_name varchar(50) not null,
	client_username varchar(30) not null unique,
	client_password bytea not null,
	client_salt bytea not null
);

alter table client drop column client_salt;
alter table Client add column client_salt bytea;
alter table client drop column client_password;
alter table Client add column client_password bytea;
select * from client;
delete from client;

insert into client (client_name, client_username, client_password, client_salt) values (
	'Testing', 'testingUsername', '\x11111111', '\x77777777'
);

create table if not exists Account_Owners (
	client_id int,
	account_id int
);

alter table Account_Owners add primary key (client_id, account_id);
alter table Account_Owners add constraint ao_client_fk foreign key(client_id) references Client(client_id);
alter table Account_Owners add constraint ao_account_fk foreign key(account_id) references Account(account_id);

create table if not exists Account (
	account_id serial primary key,
	account_balance int default 0,
	account_types_id int not null
);

alter table account add column account_types_id int;
alter table account alter column account_types_id set not null;
alter table account add constraint a_account_types_fk foreign key(account_types_id) references Account_Types(account_types_id);

create table if not exists Account_Types (
	account_types_id int primary key,
	account_type_desc varchar(15) not null unique
);
