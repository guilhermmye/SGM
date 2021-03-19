
CREATE SEQUENCE roles_roles_id_seq;

CREATE TABLE roles (
                roles_id INTEGER NOT NULL DEFAULT nextval('roles_roles_id_seq'),
                name VARCHAR NOT NULL,
                CONSTRAINT roles_id PRIMARY KEY (roles_id)
);


ALTER SEQUENCE roles_roles_id_seq OWNED BY roles.roles_id;

CREATE SEQUENCE usuario_usuario_id_seq;

CREATE TABLE usuario (
                usuario_id INTEGER NOT NULL DEFAULT nextval('usuario_usuario_id_seq'),
                email VARCHAR NOT NULL,
                password VARCHAR NOT NULL,
                username VARCHAR NOT NULL,
                CONSTRAINT usuario_id PRIMARY KEY (usuario_id)
);


ALTER SEQUENCE usuario_usuario_id_seq OWNED BY usuario.usuario_id;

CREATE TABLE usuario_roles (
                usuario_id INTEGER NOT NULL,
                roles_id INTEGER NOT NULL,
                CONSTRAINT usuario_roles_id PRIMARY KEY (usuario_id, roles_id)
);


ALTER TABLE usuario_roles ADD CONSTRAINT roles_usuario_roles_fk
FOREIGN KEY (roles_id)
REFERENCES roles (roles_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE usuario_roles ADD CONSTRAINT usuario_usuario_roles_fk
FOREIGN KEY (usuario_id)
REFERENCES usuario (usuario_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;