create database Casinoj26;
use Casinoj26;

create table accounts (
                        id int primary key auto_increment,
                        username varchar(50) not null unique,
                        password varchar(50) not null,
                        money int not null check (money >= 0 )
);

create table gametours (
                            id int primary key auto_increment,
                            gamename varchar(50) not null,
                            username varchar(50) not null ,
                            time datetime not null default now(),
                            winlose int not null,
                            foreign key (username) references accounts(username)

);


insert into accounts values
(null, 'Vasily', '123', 1000),
(null, 'Tatyana', '1234', 1000),
(null, 'Igor', '12345', 3000),
(null, 'Oleg', '666', 0);

select * from accounts;
select * from gametours;
select * from gametours where username='Igor';

select username,gamename,count(winlose) as  games,SUM(winlose) as totalwin
from gametours
 where  gamename='OB'
 group by username
 order by totalwin desc
 LIMIT 3;

 select username,gamename,count(winlose) as  games,SUM(winlose) as totalwin
from gametours
 group by username
 order by totalwin desc
 LIMIT 3;

 -- drop table gametours;

-- Вставка в gametours для проверки статистики
insert into gametours values
(null,'OB','Vasily',default, 60),
(null,'OB','Vasily',default, -1),
(null,'Ruletka','Vasily',default, 600),
(null,'BlackJack','Vasily',default, 100),
(null,'OB','Tatyana',default, 40),
(null,'OB','Tatyana',default, -1),
(null,'Ruletka','Tatyana',default, 300),
(null,'BlackJack','Tatyana',default, -50),
(null,'OB','Igor',default, 10),
(null,'OB','Igor',default, -1),
(null,'Ruletka','Igor',default, 100),
(null,'BlackJack','Igor',default, 50),
(null,'OB','Oleg',default, 2),
(null,'OB','Oleg',default, -1),
(null,'Ruletka','Oleg',default, 50),
(null,'BlackJack','Oleg',default, 200);


