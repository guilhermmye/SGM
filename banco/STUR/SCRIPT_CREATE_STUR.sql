
CREATE SEQUENCE stur_stur_id_seq;

CREATE TABLE stur (
                stur_id INTEGER NOT NULL DEFAULT nextval('stur_stur_id_seq'),
                inscricao VARCHAR NOT NULL,
                tipoImposto VARCHAR NOT NULL,
                parcela NUMERIC NOT NULL,
                valorTotal NUMERIC NOT NULL,
                cpfCnpj VARCHAR(15) NOT NULL,
                endereco VARCHAR(250) NOT NULL,
                cep VARCHAR(10),
                municipio VARCHAR NOT NULL,
                uf VARCHAR NOT NULL,
                numero VARCHAR(10) NOT NULL,
                CONSTRAINT stur_id PRIMARY KEY (stur_id)
);


ALTER SEQUENCE stur_stur_id_seq OWNED BY stur.stur_id;