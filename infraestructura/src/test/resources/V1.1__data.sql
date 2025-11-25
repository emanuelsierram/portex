INSERT INTO portafolio.cartera (idCartera, valor_total, valor_descontar, valor_retirado, valor_recargado) VALUES ('1', '0', '0', '0', '0');
INSERT INTO portafolio.trabajadores (id_trabajador, nombres, apellidos, telefono, email, cedula, profesion, estado, estado_servicio, cartera_id_cartera) VALUES ('1', 'KaZia', 'Sierra', '3017768234', 'emanuelsierra17@gmail.com', '12350407178', 'Ingeniería de Sistemas', 'activo', 'no disponible', '1');
INSERT INTO portafolio.servicios (id_servicio, nombre_servicio, descripcion_servicio, categoria_id_fk, trabajador_id_fk) VALUES ('1', 'Mantenimiento de PC', 'mantenimiento de computadores', '1', '1');

