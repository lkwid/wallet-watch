-- Account
INSERT INTO account (first_name, last_name, email, password, balance) VALUES ('Arnold', 'Borsuk', 'borsuk@onet.pl', 'borsuk1', 1000.00);

-- Roles
INSERT INTO account_roles (acc_id, roles_id) VALUES (1, 0);

-- Operations
INSERT INTO operation (transfer, profit, description, account_id) VALUES (3500, true, 'Wynagrodzenie', 1);
INSERT INTO operation (transfer, profit, description, account_id) VALUES (150, false, 'Zakupy spożywcze', 1);
INSERT INTO operation (transfer, profit, description, account_id) VALUES (99, false, 'Buty', 1);
INSERT INTO operation (transfer, profit, description, account_id) VALUES (49, false, 'The Forest', 1);