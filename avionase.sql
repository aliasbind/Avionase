create table users
(
    user_id     integer,
    username    varchar(64),
    password    varchar(64),
    constraint  pk_user_id  PRIMARY KEY(user_id)
);

insert into users values (0, 'admin',                     '12345');
insert into users values (1, 'newguy',                    'stars');
insert into users values (2, 'X_HaRdCoRe_X_guy_4you_XXL', 'parola');
insert into users values (3, 'ub3rgeek',                  '23SD3-ASDK2-asNDiu123');

CREATE USER guest WITH ENCRYPTED PASSWORD 'guest';
GRANT SELECT ON TABLE users TO guest;

