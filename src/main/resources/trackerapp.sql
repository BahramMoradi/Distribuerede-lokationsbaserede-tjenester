-- Database: trackerapp
/*
***NOTICE ...! do not try to run all SQL commands in this together her comes the instructions ***
1) You need to create a postgreSQL database . You can do this by running a create database script or from pgAdmin III 
2) create a schema in the created database. Run create schema SQL command in this file or use pgAdmin III
3) Run create tables SQL command in this file.
4) create a superuser role and set this user as owner of database. easier done in pgAdmin III

after creating the database and tables, you need to  modify config.properties file.
change the USERNAME and PASSWORD to  user name and password of the created user for database. 
put the config.properties file in users home directory
*/



-- (1)
DROP DATABASE IF EXISTS trackerapp;
CREATE DATABASE trackerapp
  WITH OWNER = admin
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'Danish_Denmark.1252'
       LC_CTYPE = 'Danish_Denmark.1252'
       CONNECTION LIMIT = -1;

ALTER DATABASE trackerapp
  SET search_path = "$user", public, tiger;
  
  

-- (2)
DROP SCHEMA IF EXISTS tracker;
CREATE SCHEMA tracker
  AUTHORIZATION postgres;
  
-- (3)
-- Table: tracker.users
DROP TABLE IF EXISTS tracker.users;
CREATE TABLE tracker.users
(
  uid serial NOT NULL,
  phone bigint NOT NULL,
  name text NOT NULL,
  description text,
  mail text NOT NULL,
  CONSTRAINT users_pkey PRIMARY KEY (uid),
  CONSTRAINT users_mail_key UNIQUE (mail)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE tracker.users
  OWNER TO postgres;
  

 -- Table: tracker.times
 DROP TABLE IF EXISTS  tracker.times;
CREATE TABLE tracker.times
(
  tid bigint NOT NULL DEFAULT nextval('tracker.time_tid_seq'::regclass),
  "time" bigint,
  CONSTRAINT time_pkey PRIMARY KEY (tid),
  CONSTRAINT time_time_key UNIQUE ("time")
)
WITH (
  OIDS=FALSE
);
ALTER TABLE tracker.times
  OWNER TO postgres;
 
 
 
-- Table: tracker.locations

DROP TABLE IF EXISTS tracker.locations;
CREATE TABLE tracker.locations
(
  lid bigserial NOT NULL, -- lid = location id
  longitude double precision NOT NULL,
  latitude double precision NOT NULL,
  CONSTRAINT locations_pkey PRIMARY KEY (lid),
  CONSTRAINT locations_longitude_latitude_key UNIQUE (longitude, latitude)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE tracker.locations
  OWNER TO postgres;
COMMENT ON COLUMN tracker.locations.lid IS 'lid = location id ';

-- Table: tracker.user_time_dependency

DROP TABLE IF EXISTS tracker.user_time_dependency;
CREATE TABLE tracker.user_time_dependency
(
  uid bigint NOT NULL,
  tid bigint NOT NULL,
  CONSTRAINT user_time_dependency_pkey PRIMARY KEY (uid, tid),
  CONSTRAINT user_time_dependency_tid_fkey FOREIGN KEY (tid)
      REFERENCES tracker.times (tid) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT user_time_dependency_uid_fkey FOREIGN KEY (uid)
      REFERENCES tracker.users (uid) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE
)
WITH (
  OIDS=FALSE
);
ALTER TABLE tracker.user_time_dependency
  OWNER TO postgres;
  
-- Table: tracker.time_location_dependency
DROP TABLE IF EXISTS tracker.time_location_dependency;
CREATE TABLE tracker.time_location_dependency
(
  tid bigint NOT NULL, -- tid comes from time table
  lid bigint NOT NULL, -- lid = location id comes from locations table
  CONSTRAINT time_location_dependency_pkey PRIMARY KEY (tid, lid),
  CONSTRAINT time_location_dependency_lid_fkey FOREIGN KEY (lid)
      REFERENCES tracker.locations (lid) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT time_location_dependency_tid_fkey FOREIGN KEY (tid)
      REFERENCES tracker.times (tid) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE
)
WITH (
  OIDS=FALSE
);
ALTER TABLE tracker.time_location_dependency
  OWNER TO admin;
COMMENT ON COLUMN tracker.time_location_dependency.tid IS 'tid comes from time table';
COMMENT ON COLUMN tracker.time_location_dependency.lid IS 'lid = location id comes from locations table';




 

  

