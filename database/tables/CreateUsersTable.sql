CREATE TABLE users
(
	username VARCHAR(16) NOT NULL PRIMARY KEY,
	password varchar(255) NOT NULL,
	email varchar(100) NOT NULL,
	picture varchar(255),
	acc_type SMALLINT DEFAULT 0
);
