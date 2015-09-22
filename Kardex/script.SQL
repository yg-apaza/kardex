/*
Created: 02/06/2015
Modified: 22/09/2015
Model: MySQL 5.5
Database: MySQL 5.5
*/

-- Create tables section -------------------------------------------------

-- Table PRODUCTO

CREATE TABLE PRODUCTO
(
  ProCod Int(6) ZEROFILL NOT NULL AUTO_INCREMENT,
  ProNom Char(50) NOT NULL,
  UniCod Int(3) ZEROFILL,
  ProEstReg Tinyint(1) NOT NULL DEFAULT 1,
 PRIMARY KEY (ProCod)
)
;

CREATE INDEX IX_Relationship6 ON PRODUCTO (UniCod)
;

-- Table KARDEX

CREATE TABLE KARDEX
(
  ProCod Int(6) ZEROFILL NOT NULL,
  AlmCod Int(6) ZEROFILL NOT NULL,
  KarCan Double(9,2) NOT NULL,
  KarValUni Double(9,2) NOT NULL,
  KarValTot Double(9,2),
  KarEstReg Tinyint(1) NOT NULL DEFAULT 1
)
;

ALTER TABLE KARDEX ADD PRIMARY KEY (ProCod,AlmCod)
;

-- Table KARDEX_DET

CREATE TABLE KARDEX_DET
(
  KarDetCod Int(6) ZEROFILL NOT NULL AUTO_INCREMENT,
  ProCod Int(6) ZEROFILL NOT NULL,
  AlmCod Int(6) ZEROFILL NOT NULL,
  KarDetAnio Int(2) ZEROFILL NOT NULL,
  KarDetMes Int(2) ZEROFILL NOT NULL,
  KarDetDia Int(2) ZEROFILL NOT NULL,
  UsrCod Int(6) ZEROFILL,
  DocCod Int(6) ZEROFILL NOT NULL,
  KarDetDocNum Int(11) NOT NULL,
  KarDetOpe Bool NOT NULL,
  KarDetCan Double(9,2) NOT NULL,
  KarDetValUni Double(9,2) NOT NULL,
  KarDetValTot Double(9,2) NOT NULL,
  KarDetSalCan Double(9,2) NOT NULL,
  KarDetSalValUni Double(9,2) NOT NULL,
  KarDetSalValTot Double(9,2) NOT NULL,
  KarDetObs Char(100),
  KarDetEstReg Tinyint(1) NOT NULL DEFAULT 1,
 PRIMARY KEY (KarDetCod,ProCod,AlmCod)
)
;

CREATE INDEX IX_Relationship16 ON KARDEX_DET (DocCod)
;

CREATE INDEX IX_Relationship17 ON KARDEX_DET (UsrCod)
;

-- Table ALMACEN

CREATE TABLE ALMACEN
(
  AlmCod Int(6) ZEROFILL NOT NULL AUTO_INCREMENT,
  AlmNom Char(20) NOT NULL,
  AlmUbi Char(40) NOT NULL,
  AlmEstReg Tinyint(1) NOT NULL DEFAULT 1,
 PRIMARY KEY (AlmCod)
)
;

-- Table UNIDAD

CREATE TABLE UNIDAD
(
  UniCod Int(3) ZEROFILL NOT NULL AUTO_INCREMENT,
  UniDes Char(20) NOT NULL,
  UniEstReg Tinyint(1) NOT NULL DEFAULT 1,
 PRIMARY KEY (UniCod)
)
;

-- Table DOCUMENTO

CREATE TABLE DOCUMENTO
(
  DocCod Int(6) ZEROFILL NOT NULL AUTO_INCREMENT,
  DocNom Char(40) NOT NULL,
  DocEstReg Tinyint(1) NOT NULL DEFAULT 1,
 PRIMARY KEY (DocCod)
)
;

-- Table USUARIO

CREATE TABLE USUARIO
(
  UsrCod Int(6) ZEROFILL NOT NULL AUTO_INCREMENT,
  UsrIde Char(32) NOT NULL,
  UsrCon Char(32) NOT NULL,
  UsrDni Char(8) NOT NULL,
  UsrNom Char(40) NOT NULL,
  UsrApe Char(40) NOT NULL,
  UsrPer Bool NOT NULL,
  UsrEstReg Tinyint(1) NOT NULL,
 PRIMARY KEY (UsrCod)
)
;

-- Create relationships section ------------------------------------------------- 

ALTER TABLE PRODUCTO ADD CONSTRAINT Relationship6 FOREIGN KEY (UniCod) REFERENCES UNIDAD (UniCod) ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE KARDEX ADD CONSTRAINT Relationship11 FOREIGN KEY (ProCod) REFERENCES PRODUCTO (ProCod) ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE KARDEX_DET ADD CONSTRAINT Relationship12 FOREIGN KEY (ProCod, AlmCod) REFERENCES KARDEX (ProCod, AlmCod) ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE KARDEX ADD CONSTRAINT Relationship14 FOREIGN KEY (AlmCod) REFERENCES ALMACEN (AlmCod) ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE KARDEX_DET ADD CONSTRAINT Relationship16 FOREIGN KEY (DocCod) REFERENCES DOCUMENTO (DocCod) ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE KARDEX_DET ADD CONSTRAINT Relationship17 FOREIGN KEY (UsrCod) REFERENCES USUARIO (UsrCod) ON DELETE NO ACTION ON UPDATE NO ACTION
;


