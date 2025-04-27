-------seq------

CREATE SEQUENCE IF NOT EXISTS user_seq start 1 increment 1;
CREATE SEQUENCE IF NOT EXISTS phone_data_seq start 1 increment 1;
CREATE SEQUENCE IF NOT EXISTS email_data_seq start 1 increment 1;
CREATE SEQUENCE IF NOT EXISTS account_seq start 1 increment 1;

---------users--------

CREATE TABLE IF NOT EXISTS public.users (
	id             BIGINT PRIMARY KEY DEFAULT nextval('user_seq'),
	date_of_birth  date NULL,
	"name"         varchar(500) NOT NULL,
	"password"     varchar(500) NOT NULL,
	CONSTRAINT uk3g1j96g94xpk3lpxl2qbl985x UNIQUE (name)
);

--------phone---------

CREATE TABLE IF NOT EXISTS public.phone_data (
	id      BIGINT      PRIMARY KEY DEFAULT nextval('phone_data_seq'),
	phone   varchar(13) NOT NULL,
	user_id BIGINT      NOT NULL,
	CONSTRAINT ukosdf5rk1hhd70y73s7aa2wafo UNIQUE (phone)
);

-- public.phone_data внешние включи

ALTER TABLE IF EXISTS public.phone_data
ADD CONSTRAINT fkddj6vjnjncixxkhhjee7dyc2h FOREIGN KEY (user_id)
REFERENCES public.users(id);



--------email---------

CREATE TABLE IF NOT EXISTS public.email_data (
	id        BIGINT         PRIMARY KEY DEFAULT nextval('email_data_seq'),
	email     varchar(200)   NOT NULL,
	user_id   BIGINT         NOT NULL,
	CONSTRAINT uknwt9u3p3dlhfekcxxh96j8x8o UNIQUE (email)
);


-- public.email_data внешние включи

ALTER TABLE IF EXISTS public.email_data
ADD CONSTRAINT fk62aitajoe5fk8s6a7b0830qjd FOREIGN KEY (user_id)
REFERENCES public.users(id);



--------account-------

CREATE TABLE IF NOT EXISTS public.account (
	id          BIGINT PRIMARY KEY DEFAULT nextval('account_seq'),
	balance     decimal(38, 2) NOT NULL,
	user_id     BIGINT NOT NULL,
	initial_balance numeric(38, 2) NOT NULL,
	CONSTRAINT ukh6dr47em6vg85yuwt4e2roca4 UNIQUE (user_id)
);


-- public.account внешние включи

ALTER TABLE IF EXISTS public.account
ADD CONSTRAINT fkra7xoi9wtlcq07tmoxxe5jrh4 FOREIGN KEY (user_id)
REFERENCES public.users(id);