INSERT INTO users
(username, password, email)
VALUES ('batman', 'bat', 'iam@batman.com'),
('robin', 'bird', 'not@batman.com');

INSERT INTO posts
(title, description, user_id)
VALUES ('I am Batman', 'duh',1),
('To the batmobile', 'hey robin', 1),
('Gosh Batman', 'robin says', 2);

INSERT INTO comments
(text, post_id, user_id)
VALUES ('BANG', 1,1),
('ZONK', 1, 1),
('POW', 2, 2);
