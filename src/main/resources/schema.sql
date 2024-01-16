-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema portafolio
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema portafolio
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `portafolio` DEFAULT CHARACTER SET utf8mb3 ;
USE `portafolio` ;

-- -----------------------------------------------------
-- Table `portafolio`.`cartera`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `portafolio`.`cartera` (
  `idCartera` INT NOT NULL AUTO_INCREMENT,
  `valor_total` DOUBLE NOT NULL,
  `valor_descontar` DOUBLE NOT NULL,
  `valor_retirado` DOUBLE NOT NULL,
  `valor_recargado` DOUBLE NOT NULL,
  PRIMARY KEY (`idCartera`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `portafolio`.`imagenes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `portafolio`.`imagenes` (
  `id_imagenes` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `formato` VARCHAR(45) NOT NULL,
  `ruta` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_imagenes`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `portafolio`.`categoria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `portafolio`.`categoria` (
  `id_categoria` INT NOT NULL AUTO_INCREMENT,
  `nombre_categoria` VARCHAR(45) NOT NULL,
  `descripcion_categoria` VARCHAR(45) NOT NULL,
  `Imagenes_id_imagenes` INT NOT NULL,
  PRIMARY KEY (`id_categoria`),
  INDEX `fk_Categoria_Imagenes1_idx` (`Imagenes_id_imagenes` ASC) VISIBLE,
  CONSTRAINT `fk_Categoria_Imagenes1`
    FOREIGN KEY (`Imagenes_id_imagenes`)
    REFERENCES `portafolio`.`imagenes` (`id_imagenes`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `portafolio`.`clientes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `portafolio`.`clientes` (
  `id_cliente` INT NOT NULL AUTO_INCREMENT,
  `nombres` VARCHAR(45) NOT NULL,
  `apellidos` VARCHAR(45) NOT NULL,
  `telefono` VARCHAR(45) NOT NULL,
  `direccion` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `ciudad` VARCHAR(45) NOT NULL,
  `barrio` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_cliente`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `portafolio`.`valoracion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `portafolio`.`valoracion` (
  `id_valoracion` INT NOT NULL AUTO_INCREMENT,
  `comentario` VARCHAR(45) NULL DEFAULT NULL,
  `valoracion_numerica` ENUM('1', '2', '3', '4', '5') NULL DEFAULT NULL,
  PRIMARY KEY (`id_valoracion`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `portafolio`.`trabajadores`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `portafolio`.`trabajadores` (
  `id_trabajador` INT NOT NULL AUTO_INCREMENT,
  `nombres` VARCHAR(45) NOT NULL,
  `apellidos` VARCHAR(45) NOT NULL,
  `telefono` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `cedula` VARCHAR(45) NOT NULL,
  `profesion` VARCHAR(45) NOT NULL,
  `estado` VARCHAR(45) NOT NULL,
  `estado_servicio` VARCHAR(45) NOT NULL,
  `cartera_id_cartera` INT NOT NULL,
  PRIMARY KEY (`id_trabajador`),
  UNIQUE INDEX `cedula_UNIQUE` (`cedula` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
  UNIQUE INDEX `telefono_UNIQUE` (`telefono` ASC) VISIBLE,
  INDEX `fk_Trabajadores_Cartera1_idx` (`cartera_id_cartera` ASC) VISIBLE,
  CONSTRAINT `fk_Trabajadores_Cartera1`
    FOREIGN KEY (`cartera_id_cartera`)
    REFERENCES `portafolio`.`cartera` (`idCartera`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `portafolio`.`pedidos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `portafolio`.`pedidos` (
  `id_pedido` INT NOT NULL AUTO_INCREMENT,
  `nombre_pedido` VARCHAR(45) NOT NULL,
  `descripcion` VARCHAR(45) NOT NULL,
  `estado` ENUM('Solicitud', 'Abierto', 'Cancelado', 'Rechazado', 'Completado') NOT NULL,
  `fecha_creacion` DATETIME NOT NULL,
  `fecha_inicio` DATETIME NOT NULL,
  `fecha_final` DATETIME NOT NULL,
  `cliente_id_fk` INT NOT NULL,
  `trabajador_id_fk` INT NOT NULL,
  `valoracion_id_fk` INT NOT NULL,
  PRIMARY KEY (`id_pedido`),
  INDEX `fk_Servicio_Clientes_idx` (`cliente_id_fk` ASC) VISIBLE,
  INDEX `fk_Servicio_Trabajador1_idx` (`trabajador_id_fk` ASC) VISIBLE,
  INDEX `fk_Pedidos_Valoracion1_idx` (`valoracion_id_fk` ASC) VISIBLE,
  CONSTRAINT `fk_Pedidos_Valoracion1`
    FOREIGN KEY (`valoracion_id_fk`)
    REFERENCES `portafolio`.`valoracion` (`id_valoracion`),
  CONSTRAINT `fk_Servicio_Clientes`
    FOREIGN KEY (`cliente_id_fk`)
    REFERENCES `portafolio`.`clientes` (`id_cliente`),
  CONSTRAINT `fk_Servicio_Trabajador1`
    FOREIGN KEY (`trabajador_id_fk`)
    REFERENCES `portafolio`.`trabajadores` (`id_trabajador`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `portafolio`.`servicios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `portafolio`.`servicios` (
  `id_servicio` INT NOT NULL,
  `nombre_servicio` VARCHAR(45) NOT NULL,
  `descripcion_servicio` VARCHAR(45) NOT NULL,
  `categoria_id_fk` INT NOT NULL,
  `trabajador_id_fk` INT NOT NULL,
  PRIMARY KEY (`id_servicio`),
  INDEX `fk_Servicios_Categoria1_idx` (`categoria_id_fk` ASC) VISIBLE,
  INDEX `fk_Servicios_Trabajadores1_idx` (`trabajador_id_fk` ASC) VISIBLE,
  CONSTRAINT `fk_Servicios_Categoria1`
    FOREIGN KEY (`categoria_id_fk`)
    REFERENCES `portafolio`.`categoria` (`id_categoria`),
  CONSTRAINT `fk_Servicios_Trabajadores1`
    FOREIGN KEY (`trabajador_id_fk`)
    REFERENCES `portafolio`.`trabajadores` (`id_trabajador`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `portafolio`.`servicios-imagenes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `portafolio`.`servicios-imagenes` (
  `id_servicios-Imagenes` INT NOT NULL AUTO_INCREMENT,
  `imagenes_id_imagenes_fk` INT NOT NULL,
  `servicios_id_servicio_fk` INT NOT NULL,
  PRIMARY KEY (`id_servicios-Imagenes`),
  INDEX `fk_Servicios-Imagenes_Imagenes1_idx` (`imagenes_id_imagenes_fk` ASC) VISIBLE,
  INDEX `fk_Servicios-Imagenes_Servicios1_idx` (`servicios_id_servicio_fk` ASC) VISIBLE,
  CONSTRAINT `fk_Servicios-Imagenes_Imagenes1`
    FOREIGN KEY (`imagenes_id_imagenes_fk`)
    REFERENCES `portafolio`.`imagenes` (`id_imagenes`),
  CONSTRAINT `fk_Servicios-Imagenes_Servicios1`
    FOREIGN KEY (`servicios_id_servicio_fk`)
    REFERENCES `portafolio`.`servicios` (`id_servicio`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `portafolio`.`solicitud pedidos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `portafolio`.`solicitud pedidos` (
  `id_solicitud_pedidos` INT NOT NULL AUTO_INCREMENT,
  `estado` ENUM('En negociaciones', 'Aplazado', 'En proceso', 'Visita', 'Realizado', 'Cerrado') NOT NULL,
  `valor` DOUBLE NOT NULL,
  `descripcion` VARCHAR(45) NULL DEFAULT NULL,
  `fecha_finalizacion` DATETIME NULL DEFAULT NULL,
  `pedidos_id_pedido` INT NOT NULL,
  PRIMARY KEY (`id_solicitud_pedidos`),
  INDEX `fk_Solicitud pedidos_Pedidos1_idx` (`pedidos_id_pedido` ASC) VISIBLE,
  CONSTRAINT `fk_Solicitud pedidos_Pedidos1`
    FOREIGN KEY (`pedidos_id_pedido`)
    REFERENCES `portafolio`.`pedidos` (`id_pedido`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `portafolio`.`traza`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `portafolio`.`traza` (
  `id_traza` INT NOT NULL AUTO_INCREMENT,
  `comentario` VARCHAR(45) NULL DEFAULT NULL,
  `fecha_creacion` VARCHAR(45) NOT NULL,
  `solicitud_pedidos_id_fk` INT NOT NULL,
  PRIMARY KEY (`id_traza`),
  INDEX `fk_Traza_Solicitud pedidos1_idx` (`solicitud_pedidos_id_fk` ASC) VISIBLE,
  CONSTRAINT `fk_Traza_Solicitud pedidos1`
    FOREIGN KEY (`solicitud_pedidos_id_fk`)
    REFERENCES `portafolio`.`solicitud pedidos` (`id_solicitud_pedidos`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `portafolio`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `portafolio`.`user` (
  `usuario` VARCHAR(20) NOT NULL,
  `contrasena` VARCHAR(200) NOT NULL,
  `email` VARCHAR(50) NULL DEFAULT NULL,
  `bloqueado` TINYINT NULL DEFAULT NULL,
  `desabilitado` TINYINT NULL DEFAULT NULL,
  PRIMARY KEY (`usuario`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `portafolio`.`user_role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `portafolio`.`user_role` (
  `usuario` VARCHAR(20) NOT NULL,
  `rol` VARCHAR(20) NOT NULL COMMENT 'CUSTOMER\\nADMIN',
  `fecha_creacion` DATETIME NOT NULL,
  PRIMARY KEY (`usuario`, `rol`),
  CONSTRAINT `fk_usuario_rol_usuario1`
    FOREIGN KEY (`usuario`)
    REFERENCES `portafolio`.`user` (`usuario`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
