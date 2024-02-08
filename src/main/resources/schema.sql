-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema portafolio
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema portafolio
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS portafolio;

-- -----------------------------------------------------
-- Table cartera
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS portafolio.cartera (
  idCartera INT NOT NULL AUTO_INCREMENT,
  valor_total DOUBLE NOT NULL,
  valor_descontar DOUBLE NOT NULL,
  valor_retirado DOUBLE NOT NULL,
  valor_recargado DOUBLE NOT NULL,
  PRIMARY KEY (idCartera));


-- -----------------------------------------------------
-- Table imagenes
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS portafolio.imagenes (
  id_imagenes INT NOT NULL AUTO_INCREMENT,
  nombre VARCHAR(45) NOT NULL,
  formato VARCHAR(45) NOT NULL,
  ruta VARCHAR(45) NOT NULL,
  PRIMARY KEY (id_imagenes));



-- -----------------------------------------------------
-- Table categoria
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS portafolio.categoria (
  id_categoria INT NOT NULL,
  nombre_categoria VARCHAR(45) NOT NULL,
  descripcion_categoria VARCHAR(45) NOT NULL,
  imagenes_id_fk INT NOT NULL,
  PRIMARY KEY (id_categoria),
  UNIQUE INDEX idx_imagenes (imagenes_id_fk),
  CONSTRAINT fk_Categoria_Imagenes1
    FOREIGN KEY (imagenes_id_fk)
    REFERENCES portafolio.imagenes (id_imagenes)
);



-- -----------------------------------------------------
-- Table clientes
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS portafolio.clientes (
  id_cliente INT NOT NULL AUTO_INCREMENT,
  nombres VARCHAR(45) NOT NULL,
  apellidos VARCHAR(45) NOT NULL,
  telefono VARCHAR(45) NOT NULL,
  direccion VARCHAR(45) NOT NULL,
  email VARCHAR(45) NOT NULL,
  ciudad VARCHAR(45) NOT NULL,
  barrio VARCHAR(45) NOT NULL,
  PRIMARY KEY (id_cliente));


-- -----------------------------------------------------
-- Table valoracion
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS portafolio.valoracion (
  id_valoracion INT NOT NULL AUTO_INCREMENT,
  comentario VARCHAR(45) NULL DEFAULT NULL,
  valoracion_numerica VARCHAR(1) NULL DEFAULT NULL CHECK (valoracion_numerica IN ('1', '2', '3', '4', '5')),
  PRIMARY KEY (id_valoracion));


-- -----------------------------------------------------
-- Table trabajadores
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS portafolio.trabajadores (
  id_trabajador INT NOT NULL AUTO_INCREMENT,
  nombres VARCHAR(45) NOT NULL,
  apellidos VARCHAR(45) NOT NULL,
  telefono VARCHAR(45) NOT NULL,
  email VARCHAR(45) NOT NULL,
  cedula VARCHAR(45) NOT NULL,
  profesion VARCHAR(45) NOT NULL,
  estado VARCHAR(45) NOT NULL,
  estado_servicio VARCHAR(45) NOT NULL,
  cartera_id_cartera INT NOT NULL,
  PRIMARY KEY (id_trabajador),
  UNIQUE INDEX cedula_UNIQUE (cedula),
  UNIQUE INDEX email_UNIQUE (email),
  UNIQUE INDEX telefono_UNIQUE (telefono),
  CONSTRAINT fk_Trabajadores_Cartera1
    FOREIGN KEY (cartera_id_cartera)
    REFERENCES portafolio.cartera (idCartera)
);

-- -----------------------------------------------------
-- Table pedidos
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS portafolio.pedidos (
  id_pedido INT NOT NULL AUTO_INCREMENT,
  nombre_pedido VARCHAR(45) NOT NULL,
  descripcion VARCHAR(45) NOT NULL,
  estado VARCHAR(45) NOT NULL CHECK (estado IN ('Solicitud', 'Abierto', 'Cancelado', 'Rechazado', 'Completado')),
  fecha_creacion DATETIME NOT NULL,
  fecha_inicio DATETIME NOT NULL,
  fecha_final DATETIME NOT NULL,
  cliente_id_fk INT NOT NULL,
  trabajador_id_fk INT NOT NULL,
  valoracion_id_fk INT NOT NULL,
  PRIMARY KEY (id_pedido),
  CONSTRAINT fk_Pedidos_Valoracion1
    FOREIGN KEY (valoracion_id_fk)
    REFERENCES portafolio.valoracion (id_valoracion),
  CONSTRAINT fk_Servicio_Clientes
    FOREIGN KEY (cliente_id_fk)
    REFERENCES portafolio.clientes (id_cliente),
  CONSTRAINT fk_Servicio_Trabajador1
    FOREIGN KEY (trabajador_id_fk)
    REFERENCES portafolio.trabajadores (id_trabajador)
);

-- -----------------------------------------------------
-- Table servicios
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS portafolio.servicios (
  id_servicio INT NOT NULL,
  nombre_servicio VARCHAR(45) NOT NULL,
  descripcion_servicio VARCHAR(45) NOT NULL,
  categoria_id_fk INT NOT NULL,
  trabajador_id_fk INT NOT NULL,
  PRIMARY KEY (id_servicio),
  CONSTRAINT fk_Servicios_Categoria1
    FOREIGN KEY (categoria_id_fk)
    REFERENCES portafolio.categoria (id_categoria),
  CONSTRAINT fk_Servicios_Trabajadores1
    FOREIGN KEY (trabajador_id_fk)
    REFERENCES portafolio.trabajadores (id_trabajador)
);

-- -----------------------------------------------------
-- Table servicios-imagenes
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS portafolio.servicios_imagenes (
  id_servicios_imagenes INT NOT NULL AUTO_INCREMENT,
  imagenes_id_imagenes_fk INT NOT NULL,
  servicios_id_servicio_fk INT NOT NULL,
  PRIMARY KEY (id_servicios_imagenes),
  CONSTRAINT fk_Servicios_Imagenes_Imagenes1
    FOREIGN KEY (imagenes_id_imagenes_fk)
    REFERENCES portafolio.imagenes (id_imagenes),
  CONSTRAINT fk_Servicios_Imagenes_Servicios1
    FOREIGN KEY (servicios_id_servicio_fk)
    REFERENCES portafolio.servicios (id_servicio)
);


-- -----------------------------------------------------
-- Table solicitud pedidos
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS portafolio.solicitud_pedidos (
  id_solicitud_pedidos INT NOT NULL AUTO_INCREMENT,
  estado VARCHAR(45) NOT NULL CHECK (estado IN ('En negociaciones', 'Aplazado', 'En proceso', 'Visita', 'Realizado', 'Cerrado')),
  valor DOUBLE NOT NULL,
  descripcion VARCHAR(45) NULL DEFAULT NULL,
  fecha_finalizacion DATETIME NULL DEFAULT NULL,
  pedidos_id_pedido INT NOT NULL,
  PRIMARY KEY (id_solicitud_pedidos),
  CONSTRAINT fk_Solicitud_pedidos_Pedidos1
    FOREIGN KEY (pedidos_id_pedido)
    REFERENCES portafolio.pedidos (id_pedido)
);


-- -----------------------------------------------------
-- Table traza
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS portafolio.traza (
  id_traza INT NOT NULL AUTO_INCREMENT,
  comentario VARCHAR(45) NULL DEFAULT NULL,
  fecha_creacion VARCHAR(45) NOT NULL,
  solicitud_pedidos_id_fk INT NOT NULL,
  PRIMARY KEY (id_traza),
  CONSTRAINT fk_Traza_Solicitud_pedidos1
    FOREIGN KEY (solicitud_pedidos_id_fk)
    REFERENCES portafolio.solicitud_pedidos (id_solicitud_pedidos)
);


-- -----------------------------------------------------
-- Table users
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS portafolio.users (
  usuario VARCHAR(20) NOT NULL,
  contrasena VARCHAR(200) NOT NULL,
  email VARCHAR(50) NULL DEFAULT NULL,
  bloqueado TINYINT NULL DEFAULT NULL,
  desabilitado TINYINT NULL DEFAULT NULL,
  PRIMARY KEY (usuario));


-- -----------------------------------------------------
-- Table user_role
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS portafolio.user_role (
  usuario VARCHAR(20) NOT NULL,
  rol VARCHAR(20) NOT NULL COMMENT 'CUSTOMER\\nADMIN',
  fecha_creacion DATETIME NOT NULL,
  PRIMARY KEY (usuario, rol),
  CONSTRAINT fk_usuario_rol_usuario1
    FOREIGN KEY (usuario)
    REFERENCES portafolio.users (usuario));



INSERT IGNORE INTO portafolio.users (usuario, contrasena, email, bloqueado, desabilitado) VALUES ('admin', '$2y$10$TjskTc1Nms0WGp4HCMdZ0eEb8NohnONsTSSM1yQxBFmBiSOx9KlGK', 'emanuelsierra17@gmail.com', '0', '0');
INSERT IGNORE INTO portafolio.user_role (usuario, rol, fecha_creacion) VALUES ('admin', 'ADMIN', '2024-01-15 13:17:12');
INSERT IGNORE INTO portafolio.imagenes (id_imagenes, nombre, formato, ruta) VALUES ('1', 'imagen', 'jpg', 'ruta');
INSERT IGNORE INTO portafolio.categoria (id_categoria, nombre_categoria, descripcion_categoria, imagenes_id_fk) VALUES ('1', 'Tecnología', 'Tecnología', '1');
INSERT IGNORE INTO portafolio.cartera (idCartera, valor_total, valor_descontar, valor_retirado, valor_recargado) VALUES ('1', '0', '0', '0', '0');
INSERT IGNORE INTO portafolio.trabajadores (id_trabajador, nombres, apellidos, telefono, email, cedula, profesion, estado, estado_servicio, cartera_id_cartera) VALUES ('1', 'KaZia', 'Sierra', '3017768234', 'emanuelsierra17@gmail.com', '12350407178', 'Ingeniería de Sistemas', 'activo', 'no disponible', '1');
INSERT IGNORE INTO portafolio.servicios (id_servicio, nombre_servicio, descripcion_servicio, categoria_id_fk, trabajador_id_fk) VALUES ('1', 'Mantenimiento de PC', 'mantenimiento de computadores', '1', '1');


