CREATE DATABASE order_service;

CREATE USER order_user WITH PASSWORD 'pass';

GRANT ALL PRIVILEGES ON DATABASE order_service TO order_user;

\connect order_service;

GRANT USAGE ON SCHEMA public TO order_user;
GRANT CREATE ON SCHEMA public TO order_user;

