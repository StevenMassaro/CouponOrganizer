CREATE SCHEMA coupons;
DROP TABLE IF EXISTS coupons.newtable;
CREATE TABLE coupons.newtable (
	id serial NOT NULL,
	"store" varchar(200) NOT NULL ,
	deal varchar(400) NOT NULL,
	"comment" varchar(1000) NULL,
	file bytea NULL
);