SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;
CREATE DATABASE catalogomultimediale WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'it_IT.UTF-8' LC_CTYPE = 'it_IT.UTF-8';
ALTER DATABASE catalogomultimediale OWNER TO postgres;
\connect catalogomultimediale
SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;
CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;
COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';
SET search_path = public, pg_catalog;
SET default_tablespace = '';
SET default_with_oids = false;
CREATE TABLE amministratore (
    idamministratore integer NOT NULL,
    email character varying(255),
    password character varying(255)
);
ALTER TABLE amministratore OWNER TO postgres;
CREATE TABLE autore (
    idautore integer NOT NULL,
    cognome character varying(255),
    nome character varying(255)
);
ALTER TABLE autore OWNER TO postgres;
CREATE TABLE catalogoutente (
    idcatalogo integer NOT NULL,
    idutente_fk integer
);
ALTER TABLE catalogoutente OWNER TO postgres;
CREATE TABLE catalogoutente_risorsa (
    catalogoutente_idcatalogo integer NOT NULL,
    risorsepossedute_idrisorsa integer NOT NULL
);
ALTER TABLE catalogoutente_risorsa OWNER TO postgres;
CREATE TABLE dataacquisto (
    idacquisto integer NOT NULL,
    dataacquisto timestamp without time zone,
    idcatalogo_fk integer,
    idrisorsa_fk integer
);
ALTER TABLE dataacquisto OWNER TO postgres;
CREATE TABLE datanoleggio (
    idnoleggio integer NOT NULL,
    datanoleggio timestamp without time zone,
    idcatalogo_fk integer,
    idrisorsa_fk integer
);
ALTER TABLE datanoleggio OWNER TO postgres;
CREATE SEQUENCE hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
ALTER TABLE hibernate_sequence OWNER TO postgres;
CREATE TABLE preferenzautente (
    idpreferenza integer NOT NULL,
    idautore_fk integer,
    idtipologia_fk integer,
    idutente_fk integer
);
ALTER TABLE preferenzautente OWNER TO postgres;
CREATE TABLE risorsa (
    idrisorsa integer NOT NULL,
    costoacquisto real,
    costonoleggio real,
    datapubblicazione date,
    filename character varying(255),
    titolo character varying(255),
    idautore_fk integer,
    idtipologia_fk integer
);
ALTER TABLE risorsa OWNER TO postgres;
CREATE TABLE tipologia (
    idtipologia integer NOT NULL,
    descrizione character varying(255)
);
ALTER TABLE tipologia OWNER TO postgres;
CREATE TABLE tipologia_autore (
    tipologia_idtipologia integer NOT NULL,
    listaautori_idautore integer NOT NULL
);
ALTER TABLE tipologia_autore OWNER TO postgres;
CREATE TABLE utente (
    idutente integer NOT NULL,
    cognome character varying(255),
    email character varying(255),
    nome character varying(255),
    password character varying(255),
    idcatalogo_fk integer
);
ALTER TABLE utente OWNER TO postgres;
ALTER TABLE ONLY amministratore
    ADD CONSTRAINT amministratore_pkey PRIMARY KEY (idamministratore);
ALTER TABLE ONLY autore
    ADD CONSTRAINT autore_pkey PRIMARY KEY (idautore);
ALTER TABLE ONLY catalogoutente
    ADD CONSTRAINT catalogoutente_pkey PRIMARY KEY (idcatalogo);
ALTER TABLE ONLY dataacquisto
    ADD CONSTRAINT dataacquisto_pkey PRIMARY KEY (idacquisto);
ALTER TABLE ONLY datanoleggio
    ADD CONSTRAINT datanoleggio_pkey PRIMARY KEY (idnoleggio);
ALTER TABLE ONLY preferenzautente
    ADD CONSTRAINT preferenzautente_pkey PRIMARY KEY (idpreferenza);
ALTER TABLE ONLY risorsa
    ADD CONSTRAINT risorsa_pkey PRIMARY KEY (idrisorsa);
ALTER TABLE ONLY tipologia
    ADD CONSTRAINT tipologia_pkey PRIMARY KEY (idtipologia);
ALTER TABLE ONLY utente
    ADD CONSTRAINT utente_pkey PRIMARY KEY (idutente);
ALTER TABLE ONLY tipologia_autore
    ADD CONSTRAINT fk_1o1vu35ggkjv22hs2huj3c438 FOREIGN KEY (listaautori_idautore) REFERENCES autore(idautore);
ALTER TABLE ONLY datanoleggio
    ADD CONSTRAINT fk_2nh9qsrfc91gnhklacr1cpdy2 FOREIGN KEY (idcatalogo_fk) REFERENCES catalogoutente(idcatalogo);
ALTER TABLE ONLY preferenzautente
    ADD CONSTRAINT fk_3ioqkdj2kofyxh94w4ydno18f FOREIGN KEY (idautore_fk) REFERENCES autore(idautore);
ALTER TABLE ONLY catalogoutente_risorsa
    ADD CONSTRAINT fk_4uck21ontxbj0nvnyue8qiyht FOREIGN KEY (risorsepossedute_idrisorsa) REFERENCES risorsa(idrisorsa);
ALTER TABLE ONLY utente
    ADD CONSTRAINT fk_7o8760ofdordw33e8ny1snofb FOREIGN KEY (idcatalogo_fk) REFERENCES catalogoutente(idcatalogo);
ALTER TABLE ONLY dataacquisto
    ADD CONSTRAINT fk_8dlr42mbkt928lkplo5br6cl3 FOREIGN KEY (idrisorsa_fk) REFERENCES risorsa(idrisorsa);
ALTER TABLE ONLY preferenzautente
    ADD CONSTRAINT fk_9yel3e43icj9t7gr3dxanrre7 FOREIGN KEY (idtipologia_fk) REFERENCES tipologia(idtipologia);
ALTER TABLE ONLY tipologia_autore
    ADD CONSTRAINT fk_bhjct6dvl6wsnfevxed90mln2 FOREIGN KEY (tipologia_idtipologia) REFERENCES tipologia(idtipologia);
ALTER TABLE ONLY datanoleggio
    ADD CONSTRAINT fk_gyre4dwof2n11d0w5avsmtk2t FOREIGN KEY (idrisorsa_fk) REFERENCES risorsa(idrisorsa);
ALTER TABLE ONLY catalogoutente_risorsa
    ADD CONSTRAINT fk_hqqvyv0w8wkuovqyqmbj2t9x6 FOREIGN KEY (catalogoutente_idcatalogo) REFERENCES catalogoutente(idcatalogo);
ALTER TABLE ONLY catalogoutente
    ADD CONSTRAINT fk_j7xln7b4y7e70btp8njqa0c71 FOREIGN KEY (idutente_fk) REFERENCES utente(idutente);
ALTER TABLE ONLY dataacquisto
    ADD CONSTRAINT fk_kqfwtnrq24k2qwi0q5wq4uean FOREIGN KEY (idcatalogo_fk) REFERENCES catalogoutente(idcatalogo);
ALTER TABLE ONLY risorsa
    ADD CONSTRAINT fk_meqa11lrieicbhmlmjmxkxvvc FOREIGN KEY (idautore_fk) REFERENCES autore(idautore);
ALTER TABLE ONLY preferenzautente
    ADD CONSTRAINT fk_rkw3rpb0fx4tu2ipxv6hxgfce FOREIGN KEY (idutente_fk) REFERENCES utente(idutente);
ALTER TABLE ONLY risorsa
    ADD CONSTRAINT fk_s5hd6b5t5ww1gm45mkbiw6gtt FOREIGN KEY (idtipologia_fk) REFERENCES tipologia(idtipologia);
