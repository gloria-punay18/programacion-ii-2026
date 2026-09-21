CREATE DATABASE parcial2_estacionamiento;

CREATE TABLE IF NOT EXISTS vehiculo (
                                        id SERIAL PRIMARY KEY,
                                        placa VARCHAR(10) NOT NULL UNIQUE,
    propietario VARCHAR(100) NOT NULL,
    tipo VARCHAR(20) NOT NULL,
    hora_ingreso VARCHAR(10) NOT NULL,
    horas_utilizadas NUMERIC(5,2) NOT NULL CHECK (horas_utilizadas > 0),
    costo NUMERIC(8,2) NOT NULL CHECK (costo >= 0),
    activo BOOLEAN DEFAULT TRUE
    );

INSERT INTO vehiculo (placa, propietario, tipo, hora_ingreso, horas_utilizadas, costo)
VALUES
    ('P123ABC', 'Carlos López', 'Automóvil', '08:00', 3, 30.00),
    ('M456DEF', 'María Rodríguez', 'Motocicleta', '09:15', 2, 12.00),
    ('P789GHI', 'Juan Pérez', 'Automóvil', '07:30', 6, 54.00),
    ('M101JKL', 'Ana Gómez', 'Motocicleta', '10:00', 6, 32.40),
    ('P202MNO', 'Luis Martínez', 'Automóvil', '11:00', 1, 10.00);


SELECT id, placa, propietario, tipo, hora_ingreso, horas_utilizadas, costo, activo
FROM vehiculo;


SELECT placa, propietario, tipo, costo
FROM vehiculo
WHERE tipo = 'Automóvil';


SELECT placa, propietario, costo
FROM vehiculo
WHERE costo > 20.00;


SELECT placa, propietario, tipo, costo
FROM vehiculo
ORDER BY costo DESC;

UPDATE vehiculo
SET horas_utilizadas = 4, costo = 40.00
WHERE placa = 'P123ABC';


UPDATE vehiculo
SET activo = FALSE
WHERE placa = 'M456DEF';


DELETE FROM vehiculo
WHERE placa = 'P202MNO';


INSERT INTO vehiculo (placa, propietario, tipo, hora_ingreso, horas_utilizadas, costo)
VALUES ('P123ABC', 'Pedro Ramírez', 'Automóvil', '12:00', 2, 20.00);


INSERT INTO vehiculo (placa, propietario, tipo, hora_ingreso, horas_utilizadas, costo)
VALUES ('P999XYZ', 'Sofia Morales', 'Automóvil', '13:00', -2, 20.00);