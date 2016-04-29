SET search_path TO trackerapp;
--CREATE DATABASE trackerapp;
-- Enable PostGIS (includes raster)
CREATE EXTENSION postgis;
-- Enable Topology
CREATE EXTENSION postgis_topology;
-- Enable PostGIS Advanced 3D 
-- and other geoprocessing algorithms
CREATE EXTENSION postgis_sfcgal;
-- fuzzy matching needed for Tiger
CREATE EXTENSION fuzzystrmatch;
-- rule based standardizer
CREATE EXTENSION address_standardizer;
-- example rule data set
CREATE EXTENSION address_standardizer_data_us;
-- Enable US Tiger Geocoder
CREATE EXTENSION postgis_tiger_geocoder;
-- routing functionality
CREATE EXTENSION pgrouting;
-- spatial foreign data wrappers
CREATE EXTENSION ogr_fdw;
-- LIDAR support
CREATE EXTENSION pointcloud;
-- LIDAR Point cloud patches to geometry type cases
CREATE EXTENSION pointcloud_postgis;

DROP SCHEMA  IF EXISTS tracker CASCADE;
CREATE SCHEMA tracker;
SET search_path TO tracker;





CREATE TABLE users(uid serial primary key,phone bigint);
CREATE TABLE last_location(uid serial  references users(uid),
time bigint );
SELECT AddGeometryColumn ('tracker','last_location','geom',4326,'POINT',2);

CREATE TABLE users_tracks
(
  uid serial references users(uid) ,
  trackid character varying(100) NOT NULL,
  description character varying(500),
  PRIMARY KEY (uid, trackid));


CREATE TABLE tracks(trackid character varying(100) NOT NULL references users_tracks(trackid),
time bigint ,
primary key (trackid,time)
);
SELECT AddGeometryColumn ('tracker','tracks','geom',4326,'POINT',2);
 
