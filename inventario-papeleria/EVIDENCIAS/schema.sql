DROP TABLE IF EXISTS movimientos_inventario;
DROP TABLE IF EXISTS productos;
DROP TABLE IF EXISTS categorias;

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