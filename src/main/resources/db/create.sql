SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS parties (
 id int PRIMARY KEY auto_increment,
 name VARCHAR,
 type VARCHAR
 );
