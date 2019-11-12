CREATE TABLE users (
    id SERIAL,
    username VARCHAR(100) NOT NULL,
    password VARCHAR(100),
    email VARCHAR(100),
    user_profile_id INT,
    user_role_id INT NOT NULL
);
