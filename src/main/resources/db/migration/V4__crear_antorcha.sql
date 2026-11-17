CREATE TABLE mi_antorcha.antorcha (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nivel INT NOT NULL,
    id_usuario VARCHAR(50) NOT NULL UNIQUE,
    CONSTRAINT chk_antorcha_nivel CHECK (nivel BETWEEN 1 AND 10)
);
