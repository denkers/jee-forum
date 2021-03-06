CREATE TABLE posts
(
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	posted_by VARCHAR(16) NOT NULL,
	thread_id INT NOT NULL,
	posted_date DATETIME DEFAULT CURRENT_TIMESTAMP,
	content VARCHAR(1500) NOT NULL,
	FOREIGN KEY (posted_by) REFERENCES users (username) ON DELETE CASCADE,
	FOREIGN KEY (thread_id) REFERENCES threads (id) ON DELETE CASCADE
);
