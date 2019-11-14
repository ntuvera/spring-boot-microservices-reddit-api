CREATE TABLE users (
    id SERIAL,
    username VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(100),
    email VARCHAR(100) UNIQUE,
    user_profile_id INT,
    user_role_id INT NOT NULL
);
