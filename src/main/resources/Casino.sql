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
                            winlose varchar(50) not null,
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



-- drop table gametours;