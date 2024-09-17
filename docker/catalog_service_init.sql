CREATE DATABASE catalog_service;

CREATE USER catalog_user WITH PASSWORD 'pass';

GRANT ALL PRIVILEGES ON DATABASE catalog_service TO catalog_user;

\connect catalog_service;

GRANT USAGE ON SCHEMA public TO catalog_user;
GRANT CREATE ON SCHEMA public TO catalog_user;

