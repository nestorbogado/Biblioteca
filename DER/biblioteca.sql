-- MySQL Script generated by MySQL Workbench
-- Thu May 30 13:43:31 2019
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema biblioteca
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `biblioteca` ;

-- -----------------------------------------------------
-- Schema biblioteca
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `biblioteca` DEFAULT CHARACTER SET utf8 COLLATE utf8_spanish2_ci ;
USE `biblioteca` ;

-- -----------------------------------------------------
-- Table `biblioteca`.`facultad`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `biblioteca`.`facultad` ;

CREATE TABLE IF NOT EXISTS `biblioteca`.`facultad` (
  `idfacultad` INT NOT NULL AUTO_INCREMENT,
  `descripcion` VARCHAR(45) NULL,
  PRIMARY KEY (`idfacultad`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `biblioteca`.`carreras`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `biblioteca`.`carreras` ;

CREATE TABLE IF NOT EXISTS `biblioteca`.`carreras` (
  `id_carrera` INT(11) NOT NULL AUTO_INCREMENT,
  `nombre_carrera` VARCHAR(45) NOT NULL,
  `facultad_idfacultad` INT NOT NULL,
  PRIMARY KEY (`id_carrera`, `facultad_idfacultad`),
  INDEX `fk_carreras_facultad1_idx` (`facultad_idfacultad` ASC),
  CONSTRAINT `fk_carreras_facultad1`
    FOREIGN KEY (`facultad_idfacultad`)
    REFERENCES `biblioteca`.`facultad` (`idfacultad`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish2_ci;


-- -----------------------------------------------------
-- Table `biblioteca`.`tipo_documento`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `biblioteca`.`tipo_documento` ;

CREATE TABLE IF NOT EXISTS `biblioteca`.`tipo_documento` (
  `id_tipo_documento` INT NOT NULL AUTO_INCREMENT,
  `descripcion_tipo_doc` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id_tipo_documento`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `biblioteca`.`alumnos`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `biblioteca`.`alumnos` ;

CREATE TABLE IF NOT EXISTS `biblioteca`.`alumnos` (
  `id_alumno` INT(11) NOT NULL AUTO_INCREMENT,
  `cedula` VARCHAR(45) NULL DEFAULT NULL,
  `nombre_alumno` VARCHAR(45) NULL DEFAULT NULL,
  `apellido_alumno` VARCHAR(45) NULL DEFAULT NULL,
  `telefono_alumno` VARCHAR(45) NULL,
  `fecha_ingreso_alumno` DATE NULL,
  `fecha_renovacion_alumno` DATE NULL,
  `CARRERAS_id_carrera` INT(11) NOT NULL,
  `tipo_documento_id_tipo_documento` INT NOT NULL,
  PRIMARY KEY (`id_alumno`),
  INDEX `fk_ALUMNOS_CARRERAS1_idx` (`CARRERAS_id_carrera` ASC),
  INDEX `fk_alumnos_tipo_documento1_idx` (`tipo_documento_id_tipo_documento` ASC),
  CONSTRAINT `fk_ALUMNOS_CARRERAS1`
    FOREIGN KEY (`CARRERAS_id_carrera`)
    REFERENCES `biblioteca`.`carreras` (`id_carrera`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_alumnos_tipo_documento1`
    FOREIGN KEY (`tipo_documento_id_tipo_documento`)
    REFERENCES `biblioteca`.`tipo_documento` (`id_tipo_documento`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish2_ci;


-- -----------------------------------------------------
-- Table `biblioteca`.`autores`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `biblioteca`.`autores` ;

CREATE TABLE IF NOT EXISTS `biblioteca`.`autores` (
  `id_autor` INT(11) NOT NULL AUTO_INCREMENT,
  `nombre_autor` VARCHAR(60) NOT NULL,
  PRIMARY KEY (`id_autor`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish2_ci;


-- -----------------------------------------------------
-- Table `biblioteca`.`tipo_material`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `biblioteca`.`tipo_material` ;

CREATE TABLE IF NOT EXISTS `biblioteca`.`tipo_material` (
  `id_tipo_material` INT(11) NOT NULL AUTO_INCREMENT,
  `descripcion_material` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`id_tipo_material`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish2_ci;


-- -----------------------------------------------------
-- Table `biblioteca`.`editoriales`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `biblioteca`.`editoriales` ;

CREATE TABLE IF NOT EXISTS `biblioteca`.`editoriales` (
  `id_editorial` INT(11) NOT NULL AUTO_INCREMENT,
  `nombre_editorial` VARCHAR(80) NOT NULL,
  PRIMARY KEY (`id_editorial`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish2_ci;


-- -----------------------------------------------------
-- Table `biblioteca`.`generos`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `biblioteca`.`generos` ;

CREATE TABLE IF NOT EXISTS `biblioteca`.`generos` (
  `id_genero` INT(11) NOT NULL AUTO_INCREMENT,
  `nombre_genero` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_genero`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish2_ci;


-- -----------------------------------------------------
-- Table `biblioteca`.`materiales`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `biblioteca`.`materiales` ;

CREATE TABLE IF NOT EXISTS `biblioteca`.`materiales` (
  `id_material` INT(11) NOT NULL AUTO_INCREMENT,
  `ISBN` VARCHAR(45) NULL DEFAULT NULL,
  `titulo` VARCHAR(100) NOT NULL,
  `año_publicacion` VARCHAR(10) NULL DEFAULT NULL,
  `estado` VARCHAR(45) NOT NULL,
  `editoriales_id_editorial` INT(11) NULL,
  `fecha_ingreso` VARCHAR(45) NOT NULL,
  `numeracion_dewey` VARCHAR(40) NULL,
  `numero_entrada` DOUBLE NOT NULL,
  `titulo_grado` VARCHAR(45) NULL,
  `autores` VARCHAR(45) NULL,
  `tipo_material_id_tipo_material` INT(11) NULL,
  `categoria` INT NULL,
  `ISSN` VARCHAR(45) NULL,
  PRIMARY KEY (`id_material`),
  INDEX `fk_LIBROS_EDITORIALES_idx` (`editoriales_id_editorial` ASC),
  INDEX `fk_materiales_tipo_material1_idx` (`tipo_material_id_tipo_material` ASC),
  CONSTRAINT `fk_LIBROS_EDITORIALES`
    FOREIGN KEY (`editoriales_id_editorial`)
    REFERENCES `biblioteca`.`editoriales` (`id_editorial`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_materiales_tipo_material1`
    FOREIGN KEY (`tipo_material_id_tipo_material`)
    REFERENCES `biblioteca`.`tipo_material` (`id_tipo_material`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish2_ci;


-- -----------------------------------------------------
-- Table `biblioteca`.`prestamos`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `biblioteca`.`prestamos` ;

CREATE TABLE IF NOT EXISTS `biblioteca`.`prestamos` (
  `id_prestamo` INT(11) NOT NULL AUTO_INCREMENT,
  `MATERIALES_id_material` INT(11) NOT NULL,
  `ALUMNOS_id_alumno` INT(11) NOT NULL,
  `fecha_prestamo` DATE NULL DEFAULT NULL,
  `fecha_devolucion` DATE NULL DEFAULT NULL,
  PRIMARY KEY (`id_prestamo`, `MATERIALES_id_material`, `ALUMNOS_id_alumno`),
  INDEX `fk_LIBROS_has_ALUMNOS_ALUMNOS1_idx` (`ALUMNOS_id_alumno` ASC),
  INDEX `fk_LIBROS_has_ALUMNOS_LIBROS1_idx` (`MATERIALES_id_material` ASC),
  CONSTRAINT `fk_LIBROS_has_ALUMNOS_ALUMNOS1`
    FOREIGN KEY (`ALUMNOS_id_alumno`)
    REFERENCES `biblioteca`.`alumnos` (`id_alumno`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_LIBROS_has_ALUMNOS_LIBROS1`
    FOREIGN KEY (`MATERIALES_id_material`)
    REFERENCES `biblioteca`.`materiales` (`id_material`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish2_ci;


-- -----------------------------------------------------
-- Table `biblioteca`.`usuarios`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `biblioteca`.`usuarios` ;

CREATE TABLE IF NOT EXISTS `biblioteca`.`usuarios` (
  `id_usuario` INT(11) NOT NULL AUTO_INCREMENT,
  `nombre_usuario` VARCHAR(45) NOT NULL,
  `apellido_usuario` VARCHAR(45) NOT NULL,
  `contraseña` VARCHAR(150) NOT NULL,
  `usuario` VARCHAR(100) NOT NULL,
  `tipo_usuario` INT NOT NULL,
  `estado` INT NOT NULL,
  PRIMARY KEY (`id_usuario`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish2_ci;

INSERT INTO `usuarios` (`nombre_usuario`, `apellido_usuario`, `contraseña`, `usuario`, `tipo_usuario`, `estado`) VALUES
('ADMIN', 'ADMIN', '25d55ad283aa400af464c76d713c07ad', 'admin', 1, 1);
-- -----------------------------------------------------
-- Table `biblioteca`.`materiales_y_carreras`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `biblioteca`.`materiales_y_carreras` ;

CREATE TABLE IF NOT EXISTS `biblioteca`.`materiales_y_carreras` (
  `materiales_id_material` INT(11) NOT NULL,
  `carreras_id_carrera` INT(11) NOT NULL,
  PRIMARY KEY (`materiales_id_material`, `carreras_id_carrera`),
  INDEX `fk_materiales_has_carreras_carreras1_idx` (`carreras_id_carrera` ASC),
  INDEX `fk_materiales_has_carreras_materiales1_idx` (`materiales_id_material` ASC),
  CONSTRAINT `fk_materiales_has_carreras_materiales1`
    FOREIGN KEY (`materiales_id_material`)
    REFERENCES `biblioteca`.`materiales` (`id_material`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_materiales_has_carreras_carreras1`
    FOREIGN KEY (`carreras_id_carrera`)
    REFERENCES `biblioteca`.`carreras` (`id_carrera`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish2_ci;


-- -----------------------------------------------------
-- Table `biblioteca`.`materiales_y_autores`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `biblioteca`.`materiales_y_autores` ;

CREATE TABLE IF NOT EXISTS `biblioteca`.`materiales_y_autores` (
  `materiales_id_material` INT(11) NOT NULL,
  `autores_id_autor` INT(11) NOT NULL,
  PRIMARY KEY (`materiales_id_material`, `autores_id_autor`),
  INDEX `fk_materiales_has_autores_autores1_idx` (`autores_id_autor` ASC),
  INDEX `fk_materiales_has_autores_materiales1_idx` (`materiales_id_material` ASC),
  CONSTRAINT `fk_materiales_has_autores_materiales1`
    FOREIGN KEY (`materiales_id_material`)
    REFERENCES `biblioteca`.`materiales` (`id_material`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_materiales_has_autores_autores1`
    FOREIGN KEY (`autores_id_autor`)
    REFERENCES `biblioteca`.`autores` (`id_autor`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish2_ci;


-- -----------------------------------------------------
-- Table `biblioteca`.`materiales_y_generos`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `biblioteca`.`materiales_y_generos` ;

CREATE TABLE IF NOT EXISTS `biblioteca`.`materiales_y_generos` (
  `materiales_id_material` INT(11) NOT NULL,
  `generos_id_genero` INT(11) NOT NULL,
  PRIMARY KEY (`materiales_id_material`, `generos_id_genero`),
  INDEX `fk_materiales_has_generos_generos1_idx` (`generos_id_genero` ASC),
  INDEX `fk_materiales_has_generos_materiales1_idx` (`materiales_id_material` ASC),
  CONSTRAINT `fk_materiales_has_generos_materiales1`
    FOREIGN KEY (`materiales_id_material`)
    REFERENCES `biblioteca`.`materiales` (`id_material`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_materiales_has_generos_generos1`
    FOREIGN KEY (`generos_id_genero`)
    REFERENCES `biblioteca`.`generos` (`id_genero`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish2_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
