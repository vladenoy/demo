INSERT INTO public.users
(date_of_birth, "name", "password")
VALUES('2020-01-01', 'one', 'testtest');
INSERT INTO public.users
(date_of_birth, "name", "password")
VALUES('2020-01-01', 'two', 'testtest');
INSERT INTO public.users
(date_of_birth, "name", "password")
VALUES('2020-01-01', 'three', 'testtest');
INSERT INTO public.users
(date_of_birth, "name", "password")
VALUES('2020-01-01', 'four', 'testtest');
INSERT INTO public.users
(date_of_birth, "name", "password")
VALUES('2020-01-01', 'five', 'testtest');




INSERT INTO public.account
(balance, user_id)
VALUES(2.00, (select id from users where name = 'one'));
INSERT INTO public.account
(balance, user_id)
VALUES(2.00, (select id from users where name = 'two'));
INSERT INTO public.account
(balance, user_id)
VALUES(2.00, (select id from users where name = 'three'));
INSERT INTO public.account
(balance, user_id)
VALUES(2.00, (select id from users where name = 'four'));
INSERT INTO public.account
(balance, user_id)
VALUES(2.00, (select id from users where name = 'five'));



INSERT INTO public.email_data
(email, user_id)
VALUES('test3@test.com', (select id from users where name = 'one'));
INSERT INTO public.email_data
(email, user_id)
VALUES('test33@test.com', (select id from users where name = 'two'));
INSERT INTO public.email_data
(email, user_id)
VALUES('test34@test.com', (select id from users where name = 'three'));
INSERT INTO public.email_data
(email, user_id)
VALUES('test344@test.com', (select id from users where name = 'four'));
INSERT INTO public.email_data
(email, user_id)
VALUES('test3445@test.com', (select id from users where name = 'five'));



INSERT INTO public.phone_data
(phone, user_id)
VALUES('12347634843', (select id from users where name = 'one'));
INSERT INTO public.phone_data
(phone, user_id)
VALUES('12347634343', (select id from users where name = 'two'));
INSERT INTO public.phone_data
(phone, user_id)
VALUES('12347638343', (select id from users where name = 'three'));
INSERT INTO public.phone_data
(phone, user_id)
VALUES('123476738343', (select id from users where name = 'four'));
INSERT INTO public.phone_data
(phone, user_id)
VALUES('123475538343', (select id from users where name = 'five'));