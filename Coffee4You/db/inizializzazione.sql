DROP DATABASE IF EXISTS c4y_db;
CREATE DATABASE c4y_db;
USE c4y_db;

DROP TABLE IF EXISTS amministratore;

CREATE TABLE amministratore(
  id int primary key AUTO_INCREMENT,
  nome varchar(255),
  cognome varchar(255),
  username char(24) unique,
  password varchar(60),
  sesso char(1),
  email varchar(255),
  telefono char(11)
);
INSERT INTO amministratore VALUES (1,"Paolo","Domini","root","root","M","admin1@gmail.com","3207694220");

DROP TABLE IF EXISTS cliente;

CREATE TABLE cliente(
  id int primary key AUTO_INCREMENT,
  nome varchar(255),
  cognome varchar(255),
  username char(24) unique,
  password varchar(60),
  dataNascita date,
  sesso char(1),
  codiceFiscale char(16),
  email varchar(255),
  telefono char(11)
);

DROP TABLE IF EXISTS cartaDiCredito;

CREATE TABLE cartaDiCredito(
  codiceCarta char(16) primary key unique,
  pin varchar(10),
  cliente_id int ,
  dataScadenza date,
  saldo float default 0.0,
  foreign key(cliente_id) REFERENCES cliente(id)
);

DROP TABLE IF EXISTS indirizzoSpedizione;

CREATE TABLE indirizzoSpedizione(
  cliente_id int,
  provincia varchar(255),
  città varchar(255),
  via varchar(255),
  cap varchar(7),
  nCivico varchar(7),
  nazione varchar(255),
  foreign key(cliente_id) REFERENCES cliente(id)
  );

DROP TABLE IF EXISTS categoriaProdotto;

CREATE TABLE categoriaProdotto (
  id int primary key AUTO_INCREMENT,
  nome varchar(255),
  descrizione blob
);

DROP TABLE IF EXISTS prodotto;

CREATE TABLE prodotto (	
  id int primary key AUTO_INCREMENT,
  nome varchar(255) not null,
  categoria_id int,
  marca varchar(255),
  descrizione blob,
  disponibilità int default 0,
  prezzo float default 0.0,
  iva int default 0,
  urlImmagine varchar(255)
);

DROP TABLE IF EXISTS ordine;

CREATE TABLE ordine(
  id int primary key AUTO_INCREMENT,
  cliente_id int,
  totaleSpesa float(5,2),
  statoOrdine tinyint(1),
  dataEmissione datetime,
  indirizzoConsegna varchar(255),
  foreign key(cliente_id) REFERENCES cliente(id)
);

DROP TABLE IF EXISTS prodotti_ordine ;

CREATE TABLE prodotti_ordine(
  ordine_id int,
  prodotto_id int,
  quantitàProdotto int
);