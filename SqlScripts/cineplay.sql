--CREATE DATABASE projecttwo;

DROP TABLE users;
CREATE TABLE users (
user_id SERIAL PRIMARY KEY NOT NULL,
username VARCHAR(30) UNIQUE NOT NULL,
usrpwd VARCHAR(20) NOT NULL,
email VARCHAR(30) NOT NULL,
first_name VARCHAR(30) NOT NULL,
last_name VARCHAR(30) NOT NULL
);
SELECT * FROM users;

INSERT INTO users VALUES
	(DEFAULT, 'cineplay', 'admin', 'admin@cineplay.com', 'Cineplay', 'Admin'),
	(DEFAULT, 'kmlee0206', 'admin', 'kmlee0206@cineplay.com', 'Kyungmin', 'Lee'),
	(DEFAULT, 'justiny', 'admin', 'justiny@cineplay.com', 'Justin', 'Yang'),
	(DEFAULT, 'alect23', 'jumpman23', 'djaether@cineplay.com', 'Alec', 'Thavychith');

DROP TABLE movies;
CREATE TABLE movies (
movie_id SERIAL PRIMARY KEY NOT NULL,
title VARCHAR(200),
director VARCHAR(1000),
plot VARCHAR(1000),
poster VARCHAR(1000),
released_date VARCHAR(50)
);
SELECT * FROM movies;

INSERT INTO movies VALUES
	(DEFAULT, 'director', 'plot', 'https://m.media-amazon.com/images/M/MV5BMzM1YzkzMTMtMGEzOC00MjRmLTk3NjgtYTM2MmIzYWYzZmI3XkEyXkFqcGdeQXVyNjEwNjY3NzU@._V1_SX300.jpg', '18 Oct 2019', 'title', 5),
	(DEFAULT, 'director2', 'plot2', 'https://m.media-amazon.com/images/M/MV5BMzM1YzkzMTMtMGEzOC00MjRmLTk3NjgtYTM2MmIzYWYzZmI3XkEyXkFqcGdeQXVyNjEwNjY3NzU@._V1_SX300.jpg', '18 Oct 2019', 'title2', 5),
	(DEFAULT, 'director3', 'plot3', 'https://m.media-amazon.com/images/M/MV5BMzM1YzkzMTMtMGEzOC00MjRmLTk3NjgtYTM2MmIzYWYzZmI3XkEyXkFqcGdeQXVyNjEwNjY3NzU@._V1_SX300.jpg', '18 Oct 2019', 'title3', 5),
	(DEFAULT, 'director4', 'plot4', 'https://m.media-amazon.com/images/M/MV5BMzM1YzkzMTMtMGEzOC00MjRmLTk3NjgtYTM2MmIzYWYzZmI3XkEyXkFqcGdeQXVyNjEwNjY3NzU@._V1_SX300.jpg', '18 Oct 2019', 'title4', 5);

DELETE FROM movies
WHERE movie_id = 4;

INSERT INTO movies(watchlist_id)
WHERE movie_id = 1;


DROP TABLE forum;
CREATE TABLE forum (
forum_id SERIAL PRIMARY KEY NOT NULL,
writer_id INTEGER REFERENCES users(user_id) NOT NULL,
message VARCHAR(1000)
);
SELECT * FROM forum;

DELETE FROM forum
WHERE user_id IS NULL;

INSERT INTO forum VALUES
	(DEFAULT, 1, 'OUR FIRST MESSAGE');

DROP TABLE watchlist;
CREATE TABLE watchlist (
watchlist_id SERIAL PRIMARY KEY NOT NULL,
watchlist_name VARCHAR(200),
owner_id INTEGER REFERENCES users(user_id) NOT NULL,
movie INTEGER REFERENCES movies(movie_id) NOT NULL
);
SELECT * FROM watchlist;

INSERT INTO watchlist VALUES
	(DEFAULT, 'TESTLIST', 5);

--CREATE TABLE project_2.forum(
--id serial,
--user_id FOREIGN KEY forum_users_f_key REFERENCES users(id),
--message varchar(500),
--dateposted timestamp WITH timezone NOT NULL DEFAULT(current_timestamp)
--);

--CREATE TABLE project_2.usermovielist(
--id serial,
--user_id FOREIGN KEY movie_users_f_key REFERENCES users(id),
--movies varchar(60) unique
);