CREATE SCHEMA IF NOT EXISTS accounts;

DROP TABLE IF EXISTS roles, login_users, login_users_roles, privilege, roles_privileges;

CREATE TABLE roles (
  role_id serial PRIMARY KEY,
  name varchar(15) NOT NULL
);

CREATE TABLE login_users (
  user_id serial PRIMARY KEY,
  active boolean NOT NULL,
  expired boolean NOT NULL,
  locked boolean NOT NULL,
  userName varchar(25) NOT NULL,
  password varchar(15) NOT NULL
);

CREATE TABLE login_users_roles (
  user_id serial NOT NULL,
  role_id serial NOT NULL,
  PRIMARY KEY (user_id,role_id),
  CONSTRAINT user_role_user_id_fk FOREIGN KEY (user_id) REFERENCES login_users(user_id) ON DELETE CASCADE ON UPDATE CASCADE,CONSTRAINT user_role_role_id_fk FOREIGN KEY (role_id) REFERENCES roles(role_id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE privilege (
  privilege_id serial PRIMARY KEY,
  name varchar(255) NOT NULL
);

CREATE TABLE roles_privileges (
  privilege_id serial NOT NULL,
  role_id serial NOT NULL,
  PRIMARY KEY (privilege_id,role_id),
  CONSTRAINT privilege_role_id_fk FOREIGN KEY (privilege_id) REFERENCES privilege(privilege_id) ON DELETE CASCADE ON UPDATE CASCADE,CONSTRAINT role_privilege_id_fk FOREIGN KEY (role_id) REFERENCES roles(role_id) ON DELETE CASCADE ON UPDATE CASCADE
);