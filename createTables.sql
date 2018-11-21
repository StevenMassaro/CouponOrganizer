create database coupons;
CREATE SCHEMA coupons;
DROP TABLE IF EXISTS coupons.coupons;
CREATE TABLE coupons.coupons (
	id serial NOT NULL,
	"store" varchar(200) NOT NULL ,
	deal varchar(400) NOT NULL,
	"comment" varchar(1000) NULL,
	expirationDate date NULL,
	file bytea NULL
);