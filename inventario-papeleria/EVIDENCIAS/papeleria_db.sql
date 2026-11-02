DROP TABLE IF EXISTS movimientos_inventario CASCADE;
DROP TABLE IF EXISTS productos CASCADE;
DROP TABLE IF EXISTS categorias CASCADE;

CREATE TABLE categorias (
                            id SERIAL PRIMARY KEY,
                            nombre VARCHAR(100) NOT NULL UNIQUE,
                            descripcion VARCHAR(250)
);

CREATE TABLE productos (
                           id SERIAL PRIMARY KEY,
                           codigo VARCHAR(20) NOT NULL UNIQUE,
                           nombre VARCHAR(150) NOT NULL,
                           descripcion VARCHAR(250),
                           precio NUMERIC(10, 2) NOT NULL CHECK (precio > 0),
                           stock INT NOT NULL CHECK (stock >= 0),
                           stock_minimo INT NOT NULL CHECK (stock_minimo >= 0),
                           categoria_id INT NOT NULL,
                           CONSTRAINT fk_producto_categoria FOREIGN KEY (categoria_id) REFERENCES categorias(id) ON DELETE RESTRICT
);

CREATE TABLE movimientos_inventario (
                                        id SERIAL PRIMARY KEY,
                                        producto_id INT NOT NULL,
                                        tipo VARCHAR(10) NOT NULL CHECK (tipo IN ('ENTRADA', 'SALIDA')),
                                        cantidad INT NOT NULL CHECK (cantidad > 0),
                                        fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                        CONSTRAINT fk_movimiento_producto FOREIGN KEY (producto_id) REFERENCES productos(id) ON DELETE RESTRICT
);

INSERT INTO categorias (nombre, descripcion) VALUES
                                                 ('Papel y Cuadernos', 'Cuadernos, hojas, blocs de notas y carpetas'),
                                                 ('Escritura', 'Bolígrafos, lápices, marcadores y correctores'),
                                                 ('Corte y Pegado', 'Tijeras, bisturís, pegamento y cintas adhesivas'),
                                                 ('Escolar y Arte', 'Pinceles, pinturas, reglas y accesorios escolares');

INSERT INTO productos (codigo, nombre, precio, stock, stock_minimo, categoria_id) VALUES
                                                                                      ('PROD-001', 'Cuaderno Universitario 100 Hojas', 15.50, 50, 10, 1),
                                                                                      ('PROD-002', 'Paquete Hojas Carta 500u', 45.00, 20, 5, 1),
                                                                                      ('PROD-003', 'Folder Manila Carta', 2.00, 120, 25, 1),
                                                                                      ('PROD-004', 'Bolígrafo Azul Tinta Gel', 5.00, 8, 15, 2),
                                                                                      ('PROD-005', 'Lápiz de Grafito 2B', 3.00, 100, 20, 2),
                                                                                      ('PROD-006', 'Marcador Permanente Negro', 8.50, 4, 10, 2),
                                                                                      ('PROD-007', 'Pegamento en Barra 40g', 12.00, 30, 8, 3),
                                                                                      ('PROD-008', 'Cinta Adhesiva Transparente', 6.50, 15, 5, 3),
                                                                                      ('PROD-009', 'Caja de Lápices de Colores 24u', 35.00, 18, 5, 4),
                                                                                      ('PROD-010', 'Regla Plástica 30cm', 4.50, 2, 8, 4);

INSERT INTO movimientos_inventario (producto_id, tipo, cantidad, fecha) VALUES
                                                                            (1, 'ENTRADA', 50, CURRENT_TIMESTAMP),
                                                                            (2, 'ENTRADA', 20, CURRENT_TIMESTAMP),
                                                                            (4, 'ENTRADA', 10, CURRENT_TIMESTAMP),
                                                                            (4, 'SALIDA', 2, CURRENT_TIMESTAMP);