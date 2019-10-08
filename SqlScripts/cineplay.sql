--CREATE DATABASE projecttwo;

--CREATE SCHEMA cineplay;

CREATE TABLE cineplay.users (
user_id SERIAL PRIMARY KEY NOT NULL,
username VARCHAR(30) UNIQUE NOT NULL,
usrpwd VARCHAR(20) NOT NULL,
email VARCHAR(30) NOT NULL,
first_name VARCHAR(30) NOT NULL,
last_name VARCHAR(30) NOT NULL
);

CREATE TABLE cineplay.movies (
movie_id SERIAL PRIMARY KEY NOT NULL,
title VARCHAR(100),
director VARCHAR(60),
actors VARCHAR (60)
);

CREATE TABLE cineplay.blog (
blog_id SERIAL PRIMARY KEY NOT NULL,
writer_id INTEGER REFERENCES cineplay.users(user_id) NOT NULL,
message varchar(1000),
post_time TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT(CURRENT_TIMESTAMP)
);

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