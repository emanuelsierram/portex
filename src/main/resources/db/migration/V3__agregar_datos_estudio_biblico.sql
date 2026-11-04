ALTER TABLE mi_antorcha.estudio_biblico
    ADD COLUMN frecuencia VARCHAR(10);

ALTER TABLE mi_antorcha.estudio_biblico
    ADD COLUMN fecha_inicializacion DATE;

ALTER TABLE mi_antorcha.estudio_biblico
    ADD COLUMN fecha_registro DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP;

ALTER TABLE mi_antorcha.historico_lecciones
    ADD COLUMN observacion VARCHAR(250) NULL;
