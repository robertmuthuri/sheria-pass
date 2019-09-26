SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS parties (
 id int PRIMARY KEY auto_increment,
 name VARCHAR,
 type VARCHAR
 );

 CREATE TABLE IF NOT EXISTS judges (
 id int PRIMARY KEY auto_increment,
 name VARCHAR,
 );

