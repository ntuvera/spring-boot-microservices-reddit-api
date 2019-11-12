CREATE TABLE comments (
    id SERIAL,
    text VARCHAR(MAX) NOT NULL,
    post_id INT NOT NULL,
    user_id INT NOT NULL
);
