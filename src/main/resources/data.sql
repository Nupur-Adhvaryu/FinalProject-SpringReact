INSERT INTO User(email, encrypted_Password, enabled) VALUES 
('simon.hood@sheridancollege.ca', '$2a$10$1ltibqiyyBJMJQ4hqM7f0OusP6np/IHshkYc4TjedwHnwwNChQZCy', true), 
('jollymood@happymood.ca', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', true);

INSERT INTO Role(rolename) VALUES 
('ROLE_USER'), 
('ROLE_GUEST');

INSERT INTO User_Role_List VALUES
(1, 1),
(1, 2),
(2, 2);