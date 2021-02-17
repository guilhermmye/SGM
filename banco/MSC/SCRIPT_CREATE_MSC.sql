
CREATE SEQUENCE sexo_sexo_id_seq;

CREATE TABLE sexo (
                sexo_id INTEGER NOT NULL DEFAULT nextval('sexo_sexo_id_seq'),
                descricao VARCHAR NOT NULL,
                CONSTRAINT sexo_id PRIMARY KEY (sexo_id)
);


ALTER SEQUENCE sexo_sexo_id_seq OWNED BY sexo.sexo_id;

CREATE SEQUENCE tipopessoa_tipopessoa_id_seq;

CREATE TABLE tipoPessoa (
                tipoPessoa_id INTEGER NOT NULL DEFAULT nextval('tipopessoa_tipopessoa_id_seq'),
                descricao VARCHAR NOT NULL,
                CONSTRAINT tipopessoa_id PRIMARY KEY (tipoPessoa_id)
);


ALTER SEQUENCE tipopessoa_tipopessoa_id_seq OWNED BY tipoPessoa.tipoPessoa_id;

CREATE SEQUENCE pessoa_pessoa_id_seq;

CREATE TABLE pessoa (
                pessoa_id INTEGER NOT NULL DEFAULT nextval('pessoa_pessoa_id_seq'),
                sexo_id INTEGER NOT NULL,
                tipoPessoa_id INTEGER NOT NULL,
                nome VARCHAR NOT NULL,
                cpfCnpj VARCHAR(15) NOT NULL,
                email VARCHAR(50) NOT NULL,
                telefone VARCHAR(12),
                dataNascimento DATE NOT NULL,
                endereco VARCHAR(250) NOT NULL,
				numero VARCHAR(10) NOT NULL,
                cep VARCHAR(10),
                municipio_id INTEGER NOT NULL,
                CONSTRAINT pessoa_id PRIMARY KEY (pessoa_id)
);


ALTER SEQUENCE pessoa_pessoa_id_seq OWNED BY pessoa.pessoa_id;

ALTER TABLE pessoa ADD CONSTRAINT sexo_pessoa_fk
FOREIGN KEY (sexo_id)
REFERENCES sexo (sexo_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE pessoa ADD CONSTRAINT tipopessoa_pessoa_fk
FOREIGN KEY (tipoPessoa_id)
REFERENCES tipoPessoa (tipoPessoa_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;
