CREATE TABLE mi_antorcha.miembros (
  id_miembro INT NOT NULL AUTO_INCREMENT,
  usuario_id VARCHAR(50) NOT NULL,
  nombres VARCHAR(100) NOT NULL,
  apellidos VARCHAR(100) NOT NULL,
  email VARCHAR(100) NOT NULL UNIQUE,
  telefono VARCHAR(20) NOT NULL,
  perfil VARCHAR(30) NOT NULL,
  id_grupo_pequeno INT NULL, -- FK lógica a grupos_pequenos (nulo por si es nuevo)
  id_anciano INT NULL,
  fecha_creacion DATETIME NOT NULL,
  fecha_actualizacion DATETIME NOT NULL,
  PRIMARY KEY (id_miembro)
);