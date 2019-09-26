SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS laws (
id int PRIMARY KEY auto_increment,
law_citation VARCHAR,
law_summary VARCHAR,
law_url VARCHAR
);

CREATE TABLE IF NOT EXISTS caselaws (
 id int PRIMARY KEY auto_increment,
 case_citation VARCHAR,
 case_summary VARCHAR,
 case_court VARCHAR,
 case_holding VARCHAR,
 case_url VARCHAR
 );

CREATE TABLE IF NOT EXISTS parties (
 id int PRIMARY KEY auto_increment,
 name VARCHAR,
 type VARCHAR
 );

 CREATE TABLE IF NOT EXISTS judges (
 id int PRIMARY KEY auto_increment,
 judge_name VARCHAR,
 judge_rank VARCHAR
 );

CREATE TABLE IF NOT EXISTS caselaws_parties (
 id int PRIMARY KEY auto_increment,
 case_id VARCHAR,
 party_id VARCHAR
 );
CREATE TABLE IF NOT EXISTS caselaws_judges (
 id int PRIMARY KEY auto_increment,
 case_id VARCHAR,
 judge_id VARCHAR
 );