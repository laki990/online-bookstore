CREATE DATABASE user_service;

CREATE USER laki WITH PASSWORD 'pass';

GRANT ALL PRIVILEGES ON DATABASE user_service TO laki;

\connect user_service;

GRANT USAGE ON SCHEMA public TO laki;
GRANT CREATE ON SCHEMA public TO laki;

