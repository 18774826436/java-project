create schema db_database01;
use db_database01;

--create table tb_user(
--id int(11) not null auto_increment primary key,
--username varchar(15) not null,
--password varchar(20) not null,
--email varchar(45) not null);
--insert into tb_user values(1,"xiaoma","xiaoma","xiaoma419@gmail.com");

create table tb_date(
id int(11) not null auto_increment primary key,
year varchar(10) not null,
mouth varchar(3) not null,
day varchar(45) not null);
insert into tb_date values(1,"2017","9","10");