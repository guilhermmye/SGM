
CREATE TABLE regiao (
                regiao_id INTEGER NOT NULL,
                nome VARCHAR NOT NULL,
                sigla VARCHAR NOT NULL,
                CONSTRAINT regiao_id PRIMARY KEY (regiao_id)
);


CREATE TABLE uf (
                uf_id INTEGER NOT NULL,
                nome VARCHAR NOT NULL,
                sigla VARCHAR NOT NULL,
                regiao_id INTEGER NOT NULL,
                CONSTRAINT uf_id PRIMARY KEY (uf_id)
);


CREATE TABLE regiaoIntermedia (
                regiaoIntermediaria_id INTEGER NOT NULL,
                nome VARCHAR NOT NULL,
                uf_id INTEGER NOT NULL,
                CONSTRAINT regiaointermediaria_id PRIMARY KEY (regiaoIntermediaria_id)
);


CREATE TABLE regiaoImediata (
                regiaoImediata_id INTEGER NOT NULL,
                nome VARCHAR NOT NULL,
                regiaoIntermediaria_id INTEGER NOT NULL,
                CONSTRAINT regiaoimediata_id PRIMARY KEY (regiaoImediata_id)
);


CREATE TABLE mesorregiao (
                mesorregiao_id INTEGER NOT NULL,
                nome VARCHAR NOT NULL,
                uf_id INTEGER NOT NULL,
                CONSTRAINT mesorregiao_id PRIMARY KEY (mesorregiao_id)
);


CREATE TABLE microrregiao (
                microrregiao_id INTEGER NOT NULL,
                nome VARCHAR NOT NULL,
                mesorregiao_id INTEGER NOT NULL,
                CONSTRAINT microrregiao_id PRIMARY KEY (microrregiao_id)
);


CREATE TABLE municipio (
                municipio_id INTEGER NOT NULL,
                nome VARCHAR NOT NULL,
                regiaoImediata_id INTEGER NOT NULL,
                microrregiao_id INTEGER NOT NULL,
                CONSTRAINT municipio_id PRIMARY KEY (municipio_id)
);


CREATE TABLE distrito (
                distrito_id INTEGER NOT NULL,
                nome VARCHAR NOT NULL,
                municipio_id INTEGER NOT NULL,
                CONSTRAINT distrito_id PRIMARY KEY (distrito_id)
);


CREATE TABLE subdistrito (
                subdistrito_id INTEGER NOT NULL,
                nome VARCHAR NOT NULL,
                distrito_id INTEGER NOT NULL,
                CONSTRAINT subdistrito_id PRIMARY KEY (subdistrito_id)
);


ALTER TABLE uf ADD CONSTRAINT regiao_uf_fk
FOREIGN KEY (regiao_id)
REFERENCES regiao (regiao_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE mesorregiao ADD CONSTRAINT uf_mesorregiao_fk
FOREIGN KEY (uf_id)
REFERENCES uf (uf_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE regiaoIntermedia ADD CONSTRAINT uf_regiaointermedia_fk
FOREIGN KEY (uf_id)
REFERENCES uf (uf_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE regiaoImediata ADD CONSTRAINT regiaointermedia_regiaoimediata_fk
FOREIGN KEY (regiaoIntermediaria_id)
REFERENCES regiaoIntermedia (regiaoIntermediaria_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE municipio ADD CONSTRAINT regiaoimediata_municipio_fk
FOREIGN KEY (regiaoImediata_id)
REFERENCES regiaoImediata (regiaoImediata_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE microrregiao ADD CONSTRAINT mesorregiao_microrregiao_fk
FOREIGN KEY (mesorregiao_id)
REFERENCES mesorregiao (mesorregiao_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE municipio ADD CONSTRAINT microrregiao_municipio_fk
FOREIGN KEY (microrregiao_id)
REFERENCES microrregiao (microrregiao_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE distrito ADD CONSTRAINT municipio_distrito_fk
FOREIGN KEY (municipio_id)
REFERENCES municipio (municipio_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE subdistrito ADD CONSTRAINT distrito_subdistrito_fk
FOREIGN KEY (distrito_id)
REFERENCES distrito (distrito_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;
