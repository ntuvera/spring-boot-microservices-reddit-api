CREATE TABLE comments (
    id SERIAL,
    text VARCHAR(250) NOT NULL,
    post_id INT NOT NULL,
    user_id INT NOT NULL
);
