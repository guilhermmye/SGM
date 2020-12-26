
CREATE TABLE teste.regiao (
                regiao_id INTEGER NOT NULL,
                nome VARCHAR NOT NULL,
                sigla VARCHAR NOT NULL,
                CONSTRAINT regiao_id PRIMARY KEY (regiao_id)
);


CREATE TABLE teste.uf (
                uf_id INTEGER NOT NULL,
                nome VARCHAR NOT NULL,
                sigla VARCHAR NOT NULL,
                regiao_id INTEGER NOT NULL,
                CONSTRAINT uf_id PRIMARY KEY (uf_id)
);


CREATE TABLE teste.regiaoIntermedia (
                regiaoIntermediaria_id INTEGER NOT NULL,
                nome VARCHAR NOT NULL,
                uf_id INTEGER NOT NULL,
                CONSTRAINT regiaointermediaria_id PRIMARY KEY (regiaoIntermediaria_id)
);


CREATE TABLE teste.regiaoImediata (
                regiaoImediata_id INTEGER NOT NULL,
                nome VARCHAR NOT NULL,
                regiaoIntermediaria_id INTEGER NOT NULL,
                CONSTRAINT regiaoimediata_id PRIMARY KEY (regiaoImediata_id)
);


CREATE TABLE teste.mesorregiao (
                mesorregiao_id INTEGER NOT NULL,
                nome VARCHAR NOT NULL,
                uf_id INTEGER NOT NULL,
                CONSTRAINT mesorregiao_id PRIMARY KEY (mesorregiao_id)
);


CREATE TABLE teste.microrregiao (
                microrregiao_id INTEGER NOT NULL,
                nome VARCHAR NOT NULL,
                mesorregiao_id INTEGER NOT NULL,
                CONSTRAINT microrregiao_id PRIMARY KEY (microrregiao_id)
);


CREATE TABLE teste.municipio (
                municipio_id INTEGER NOT NULL,
                nome VARCHAR NOT NULL,
                regiaoImediata_id INTEGER NOT NULL,
                microrregiao_id INTEGER NOT NULL,
                CONSTRAINT municipio_id PRIMARY KEY (municipio_id)
);


CREATE TABLE teste.distrito (
                distrito_id INTEGER NOT NULL,
                nome VARCHAR NOT NULL,
                municipio_id INTEGER NOT NULL,
                CONSTRAINT distrito_id PRIMARY KEY (distrito_id)
);


CREATE TABLE teste.subdistrito (
                subdistrito_id INTEGER NOT NULL,
                nome VARCHAR NOT NULL,
                distrito_id INTEGER NOT NULL,
                CONSTRAINT subdistrito_id PRIMARY KEY (subdistrito_id)
);


ALTER TABLE teste.uf ADD CONSTRAINT regiao_uf_fk
FOREIGN KEY (regiao_id)
REFERENCES teste.regiao (regiao_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE teste.mesorregiao ADD CONSTRAINT uf_mesorregiao_fk
FOREIGN KEY (uf_id)
REFERENCES teste.uf (uf_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE teste.regiaoIntermedia ADD CONSTRAINT uf_regiaointermedia_fk
FOREIGN KEY (uf_id)
REFERENCES teste.uf (uf_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE teste.regiaoImediata ADD CONSTRAINT regiaointermedia_regiaoimediata_fk
FOREIGN KEY (regiaoIntermediaria_id)
REFERENCES teste.regiaoIntermedia (regiaoIntermediaria_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE teste.municipio ADD CONSTRAINT regiaoimediata_municipio_fk
FOREIGN KEY (regiaoImediata_id)
REFERENCES teste.regiaoImediata (regiaoImediata_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE teste.microrregiao ADD CONSTRAINT mesorregiao_microrregiao_fk
FOREIGN KEY (mesorregiao_id)
REFERENCES teste.mesorregiao (mesorregiao_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE teste.municipio ADD CONSTRAINT microrregiao_municipio_fk
FOREIGN KEY (microrregiao_id)
REFERENCES teste.microrregiao (microrregiao_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE teste.distrito ADD CONSTRAINT municipio_distrito_fk
FOREIGN KEY (municipio_id)
REFERENCES teste.municipio (municipio_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE teste.subdistrito ADD CONSTRAINT distrito_subdistrito_fk
FOREIGN KEY (distrito_id)
REFERENCES teste.distrito (distrito_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;
