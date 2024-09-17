CREATE DATABASE inventory_service;

CREATE USER inventory_user WITH PASSWORD 'pass';

GRANT ALL PRIVILEGES ON DATABASE inventory_service TO inventory_user;

\connect inventory_service;

GRANT USAGE ON SCHEMA public TO inventory_user;
GRANT CREATE ON SCHEMA public TO inventory_user;

