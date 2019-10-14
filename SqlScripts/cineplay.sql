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
	(DEFAULT, 'admin', 'password', 'admin@cineplay.com', 'Alec', 'Yang');

DROP TABLE movies;
CREATE TABLE movies (
movie_id SERIAL PRIMARY KEY NOT NULL,
title VARCHAR(100),
director VARCHAR(60),
actor VARCHAR(60),
released_date VARCHAR(50)
);
SELECT * FROM movies;

DROP TABLE forum;
CREATE TABLE forum (
forum_id SERIAL PRIMARY KEY NOT NULL,
writer_id INTEGER REFERENCES users(user_id) NOT NULL,
message VARCHAR(1000),
post_time TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT(CURRENT_TIMESTAMP)
);
SELECT * FROM forum;

DROP TABLE watchlist;
CREATE TABLE watchlist (
watchlist_id SERIAL PRIMARY KEY NOT NULL,
watchlist_name VARCHAR(200),
owner_id INTEGER REFERENCES users(user_id) NOT NULL,
--movie INTEGER REFERENCES movies(movie_id) NOT NULL
);
SELECT * FROM watchlist;

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