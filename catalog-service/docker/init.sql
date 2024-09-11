CREATE DATABASE IF NOT EXISTS catalog_service;
CREATE USER catalog_user WITH PASSWORD 'pass';
GRANT ALL PRIVILEGES ON DATABASE catalog_service TO catalog_user;