create table if not exists users
(
  id              bigint auto_increment primary key ,
  username        varchar(255) not null,
  fullname        varchar(255) not null,
  email           varchar(255) not null,
  user_password   varchar(255) null,
  age             varchar(255) null,
  designation     varchar(255) not null,
  user_role       varchar(255) not null,
  signup_page     varchar(255) null
) engine = InnoDB;


INSERT IGNORE INTO users (id, username, fullname, email, user_password, age, designation, user_role,signup_page) VALUES (1,'admin@gmail.com', 'admin','admin@gmail.com','$2a$10$xbg09zEvp9jPUM.k1.qHROfVgm.2hLU2hqA6LyfCsZa4KPTNEA00q',null,'HR','ROLE_HR','SIGNUP_COMPLETE');

