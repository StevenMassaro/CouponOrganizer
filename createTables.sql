create database coupons;

create schema coupons;
drop table if exists coupons.coupons;
create table coupons.coupons (
	id serial not null,
	"store" varchar(200) not null ,
	deal varchar(400) not null,
	"comment" varchar(1000) null,
	expirationDate date null,
	dateCreated timestamp not null,
	dateDeleted timestamp null
);

drop table if exists coupons.file;
create table coupons.file (
	id int not null,
	type varchar(1000) null,
	size int not null,
	filename varchar(1000) not null,
	file bytea not null
);