DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS posts;
DROP TABLE IF EXISTS comments;
DROP TABLE IF EXISTS user_profiles;
DROP TABLE IF EXISTS user_roles;

CREATE TABLE users (
    id SERIAL,
    username VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(100),
    email VARCHAR(100) UNIQUE,
    user_profile_id INT,
    user_role_id INT NOT NULL
);
