-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema catalogomultimediale
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema catalogomultimediale
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `catalogomultimediale` DEFAULT CHARACTER SET latin1 ;
USE `catalogomultimediale` ;

-- -----------------------------------------------------
-- Table `catalogomultimediale`.`Amministratore`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `catalogomultimediale`.`Amministratore` (
  `idAmministratore` INT(11) NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(255) NULL DEFAULT NULL,
  `password` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`idAmministratore`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `catalogomultimediale`.`Autore`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `catalogomultimediale`.`Autore` (
  `idAutore` INT(11) NOT NULL AUTO_INCREMENT,
  `cognome` VARCHAR(255) NULL DEFAULT NULL,
  `nome` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`idAutore`))
ENGINE = InnoDB
AUTO_INCREMENT = 54
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `catalogomultimediale`.`Utente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `catalogomultimediale`.`Utente` (
  `idUtente` INT(11) NOT NULL AUTO_INCREMENT,
  `cognome` VARCHAR(255) NULL DEFAULT NULL,
  `email` VARCHAR(255) NULL DEFAULT NULL,
  `nome` VARCHAR(255) NULL DEFAULT NULL,
  `password` VARCHAR(255) NULL DEFAULT NULL,
  `idCatalogo_fk` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`idUtente`),
  INDEX `FK_7o8760ofdordw33e8ny1snofb` (`idCatalogo_fk` ASC),
  CONSTRAINT `FK_7o8760ofdordw33e8ny1snofb`
    FOREIGN KEY (`idCatalogo_fk`)
    REFERENCES `catalogomultimediale`.`CatalogoUtente` (`idCatalogo`))
ENGINE = InnoDB
AUTO_INCREMENT = 27
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `catalogomultimediale`.`CatalogoUtente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `catalogomultimediale`.`CatalogoUtente` (
  `idCatalogo` INT(11) NOT NULL AUTO_INCREMENT,
  `idUtente_fk` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`idCatalogo`),
  INDEX `FK_j7xln7b4y7e70btp8njqa0c71` (`idUtente_fk` ASC),
  CONSTRAINT `FK_j7xln7b4y7e70btp8njqa0c71`
    FOREIGN KEY (`idUtente_fk`)
    REFERENCES `catalogomultimediale`.`Utente` (`idUtente`))
ENGINE = InnoDB
AUTO_INCREMENT = 30
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `catalogomultimediale`.`Tipologia`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `catalogomultimediale`.`Tipologia` (
  `idTipologia` INT(11) NOT NULL AUTO_INCREMENT,
  `descrizione` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`idTipologia`))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `catalogomultimediale`.`Risorsa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `catalogomultimediale`.`Risorsa` (
  `idRisorsa` INT(11) NOT NULL AUTO_INCREMENT,
  `costoAcquisto` FLOAT NULL DEFAULT NULL,
  `costoNoleggio` FLOAT NULL DEFAULT NULL,
  `dataPubblicazione` DATE NULL DEFAULT NULL,
  `fileName` VARCHAR(255) NULL DEFAULT NULL,
  `titolo` VARCHAR(255) NULL DEFAULT NULL,
  `idAutore_fk` INT(11) NULL DEFAULT NULL,
  `idTipologia_fk` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`idRisorsa`),
  INDEX `FK_meqa11lrieicbhmlmjmxkxvvc` (`idAutore_fk` ASC),
  INDEX `FK_s5hd6b5t5ww1gm45mkbiw6gtt` (`idTipologia_fk` ASC),
  CONSTRAINT `FK_meqa11lrieicbhmlmjmxkxvvc`
    FOREIGN KEY (`idAutore_fk`)
    REFERENCES `catalogomultimediale`.`Autore` (`idAutore`),
  CONSTRAINT `FK_s5hd6b5t5ww1gm45mkbiw6gtt`
    FOREIGN KEY (`idTipologia_fk`)
    REFERENCES `catalogomultimediale`.`Tipologia` (`idTipologia`))
ENGINE = InnoDB
AUTO_INCREMENT = 58
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `catalogomultimediale`.`CatalogoUtente_Risorsa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `catalogomultimediale`.`CatalogoUtente_Risorsa` (
  `CatalogoUtente_idCatalogo` INT(11) NOT NULL,
  `risorsePossedute_idRisorsa` INT(11) NOT NULL,
  INDEX `FK_4uck21ontxbj0nvnyue8qiyht` (`risorsePossedute_idRisorsa` ASC),
  INDEX `FK_hqqvyv0w8wkuovqyqmbj2t9x6` (`CatalogoUtente_idCatalogo` ASC),
  CONSTRAINT `FK_4uck21ontxbj0nvnyue8qiyht`
    FOREIGN KEY (`risorsePossedute_idRisorsa`)
    REFERENCES `catalogomultimediale`.`Risorsa` (`idRisorsa`),
  CONSTRAINT `FK_hqqvyv0w8wkuovqyqmbj2t9x6`
    FOREIGN KEY (`CatalogoUtente_idCatalogo`)
    REFERENCES `catalogomultimediale`.`CatalogoUtente` (`idCatalogo`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `catalogomultimediale`.`DataAcquisto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `catalogomultimediale`.`DataAcquisto` (
  `idAcquisto` INT(11) NOT NULL AUTO_INCREMENT,
  `dataAcquisto` DATETIME NULL DEFAULT NULL,
  `idCatalogo_fk` INT(11) NULL DEFAULT NULL,
  `idRisorsa_fk` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`idAcquisto`),
  INDEX `FK_kqfwtnrq24k2qwi0q5wq4uean` (`idCatalogo_fk` ASC),
  INDEX `FK_8dlr42mbkt928lkplo5br6cl3` (`idRisorsa_fk` ASC),
  CONSTRAINT `FK_8dlr42mbkt928lkplo5br6cl3`
    FOREIGN KEY (`idRisorsa_fk`)
    REFERENCES `catalogomultimediale`.`Risorsa` (`idRisorsa`),
  CONSTRAINT `FK_kqfwtnrq24k2qwi0q5wq4uean`
    FOREIGN KEY (`idCatalogo_fk`)
    REFERENCES `catalogomultimediale`.`CatalogoUtente` (`idCatalogo`))
ENGINE = InnoDB
AUTO_INCREMENT = 55
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `catalogomultimediale`.`DataNoleggio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `catalogomultimediale`.`DataNoleggio` (
  `idNoleggio` INT(11) NOT NULL AUTO_INCREMENT,
  `dataNoleggio` DATETIME NULL DEFAULT NULL,
  `idCatalogo_fk` INT(11) NULL DEFAULT NULL,
  `idRisorsa_fk` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`idNoleggio`),
  INDEX `FK_2nh9qsrfc91gnhklacr1cpdy2` (`idCatalogo_fk` ASC),
  INDEX `FK_gyre4dwof2n11d0w5avsmtk2t` (`idRisorsa_fk` ASC),
  CONSTRAINT `FK_2nh9qsrfc91gnhklacr1cpdy2`
    FOREIGN KEY (`idCatalogo_fk`)
    REFERENCES `catalogomultimediale`.`CatalogoUtente` (`idCatalogo`),
  CONSTRAINT `FK_gyre4dwof2n11d0w5avsmtk2t`
    FOREIGN KEY (`idRisorsa_fk`)
    REFERENCES `catalogomultimediale`.`Risorsa` (`idRisorsa`))
ENGINE = InnoDB
AUTO_INCREMENT = 52
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `catalogomultimediale`.`PreferenzaUtente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `catalogomultimediale`.`PreferenzaUtente` (
  `idPreferenza` INT(11) NOT NULL AUTO_INCREMENT,
  `idAutore_fk` INT(11) NULL DEFAULT NULL,
  `idTipologia_fk` INT(11) NULL DEFAULT NULL,
  `idUtente_fk` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`idPreferenza`),
  INDEX `FK_3ioqkdj2kofyxh94w4ydno18f` (`idAutore_fk` ASC),
  INDEX `FK_9yel3e43icj9t7gr3dxanrre7` (`idTipologia_fk` ASC),
  INDEX `FK_rkw3rpb0fx4tu2ipxv6hxgfce` (`idUtente_fk` ASC),
  CONSTRAINT `FK_3ioqkdj2kofyxh94w4ydno18f`
    FOREIGN KEY (`idAutore_fk`)
    REFERENCES `catalogomultimediale`.`Autore` (`idAutore`),
  CONSTRAINT `FK_9yel3e43icj9t7gr3dxanrre7`
    FOREIGN KEY (`idTipologia_fk`)
    REFERENCES `catalogomultimediale`.`Tipologia` (`idTipologia`),
  CONSTRAINT `FK_rkw3rpb0fx4tu2ipxv6hxgfce`
    FOREIGN KEY (`idUtente_fk`)
    REFERENCES `catalogomultimediale`.`Utente` (`idUtente`))
ENGINE = InnoDB
AUTO_INCREMENT = 46
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `catalogomultimediale`.`Tipologia_Autore`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `catalogomultimediale`.`Tipologia_Autore` (
  `Tipologia_idTipologia` INT(11) NOT NULL,
  `listaAutori_idAutore` INT(11) NOT NULL,
  INDEX `FK_1o1vu35ggkjv22hs2huj3c438` (`listaAutori_idAutore` ASC),
  INDEX `FK_bhjct6dvl6wsnfevxed90mln2` (`Tipologia_idTipologia` ASC),
  CONSTRAINT `FK_1o1vu35ggkjv22hs2huj3c438`
    FOREIGN KEY (`listaAutori_idAutore`)
    REFERENCES `catalogomultimediale`.`Autore` (`idAutore`),
  CONSTRAINT `FK_bhjct6dvl6wsnfevxed90mln2`
    FOREIGN KEY (`Tipologia_idTipologia`)
    REFERENCES `catalogomultimediale`.`Tipologia` (`idTipologia`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
