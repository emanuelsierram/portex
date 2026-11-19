ALTER TABLE mi_antorcha.antorcha
    DROP CONSTRAINT chk_antorcha_nivel;

ALTER TABLE mi_antorcha.antorcha
    ADD CONSTRAINT chk_antorcha_nivel CHECK (nivel BETWEEN 0 AND 10);

CREATE TABLE mi_antorcha.niveles_antorcha (
    nivel INT PRIMARY KEY,
    puntos_minimos INT NOT NULL UNIQUE,
    puntos_maximos INT NULL
);

INSERT INTO mi_antorcha.niveles_antorcha (nivel, puntos_minimos, puntos_maximos) VALUES
    (0, 0, 99),
    (1, 100, 199),
    (2, 200, 299),
    (3, 300, 399),
    (4, 400, 499),
    (5, 500, 599),
    (6, 600, 699),
    (7, 700, 799),
    (8, 800, 899),
    (9, 900, 999),
    (10, 1000, NULL);

CREATE TABLE mi_antorcha.tipos_actividad (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    codigo VARCHAR(60) NOT NULL UNIQUE,
    nombre VARCHAR(150) NOT NULL,
    puntos INT NOT NULL,
    activo BOOLEAN NOT NULL DEFAULT TRUE
);

INSERT INTO mi_antorcha.tipos_actividad (codigo, nombre, puntos) VALUES
    ('PREDICACION_CALLE', 'Predicación pública en la calle', 7),
    ('PREDICACION_GRUPO_PEQUENO', 'Predicación en un grupo pequeño', 5),
    ('PREDICACION_IGLESIA', 'Predicación pública en la iglesia', 1),
    ('VISITAR_AMIGO_ESPERANZA', 'Visitar a un amigo de esperanza', 1),
    ('REPARTIR_LITERATURAS', 'Repartir literaturas', 1),
    ('HABLAR_DE_CRISTO', 'Hablarle a alguien de Cristo', 2),
    ('AYUDAR_NECESITADO', 'Ayudar a un necesitado', 7),
    ('VISITAR_ORFANATO', 'Visitar un orfanato', 5),
    ('VISITAR_HOSPITAL', 'Visitar hospital', 3),
    ('VISITAR_CARCEL', 'Visitar cárcel', 6),
    ('VISITAR_ASILO', 'Visitar asilo', 3),
    ('CENA_BONDAD', 'Participar en la cena de la bondad', 2),
    ('LLEVAR_AMIGO_IGLESIA', 'Llevar a un amigo a la iglesia', 4),
    ('INVITAR_AMIGO_GRUPO', 'Invitar a un amigo al grupo pequeño', 1),
    ('INVITAR_AMIGO_IGLESIA', 'Invitar a un amigo a la iglesia', 1),
    ('LLEVAR_AMIGO_GRUPO', 'Llevar un amigo al grupo pequeño', 3),
    ('EMPEZAR_ESTUDIO_BIBLICO', 'Empezar un estudio bíblico', 5),
    ('OTRO', 'Otra actividad', 1);

CREATE TABLE mi_antorcha.actividades (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_usuario VARCHAR(50) NOT NULL,
    id_tipo_actividad BIGINT NOT NULL,
    descripcion_otro VARCHAR(250) NULL,
    estado VARCHAR(20) NOT NULL,
    fecha_realizacion DATE NOT NULL,
    informar_obra BOOLEAN NOT NULL,
    observaciones VARCHAR(500) NULL,
    puntos INT NOT NULL,
    fecha_creacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    fecha_actualizacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_actividad_tipo
        FOREIGN KEY (id_tipo_actividad) REFERENCES mi_antorcha.tipos_actividad(id),
    CONSTRAINT chk_actividad_estado
        CHECK (estado IN ('POR_HACER', 'EN_PROCESO', 'HECHO')),
    CONSTRAINT chk_actividad_observaciones
        CHECK (informar_obra = FALSE OR observaciones IS NOT NULL)
);
