create database db_products default charset utf8mb4;

create table products(pid int not null primary key auto_increment,pname varchar(200),type varchar(50),price double,create_time timestamp );