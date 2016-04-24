CREATE TABLE threads
(
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	created_by VARCHAR(16) NOT NULL,
	category_id INT NOT NULL,
	date_created DATETIME DEFAULT CURRENT_TIMESTAMP,
	title varchar(50) NOT NULL,
	content varchar(1500) NOT NULL,
	FOREIGN KEY (created_by) REFERENCES users (username) ON DELETE CASCADE,
	FOREIGN KEY (category_id) REFERENCES categories (id) ON DELETE CASCADE
);

