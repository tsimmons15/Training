create table if not exists team(
	team_id serial primary key,
	team_name varchar(30) not null unique,
	team_hometown varchar(50) not null,
	team_mascot varchar(30)
);

create table if not exists player(
	player_id serial primary key,
	player_name varchar(30),
	salary int check (salary > 0),
	team_id int
);

create table game (
	game_id serial primary key,
	game_location varchar(50)
);

create table player_game (
	game_id int,
	player_id int
);

alter table player drop column salary;
alter table player add column player_salary int check (player_salary > 0);
alter table player add constraint p_team_fk foreign key(team_id) references Team(team_id);


alter table player_game add constraint pg_pk primary key(game_id, player_id);
alter table player_game add constraint pg_game_fk foreign key(game_id) references Game(game_id);
alter table player_game add constraint pg_player_fk foreign key(player_id) references Player(player_id);



insert into team (team_name, team_hometown, team_mascot) 
	values ('Grand Dunk Railroad', 'Funky Town', 'The Conductor');
insert into team (team_name, team_hometown, team_mascot) 
	values ('Monstars', 'Moron Mountain', 'The Monster');
insert into team (team_name, team_hometown, team_mascot) 
	values ('MuttonChop Mountaineers', 'Morgantown', 'The Mountaineer');
insert into team (team_name, team_hometown, team_mascot) 
	values ('The Utah Jazz', 'Salt Lake City', null);

insert into player (player_name, player_salary, team_id)
	values ('Adam', 100000, 1);
insert into player (player_name, player_salary, team_id)
	values ('Billy', 1000, 1);
insert into player (player_name, player_salary, team_id)
	values ('Chucky', 100000, 2);
insert into player (player_name, player_salary, team_id)
	values ('Ursula', 100000, 2);
insert into player (player_name, player_salary, team_id)
	values ('Dracula', 100000, 2);
insert into player (player_name, player_salary, team_id)
	values ('Jerry West', 100000, 3);
insert into player (player_name, player_salary, team_id)
	values ('Will Grier', 100000, 3);

insert into game (game_location)
	values ('Funky Town');
insert into game (game_location)
	values ('Madison Square Garden');
insert into game (game_location)
	values ('Morgantown');

insert into player_game (player_id, game_id) values (1,1);
insert into player_game (player_id, game_id) values (1,2);
insert into player_game (player_id, game_id) values (4,3);

select * from player;
select * from team;

-- normal join
select * from player left join team on player.team_id = team.team_id;
-- next two are identical because of the commutative property of right/left joins
select * from team left join player on player.team_id = team.team_id;
select * from player right join team on player.team_id = team.team_id;
-- no players without teams, so basically the first left join
select * from player inner join team on player.team_id = team.team_id; 
-- CARTESIAN PRODUCT!
select * from player left join team on 1 = 1;
select * from player cross join team;

select player_name, team_hometown from player left join team on player.team_id = team.team_id;

insert into player (player_name, player_salary, team_id)
	values ('Kim', 4000, 10); -- orphan, no referential integrity
	
select upper('Hello, there');
select * from upper('Hello, there!');

select avg(player_salary) from player;
select avg(player_salary), team.team_name 
from player left join team on player.team_id = team.team_id 
group by team.team_id;

select * from player;

insert into player (player_name, player_salary, team_id)
	values ('Mike', 10000, 4);
insert into player (player_name, player_salary, team_id)
	values ('Edward', 1000, 4);
	
rollback;



commit;

select player_name, player_salary from player
where player_salary > (select avg(player_salary) from player);
