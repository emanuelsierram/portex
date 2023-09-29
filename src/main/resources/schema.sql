-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema portafolio
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema portafolio
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `portafolio` DEFAULT CHARACTER SET utf8 ;
USE `portafolio` ;

-- -----------------------------------------------------
-- Table `portafolio`.`Clientes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `portafolio`.`Clientes` (
  `id_cliente` INT NOT NULL AUTO_INCREMENT,
  `nombres` VARCHAR(45) NOT NULL,
  `apellidos` VARCHAR(45) NOT NULL,
  `telefono` VARCHAR(45) NOT NULL,
  `direccion` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `ciudad` VARCHAR(45) NOT NULL,
  `barrio` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_cliente`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `portafolio`.`Trabajadores`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `portafolio`.`Trabajadores` (
  `id_trabajador` INT NOT NULL AUTO_INCREMENT,
  `nombres` VARCHAR(45) NOT NULL,
  `apellidos` VARCHAR(45) NOT NULL,
  `telefono` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `cedula` VARCHAR(45) NOT NULL,
  `profesion` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_trabajador`),
  UNIQUE INDEX `cedula_UNIQUE` (`cedula` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
  UNIQUE INDEX `telefono_UNIQUE` (`telefono` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `portafolio`.`Imagenes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `portafolio`.`Imagenes` (
  `id_imagenes` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `formato` VARCHAR(45) NOT NULL,
  `ruta` VARCHAR(45) NOT NULL,
  `servicio_id_fk` INT NOT NULL,
  PRIMARY KEY (`id_imagenes`, `servicio_id_fk`),
  INDEX `fk_Imagenes_Servicios1_idx` (`servicio_id_fk` ASC) VISIBLE,
  CONSTRAINT `fk_Imagenes_Servicios1`
    FOREIGN KEY (`servicio_id_fk`)
    REFERENCES `portafolio`.`Servicios` (`id_servicio`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `portafolio`.`Categoria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `portafolio`.`Categoria` (
  `id_categoria` INT NOT NULL AUTO_INCREMENT,
  `nombre_categoria` VARCHAR(45) NOT NULL,
  `descripcion_categoria` VARCHAR(45) NOT NULL,
  `icono_id_imagenes_fk` INT NOT NULL,
  PRIMARY KEY (`id_categoria`),
  INDEX `fk_Categoria_Imagenes1_idx` (`icono_id_imagenes_fk` ASC) VISIBLE,
  CONSTRAINT `fk_Categoria_Imagenes1`
    FOREIGN KEY (`icono_id_imagenes_fk`)
    REFERENCES `portafolio`.`Imagenes` (`id_imagenes`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `portafolio`.`Servicios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `portafolio`.`Servicios` (
  `id_servicio` INT NOT NULL,
  `nombre_servicio` VARCHAR(45) NOT NULL,
  `descripcion_servicio` VARCHAR(45) NOT NULL,
  `categoria_id_fk` INT NOT NULL,
  PRIMARY KEY (`id_servicio`, `categoria_id_fk`),
  INDEX `fk_Servicios_Categoria1_idx` (`categoria_id_fk` ASC) VISIBLE,
  CONSTRAINT `fk_Servicios_Categoria1`
    FOREIGN KEY (`categoria_id_fk`)
    REFERENCES `portafolio`.`Categoria` (`id_categoria`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `portafolio`.`Valoracion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `portafolio`.`Valoracion` (
  `id_valoracion` INT NOT NULL AUTO_INCREMENT,
  `comentario` VARCHAR(45) NULL,
  `valoracion_numerica` ENUM('1', '2', '3', '4', '5') NULL,
  PRIMARY KEY (`id_valoracion`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `portafolio`.`Traza`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `portafolio`.`Traza` (
  `id_traza` INT NOT NULL AUTO_INCREMENT,
  `comentario` VARCHAR(45) NULL,
  `fecha_creacion` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_traza`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `portafolio`.`Pedidos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `portafolio`.`Pedidos` (
  `id_pedido` INT NOT NULL AUTO_INCREMENT,
  `nombre_pedido` VARCHAR(45) NOT NULL,
  `descripcion` VARCHAR(45) NOT NULL,
  `estado` ENUM('solicitado', 'rechazado', 'cancelado', 'realizado', 'en proceso', 'visita', 'aceptado', 'aplazado') NOT NULL,
  `fecha_creacion` DATE NOT NULL,
  `fecha_final` DATE NOT NULL,
  `cliente_id_fk` INT NOT NULL,
  `trabajador_id_fk` INT NOT NULL,
  `servicio_id_fk` INT NOT NULL,
  `valoracion_id_fk` INT NOT NULL,
  `traza_id_fk` INT NOT NULL,
  PRIMARY KEY (`id_pedido`, `cliente_id_fk`, `trabajador_id_fk`, `servicio_id_fk`, `valoracion_id_fk`, `traza_id_fk`),
  INDEX `fk_Servicio_Clientes_idx` (`cliente_id_fk` ASC) VISIBLE,
  INDEX `fk_Servicio_Trabajador1_idx` (`trabajador_id_fk` ASC) VISIBLE,
  INDEX `fk_Pedidos_Servicios1_idx` (`servicio_id_fk` ASC) VISIBLE,
  INDEX `fk_Pedidos_Valoracion1_idx` (`valoracion_id_fk` ASC) VISIBLE,
  INDEX `fk_Pedidos_Traza1_idx` (`traza_id_fk` ASC) VISIBLE,
  CONSTRAINT `fk_Servicio_Clientes`
    FOREIGN KEY (`cliente_id_fk`)
    REFERENCES `portafolio`.`Clientes` (`id_cliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Servicio_Trabajador1`
    FOREIGN KEY (`trabajador_id_fk`)
    REFERENCES `portafolio`.`Trabajadores` (`id_trabajador`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Pedidos_Servicios1`
    FOREIGN KEY (`servicio_id_fk`)
    REFERENCES `portafolio`.`Servicios` (`categoria_id_fk`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Pedidos_Valoracion1`
    FOREIGN KEY (`valoracion_id_fk`)
    REFERENCES `portafolio`.`Valoracion` (`id_valoracion`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Pedidos_Traza1`
    FOREIGN KEY (`traza_id_fk`)
    REFERENCES `portafolio`.`Traza` (`id_traza`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
