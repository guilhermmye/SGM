INSERT INTO roles(name,descricao) VALUES('ROLE_USUARIO','Usuário');
INSERT INTO roles(name,descricao) VALUES('ROLE_TECNICO','Técnico');
INSERT INTO roles(name,descricao) VALUES('ROLE_ADMIN','Administrador');

INSERT INTO public.usuario(email, password, username)
VALUES ('admin@email.com','$2a$10$Z6IVUsSmd6sOb3JRkgmO3ugcG9f/Q29hN41bzY9MCP.OkwClUMe/6','admin');

INSERT INTO public.usuario_roles(usuario_id, roles_id)
VALUES (1,3);