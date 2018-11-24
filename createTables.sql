create database coupons;
CREATE SCHEMA coupons;
DROP TABLE IF EXISTS coupons.coupons;
CREATE TABLE coupons.coupons (
	id serial NOT NULL,
	"store" varchar(200) NOT NULL ,
	deal varchar(400) NOT NULL,
	"comment" varchar(1000) NULL,
	expirationDate date NULL,
	dateDeleted date NULL
);

drop table if exists coupons.file;
create table coupons.file (
	id int not null,
	file bytea not null,
	extension varchar(50) not null
)