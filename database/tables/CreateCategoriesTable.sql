CREATE TABLE categories
(
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	name varchar(25),
	description varchar(40),
	section_id INT NOT NULL,
	created_date DATETIME DEFAULT CURRENT_TIMESTAMP,
	FOREIGN KEY(section_id) REFERENCES sections(id) ON DELETE CASCADE
);
